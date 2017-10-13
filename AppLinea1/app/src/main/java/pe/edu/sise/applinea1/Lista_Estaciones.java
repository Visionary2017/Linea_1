package pe.edu.sise.applinea1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Entidades.Estaciones;
import Entidades.EstacionesAdapter;

import static pe.edu.sise.applinea1.ClassConstante.DOMINIO;
import static pe.edu.sise.applinea1.ClassConstante.LISTAR_ESTACIONES;

public class Lista_Estaciones extends AppCompatActivity {

    private List<Estaciones> estacion;
    private RecyclerView recyclerView;
    public static RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    public TextView id_estacion;
    public TextView nombre_estacion;
    public TextView descipcion;
    public TextView direccion;
    public TextView distrito;
    public TextView latitud;
    public TextView longitud;
    String numero_tarjeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__estaciones);
        getJson(DOMINIO + LISTAR_ESTACIONES);
        id_estacion = (TextView) findViewById(R.id.txtidEstacion);
        descipcion = (TextView) findViewById(R.id.txtDescripcion);
        nombre_estacion = (TextView) findViewById(R.id.txtNombreEstacion);
        direccion = (TextView) findViewById(R.id.txtDireccion);
        distrito = (TextView) findViewById(R.id.txtDistrito);
        latitud = (TextView) findViewById(R.id.txtLatitud);
        longitud = (TextView) findViewById(R.id.txtLongitud);
        recyclerView = (RecyclerView) findViewById(R.id.RecyclerViewEstaciones);
        mLayoutManager = new LinearLayoutManager(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.ListaEstaciones);
        navigationView = (NavigationView) findViewById(R.id.navview);
        setToolbar();

        Bundle b = getIntent().getExtras();
        numero_tarjeta = b.getString("numero_tarjeta");
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.mnuUsuario:
                        Intent a = new Intent(getApplicationContext(), updatePasajeroActivity.class);
                        a.putExtra("numero_tarjeta", numero_tarjeta.toString());
                        startActivity(a);
                        finish();
                        break;
                    case R.id.mnuRecarga:
                        Intent e = new Intent(getApplicationContext(), RecargaActivity.class);
                        e.putExtra("numero_tarjeta", numero_tarjeta.toString());
                        startActivity(e);
                        finish();
                        break;
                    case R.id.mnuSaldo:
                        Intent i = new Intent(getApplicationContext(), consulta_saldo.class);
                        i.putExtra("numero_tarjeta", numero_tarjeta.toString());
                        startActivity(i);
                        finish();
                        break;
                    case R.id.mnuEstacion:
                        Intent o = new Intent(getApplicationContext(), Lista_Estaciones.class);
                        o.putExtra("numero_tarjeta", numero_tarjeta.toString());
                        startActivity(o);
                        finish();
                        break;
                    case R.id.mnuViaje:
                        Intent u = new Intent(getApplicationContext(), activity_calcular_viaje.class);
                        u.putExtra("numero_tarjeta", numero_tarjeta.toString());
                        startActivity(u);
                        finish();
                        break;
                    case R.id.mnuContacto:
                        Intent s = new Intent(getApplicationContext(), ContactanosActivity.class);
                        startActivity(s);
                        finish();
                        break;
                }

                return true;
            }
        });
    }

    private void getJson(final String s) {

        class GetJson extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //Toast.makeText(Lista_Estaciones.this, s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... params) {
                try {
                    URL url = new URL(s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(con.getInputStream())));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }
        }
        GetJson geteJson = new GetJson();
        geteJson.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        JSONObject object = new JSONObject(json);
        JSONArray Jarray = object.getJSONArray("estacion");
        final String[] heroes = new String[Jarray.length()];
        String id_estacion = null;
        String Nombre = null;
        String Direccion = null;
        String Distrito = null;
        String Descripcion = null;
        String Latitud = null;
        String Longitud = null;
        ArrayList<Estaciones> arrayList = new ArrayList<>();
        for (int i = 0; i < Jarray.length(); i++) {

            id_estacion = Jarray.getJSONObject(i).getString("id_estacion");
            Nombre = Jarray.getJSONObject(i).getString("nombre_estacion");
            Distrito = Jarray.getJSONObject(i).getString("distrito");
            Direccion = Jarray.getJSONObject(i).getString("direccion");
            Latitud = Jarray.getJSONObject(i).getString("latitud");
            Longitud = Jarray.getJSONObject(i).getString("longitud");
            Descripcion = Jarray.getJSONObject(i).getString("descripcion");

            arrayList.add(new Estaciones(id_estacion, Nombre, Descripcion, Direccion, Distrito, Latitud, Longitud));
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, heroes);

        estacion = arrayList;
        mAdapter = new EstacionesAdapter(estacion, R.layout.item_estaciones, new EstacionesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Estaciones estacion, int position) {

                Intent intent = new Intent(Lista_Estaciones.this, Detalle_Estacion.class);
                intent.putExtra("id_estacion", estacion.getId_estaciones());
                intent.putExtra("nombre", estacion.getNombre_estacion());
                intent.putExtra("descripcion", estacion.getDescripcion());
                intent.putExtra("latitud", estacion.getLatitud());
                intent.putExtra("longitud", estacion.getLongitud());
                startActivity(intent);
            }
        });


        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
