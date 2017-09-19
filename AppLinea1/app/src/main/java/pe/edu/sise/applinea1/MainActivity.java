package pe.edu.sise.applinea1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

   // ListView listView;
    String[] opciones = { "Actualizar Usuario", "Recarga Virtual", "Consultar Saldo", "Estaciones","Calcular Viaje","Contacto" };
    //DrawerLayout drawerLayout;

    private Integer[] imgid={
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


        /*listView = (ListView) findViewById(R.id.list_view);
         drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        listView.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                opciones));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Item: " + opciones[position],
                        Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawers();
            }

        });
        */




    }





    // Mostramos el botón en la barra de la aplicación
    //getActionBar().setDisplayHomeAsUpEnabled(true);

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(listView)) {
                    drawerLayout.closeDrawers();
                } else {
                    drawerLayout.openDrawer(listView);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/

}
