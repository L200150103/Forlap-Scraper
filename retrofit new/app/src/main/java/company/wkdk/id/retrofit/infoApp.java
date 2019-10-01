package company.wkdk.id.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class infoApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_app);

        getSupportActionBar().setTitle("Tentang Aplikasi");
    }
}
