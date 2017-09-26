package pe.edu.sise.applinea1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registro2Activity extends AppCompatActivity {

    Button btnRegis;
    EditText etpass, etValPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro2);
        btnRegis=(Button)findViewById(R.id.btnRegistrar);

        etpass = (EditText) findViewById(R.id.etPassword);
        etValPass = (EditText) findViewById(R.id.etValidarPassword) ;


        //Recuperar datos
        Bundle datos = this.getIntent().getExtras();
        final String numero_documento = datos.getString("numero_documento");
        final String nombre_completo = datos.getString("nombre_completo");
        final  String apellido_completo = datos.getString("apellido_completo");
        final String celular = datos.getString("celular");
        final String correo = datos.getString("correo");

        /*Toast.makeText(this, numero_documento.toString(), Toast.LENGTH_SHORT).show();**/

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getApplicationContext(),Registro3Activity.class);
                i.putExtra("numero_documento",numero_documento.toString());
                i.putExtra("nombre_completo",nombre_completo.toString());
                i.putExtra("apellido_completo",apellido_completo.toString());
                i.putExtra("celular",celular.toString());
                i.putExtra("correo",correo.toString());
                i.putExtra("password",etpass.getText().toString());
                startActivity(i);

            }
        });


    }


}
