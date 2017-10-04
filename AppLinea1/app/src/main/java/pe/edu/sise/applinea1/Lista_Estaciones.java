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
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__estaciones);
        getJson(DOMINIO+LISTAR_ESTACIONES);
        recyclerView=(RecyclerView)findViewById(R.id.RecyclerViewEstaciones);
        mLayoutManager=new LinearLayoutManager(this);
        drawerLayout=(DrawerLayout) findViewById(R.id.ListaEstaciones);
        navigationView=(NavigationView)findViewById(R.id.navview);
        setToolbar();

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

    }

    private void getJson(final String s) {

        class GetJson extends AsyncTask<Void,Void,String>{

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //Toast.makeText(Lista_Estaciones.this, s, Toast.LENGTH_SHORT).show();
                try{
                    loadIntoListView(s);
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }

            @Override
            protected String doInBackground(Void... params) {
                try {
                    URL url=new URL(s);
                    HttpURLConnection con=(HttpURLConnection)url.openConnection();
                    StringBuilder sb=new StringBuilder();
                    BufferedReader bufferedReader=new BufferedReader((new InputStreamReader(con.getInputStream())));
                    String json;
                    while((json=bufferedReader.readLine())!=null){
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }
        }
        GetJson geteJson=new GetJson();
        geteJson.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        JSONObject object = new JSONObject(json);
        JSONArray Jarray  = object.getJSONArray("estacion");
        final String[] heroes = new String[Jarray.length()];
        int id_estacion=0;
        String Nombre = null;
         String Direccion=null;
        ArrayList<Estaciones> arrayList=new ArrayList<>() ;
        for (int i = 0; i < Jarray.length(); i++) {

            id_estacion=Jarray.getJSONObject(i).getInt("id_estacion");
            Nombre = Jarray.getJSONObject(i).getString("nombre_estacion");
            Direccion=Jarray.getJSONObject(i).getString("direccion");
            arrayList.add(new Estaciones(""+Nombre,""+Direccion));
        }
       ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,heroes );

estacion=arrayList;
        mAdapter=new EstacionesAdapter(estacion, R.layout.item_estaciones, new EstacionesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Estaciones estacion, int position) {
                Toast.makeText(Lista_Estaciones.this, "Todo bien", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
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
