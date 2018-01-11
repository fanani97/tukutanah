package tukutanah.uas.anisashihhatin.com.tukutanah.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import tukutanah.uas.anisashihhatin.com.tukutanah.R;
import tukutanah.uas.anisashihhatin.com.tukutanah.manager.AppSharedPreferences;
import tukutanah.uas.anisashihhatin.com.tukutanah.model.UsersModel;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private EditText etEmail, etPass;
    private final String url = "http://tpalwayscreative.esy.es/task_manager/v1/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.btnLogin);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPass.getText().toString();
                if (email.equals("") || password.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter full field", Toast.LENGTH_SHORT).show();
                } else {
                    UsersModel user = new UsersModel();
                    user.setEmail(email);
                    user.setPassWord(password);
                    login(user);
                }
            }
        });
    }

    private void login(UsersModel user) {
        Ion.with(LoginActivity.this)
            .load(url)
            .setBodyParameter("email", user.getEmail())
            .setBodyParameter("password", user.getPassword())
            .asJsonObject()
            .withResponse()
            .setCallback(new FutureCallback<Response<JsonObject>>() {
                @Override
                public void onCompleted(Exception e, Response<JsonObject> response) {
                    JsonObject result = response.getResult();
                    boolean error = result.get("error").getAsBoolean();
                    if (!error) {
                        String name = result.get("name").getAsString();
                        String email = result.get("email").getAsString();
                        String apiKey = result.get("apiKey").getAsString();
                        System.out.println(name);
                        AppSharedPreferences.setStringValue(LoginActivity.this, "NAME", name);
                        System.out.println(AppSharedPreferences.getStringValue(LoginActivity.this, "NAME"));
                        startHomeActivity();
                    } else {
                        Toast.makeText(LoginActivity.this, "Error: "+result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    private void startHomeActivity() {
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
    }
}
