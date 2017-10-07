package pe.edu.sise.applinea1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class PagoRealizadoActivity extends AppCompatActivity {

    ImageButton imgButt;
    TextView nue_sal,mont,sal_ant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago_realizado);

        imgButt = (ImageButton) findViewById(R.id.imageButton);

        imgButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MenuPrincipalActivity.class);
                startActivity(i);
            }
        });

        Bundle b = getIntent().getExtras();
       // String nuevo_saldo = b.getString("saldo_nuevo");
        double monto = b.getDouble("monto_recarga");
       // double saldo_anterior = Double.parseDouble(nuevo_saldo) - Double.parseDouble(monto);

        nue_sal = (TextView) findViewById(R.id.txtNuevoSaldo);
        mont = (TextView) findViewById(R.id.txtMontodeRecarga);
        sal_ant = (TextView) findViewById(R.id.txtSaldoAnterior);

        //nue_sal.setText(""+nuevo_saldo);
        mont.setText(""+monto);
        //sal_ant.setText(""+saldo_anterior);


    }
}
