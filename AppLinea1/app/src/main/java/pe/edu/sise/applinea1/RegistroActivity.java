package pe.edu.sise.applinea1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {




    Button btnSiguien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        btnSiguien = (Button) findViewById(R.id.btnSiguiente);

       btnSiguien.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(RegistroActivity.this, "Hola", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(),Registro2Activity.class);
               startActivity(i);
           }
       });
    }




}
