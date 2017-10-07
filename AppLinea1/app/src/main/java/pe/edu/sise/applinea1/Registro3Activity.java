package pe.edu.sise.applinea1;

import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

    private NfcAdapter nfcAdapter;
    String numero_documento;
    String nombre_completo;
    String apellido_completo;
    String celular;
    String correo;
    String id_perfil;
    String id_estado;
       String password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro3);


        Bundle datos = this.getIntent().getExtras();
        numero_documento = datos.getString("numero_documento");
        nombre_completo = datos.getString("nombre_completo");
        apellido_completo = datos.getString("apellido_completo");
        celular = datos.getString("celular");
        correo = datos.getString("correo");
        id_perfil = "2";
        id_estado = "1";
        password = datos.getString("password");
        Button btn = (Button) findViewById(R.id.button);


        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            Toast.makeText(this, "NFC NOT supported on this devices!", Toast.LENGTH_LONG).show();
            finish();
        } else if (!nfcAdapter.isEnabled()) {
            Toast.makeText(this, "NFC NOT Enabled!", Toast.LENGTH_LONG).show();
            finish();
        }

      /*   ImageView imageView = (ImageView) findViewById(R.id.imgView);

        Glide.with(Registro3Activity.this)
                .load(R.drawable.nfc)
                .asGif()
                .placeholder(R.drawable.nfc)
                .crossFade()
                .into(imageView);
*/

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Registro4Activity.class);
                i.putExtra("numero_documento1",numero_documento.toString());
                i.putExtra("nombre_completo",nombre_completo.toString());
                i.putExtra("apellido_completo",apellido_completo.toString());
                i.putExtra("celular",celular.toString());
                i.putExtra("correo",correo.toString());
                i.putExtra("id_perfil", id_perfil.toString());
                i.putExtra("id_estado",id_estado.toString());
                i.putExtra("nfc","1234566789");
                i.putExtra("password",password.toString());
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();

        Intent intent = getIntent();
        String action = intent.getAction();


        if(nfcAdapter.ACTION_TAG_DISCOVERED.equals(action)){
            Toast.makeText(this, "Bienvenido!!", Toast.LENGTH_SHORT).show();


            Tag tag = intent.getParcelableExtra(nfcAdapter.EXTRA_TAG);
            String tagInfo = tag.toString();
            byte[] tagId = tag.getId();
            tagInfo = "" + ByteBuffer.wrap(tagId).getInt();

         /*   Intent i = new Intent(this,Registro4Activity.class);
            i.putExtra("numero_documento1",numero_documento.toString());
            i.putExtra("nombre_completo",nombre_completo.toString());
            i.putExtra("apellido_completo",apellido_completo.toString());
            i.putExtra("celular",celular.toString());
            i.putExtra("correo",correo.toString());
            i.putExtra("id_perfil", id_perfil.toString());
            i.putExtra("id_estado",id_estado.toString());
            i.putExtra("nfc",tagInfo);
            i.putExtra("password",password.toString());
            startActivity(i);*/

            Toast.makeText(this, tagInfo, Toast.LENGTH_SHORT).show();




        }else{
            Toast.makeText(this, "onResume() : " + action, Toast.LENGTH_SHORT).show();
        }

    }


}
