package company.wkdk.id.retrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread(){
            public void run(){
                try{
                    sleep(1500);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally {
                    startActivity(new Intent(splashActivity.this,homePage.class));
                    finish();
                }
            }
        };
        thread.start();

    }
}
