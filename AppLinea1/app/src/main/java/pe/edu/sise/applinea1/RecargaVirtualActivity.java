package pe.edu.sise.applinea1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static pe.edu.sise.applinea1.ClassConstante.BUSCAR_TARJETA;
import static pe.edu.sise.applinea1.ClassConstante.DOMINIO;
import static pe.edu.sise.applinea1.ClassConstante.UDP_SALDO_VISA;

public class RecargaVirtualActivity extends AppCompatActivity {



    Bundle datos;
    String numero_tarjeta;
    double monto_Recarga;

    ImageButton imgButt2;
    EditText num_tar_visa,fecha_ven,cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recarga_virtual);





        datos = this.getIntent().getExtras();
        numero_tarjeta = datos.getString("numero_tarjeta");
        monto_Recarga = datos.getDouble("monto");


        Toast.makeText(this, numero_tarjeta + monto_Recarga , Toast.LENGTH_SHORT).show();



        imgButt2 = (ImageButton) findViewById(R.id.imgbtn2);
         num_tar_visa = (EditText) findViewById(R.id.etNumTarjeta_Visa);
        fecha_ven = (EditText) findViewById(R.id.etmmaa);
        cv = (EditText) findViewById(R.id.etcvv);

        imgButt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i  = new Intent(getApplicationContext(),PagoRealizadoActivity.class);
                startActivity(i);*/
                AsyncHttpClient client = new AsyncHttpClient();
                try {
                    String URL_VERIFICAR = DOMINIO + BUSCAR_TARJETA;
                    final RequestParams params = new RequestParams();
                    params.put("nro_Tarjeta", num_tar_visa.getText().toString());
                    params.put("fecha_vencimiento",fecha_ven.getText().toString());
                    params.put("ccv",cv.getText().toString());

                    client.post(URL_VERIFICAR, params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                            if (statusCode == 200 && obtieneDatosJSON(new String(responseBody)).toString().equals("ERROR-01")) {

                                Toast.makeText(getApplicationContext(), "Tarjeta no concuerda", Toast.LENGTH_SHORT).show();

                            }else if(statusCode == 200){
                                Toast.makeText(getApplicationContext(), "Tarjeta correctamente ingresada", Toast.LENGTH_SHORT).show();

                                AsyncHttpClient client1 = new AsyncHttpClient();
                                try {
                                    String URL_UDP_SALDO_VISA = DOMINIO + UDP_SALDO_VISA;
                                    RequestParams params1 = new RequestParams();
                                    params1.put("nro_Tarjeta",num_tar_visa.getText().toString());
                                    params1.put("saldo",monto_Recarga);

                                    client1.post(URL_UDP_SALDO_VISA, params1, new AsyncHttpResponseHandler() {
                                        @Override
                                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                            if(statusCode == 200 && obtieneDatosJSON2(new String(responseBody)).toString().equals("ERROR-01")){
                                                Toast.makeText(getApplicationContext(), "Tarjeta no concuerda", Toast.LENGTH_SHORT).show();
                                            }else if(statusCode == 200){
                                                Toast.makeText(getApplicationContext(), "Transacción correcta", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                                        }
                                    });

                                }catch (Exception e){
                                    Toast.makeText(getApplicationContext(), "Error - " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }

                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            //Toast.makeText(RecargaVirtualActivity.this, "No esta en la BD", Toast.LENGTH_SHORT).show();
                        }
                    });

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Error - " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        }


    public String obtieneDatosJSON(String response){
        String texto="";
        try {

            JSONObject object = new JSONObject(response);
            JSONArray Jarray  = object.getJSONArray("tarjeta");

            for (int i = 0; i < Jarray.length(); i++)
            {
                texto = Jarray.getJSONObject(i).getString("nro_Tarjeta");
            }
            Log.i("texto-valor ",texto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return texto;
    }


    public String obtieneDatosJSON2(String response){
        String texto="";
        try {

            JSONObject object = new JSONObject(response);
            JSONArray Jarray  = object.getJSONArray("tarjeta");

            for (int i = 0; i < Jarray.length(); i++)
            {
                texto = Jarray.getJSONObject(i).getString("udptarjeta");
            }
            Log.i("texto-valor ",texto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return texto;
    }
}
