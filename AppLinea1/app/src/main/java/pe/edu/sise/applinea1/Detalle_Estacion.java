package pe.edu.sise.applinea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Detalle_Estacion extends AppCompatActivity  {

    String id_estacion;
    String Nombre;
    String descripcion;
    String latitud;
    String longitud;
    private TextView txtnombre;
    private TextView txtdescripcion;
    private Button btnMapa;
    private GoogleMap mMap;
    Double lat=0.0;
    Double lon=0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle__estacion);
        txtnombre=(TextView)findViewById(R.id.txtNombreEstacion_Detalle);
        txtdescripcion=(TextView)findViewById(R.id.txtDetalle_Estacion);
        btnMapa=(Button)findViewById(R.id.btnVerMapa);
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);

        Bundle b = getIntent().getExtras();
        id_estacion=b.getString("id_estacion");
        Nombre=b.getString("nombre");
        descripcion=b.getString("descripcion");
        latitud=b.getString("latitud");
        longitud=b.getString("longitud");

        txtnombre.setText(Nombre);
        txtdescripcion.setText("Estación "+descripcion);
        lat=Double.parseDouble(latitud);
        lon=Double.parseDouble(longitud);


        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Detalle_Estacion.this,MapsActivity.class);
                intent.putExtra("latitud",lat);
                intent.putExtra("longitud",lon);
                intent.putExtra("nombre",Nombre);
                startActivity(intent);
                Toast.makeText(Detalle_Estacion.this, "Latitud: "+lat+ "Longitud: "+lon, Toast.LENGTH_SHORT).show();
            }
        });


    }


//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//        Toast.makeText(Detalle_Estacion.this, "Latitud: "+lat+ "Longitud: "+lon, Toast.LENGTH_SHORT).show();
//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(lat, lon);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//    }
}
