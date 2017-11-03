package pe.edu.sise.applinea1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;

import static pe.edu.sise.applinea1.ClassConstante.CONSULTA_TARJETA_TREN;
import static pe.edu.sise.applinea1.ClassConstante.DOMINIO;

public class RegistroActivity extends AppCompatActivity {

private RegistroActivity registroActivity;
    Button btnSiguien;
    EditText num_doc, nom_com, ape_com, cel, correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        btnSiguien = (Button) findViewById(R.id.btnSiguiente);
        num_doc = (EditText) findViewById(R.id.etNumeroDocumento);
        nom_com = (EditText) findViewById(R.id.etNombreCompleto);
        ape_com = (EditText) findViewById(R.id.etApellidoCompleto);
        cel = (EditText) findViewById(R.id.etTelefono);
        correo = (EditText) findViewById(R.id.etCorreo);

        Bundle b = getIntent().getExtras();
        final String nfc = b.getString("nfc");
        //Toast.makeText(this, nfc, Toast.LENGTH_SHORT).show();


        AsyncHttpClient client = new AsyncHttpClient();
        try {
            String URL_CONSULTAR_TREN = DOMINIO + CONSULTA_TARJETA_TREN;
            final RequestParams params = new RequestParams();
            params.put("nro_tarjeta", nfc.toString());

            client.get(URL_CONSULTAR_TREN, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    if (statusCode == 200 && obtieneDatosJSON(new String(responseBody)).toString().equals("ERROR-01")) {
                    CerrarView();
                        Toast.makeText(getApplicationContext(), "Tarjeta no Permitida!", Toast.LENGTH_SHORT).show();
                    } else if (statusCode == 200) {
                        Toast.makeText(getApplicationContext(), "Tarjeta Válida!", Toast.LENGTH_SHORT).show();
                        btnSiguien.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String validemail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                                        "\\@" +
                                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                                        "(" +
                                        "\\." +
                                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                                        ")+";

                                String email = correo.getText().toString();
                                Matcher matcher = Pattern.compile(validemail).matcher(email);


                                if (num_doc.getText().toString().trim().equalsIgnoreCase("")) {
                                    num_doc.setError("Ingresar Número de documento");
                                }

                                if (nom_com.getText().toString().trim().equalsIgnoreCase("")) {
                                    nom_com.setError("Ingresar Nombres");
                                }

                                if (ape_com.getText().toString().trim().equalsIgnoreCase("")) {
                                    ape_com.setError("Ingresar Apellidos");
                                }

                                if (cel.getText().toString().trim().equalsIgnoreCase("")) {
                                    cel.setError("Ingresar Celular");
                                }

                                if (correo.getText().toString().trim().equalsIgnoreCase("")) {
                                    correo.setError("Ingresar Correo");
                                }


                                if (num_doc.getText().length() >= 9 || num_doc.getText().length() <= 7) {
                                    num_doc.setError("El número de documento debe contar con 8 dígitos");
                                }

                                if (matcher.matches()) {

                                } else {
                                    correo.setError("Ingresar correo válido!!!");
                                }

                                if (!num_doc.getText().toString().trim().equalsIgnoreCase("") &&
                                        !nom_com.getText().toString().trim().equalsIgnoreCase("") &&
                                        !ape_com.getText().toString().trim().equalsIgnoreCase("") &&
                                        !cel.getText().toString().trim().equalsIgnoreCase("") &&
                                        !correo.getText().toString().trim().equalsIgnoreCase("")) {

                                    if (num_doc.getText().length() >= 9 || num_doc.getText().length() <= 7) {
                                        num_doc.setError("El número de documento debe contar con 8 dígitos");
                                    } else if (!matcher.matches()) {
                                        correo.setError("Ingresar correo válido!!!");
                                    } else {
                                        Intent i = new Intent(getApplicationContext(), Registro2Activity.class);
                                        i.putExtra("numero_documento", num_doc.getText().toString());
                                        i.putExtra("nombre_completo", nom_com.getText().toString());
                                        i.putExtra("apellido_completo", ape_com.getText().toString());
                                        i.putExtra("celular", cel.getText().toString());
                                        i.putExtra("correo", correo.getText().toString());
                                        i.putExtra("nfc", nfc);
                                        startActivity(i);
                                    }
                                }
                            }
                        });
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error - " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public String obtieneDatosJSON(String response) {
        String texto = "";
        try {

            JSONObject object = new JSONObject(response);
            JSONArray Jarray = object.getJSONArray("s_resultado");

            for (int i = 0; i < Jarray.length(); i++) {
                texto = Jarray.getJSONObject(i).getString("resultado");
            }
            Log.i("texto-valor ", texto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return texto;
    }

    public void CerrarView(){
        Intent i = new Intent(this,Registro3Activity.class);
        startActivity(i);
        finish();
    }
}
