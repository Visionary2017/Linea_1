package pe.edu.sise.applinea1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.zip.Inflater;

public class MenuPrincipalActivity extends AppCompatActivity {

    // private DrawerLayout mdrawerLayout;
     //private ActionBarDrawerToggle mToggle;

    ImageButton act_Usuario, recarga,consul_Saldo,estacion,calcu_Viaje,contacto;
    TextView txtTitulo;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        setToolbar();

        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView=(NavigationView)findViewById(R.id.navview);
        txtTitulo=(TextView)findViewById(R.id.toolbar_title);
        recarga = (ImageButton) findViewById(R.id.btnRecargar);
        estacion=(ImageButton)findViewById(R.id.btnEstaciones);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                boolean fragmentTransaction=false;

                switch (item.getItemId()){
                    case R.id.mnuUsuario:

                        break;
                    case R.id.mnuRecarga:

                        break;
                    case R.id.mnuSaldo:

                        break;
                    case R.id.mnuEstacion:
                        Intent i=new Intent(getApplicationContext(),Lista_Estaciones.class);
                        startActivity(i);
                        break;
                    case R.id.mnuViaje:

                        break;
                    case R.id.mnuContacto:

                        break;
                }

                return true;
            }
        });

        Botones();
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

    public void Botones(){
        recarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),CantidadRecargarActivity.class);
                startActivity(i);
            }
        });

        estacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Lista_Estaciones.class);
                startActivity(i);
            }
        });


    }
}
