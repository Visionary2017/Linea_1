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

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.ByteBuffer;

import cz.msebera.android.httpclient.Header;

import static pe.edu.sise.applinea1.ClassConstante.CONSULTA_TARJETA_TREN;
import static pe.edu.sise.applinea1.ClassConstante.DOMINIO;


public class Registro3Activity extends AppCompatActivity {

    private NfcAdapter nfcAdapter;
    String tagInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro3);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            Toast.makeText(this, "NFC NO es compatible con estos dispositivos!", Toast.LENGTH_LONG).show();
            finish();
        } else if (!nfcAdapter.isEnabled()) {
            Toast.makeText(this, "NFC NO habilitado!", Toast.LENGTH_LONG).show();
            finish();
        }

        ImageView imageView = (ImageView) findViewById(R.id.imgView);

        Glide.with(Registro3Activity.this)
                .load(R.drawable.nfc)
                .asGif()
                .placeholder(R.drawable.nfc)
                .crossFade()
                .into(imageView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String action = intent.getAction();

        if (nfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
            Tag tag = intent.getParcelableExtra(nfcAdapter.EXTRA_TAG);
            tagInfo = tag.toString();
            byte[] tagId = tag.getId();
            tagInfo = "" + ByteBuffer.wrap(tagId).getInt();

            Intent i = new Intent(getApplicationContext(), RegistroActivity.class);
            i.putExtra("nfc", tagInfo);
            startActivity(i);
        } else {

        }

    }




}
