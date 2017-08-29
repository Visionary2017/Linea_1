package pe.edu.sise.applinea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    View toolbar;
    TextView tvRegistro, tvVisitante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = (View) findViewById(R.id.toolbarLogin);
        tvRegistro = (TextView) findViewById(R.id.tvRegistro);
        tvVisitante = (TextView) findViewById(R.id.tvVisitante);
        toolbar.setEnabled(false);
    }

    public void fnOnClickRegistro(View view){

        Intent intent = new Intent(getApplicationContext(),RegistroActivity.class);
        startActivity(intent);

    }

    public void fnOnClickVisitante(View view){

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);

    }


}
