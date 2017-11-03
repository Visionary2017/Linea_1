package pe.edu.sise.applinea1;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

import static pe.edu.sise.applinea1.ClassConstante.ADD_PASAJERO;
import static pe.edu.sise.applinea1.ClassConstante.DOMINIO;

public class Registro2Activity extends AppCompatActivity {

    Button btnRegis;
    EditText etpass, etValPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro2);

        btnRegis = (Button) findViewById(R.id.btnRegistrar);
        etpass = (EditText) findViewById(R.id.etPassword);
        etValPass = (EditText) findViewById(R.id.etValidarPassword);

        //Recuperar datos
        Bundle datos = this.getIntent().getExtras();
        final String numero_documento = datos.getString("numero_documento");
        final String nombre_completo = datos.getString("nombre_completo");
        final String apellido_completo = datos.getString("apellido_completo");
        final String celular = datos.getString("celular");
        final String correo = datos.getString("correo");
        final int id_perfil = 2;
        final int id_estado = 1;
        final String nfc = datos.getString("nfc");

        etpass.addTextChangedListener(new TextValidator(etpass) {
            @Override
            public void validate(EditText editText, String text) {
                if (text.length() < 8)
                    etpass.setError("La contraseña es muy corta");
            }
        });

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etpass.getText().toString().equals(etValPass.getText().toString())) {
                    AsyncHttpClient client = new AsyncHttpClient();
                    try {
                        String URL_ADD = DOMINIO + ADD_PASAJERO;
                        RequestParams params = new RequestParams();
                        params.put("numero_documento", numero_documento.toString());
                        params.put("nombres_completo", nombre_completo.toString());
                        params.put("apellidos_completo", apellido_completo.toString());
                        params.put("telefono", celular.toString());
                        params.put("email", correo.toString());
                        params.put("id_perfil", id_perfil);
                        params.put("id_estado", id_estado);
                        params.put("nro_tarjeta", nfc);
                        params.put("password", etpass.getText().toString());

                        client.post(URL_ADD, params, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                if (statusCode == 200) {
                                    Toast.makeText(getApplicationContext(), "Registrado Correctamente", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(i);
                                }
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                Toast.makeText(getApplicationContext(), "Error de conexion", Toast.LENGTH_SHORT).show();

                            }
                        });

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Error - " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    etValPass.setError("No coindicen!!!");
                }
            }
        });

    }

    public abstract class TextValidator implements TextWatcher {
        private final EditText editText;

        public TextValidator(EditText editText) {
            this.editText = editText;
        }

        public abstract void validate(EditText editText, String text);

        @Override
        final public void afterTextChanged(Editable s) {
            String text = editText.getText().toString();
            validate(editText, text);
        }

        @Override
        final public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* Don't care */ }

        @Override
        final public void onTextChanged(CharSequence s, int start, int before, int count) { /* Don't care */ }
    }

}


