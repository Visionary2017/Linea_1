package pe.edu.sise.applinea1;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;

import static pe.edu.sise.applinea1.ClassConstante.DOMINIO;
import static pe.edu.sise.applinea1.ClassConstante.UDP_PASAJERO;

public class updatePasajeroActivity extends AppCompatActivity {

    Button btnActualizar;
    EditText nombre, apellido, celular, correo;
    Bundle datos;
    String numero_tarjeta;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    // Session Manager Class
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pasajero);
        navigationView = (NavigationView) findViewById(R.id.navview);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_ActualizarContacto);
        nombre = (EditText) findViewById(R.id.etNombreCompletoUpdate);
        apellido = (EditText) findViewById(R.id.etApellidoCompletoUpdate);
        celular = (EditText) findViewById(R.id.etTelefonoUpdate);
        correo = (EditText) findViewById(R.id.etCorreoUpdate);
        btnActualizar = (Button) findViewById(R.id.btnUpdate);
        setToolbar();

        session = new SessionManagement(getApplicationContext());

        HashMap<String, String> user = session.getUserDetails();
        // name
        String tarjeta = user.get(SessionManagement.KEY_NRO_TARJETA);

        /*datos = this.getIntent().getExtras();*/
        numero_tarjeta = tarjeta;

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

        Button cerrar_Sesion = (Button) findViewById(R.id.btnCerrarSesion);

        cerrar_Sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogoConfirmacion2 dialogo = new DialogoConfirmacion2();
                dialogo.show(fragmentManager, "tagAlerta");
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UDP_Pasajero();
            }
        });
    }


    private void UDP_Pasajero() {
        final ProgressDialog progressDialog=new ProgressDialog(updatePasajeroActivity.this);
        progressDialog.setTitle("Actualizando");
        progressDialog.setMessage("Espere por favor...");
        progressDialog.show();
        String validemail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+";

        String email = correo.getText().toString();
        Matcher matcher = Pattern.compile(validemail).matcher(email);

        if (nombre.getText().toString().trim().equalsIgnoreCase("")) {
            nombre.setError("Ingresar Nombres");
            progressDialog.cancel();
        }

        if (apellido.getText().toString().trim().equalsIgnoreCase("")) {
            apellido.setError("Ingresar Apellidos");
            progressDialog.cancel();
        }

        if (celular.getText().toString().trim().equalsIgnoreCase("")) {
            celular.setError("Ingresar Celular");
            progressDialog.cancel();
        }

        if (correo.getText().toString().trim().equalsIgnoreCase("")) {
            correo.setError("Ingresar Correo");
            progressDialog.cancel();
        }

        if (matcher.matches()) {

        } else {
            correo.setError("Ingresar correo válido!!!");
            progressDialog.cancel();
        }

        if (!nombre.getText().toString().trim().equalsIgnoreCase("") &&
                !apellido.getText().toString().trim().equalsIgnoreCase("") &&
                !celular.getText().toString().trim().equalsIgnoreCase("") &&
                !correo.getText().toString().trim().equalsIgnoreCase("")) {
            progressDialog.cancel();
            if (!matcher.matches()) {
                correo.setError("Ingresar correo válido!!!");
                progressDialog.cancel();
            } else {
                AsyncHttpClient client = new AsyncHttpClient();
                try {
                    String URL_UPDATE = DOMINIO + UDP_PASAJERO;
                    RequestParams params = new RequestParams();
                    params.put("nombres_completo", nombre.getText().toString());
                    params.put("apellidos_completo", apellido.getText().toString());
                    params.put("telefono", celular.getText().toString());
                    params.put("email", correo.getText().toString());
                    params.put("nro_tarjeta", numero_tarjeta);

                    client.post(URL_UPDATE, params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            if (statusCode == 200) {
                                progressDialog.cancel();
                                Toast.makeText(updatePasajeroActivity.this, "Actualizado correctamente.", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), MenuPrincipalActivity.class);
                                i.putExtra("valor", numero_tarjeta);
                                startActivity(i);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Toast.makeText(updatePasajeroActivity.this, "No se actualizó!!!", Toast.LENGTH_SHORT).show();
                            progressDialog.cancel();
                        }
                    });

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error - " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.cancel();
                }
            }
        }
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
