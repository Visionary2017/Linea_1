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
    EditText num_doc,nom_com,ape_com,cel,correo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        btnSiguien = (Button) findViewById(R.id.btnSiguiente);
        num_doc = (EditText) findViewById(R.id.etNumeroDocumento);
        nom_com = (EditText) findViewById(R.id.etNombreCompleto);
        ape_com = (EditText) findViewById(R.id.etApellidoCompleto) ;
        cel = (EditText) findViewById(R.id.etTelefono);
        correo = (EditText) findViewById(R.id.etCorreo);


       btnSiguien.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(RegistroActivity.this, "Hola", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(),Registro2Activity.class);
               i.putExtra("numero_documento",num_doc.getText().toString());
               i.putExtra("nombre_completo",nom_com.getText().toString());
               i.putExtra("apellido_completo",ape_com.getText().toString());
               i.putExtra("celular",cel.getText().toString());
               i.putExtra("correo",correo.getText().toString());
               startActivity(i);
           }
       });
    }




}
