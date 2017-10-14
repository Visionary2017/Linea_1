package pe.edu.sise.applinea1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.sufficientlysecure.htmltextview.HtmlAssetsImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

public class activity_resultado_calcular_viaje extends AppCompatActivity {
    Toolbar toolbar;
    Bundle datos;
    String html_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_calcular_viaje);
        toolbar = (Toolbar) findViewById(R.id.toolbar_top);
        setToolbar();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        datos = this.getIntent().getExtras();
        html_value = datos.getString("html_text");
        Log.i("html_valor ", html_value);

        HtmlTextView textView = (HtmlTextView) findViewById(R.id.html_text);
        textView.setHtml(html_value, new HtmlAssetsImageGetter(textView));


    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
