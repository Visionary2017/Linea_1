package pe.edu.sise.applinea1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class CantidadRecargarActivity extends AppCompatActivity {

    Button sig_Recargar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cantidad_recargar);
        drawerLayout=(DrawerLayout) findViewById(R.id.activity_login);
        navigationView=(NavigationView)findViewById(R.id.navview);
        setToolbar();

        sig_Recargar = (Button) findViewById(R.id.btnSiguienteRecargar);

        sig_Recargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),RecargaVirtualActivity.class);
                startActivity(i);
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            boolean fragmentTransaction=false;

            switch (item.getItemId()){
                case R.id.mnuUsuario:

                    break;
                case R.id.mnuRecarga:
                    Intent e=new Intent(getApplicationContext(),CantidadRecargarActivity.class);
                    startActivity(e);
                    break;
                case R.id.mnuSaldo:

                    break;
                case R.id.mnuEstacion:
                    Intent o=new Intent(getApplicationContext(),Lista_Estaciones.class);
                    startActivity(o);
                    break;
                case R.id.mnuViaje:
                    Intent u=new Intent(getApplicationContext(),activity_calcular_viaje.class);
                    startActivity(u);
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
