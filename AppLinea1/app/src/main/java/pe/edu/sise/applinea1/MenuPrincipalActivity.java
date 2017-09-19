package pe.edu.sise.applinea1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MenuPrincipalActivity extends AppCompatActivity {

    // private DrawerLayout mdrawerLayout;
     //private ActionBarDrawerToggle mToggle;

    ImageButton act_Usuario, recarga,consul_Saldo,estacion,calcu_Viaje,contacto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        recarga = (ImageButton) findViewById(R.id.btnRecargar);

        recarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),CantidadRecargarActivity.class);
                startActivity(i);
            }
        });

    /*  mdrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mdrawerLayout,R.string.open,R.string.close);

        mdrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
    }

    /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);

    }*/
}
