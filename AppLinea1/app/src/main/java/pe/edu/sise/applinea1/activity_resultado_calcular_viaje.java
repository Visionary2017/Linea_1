package pe.edu.sise.applinea1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

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

        WebView view = (WebView) findViewById(R.id.miVisor);
        //view.getSettings().setBuiltInZoomControls(true);

        String data_html = "";

        data_html = data_html +"<html>";
        data_html = data_html + "<head>";
        data_html = data_html + "<title>Hola Mundo</title>";
        data_html = data_html + "<link rel='stylesheet' type='text/css' href='http://www.lineauno.pe/css/main.css' />";
        data_html = data_html + "</head>";
        data_html = data_html + "<body style='padding:10px'>";
        data_html = data_html + html_value;
        data_html = data_html + "</body>";
        data_html = data_html + "</html>";
        view.loadData(data_html, "text/html", "UTF-8");

/*
        HtmlTextView textView = (HtmlTextView) findViewById(R.id.html_text);
        textView.setHtml(html_value, new HtmlAssetsImageGetter(textView));
*/

    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
