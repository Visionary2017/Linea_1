package pe.edu.sise.applinea1;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.nio.ByteBuffer;

import cz.msebera.android.httpclient.Header;

import static pe.edu.sise.applinea1.ClassConstante.ADD_PASAJERO;
import static pe.edu.sise.applinea1.ClassConstante.DOMINIO;

public class Registro3Activity extends AppCompatActivity {

    Bundle datos ;
     String numero_documento ;
     String nombre_completo ;
      String apellido_completo ;
     String celular ;
     String correo ;
    int id_perfil ;
    int id_estado ;
     String nro_tarjeta ;
     String password ;

    private NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro3);

        datos = this.getIntent().getExtras();
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

          numero_documento = datos.getString("numero_documento");
          nombre_completo = datos.getString("nombre_completo");
        apellido_completo = datos.getString("apellido_completo");
          celular = datos.getString("celular");
          correo = datos.getString("correo");
          id_perfil = 2;
          id_estado = 1;
      nro_tarjeta = CodigoNFC();
          password = datos.getString("password");

        ImageView imageView = (ImageView) findViewById(R.id.imgView);

        Glide.with(Registro3Activity.this)
                .load(R.drawable.nfc)
                .asGif()
                .placeholder(R.drawable.nfc)
                .crossFade()
                .into(imageView);

       // add_Pasajero();
    }


    @Override
    protected void onResume(){
        super.onResume();

        Intent intent = getIntent();
        String action = intent.getAction();

        if(nfcAdapter.ACTION_TAG_DISCOVERED.equals(action)){

            AsyncHttpClient client = new AsyncHttpClient();
            try{

                String URL_ADD = DOMINIO + ADD_PASAJERO;
                RequestParams params = new RequestParams();
                params.put("numero_documento",numero_documento.toString());
                params.put("nombres_completo",nombre_completo.toString());
                params.put("apellidos_completo",apellido_completo.toString());
                params.put("telefono",celular.toString());
                params.put("email",correo.toString());
                params.put("id_perfil",id_perfil);
                params.put("id_estado",id_estado);
                params.put("nro_tarjeta",nro_tarjeta);
                params.put("password",password.toString());

                client.post(URL_ADD, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        if(statusCode == 200){
                            Toast.makeText(getApplicationContext(), "Registrado Correctamente", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(i);
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
        }else{
            //Toast.makeText(this, "onResume() : " + action, Toast.LENGTH_SHORT).show();
        }


    }



    public String CodigoNFC() {
        Intent intent = getIntent();
        Tag tag = intent.getParcelableExtra(nfcAdapter.EXTRA_TAG);
        String tagInfo = tag.toString();
        byte[] tagId = tag.getId();
        tagInfo = "" + ByteBuffer.wrap(tagId).getInt();
        return tagInfo;
    }


    public void add_Pasajero(){

    }


}
