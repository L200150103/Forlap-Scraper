package company.wkdk.id.retrofit;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText jobEditText;
    private Spinner prodiSpinner;
    private TextView t;
    private TextView tx;
    private Button saveUserButton;
    private WebView view;
    private Activity activity;

    private String prodiDipilih;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Pencarian Data");
        //toolbar.setSubtitle("Forum Laporan Pendidikan Tinggi");
        //toolbar.setLogo(android.R.drawable.ic_menu_info_details);

        ////taruh di .xml (support action bar)
        //<android.support.v7.widget.Toolbar
        //android:id="@+id/toolbar"
        //android:layout_width="match_parent"
        //android:layout_height="56dp"
        //android:background="#191970"/>

        nameEditText = findViewById(R.id.name);
        jobEditText = findViewById(R.id.job);
        prodiSpinner = findViewById(R.id.spinner);
        saveUserButton = findViewById(R.id.save_user);

        saveUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser();
            }
        });

        final ArrayAdapter<String> adapterdep = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.list_prodi2));
        adapterdep.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prodiSpinner.setAdapter(adapterdep);
        prodiSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                prodiDipilih = adapterdep.getItem(position).replace(" ","_").toLowerCase();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void saveUser() {
        if (!nameEditText.getText().toString().isEmpty()) {
            User user = new User(nameEditText.getText().toString(), prodiDipilih);

            MainApplication.apiManager.createUser(user, new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful() && response.body().getName() != null) {
                        Log.d("TAG", "Success");

                        Intent intent = new Intent(MainActivity.this, webView.class);
                        intent.putExtra("link", response.body().getJob());
                        MainActivity.this.startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this,
                                "User tidak ditemukan" // + t.getMessage()
                                , Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(MainActivity.this,
                            t.getMessage() // + t.getMessage()
                            , Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(MainActivity.this,
                    "Silahkan masukan NIM" // + t.getMessage()
                    , Toast.LENGTH_LONG).show();
        }
    }
}
