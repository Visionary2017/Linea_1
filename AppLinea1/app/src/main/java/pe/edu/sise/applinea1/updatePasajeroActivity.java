package pe.edu.sise.applinea1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;

import static pe.edu.sise.applinea1.ClassConstante.DOMINIO;
import static pe.edu.sise.applinea1.ClassConstante.UDP_PASAJERO;

public class updatePasajeroActivity extends AppCompatActivity {

    Button btnActualizar;
    EditText nombre,apellido,celular,correo;
    Bundle datos;
    String numero_tarjeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pasajero);


        nombre = (EditText) findViewById(R.id.etNombreCompletoUpdate);
        apellido = (EditText) findViewById(R.id.etApellidoCompletoUpdate);
        celular = (EditText) findViewById(R.id.etTelefonoUpdate);
        correo = (EditText) findViewById(R.id.etCorreoUpdate);
        btnActualizar = (Button) findViewById(R.id.btnUpdate);

        datos = this.getIntent().getExtras();
       numero_tarjeta = datos.getString("numero_tarjeta");

        Toast.makeText(this, numero_tarjeta, Toast.LENGTH_SHORT).show();


        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UDP_Pasajero();
            }
        });

    }


    private void UDP_Pasajero(){

        String validemail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+";

        String email = correo.getText().toString();
        Matcher matcher= Pattern.compile(validemail).matcher(email);

        if(nombre.getText().toString().trim().equalsIgnoreCase("")){
            nombre.setError("Ingresar Nombres");
        }

        if(apellido.getText().toString().trim().equalsIgnoreCase("")){
            apellido.setError("Ingresar Apellidos");
        }

        if(celular.getText().toString().trim().equalsIgnoreCase("")){
            celular.setError("Ingresar Celular");
        }

        if(correo.getText().toString().trim().equalsIgnoreCase("")){
            correo.setError("Ingresar Correo");
        }

        if(matcher.matches()){

        }else  {
            correo.setError("Ingresar correo válido!!!");
        }

        if(    !nombre.getText().toString().trim().equalsIgnoreCase("") &&
                !apellido.getText().toString().trim().equalsIgnoreCase("") &&
                !celular.getText().toString().trim().equalsIgnoreCase("") &&
                !correo.getText().toString().trim().equalsIgnoreCase("")){

            if(!matcher.matches()){
                correo.setError("Ingresar correo válido!!!");
            }else  {
                AsyncHttpClient client = new AsyncHttpClient();
                try{
                    String URL_UPDATE = DOMINIO + UDP_PASAJERO;
                    RequestParams params = new RequestParams();
                    params.put("nombres_completo",nombre.getText().toString());
                    params.put("apellidos_completo",apellido.getText().toString());
                    params.put("telefono",celular.getText().toString());
                    params.put("email",correo.getText().toString());
                    params.put("nro_tarjeta",numero_tarjeta);

                    client.post(URL_UPDATE, params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            if(statusCode == 200){
                                Toast.makeText(updatePasajeroActivity.this, "Actualizado correctamente.", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(),MenuPrincipalActivity.class);
                                i.putExtra("valor",numero_tarjeta);
                                startActivity(i);
                               // finish();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Toast.makeText(updatePasajeroActivity.this, "No se actualizó!!!", Toast.LENGTH_SHORT).show();
                        }
                    });

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Error - " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        }
    }


}
