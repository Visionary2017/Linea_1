package pe.edu.sise.applinea1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Console;
import java.net.URL;

import cz.msebera.android.httpclient.Header;

import static pe.edu.sise.applinea1.ClassConstante.ACCESO_MENU;
import static pe.edu.sise.applinea1.ClassConstante.*;

public class activity_calcular_viaje extends AppCompatActivity {
    Spinner spinner_origen, spinner_destino;
    Button calcu_Viaje;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    public static int value_origen = 0;
    public static int value_destino = 0;
    public static int value_opcion = 0;
    public int[] values =new int[27];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_viaje);

        calcu_Viaje = (Button) findViewById(R.id.btnCalcular);
        drawerLayout = (DrawerLayout) findViewById(R.id.calcular_viaje);
        navigationView = (NavigationView) findViewById(R.id.navview);
        Spinner spinner_origen = (Spinner) findViewById(R.id.spinner_origen);
        Spinner spinner_destino = (Spinner) findViewById(R.id.spinner_destino);
        setToolbar();

        String[] datos = new String[]{
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

        int v = 0;
        int y = 26;
        for (int x=1; x<=26;x++){
            v = v + 1;
            values[v] = y;
            y = y - 1;
        }

        String[] datos2 = new String[]{
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
                android.R.layout.simple_spinner_dropdown_item, datos);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, datos2);
        spinner_origen.setAdapter(adapter);
        spinner_destino.setAdapter(adapter2);

        calcu_Viaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calcular_Viaje();
            }
        });

        spinner_origen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected_Origen = parent.getItemAtPosition(position).toString();

                if(selected_Origen != "--Seleccione--"){
                    value_opcion = 1;
                    value_origen = position;
                    //calcu_Viaje.setEnabled(false);
                    //ObtenerIdEstacion(selected_Origen);
                    Toast.makeText(getApplicationContext(), "Origen " + selected_Origen, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_destino.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected_Destino = parent.getItemAtPosition(position).toString();
                if(selected_Destino != "--Seleccione--"){
                    value_opcion = 2;
                    value_destino = values[position];
                    //calcu_Viaje.setEnabled(false);
                    //ObtenerIdEstacion(selected_Destino);
                    Toast.makeText(getApplicationContext(), "Destino : " + selected_Destino, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void Calcular_Viaje() {


        AsyncHttpClient client = new AsyncHttpClient();
        try {

            if(value_origen > 0 && value_destino > 0){
                String URL_SERVICE = SERVICE_LINEA_CALCULAR;
                RequestParams parametros = new RequestParams();
                parametros.put("var_id_origen", value_origen);
                parametros.put("var_id_destino", value_destino);

                client.post(URL_SERVICE, parametros, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        if (statusCode == 200) {
                            Intent intent = new Intent(getApplicationContext(), activity_resultado_calcular_viaje.class);
                            intent.putExtra("html_text", obtieneDatosJSON(new String(responseBody)).toString());
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(getApplicationContext(), "onFail", Toast.LENGTH_SHORT).show();
                        //btnLog.setEnabled(true);
                    }
                });
            }else{
                Toast.makeText(getApplicationContext(), "Seleccione ambas estaciones", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error - " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public String obtieneDatosJSON(String response) {
        String texto = "";
        try {

            texto = response;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return texto;
    }


    public void ObtenerIdEstacion(String descripcion) {
        AsyncHttpClient client = new AsyncHttpClient();
        try {
            String URL_SERVICE = DOMINIO + CONSULTA_ESTADO;
            RequestParams parametros = new RequestParams();
            parametros.put("descripcion", descripcion);

            client.get(URL_SERVICE, parametros, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    if (statusCode == 200) {
                        int value = obtieneDatosJSONEstacion(new String(responseBody));
                        if(value > 0){

                            if(value_opcion == 1){
                                value_origen = value;
                                calcu_Viaje.setEnabled(true);
                            }else if(value_opcion == 2){
                                calcu_Viaje.setEnabled(true);
                            }

                        }else{
                            Toast.makeText(getApplicationContext(), "Debe Seleccionar las estaciones", Toast.LENGTH_SHORT).show();
                            calcu_Viaje.setEnabled(true);
                        }
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Toast.makeText(getApplicationContext(), "onFail", Toast.LENGTH_SHORT).show();
                    //btnLog.setEnabled(true);
                }
            });

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error - " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public int obtieneDatosJSONEstacion(String response) {
        int texto = 0;
        try {
            JSONObject object = new JSONObject(response);
            JSONArray Jarray = object.getJSONArray("estacion");

            for (int i = 0; i < Jarray.length(); i++) {
                texto = Jarray.getJSONObject(i).getInt("id_estacion");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return texto;
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
