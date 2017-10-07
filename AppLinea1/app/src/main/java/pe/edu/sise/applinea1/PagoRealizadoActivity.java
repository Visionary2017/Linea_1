package pe.edu.sise.applinea1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

import static pe.edu.sise.applinea1.ClassConstante.DOMINIO;
import static pe.edu.sise.applinea1.ClassConstante.MOSTRAR_SALDO;

public class PagoRealizadoActivity extends AppCompatActivity {

    ImageButton imgButt;
    TextView nue_sal,mont,sal_ant, txt_fecha;
    String numero_tarjeta;
    double monto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago_realizado);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());


        imgButt = (ImageButton) findViewById(R.id.imageButton);

        imgButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MenuPrincipalActivity.class);
                startActivity(i);
            }
        });

        Bundle b = getIntent().getExtras();

        numero_tarjeta = b.getString("numero_tarjeta");
         monto = b.getDouble("monto_recarga");


        nue_sal = (TextView) findViewById(R.id.txtNuevoSaldo);
        mont = (TextView) findViewById(R.id.txtMontodeRecarga);
        sal_ant = (TextView) findViewById(R.id.txtSaldoAnterior);
        txt_fecha = (TextView) findViewById(R.id.txtFechadeRecarga);


        mont.setText(""+monto);
        txt_fecha.setText(""+formattedDate);

        Consultar_Saldo();

    }

    public void Consultar_Saldo(){
        AsyncHttpClient client = new AsyncHttpClient();
        try {
            String URL_SALDO = DOMINIO + MOSTRAR_SALDO;
            RequestParams parametros = new RequestParams();
            parametros.put("nro_tarjeta",numero_tarjeta);

            client.get(URL_SALDO, parametros, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    if (statusCode == 200 && obtieneDatosJSON(new String(responseBody)).toString().equals("ERROR-01")) {
                        Toast.makeText(getApplicationContext(), "Tarjeta Incorrecta.", Toast.LENGTH_SHORT).show();
                    }else if(statusCode == 200){
                        /*session.createLoginSession("Android Hive", "anroidhive@gmail.com");*/
                        String value  = obtieneDatosJSON(new String(responseBody)).toString();
                        nue_sal.setText("S/. " + value);
                    double saldo_anterior = Double.parseDouble(value) - Double.parseDouble(mont.getText().toString());
                    sal_ant.setText(""+saldo_anterior);

                    Toast.makeText(getApplicationContext(), "Respuesta Exitosa.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Toast.makeText(getApplicationContext(), "onFail", Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error - " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public String obtieneDatosJSON(String response){
        String texto="";
        try {
            JSONObject object = new JSONObject(response);
            JSONArray Jarray  = object.getJSONArray("tarjeta");

            for (int i = 0; i < Jarray.length(); i++)
            {
                texto = Jarray.getJSONObject(i).getString("saldo");
            }
            Log.i("texto-valor ",texto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return texto;
    }
}
