package pe.edu.sise.applinea1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro2Activity extends AppCompatActivity {

    Button btnRegis;
    //EditText etpass, etValPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro2);
        btnRegis=(Button)findViewById(R.id.btnRegistrar);
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Registro2Activity.this, "Coinciden", Toast.LENGTH_SHORT).show();
                     Intent i=new Intent(getApplicationContext(),Registro3Activity.class);
                     startActivity(i);

            }
        });


    }


}
