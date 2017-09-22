package pe.edu.sise.applinea1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class Lista_Estaciones extends AppCompatActivity {


    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__estaciones);
        drawerLayout=(DrawerLayout) findViewById(R.id.ListaEstaciones);
        navigationView=(NavigationView)findViewById(R.id.navview);
        setToolbar();

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
                        item.setChecked(true);
                        break;
                    case R.id.mnuViaje:

                        break;
                    case R.id.mnuContacto:

                        break;
                }

                return true;
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
