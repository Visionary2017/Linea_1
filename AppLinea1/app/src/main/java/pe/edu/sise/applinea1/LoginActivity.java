package pe.edu.sise.applinea1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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

import static pe.edu.sise.applinea1.ClassConstante.ACCESO_MENU;
import static pe.edu.sise.applinea1.ClassConstante.DOMINIO;

public class LoginActivity extends AppCompatActivity {
    //View toolbar;
    TextView tvRegistro, tvVisitante;
    EditText etUsu, etPass;
    Button btnLog;
    // Session Manager Class
    SessionManagement session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Session Manager
        session = new SessionManagement(getApplicationContext());

        //toolbar = (View) findViewById(R.id.toolbarLogin);
        tvRegistro = (TextView) findViewById(R.id.tvRegistro);
        // toolbar.setEnabled(false);

        etUsu = (EditText) findViewById(R.id.etUsua);
        etPass = (EditText) findViewById(R.id.etContrasena);
        btnLog = (Button) findViewById(R.id.btnLogin);

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Acceso_Menu();
            }
        });
    }

    public void Acceso_Menu() {
        AsyncHttpClient client = new AsyncHttpClient();
        try {
            String URL_LOGIN = DOMINIO + ACCESO_MENU;
            RequestParams parametros = new RequestParams();
            parametros.put("usuario", etUsu.getText().toString());
            parametros.put("password", etPass.getText().toString());


            client.get(URL_LOGIN, parametros, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    if (statusCode == 200 && obtieneDatosJSON(new String(responseBody)).toString().equals("ERROR-01")) {
                        etPass.setText("");
                        btnLog.setEnabled(true);
                        Toast.makeText(getApplicationContext(), "Credenciales incorrectas.", Toast.LENGTH_SHORT).show();
                    } else if (statusCode == 200 && obtieneDatosJSON(new String(responseBody)).toString().equals("ERROR-02")) {
                        etPass.setText("");
                        btnLog.setEnabled(true);
                        Toast.makeText(getApplicationContext(), "Debe Llenar los datos.", Toast.LENGTH_SHORT).show();
                    } else if (statusCode == 200) {
                        /*session.createLoginSession("Android Hive", "anroidhive@gmail.com");*/
                        etUsu.setText("");
                        etPass.setText("");
                        etUsu.requestFocus();
                        btnLog.setEnabled(true);
                        String nro_tarjeta = obtieneDatosJSON(new String(responseBody)).toString();
                        session.createLoginSession(nro_tarjeta);
                        Intent intent = new Intent(getApplicationContext(), MenuPrincipalActivity.class);
                        intent.putExtra("numero_tarjeta", nro_tarjeta);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Toast.makeText(getApplicationContext(), "onFail", Toast.LENGTH_SHORT).show();
                    btnLog.setEnabled(true);
                }
            });

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error - " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        //System.exit(0);
    }

    public String obtieneDatosJSON(String response) {
        String texto = "";
        try {
            //JSONObject jsonObject2 = new JSONObject(response);
            //texto = jsonArray2.getString("nombres_completo");
            //JSONArray jsonArray2 = new JSONArray(response);
            //texto = (jsonArray2.getJSONObject(0).getString("nombres_completo"));
            JSONObject object = new JSONObject(response);
            JSONArray Jarray = object.getJSONArray("persona");

            for (int i = 0; i < Jarray.length(); i++) {
                texto = Jarray.getJSONObject(i).getString("nro_tarjeta");
            }
            Log.i("texto-valor ", texto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return texto;
    }

    //////////////////

    public void fnOnClickRegistro(View view) {

        Intent intent = new Intent(LoginActivity.this, Registro3Activity.class);
        startActivity(intent);

    }


}
