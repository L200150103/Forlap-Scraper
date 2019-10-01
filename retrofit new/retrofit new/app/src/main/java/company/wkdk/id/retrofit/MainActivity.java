package company.wkdk.id.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameEditText;
    private EditText jobEditText;
    private TextView t;
    private TextView tx;
    private Button saveUserButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.name);
        jobEditText = findViewById(R.id.job);
        saveUserButton = findViewById(R.id.save_user);
        t = findViewById(R.id.textView);
        tx = findViewById(R.id.textView2);


        saveUserButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save_user:

                User user = new User(nameEditText.getText().toString(), jobEditText.getText().toString());


                MainApplication.apiManager.createUser(user, new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        t.setText(response.body().getName());
                        tx.setText(response.body().getJob());

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                        Toast.makeText(MainActivity.this,
                                "Error is " + t.getMessage()
                                , Toast.LENGTH_LONG).show();
                    }
                });

                break;
        }
    }
}