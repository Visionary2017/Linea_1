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

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import cz.msebera.android.httpclient.Header;

import static pe.edu.sise.applinea1.ClassConstante.*;

public class LoginActivity extends AppCompatActivity {

    //View toolbar;
    TextView tvRegistro, tvVisitante;

    EditText etUsu, etPass;
    Button btnLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //toolbar = (View) findViewById(R.id.toolbarLogin);
        tvRegistro = (TextView) findViewById(R.id.tvRegistro);
        tvVisitante = (TextView) findViewById(R.id.tvVisitante);
       // toolbar.setEnabled(false);


        etUsu = (EditText) findViewById(R.id.etUsua);
        etPass = (EditText) findViewById(R.id.etContrasena);
        btnLog = (Button) findViewById(R.id.btnLogin);

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Thread tr = new Thread(){
//                    @Override
//                    public void run() {
//                       final String res = enviarPost(etUsu.getText().toString(),etPass.getText().toString());
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                int r = objJson(res);
//                                if(r > 0){
//                                    Intent i = new Intent(getApplicationContext(),MenuPrincipalActivity.class);
//                                    startActivity(i);
//                                }else{
//                                    Toast.makeText(getApplicationContext(), "Usuario o Password incorrectos", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//                    }
//                };
//                tr.start();
                /*Intent intent=new Intent(getApplicationContext(),MenuPrincipalActivity.class);
                startActivity(intent);*/
                Acceso_Menu();
            }
        });
    }


    public String enviarPost(String usu,String pass){
        String parametros="numero_documento="+usu+"&password="+pass;
        HttpURLConnection conection = null;
        String respuesta = "";
        try{
            URL url= new URL("http://10.0.3.2/CursoAndroid/prueba.php");
            conection = (HttpURLConnection)url.openConnection();
            conection.setRequestMethod("POST");
            conection.setRequestProperty("Content-Length",""+Integer.toString(parametros.getBytes().length));
            conection.setDoInput(true);
            DataOutputStream wr = new DataOutputStream(conection.getOutputStream());
            wr.writeBytes(parametros);
            wr.close();

            Scanner insStram = new Scanner(conection.getInputStream());

            while(insStram.hasNextLine()){
                respuesta+=(insStram.nextLine());
            }

        }catch(Exception e){        }
        return respuesta.toString();
    }


    //capturar resultado 0 -1
    public int objJson(String rpsta){
        int res = 0;
        try {
            JSONArray json = new JSONArray(rpsta);
            if(json.length()>0){
                res=1;
            }
        }catch (Exception e){
        }
        return res;
    }


    public void Acceso_Menu(){
        AsyncHttpClient client = new AsyncHttpClient();
        try {
            String URL_LOGIN = DOMINIO + ACCESO_MENU;
            RequestParams parametros = new RequestParams();
            parametros.put("usuario",etUsu.getText().toString());
            parametros.put("password",etPass.getText().toString());

            client.get(URL_LOGIN, parametros, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    if (statusCode == 200 && obtieneDatosJSON(new String(responseBody)).toString().equals("ERROR-01")) {
                        etPass.setText("");
                        btnLog.setEnabled(true);
                        Toast.makeText(getApplicationContext(), "Credenciales incorrectas.", Toast.LENGTH_SHORT).show();
                    }else if(statusCode == 200 && obtieneDatosJSON(new String(responseBody)).toString().equals("ERROR-02")){
                        etPass.setText("");
                        btnLog.setEnabled(true);
                        Toast.makeText(getApplicationContext(), "Debe Llenar los datos.", Toast.LENGTH_SHORT).show();
                    }else if(statusCode == 200){
                        /*session.createLoginSession("Android Hive", "anroidhive@gmail.com");*/
                        etUsu.setText("");
                        etPass.setText("");
                        etUsu.requestFocus();
                        btnLog.setEnabled(true);
                        Intent intent = new Intent(getApplicationContext(),MenuPrincipalActivity.class);
                        intent.putExtra("valor",obtieneDatosJSON(new String(responseBody)).toString());
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Toast.makeText(getApplicationContext(), "onFail", Toast.LENGTH_SHORT).show();
                    btnLog.setEnabled(true);
                }
            });

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error - " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public String obtieneDatosJSON(String response){
        String texto="";
        try {
            //JSONObject jsonObject2 = new JSONObject(response);
            //texto = jsonArray2.getString("nombres_completo");
            //JSONArray jsonArray2 = new JSONArray(response);
            //texto = (jsonArray2.getJSONObject(0).getString("nombres_completo"));
            JSONObject object = new JSONObject(response);
            JSONArray Jarray  = object.getJSONArray("persona");

            for (int i = 0; i < Jarray.length(); i++)
            {
                texto = Jarray.getJSONObject(i).getString("nombres_completo");
            }
            Log.i("texto-valor ",texto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return texto;
    }

   //////////////////

    public void fnOnClickRegistro(View view){

        Intent intent = new Intent(LoginActivity.this,RegistroActivity.class);
        startActivity(intent);

    }

    public void fnOnClickVisitante(View view){

        Intent intent = new Intent(getApplicationContext(),MenuPrincipalActivity.class);
        startActivity(intent);

    }


}
