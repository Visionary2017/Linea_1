package pe.edu.sise.applinea1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.HashMap;

public class RecargaActivity extends AppCompatActivity {

    EditText monto_Recarga;
    Button btnSiguiente;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    Bundle datos;
    String numero_tarjeta;

    // Session Manager Class
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recarga);

        session = new SessionManagement(getApplicationContext());

        HashMap<String, String> user = session.getUserDetails();
        // name
        String tarjeta = user.get(SessionManagement.KEY_NRO_TARJETA);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_recarga);
        navigationView = (NavigationView) findViewById(R.id.navview);
        btnSiguiente = (Button) findViewById(R.id.btnSiguienteRecargar);
        setToolbar();

        /*datos = this.getIntent().getExtras()*/;
        numero_tarjeta = tarjeta;

        monto_Recarga = (EditText) findViewById(R.id.etMonto_Recarga);

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

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LEO MI VARIABLE DDE ENTRADA
                double monto = Double.parseDouble(monto_Recarga.getText().toString());

                if (roundTwoDecimals(monto) >= 0.1 && roundTwoDecimals(monto) <= 99) {
                    Intent i = new Intent(getApplicationContext(), RecargaVirtualActivity.class);
                    i.putExtra("numero_tarjeta", numero_tarjeta);
                    i.putExtra("monto", roundTwoDecimals(monto));
                    startActivity(i);
                } else {
                    monto_Recarga.setError("Saldo maximo de recarga S/.99.00.");
                }
            }
        });
    }

    double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
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
