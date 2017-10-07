package pe.edu.sise.applinea1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Registro4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro4);

        Bundle datos = this.getIntent().getExtras();

        String numero_documento = datos.getString("numero_documento1");
        String nombre_completo = datos.getString("nombre_completo");
        String apellido_completo = datos.getString("apellido_completo");
        String celular = datos.getString("celular");
        String correo = datos.getString("correo");
        String id_perfil = datos.getString("id_perfil");
        String id_estado = datos.getString("id_estado");
        String NFC = datos.getString("nfc");
        String password = datos.getString("password");

        Toast.makeText(this,numero_documento +"\n" +
                nombre_completo.toString() + "\n"+
                apellido_completo.toString() +"\n" +
                celular.toString() + "\n" +
                correo.toString() + "\n" +
                id_perfil.toString()+"\n"+
                id_estado.toString()+"\n"+
                NFC.toString()+"\n"+
                password.toString(), Toast.LENGTH_SHORT).show();

    }
}
