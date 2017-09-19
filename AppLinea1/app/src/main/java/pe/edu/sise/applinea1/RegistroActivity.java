package pe.edu.sise.applinea1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistroActivity extends AppCompatActivity {



    EditText etNumeroDoc, etNomComple, etApellComple,etTelef,etCorr,etNumTarjeta;
    Button btnSiguien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);




        etNumeroDoc = (EditText) findViewById(R.id.etNumeroDocumento);
        etNomComple = (EditText) findViewById(R.id.etNombreCompleto);
        etApellComple = (EditText) findViewById(R.id.etApellidoCompleto);
        etTelef = (EditText) findViewById(R.id.etTelefono);
        etCorr = (EditText) findViewById(R.id.etCorreo);
        //etNumTarjeta = (EditText)findViewById(R.id.etTarjeta);
        //etPass = (EditText) findViewById(R.id.etPassword);
        //etValPassword = (EditText) findViewById(R.id.etValidarPassword);
        btnSiguien = (Button) findViewById(R.id.btnSiguiente);


        btnSiguien.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String num_doc = etNumeroDoc.getText().toString();
        String nom_com = etNomComple.getText().toString();
        String ape_com = etApellComple.getText().toString();
        String telf = etTelef.getText().toString();
        String corre = etCorr.getText().toString();
        String num_tar = etNumTarjeta.getText().toString();
        ////


        if (num_doc.isEmpty()){
            etNumeroDoc.setError("No puede ir vacío");
            etNumeroDoc.requestFocus();
        }
        else if(num_doc.length()<8 || num_doc.length()>8 ){
            etNumeroDoc.setError("Debe tener 8 digítos");
        }

        ////
        if (nom_com.isEmpty()){
            etNomComple.setError("No puede ir vacío");
        }

        ////22
        if (ape_com.isEmpty()){
            etApellComple.setError("No puede ir vacío");
        }

        ////
        if (telf.isEmpty()){
            etTelef.setError("No puede ir vacío");
        }

        ////
        if (corre.isEmpty()){
            etCorr.setError("No puede ir vacío");
        }

        ////
        if (num_tar.isEmpty()){
            etNumTarjeta.setError("No puede ir vacío");
        }


        if((!num_doc.isEmpty() && !(num_doc.length()<8 || num_doc.length()>8)) && !nom_com.isEmpty() && !ape_com.isEmpty() && !telf.isEmpty() && !corre.isEmpty() && !num_tar.isEmpty() ) {
            Intent i = new Intent(RegistroActivity.this,Registro2Activity.class);
            i.putExtra("numero_documento",etNumeroDoc.getText().toString());
            i.putExtra("nombre_completo",etNomComple.getText().toString());
            i.putExtra("apellido_completo",etApellComple.getText().toString());
            i.putExtra("telefono",etTelef.getText().toString());
            i.putExtra("correo",etCorr.getText().toString());
            i.putExtra("numero_tarjeta",etNumTarjeta.getText().toString());
            startActivity(i);
            }


    }
});
    }




}
