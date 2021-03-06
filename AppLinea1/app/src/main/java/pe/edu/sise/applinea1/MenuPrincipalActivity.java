package pe.edu.sise.applinea1;

import android.app.Dialog;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.impl.client.SystemDefaultCredentialsProvider;

import static pe.edu.sise.applinea1.ClassConstante.DOMINIO;
import static pe.edu.sise.applinea1.ClassConstante.MOSTRAR_SALDO;

public class MenuPrincipalActivity extends AppCompatActivity {

    NotificationCompat.Builder mBuilder;
    public static final int NOTIFICACION_ID = 1;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FragmentTransaction fragmentTransaction;
    LinearLayout act_Usuario, recarga, consul_Saldo, estacion, calcu_Viaje, contacto;

    Bundle datos;
    String numero_tarjeta;

    // Session Manager Class
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        session = new SessionManagement(getApplicationContext());
        session.checkLogin();

        if(session.isLoggedIn()) {


            // get user data from session
            HashMap<String, String> user = session.getUserDetails();
            // name
            String tarjeta = user.get(SessionManagement.KEY_NRO_TARJETA);

            setToolbar();
            drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            navigationView = (NavigationView) findViewById(R.id.navview);
            act_Usuario = (LinearLayout) findViewById(R.id.btnActualizarUsuario);
            recarga = (LinearLayout) findViewById(R.id.btnRecargar);
            calcu_Viaje = (LinearLayout) findViewById(R.id.btnCalcular_Viaje);
            estacion = (LinearLayout) findViewById(R.id.btnEstaciones);
            consul_Saldo = (LinearLayout) findViewById(R.id.btnConsultarSaldo);
            contacto = (LinearLayout) findViewById(R.id.btnContacto);

            Button cerrar_Sesion = (Button) findViewById(R.id.btnCerrarSesion);

        /*datos = this.getIntent().getExtras();*/
            numero_tarjeta = tarjeta;

            this.Botones();
            //   this.Notificar_saldo();
            this.Consultar_Saldo();
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.mnuUsuario:
                            Intent a = new Intent(getApplicationContext(), updatePasajeroActivity.class);
                            a.putExtra("numero_tarjeta", numero_tarjeta.toString());
                            startActivity(a);
                            //finish();
                            break;
                        case R.id.mnuRecarga:
                            Intent e = new Intent(getApplicationContext(), RecargaActivity.class);
                            e.putExtra("numero_tarjeta", numero_tarjeta.toString());
                            startActivity(e);
                            // finish();
                            break;
                        case R.id.mnuSaldo:
                            Intent i = new Intent(getApplicationContext(), consulta_saldo.class);
                            i.putExtra("numero_tarjeta", numero_tarjeta.toString());
                            startActivity(i);
                            //finish();
                            break;
                        case R.id.mnuEstacion:
                            Intent o = new Intent(getApplicationContext(), Lista_Estaciones.class);
                            o.putExtra("numero_tarjeta", numero_tarjeta.toString());
                            startActivity(o);
                            // finish();
                            break;
                        case R.id.mnuViaje:
                            Intent u = new Intent(getApplicationContext(), activity_calcular_viaje.class);
                            u.putExtra("numero_tarjeta", numero_tarjeta.toString());
                            startActivity(u);
                            //  finish();
                            break;
                        case R.id.mnuContacto:
                            Intent s = new Intent(getApplicationContext(), ContactanosActivity.class);
                            s.putExtra("numero_tarjeta", numero_tarjeta.toString());
                            startActivity(s);
                            break;
                    }

