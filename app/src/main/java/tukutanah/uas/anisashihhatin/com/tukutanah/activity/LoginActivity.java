package tukutanah.uas.anisashihhatin.com.tukutanah.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tukutanah.uas.anisashihhatin.com.tukutanah.R;
import tukutanah.uas.anisashihhatin.com.tukutanah.manager.AsyncTaskUser;
import tukutanah.uas.anisashihhatin.com.tukutanah.model.UsersModel;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private EditText etEmail, etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button)findViewById(R.id.btnLogin);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPass = (EditText)findViewById(R.id.etPass);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = etEmail.getText().toString();
                String password = etPass.getText().toString();

                if(email.equals("") || password.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please enter full field",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    UsersModel user = new UsersModel();
                    user.setEmail(email);
                    user.setPassWord(password);
                    new AsyncTaskUser(LoginActivity.this,user,true).execute("http://tpalwayscreative.esy.es/task_manager/v1/login");
                }

            }
        });
    }
}
