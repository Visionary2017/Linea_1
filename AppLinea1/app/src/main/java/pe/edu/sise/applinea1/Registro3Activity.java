package pe.edu.sise.applinea1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Registro3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro3);

        ImageView imageView = (ImageView) findViewById(R.id.imgView);

        Glide.with(Registro3Activity.this)
                .load(R.drawable.nfc)
                .asGif()
                .placeholder(R.drawable.nfc)
                .crossFade()
                .into(imageView);
    }
}
