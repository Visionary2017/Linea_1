package pe.edu.sise.applinea1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import layout.Recarga;

public class MenuPrincipalActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    Fragment selectedFragment;
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

                        break;
                    case R.id.mnuRecarga:
                        selectedFragment=new Recarga();
                        fragmentManager=getFragmentManager();
                        fragmentTransaction=fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.Fragment_Cuerpo,selectedFragment);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mnuSaldo:

                        break;
                    case R.id.mnuEstacion:

                        break;
                    case R.id.mnuViaje:


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
                selectedFragment=new Recarga();
                fragmentManager=getFragmentManager();
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Fragment_Cuerpo,selectedFragment);
                fragmentTransaction.commit();
            }
        });

        estacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        calcu_Viaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        consul_Saldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
