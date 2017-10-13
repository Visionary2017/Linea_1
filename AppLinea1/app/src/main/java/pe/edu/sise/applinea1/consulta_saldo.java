package pe.edu.sise.applinea1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static pe.edu.sise.applinea1.ClassConstante.DOMINIO;
import static pe.edu.sise.applinea1.ClassConstante.MOSTRAR_SALDO;

public class consulta_saldo extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    public TextView lblConsultaSaldo;
    public Button btnConsultaSaldoC;
    public EditText txtNumeroTarjetaSaldo;
    public TextView lblNumero_TarjetaC;
    String numero_tarjeta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_saldo);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_consulta);
        navigationView=(NavigationView)findViewById(R.id.navview);
        setToolbar();

        lblNumero_TarjetaC = (TextView) findViewById(R.id.lblNumero_TarjetaC);
        lblConsultaSaldo = (TextView) findViewById(R.id.lblConsultaSaldo);

        Bundle b = getIntent().getExtras();
        numero_tarjeta =  b.getString("numero_tarjeta");

        lblNumero_TarjetaC.setText(""+numero_tarjeta.toString());
        Consultar_Saldo();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.mnuUsuario:
                        Intent a= new Intent(getApplicationContext(),Lista_Estaciones.class);
                        a.putExtra("numero_tarjeta",numero_tarjeta.toString());
                        startActivity(a);
                        finish();
                        break;
                    case R.id.mnuRecarga:
                        Intent e= new Intent(getApplicationContext(),RecargaActivity.class);
                        e.putExtra("numero_tarjeta",numero_tarjeta.toString());
                        startActivity(e);
                        finish();
                        break;
                    case R.id.mnuSaldo:
                        Intent i= new Intent(getApplicationContext(),consulta_saldo.class);
                        i.putExtra("numero_tarjeta",numero_tarjeta.toString());
                        startActivity(i);
                        finish();
                        break;
                    case R.id.mnuEstacion:
                        Intent o= new Intent(getApplicationContext(),Lista_Estaciones.class);
                        o.putExtra("numero_tarjeta",numero_tarjeta.toString());
                        startActivity(o);
                        finish();
                        break;
                    case R.id.mnuViaje:
                        Intent u= new Intent(getApplicationContext(),activity_calcular_viaje.class);
                        u.putExtra("numero_tarjeta",numero_tarjeta.toString());
                        startActivity(u);
                        finish();
                        break;
                    case R.id.mnuContacto:

                        break;
                }

                return true;
            }
        });


        //btnConsultaSaldoC = (Button) findViewById(R.id.btnConsultaSaldoC);
        //txtNumeroTarjetaSaldo = (EditText) findViewById(R.id.txtNumeroTarjetaSaldo);
/*
        btnConsultaSaldoC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Consultar_Saldo();
            }
        });
*/
    }

    public void Consultar_Saldo(){
        AsyncHttpClient client = new AsyncHttpClient();
        try {
            String URL_SALDO = DOMINIO + MOSTRAR_SALDO;
            RequestParams parametros = new RequestParams();
            parametros.put("nro_tarjeta",numero_tarjeta.toString());

            client.get(URL_SALDO, parametros, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    if (statusCode == 200 && obtieneDatosJSON(new String(responseBody)).toString().equals("ERROR-01")) {
                        Toast.makeText(getApplicationContext(), "Tarjeta Incorrecta.", Toast.LENGTH_SHORT).show();
                    }else if(statusCode == 200){
                        /*session.createLoginSession("Android Hive", "anroidhive@gmail.com");*/
                        String value  = obtieneDatosJSON(new String(responseBody)).toString();
                        lblConsultaSaldo.setText("S/. " + value);
                        Toast.makeText(getApplicationContext(), "Respuesta Exitosa.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Toast.makeText(getApplicationContext(), "Error de conexion", Toast.LENGTH_SHORT).show();
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


    private void setToolbar(){

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
