package pe.edu.sise.applinea1;

import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Detalle_Estacion extends AppCompatActivity {

    public String id_estacion = "";
    public String Nombre = "";
    public String descripcion = "";
    public String latitud = "";
    public String longitud = "";
    private TextView txtnombre;
    private TextView txtdescripcion;
    private Button btnMapa;
    private GoogleMap mMap;
    Double lat = 0.0;
    Double lon = 0.0;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle__estacion);
        txtnombre = (TextView) findViewById(R.id.txtNombreEstacion_Detalle);
        txtdescripcion = (TextView) findViewById(R.id.txtDetalle_Estacion);
        btnMapa = (Button) findViewById(R.id.btnVerMapa);
        toolbar = (Toolbar) findViewById(R.id.toolbar_top);
        setToolbar();


        if (id_estacion == ""){
            Bundle b = getIntent().getExtras();
            id_estacion = b.getString("id_estacion");
            Nombre = b.getString("nombre");
            descripcion = b.getString("descripcion");
            latitud = b.getString("latitud");
            longitud = b.getString("longitud");
        }

        txtnombre.setText(Nombre);
        txtdescripcion.setText("Estación " + descripcion);
        /*lat = Double.parseDouble(latitud);
        lon = Double.parseDouble(longitud);*/

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detalle_Estacion.this, MapsActivity.class);
                intent.putExtra("id_estacion",id_estacion);
                intent.putExtra("descripcion",descripcion);
                intent.putExtra("latitud", latitud);
                intent.putExtra("longitud", longitud);
                intent.putExtra("nombre", Nombre);
                startActivity(intent);
                //Toast.makeText(Detalle_Estacion.this, "Latitud: " + lat + "Longitud: " + lon, Toast.LENGTH_SHORT).show();
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Lista_Estaciones.class);
                startActivity(intent);
            }
        });
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), Lista_Estaciones.class);
        startActivity(intent);
        //System.exit(0);
    }


}
