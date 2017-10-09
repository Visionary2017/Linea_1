package pe.edu.sise.applinea1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class splashActivity extends AppCompatActivity {

    
  // private NfcAdapter nfcAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

      //  nfcAdapter= nfcAdapter.getDefaultAdapter(this);

     /*   if(nfcAdapter == null){
            Toast.makeText(this, "Este dispositivo no soporta NFC!!!", Toast.LENGTH_SHORT).show();
            finish();
        }else if(!nfcAdapter.isEnabled()){
            Toast.makeText(this, "NFC no habilitado!!!", Toast.LENGTH_SHORT).show();
            finish();
        }*/

        Arrancar_Splash();
    }


    private void Arrancar_Splash(){
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(splashActivity.this,LoginActivity.class);
                    startActivity(intent);
                    //finish();
                }
            }
        };
        timerThread.start();
    }

/*    @Override
    protected  void onResume(){
        super.onResume();

        Intent intent = getIntent();
        String action = intent.getAction();

        if(nfcAdapter.ACTION_TAG_DISCOVERED.equals(action)){
            Toast.makeText(this, "Bienvenido!!", Toast.LENGTH_SHORT).show();
            Arrancar_Splash();
        }else{
            //Toast.makeText(this, "onResume() : " + action, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
*/
}
