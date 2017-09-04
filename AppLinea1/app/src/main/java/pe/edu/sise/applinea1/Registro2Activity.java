package pe.edu.sise.applinea1;

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
    EditText etpass, etValPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro2);

        etpass = (EditText) findViewById(R.id.etPassword);
        etValPass = (EditText) findViewById(R.id.etValidarPassword);
        btnRegis = (Button) findViewById(R.id.btnRegistrar);

        String numero_documento1= getIntent().getStringExtra("numero_documento");
        String nombre_completo = getIntent().getStringExtra("nombre_completo");
        String apellido_completo = getIntent().getStringExtra("apellido_completo");
        String telefono = getIntent().getStringExtra("telefono");
        String correo = getIntent().getStringExtra("correo");
        String numero_tarjeta = getIntent().getStringExtra("numero_tarjeta");


        etpass.addTextChangedListener(new TextValidator(etpass) {
            @Override
            public void validate(EditText editText, String text) {
                if(text.length() < 8) {
                  etpass.setError("La contraseña es muy corta");
                }
            }
        });


        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(Registro2Activity.this, etValPass.getText().toString(), Toast.LENGTH_SHORT).show();
                String pass = etpass.getText().toString();
                String passVal = etValPass.getText().toString();

                 if (pass.equals(passVal)){
                     Toast.makeText(Registro2Activity.this, "Coinciden", Toast.LENGTH_SHORT).show();
                 }else {
                     Toast.makeText(Registro2Activity.this, "No Coinciden", Toast.LENGTH_SHORT).show();
                 }
            }
        });





       // Toast.makeText(this, numero_documento1+" "+nombre_completo+" "+apellido_completo+" "+telefono+
         //       " "+correo+" "+numero_tarjeta, Toast.LENGTH_LONG).show();

    }


}

 abstract class TextValidator implements TextWatcher {
    private final EditText editText;

    public TextValidator(EditText editText) {
        this.editText = editText;
    }

    public abstract void validate(EditText editText, String text);

    @Override
    final public void afterTextChanged(Editable s) {
        String text = editText.getText().toString();
        validate(editText, text);
    }

    @Override
    final public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* Don't care */ }

    @Override
    final public void onTextChanged(CharSequence s, int start, int before, int count) { /* Don't care */ }
}
