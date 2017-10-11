package pe.edu.sise.applinea1;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.squareup.picasso.Picasso;

public class ContactanosActivity extends AppCompatActivity {

    ImageButton imgFB,imgYou,imgTwitter;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactanos);
        imgFB=(ImageButton)findViewById(R.id.imgFacebook);
        imgYou=(ImageButton)findViewById(R.id.imgYoutube);
        imgTwitter=(ImageButton)findViewById(R.id.imgTwitter);


        imgFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String facebookId = "fb://page/1488069544613775";
                String urlPage = "https://www.facebook.com/Lineauno.pe";

                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookId )));
                } catch (Exception e) {

                    //Abre url de pagina.
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlPage)));
                }
            }
        });

        imgYou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String youtubeID = "com.youtube";
                String urlPage = "https://www.youtube.com/user/Lineaunope";

                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeID )));
                } catch (Exception e) {

                    //Abre url de pagina.
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlPage)));
                }
            }
        });

        imgTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String youtubeID = "com.twitter";
                String urlPage = "https://twitter.com/lineaunope";

                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeID )));
                } catch (Exception e) {

                    //Abre url de pagina.
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlPage)));
                }
            }
        });

    }
}
