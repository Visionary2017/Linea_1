package pe.edu.sise.applinea1;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.squareup.picasso.Picasso;

public class ContactanosActivity extends AppCompatActivity {

    ImageButton imgFB,imgYou,imgTwitter,imgWeb;
    Button imgCall;
    private Context context;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    String numero_tarjeta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactanos);
        navigationView=(NavigationView)findViewById(R.id.navview);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_contacto);
        imgFB=(ImageButton)findViewById(R.id.imgFacebook);
        imgYou=(ImageButton)findViewById(R.id.imgYoutube);
        imgTwitter=(ImageButton)findViewById(R.id.imgTwitter);
        imgWeb=(ImageButton)findViewById(R.id.imgWeb);
        imgCall=(Button)findViewById(R.id.imgCall);
        setToolbar();

        Bundle b = getIntent().getExtras();
        numero_tarjeta =  b.getString("numero_tarjeta");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.mnuUsuario:
                        Intent a= new Intent(getApplicationContext(),updatePasajeroActivity.class);
                        a.putExtra("numero_tarjeta",numero_tarjeta.toString());
                        startActivity(a);
                        //finish();
                        break;
                    case R.id.mnuRecarga:
                        Intent e= new Intent(getApplicationContext(),RecargaActivity.class);
                        e.putExtra("numero_tarjeta",numero_tarjeta.toString());
                        startActivity(e);
                        // finish();
                        break;
                    case R.id.mnuSaldo:
                        Intent i= new Intent(getApplicationContext(),consulta_saldo.class);
                        i.putExtra("numero_tarjeta",numero_tarjeta.toString());
                        startActivity(i);
                        //finish();
                        break;
                    case R.id.mnuEstacion:
                        Intent o= new Intent(getApplicationContext(),Lista_Estaciones.class);
                        // o.putExtra("numero_tarjeta",numero_tarjeta.toString());
                        startActivity(o);
                        // finish();
                        break;
                    case R.id.mnuViaje:
                        Intent u= new Intent(getApplicationContext(),activity_calcular_viaje.class);
                        u.putExtra("numero_tarjeta",numero_tarjeta.toString());
                        startActivity(u);
                        //  finish();
                        break;
                    case R.id.mnuContacto:
                        Intent s=new Intent(getApplicationContext(),ContactanosActivity.class);
                        startActivity(s);
                        break;
                }

                return true;
            }
        });


        imgFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String facebookId = "fb://page/206969889390420";
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

        imgWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlPage = "http://www.lineauno.pe/";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlPage)));
            }
        });

        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:080011121"));
                startActivity(i);
            }
        });

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
