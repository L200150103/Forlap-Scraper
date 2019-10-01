package company.wkdk.id.retrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;


public class homePage extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        getSupportActionBar().setTitle("Home Page");

        CardView vwCari = (CardView) findViewById(R.id.btnCari);
        vwCari.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        CardView vwBeranda = (CardView) findViewById(R.id.btnBeranda);
        vwBeranda.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), forlapDikti.class);
                startActivity(intent);
            }
        });

        CardView vwTentang = (CardView) findViewById(R.id.btnTentang);
        vwTentang.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), infoApp.class);
                startActivity(intent);
            }
        });


    }
}
