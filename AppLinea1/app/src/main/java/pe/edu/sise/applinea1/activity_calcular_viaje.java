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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

public class activity_calcular_viaje extends AppCompatActivity {


    Spinner spinner_origen,spinner_destino;
    Button calcu_Viaje;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_viaje);

       Button calcu_Viaje = (Button) findViewById(R.id.btnCalcular);
        drawerLayout=(DrawerLayout) findViewById(R.id.calcular_viaje);
        navigationView=(NavigationView)findViewById(R.id.navview);
        Spinner spinner_origen = (Spinner) findViewById(R.id.spinner_origen);
        Spinner spinner_destino = (Spinner) findViewById(R.id.spinner_destino);
        setToolbar();

        String[] datos = new String[] {
                "--Seleccione--",
                "Villa El Salvador",
                "Parque Industrial",
                "Pumacahua",
                "Villa María",
                "María Auxiliadora",
                "San Juan",
                "Atocongo",
                "Jorge Chávez",
                "Ayacucho",
                "Cabitos",
                "Angamos",
                "San Borja Sur",
                "La Cultura",
                "Arriola",
                "Gamarra",
                "Grau",
                "El Ángel",
                "Presbítero Maestro",
                "Caja de Agua",
                "Pirámide del Sol",
                "Los Jardines",
                "Los Postes",
                "San Carlos",
                "San Martín",
                "Santa Rosa",
                "Bayóvar"

        };

        String[] datos2 = new String[] {
                "--Seleccione--",
                "Bayóvar",
                "Santa Rosa",
                "San Martín",
                "San Carlos",
                "Los Postes",
                "Los Jardines",
                "Pirámide del Sol",
                "Caja de Agua",
                "Presbítero Maestro",
                "El Ángel",
                "Grau",
                "Gamarra",
                "Arriola",
                "La Cultura",
                "San Borja Sur",
                "Angamos",
                "Cabitos",
                "Ayacucho",
                "Jorge Chávez",
                "Atocongo",
                "San Juan",
                "María Auxiliadora",
                "Villa María",
                "Pumacahua",
                "Parque Industrial",
                "Villa El Salvador",


        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos2);
        spinner_origen.setAdapter(adapter);
        spinner_destino.setAdapter(adapter2);

        calcu_Viaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),activity_resultado_calcular_viaje.class);
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