                    return true;
                }
            });

            cerrar_Sesion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    DialogoConfirmacion2 dialogo = new DialogoConfirmacion2();
                    dialogo.show(fragmentManager, "tagAlerta");
                }
            });


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

    public void Botones() {
        act_Usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), updatePasajeroActivity.class);
                i.putExtra("numero_tarjeta", numero_tarjeta.toString());
                startActivity(i);
                // finish();
            }
        });


        recarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent e = new Intent(getApplicationContext(), RecargaActivity.class);
                e.putExtra("numero_tarjeta", numero_tarjeta.toString());
                startActivity(e);
                // finish();
            }
        });

        estacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(getApplicationContext(), Lista_Estaciones.class);
                startActivity(o);
                o.putExtra("numero_tarjeta", numero_tarjeta.toString());
                startActivity(o);
                //  finish();
            }
        });
        calcu_Viaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent u = new Intent(getApplicationContext(), activity_calcular_viaje.class);
                u.putExtra("numero_tarjeta", numero_tarjeta.toString());
                startActivity(u);
                // finish();
            }
        });
        consul_Saldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), consulta_saldo.class);
                i.putExtra("numero_tarjeta", numero_tarjeta.toString());
                startActivity(i);
                // finish();

            }
        });
        contacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent(getApplicationContext(), ContactanosActivity.class);
                s.putExtra("numero_tarjeta", numero_tarjeta.toString());
                startActivity(s);
                // finish();
            }
        });


    }


    @Override
    public void onBackPressed() {
        /*FragmentManager fragmentManager = getSupportFragmentManager();
        DialogoConfirmacion dialogo = new DialogoConfirmacion();
        dialogo.show(fragmentManager, "tagAlerta");*/
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        //System.exit(0);
    }


    public void Consultar_Saldo() {
        AsyncHttpClient client = new AsyncHttpClient();
        try {
            String URL_SALDO = DOMINIO + MOSTRAR_SALDO;
            RequestParams parametros = new RequestParams();
            parametros.put("nro_tarjeta", numero_tarjeta.toString());

            client.get(URL_SALDO, parametros, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    if (statusCode == 200 && obtieneDatosJSON(new String(responseBody)).toString().equals("ERROR-01")) {
                        Toast.makeText(getApplicationContext(), "Tarjeta Incorrecta.", Toast.LENGTH_SHORT).show();
                    } else if (statusCode == 200) {
                        /*session.createLoginSession("Android Hive", "anroidhive@gmail.com");*/
                        String value = obtieneDatosJSON(new String(responseBody)).toString();

                        if (Double.parseDouble(value) < 1.50) {
                            Mensaje("LINEA1", "Usted no cuenta con saldo disponible!", "¿Desea ir al módulo de Recarga?");
                        } else if (Double.parseDouble(value) >= 1.50 && Double.parseDouble(value) < 3) {
                            Mensaje("LINEA1", "Ha usted le queda solo un viaje!", "¿Desea recargar?");
                        }
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Toast.makeText(getApplicationContext(), "Error de conexion", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error - " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public String obtieneDatosJSON(String response) {
        String texto = "";
        try {
            JSONObject object = new JSONObject(response);
            JSONArray Jarray = object.getJSONArray("tarjeta");

            for (int i = 0; i < Jarray.length(); i++) {
                texto = Jarray.getJSONObject(i).getString("saldo");
            }
            Log.i("texto-valor ", texto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return texto;
    }

    private void Mensaje(String titulo, String Mensaje1, String Info) {
        mBuilder = new NotificationCompat.Builder(this)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(Mensaje1))
                .setSmallIcon(R.drawable.ic_logo)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_linea1_launcher))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setPriority(Notification.PRIORITY_MAX)
                .setVibrate(new long[]{0, 300, 200, 300})
                .setContentTitle(titulo)
                .setContentText(Mensaje1)
                .setAutoCancel(true)
                .setSubText(Info);



        //Activity
        Intent intent = new Intent(getApplicationContext(), RecargaActivity.class);
        intent.putExtra("numero_tarjeta", numero_tarjeta.toString());
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICACION_ID, mBuilder.build());
    }


}

class DialogoConfirmacion extends DialogFragment {

    // Session Manager Class
    SessionManagement session;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        builder.setMessage("¿Confirma la acción seleccionada?")
                .setTitle("Confirmacion")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.i("Dialogos", "Confirmacion Aceptada.");
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.i("Dialogos", "Confirmacion Cancelada.");
                        dialog.cancel();
                    }
                });

        return builder.create();
    }
}


class DialogoConfirmacion2 extends DialogFragment {
    // Session Manager Class
    SessionManagement session;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        session = new SessionManagement(getContext());

        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        builder.setMessage("¿Desea cerrar Sesión?")
                .setTitle("Confirmacion")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.i("Dialogos", "Confirmacion Aceptada.");
                        session.logoutUser();
                        //System.exit(0);
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.i("Dialogos", "Confirmacion Cancelada.");
                        dialog.cancel();
                    }
                });

        return builder.create();
    }
}




