package pe.edu.sise.applinea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CantidadRecargarActivity extends AppCompatActivity {

    Button sig_Recargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cantidad_recargar);

        sig_Recargar = (Button) findViewById(R.id.btnSiguienteRecargar);

        sig_Recargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),RecargaVirtualActivity.class);
                startActivity(i);
            }
        });
    }
}
