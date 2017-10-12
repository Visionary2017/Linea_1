package pe.edu.sise.applinea1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import static pe.edu.sise.applinea1.ClassConstante.SUMAR_SALDO_METRO;
import static pe.edu.sise.applinea1.ClassConstante.UDP_SALDO_VISA;
import static pe.edu.sise.applinea1.ClassConstante.VER_SALDO;

public class RecargaVirtualActivity extends AppCompatActivity {



    Bundle datos;
    String numero_tarjeta;
    double monto_Recarga;
    Toolbar toolbar;
    ImageButton imgButt2;
    EditText num_tar_visa,fecha_ven,cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recarga_virtual);
        toolbar=(Toolbar)findViewById(R.id.toolbar_top);
        datos = this.getIntent().getExtras();
        numero_tarjeta = datos.getString("numero_tarjeta");
        monto_Recarga = datos.getDouble("monto");
        setToolbar();
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

                if(num_tar_visa.getText().toString().trim().equalsIgnoreCase("")){
                    num_tar_visa.setError("Ingresar Número de Tarjeta.");
                }

                if(fecha_ven.getText().toString().trim().equalsIgnoreCase("")){
                    fecha_ven.setError("Ingresar fecha de expiración.");
                }

                if(cv.getText().toString().trim().equalsIgnoreCase("")){
                    cv.setError("Ingresar código de verificación.");
                }




                if(!num_tar_visa.getText().toString().trim().equalsIgnoreCase("") &&
                        !fecha_ven.getText().toString().trim().equalsIgnoreCase("") &&
                        !cv.getText().toString().trim().equalsIgnoreCase("") ){

                    if(num_tar_visa.getText().length()<=15){
                        num_tar_visa.setError("Ingresar Tarjeta completa.");
                    }else{
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

                                        Toast.makeText(getApplicationContext(), "Datos incorrectos, ingresar nuevamente.", Toast.LENGTH_SHORT).show();

                                    }else if(statusCode == 200){
                                        Toast.makeText(getApplicationContext(), "Tarjeta correctamente ingresada", Toast.LENGTH_SHORT).show();

                                        //VERIFICAR QUE TENGA SALDO

                                        AsyncHttpClient client1 = new AsyncHttpClient();
                                        try {
                                            String URL_VER_SALDO = DOMINIO + VER_SALDO;
                                            final RequestParams params1 = new RequestParams();
                                            params1.put("nro_Tarjeta",num_tar_visa.getText().toString());
                                            params1.put("fecha_vencimiento",fecha_ven.getText().toString());
                                            params1.put("ccv",cv.getText().toString());
                                            params1.put("saldo",monto_Recarga);

                                            client1.get(URL_VER_SALDO, params1, new AsyncHttpResponseHandler() {
                                                @Override
                                                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                                                    if (statusCode == 200 && obtieneDatosJSON2(new String(responseBody)).toString().equals("ERROR-01")) {

                                                        Toast.makeText(getApplicationContext(), "No cuenta con el saldo suficiente.", Toast.LENGTH_SHORT).show();

                                                    }else if(statusCode == 200){
                                                        Toast.makeText(getApplicationContext(), "Cuenta con saldo suficiente", Toast.LENGTH_SHORT).show();

                                                        AsyncHttpClient client2 = new AsyncHttpClient();
                                                        try {
                                                            String URL_UDP_SALDO_VISA = DOMINIO + UDP_SALDO_VISA;
                                                            RequestParams params2 = new RequestParams();
                                                            params2.put("nro_Tarjeta",num_tar_visa.getText().toString());
                                                            params2.put("saldo",monto_Recarga);

                                                            client2.post(URL_UDP_SALDO_VISA, params2, new AsyncHttpResponseHandler() {
                                                                @Override
                                                                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                                                    if(statusCode == 200 && obtieneDatosJSON3(new String(responseBody)).toString().equals("ERROR-01")){
                                                                        //Toast.makeText(getApplicationContext(), "Tarjeta no concuerda", Toast.LENGTH_SHORT).show();
                                                                    }else if(statusCode == 200){
                                                                        Toast.makeText(getApplicationContext(), "Transacción correcta", Toast.LENGTH_SHORT).show();

                                                                        AsyncHttpClient client3 = new AsyncHttpClient();
                                                                        try {
                                                                            String URL_UDP_SALDO_METRO = DOMINIO + SUMAR_SALDO_METRO;
                                                                            RequestParams params3 = new RequestParams();
                                                                            params3.put("nro_tarjeta",numero_tarjeta);
                                                                            params3.put("saldo",monto_Recarga);

                                                                            client3.post(URL_UDP_SALDO_METRO, params3, new AsyncHttpResponseHandler() {
                                                                                @Override
                                                                                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                                                                    if(statusCode == 200 && obtieneDatosJSON3(new String(responseBody)).toString().equals("ERROR-01")){

                                                                                    }else if(statusCode == 200){

                                                                                        Intent i = new Intent(getApplicationContext(),PagoRealizadoActivity.class);
                                                                                        i.putExtra("numero_tarjeta",numero_tarjeta);
                                                                                        i.putExtra("monto_recarga",monto_Recarga);
                                                                                        startActivity(i);
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

                                                                }
                                                            });

                                                        }catch (Exception e){
                                                            Toast.makeText(getApplicationContext(), "Error - " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                        }
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
                }
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
    private void setToolbar(){

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
            JSONArray Jarray  = object.getJSONArray("s_resultado");

            for (int i = 0; i < Jarray.length(); i++)
            {
                texto = Jarray.getJSONObject(i).getString("resultado");
            }
            Log.i("texto-valor ",texto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return texto;
    }


    public String obtieneDatosJSON3(String response){
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

    public String obtieneDatosJSON4(String response){
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
}
