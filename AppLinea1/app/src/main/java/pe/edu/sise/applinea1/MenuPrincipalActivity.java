package pe.edu.sise.applinea1;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MenuPrincipalActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FragmentTransaction fragmentTransaction;
    ImageButton act_Usuario, recarga,consul_Saldo,estacion,calcu_Viaje,contacto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        setToolbar();

        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView=(NavigationView)findViewById(R.id.navview);
        recarga = (ImageButton) findViewById(R.id.btnRecargar);
        calcu_Viaje = (ImageButton) findViewById(R.id.btnCalcular_Viaje);
        estacion=(ImageButton)findViewById(R.id.btnEstaciones);
        consul_Saldo = (ImageButton)findViewById(R.id.btnConsultarSaldo);



        this.Botones();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.mnuUsuario:
                        Intent a= new Intent(getApplicationContext(),Lista_Estaciones.class);
                        startActivity(a);
                        finish();
                        break;
                    case R.id.mnuRecarga:
                        Intent e= new Intent(getApplicationContext(),RecargaActivity.class);
                        startActivity(e);
                        finish();
                        break;
                    case R.id.mnuSaldo:
                        Intent i= new Intent(getApplicationContext(),consulta_saldo.class);
                        startActivity(i);
                        finish();
                        break;
                    case R.id.mnuEstacion:
                        Intent o= new Intent(getApplicationContext(),Lista_Estaciones.class);
                        startActivity(o);
                        finish();
                        break;
                    case R.id.mnuViaje:
                        Intent u= new Intent(getApplicationContext(),activity_calcular_viaje.class);
                        startActivity(u);
                        finish();
                        break;
                    case R.id.mnuContacto:

                        break;
                }

                return true;
            }
        });

        //Botones();
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
                Intent e= new Intent(getApplicationContext(),RecargaActivity.class);
                startActivity(e);
                finish();
            }
        });

        estacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o= new Intent(getApplicationContext(),Lista_Estaciones.class);
                startActivity(o);
                finish();
            }
        });
        calcu_Viaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent u= new Intent(getApplicationContext(),activity_calcular_viaje.class);
                startActivity(u);
                finish();
            }
        });
        consul_Saldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),consulta_saldo.class);
                startActivity(i);
                finish();
            }
        });

    }
}
