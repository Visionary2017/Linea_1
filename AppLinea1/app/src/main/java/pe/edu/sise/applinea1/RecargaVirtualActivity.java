package pe.edu.sise.applinea1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class RecargaVirtualActivity extends AppCompatActivity {

 ImageButton imgButt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recarga_virtual);

        imgButt = (ImageButton) findViewById(R.id.imgButtonPaso2);

        imgButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(getApplicationContext(),PagoRealizadoActivity.class);
                startActivity(i);
            }
        });

    }
}
