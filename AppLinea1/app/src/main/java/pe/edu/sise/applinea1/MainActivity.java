package pe.edu.sise.applinea1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // ListView listView;
    String[] opciones = {"Actualizar Usuario", "Recarga Virtual", "Consultar Saldo", "Estaciones", "Calcular Viaje", "Contacto"};
    //DrawerLayout drawerLayout;

    private Integer[] imgid = {
            R.drawable.ic_edit_user,
            R.drawable.ic_visa,
            R.drawable.ic_consultar_saldo,
            R.drawable.ic_estacion,
            R.drawable.ic_calcular_viaje,
            R.drawable.ic_contacto
    };

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

}
