package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project.api.ApiService;
import com.example.project.api.MockApiService;
import com.example.project.model.LoginResponse;
import com.example.project.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button   btnLogin;
    private TextView tvGoToRegister;
    private ApiService api;

    @Override
    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_login);

        etUsername      = findViewById(R.id.etUsername);
        etPassword      = findViewById(R.id.etPassword);
        btnLogin        = findViewById(R.id.btnLogin);
        tvGoToRegister  = findViewById(R.id.tvGoToRegister);
        api             = new MockApiService();

        btnLogin.setOnClickListener(v -> {
            String u = etUsername.getText().toString().trim();
            String p = etPassword.getText().toString().trim();
            if (u.isEmpty() || p.isEmpty()){
                Toast.makeText(this,"Введите данные",Toast.LENGTH_SHORT).show(); return;
            }
            User user = new User(); user.setUsername(u); user.setPassword(p);

            api.login(user).enqueue(new Callback<LoginResponse>() {
                @Override public void onResponse(Call<LoginResponse> c, Response<LoginResponse> r){
                    runOnUiThread(()->{
                        if (r.isSuccessful() && r.body()!=null && r.body().isSuccess()){
                            UserSession.loginUser(LoginActivity.this,u);
                            Toast.makeText(LoginActivity.this,"Успешный вход",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this,"Неверные данные",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                @Override public void onFailure(Call<LoginResponse> c, Throwable t){
                    runOnUiThread(()-> Toast.makeText(LoginActivity.this,
                            "Ошибка: "+t.getMessage(),Toast.LENGTH_SHORT).show());
                }
            });
        });

        tvGoToRegister.setOnClickListener(v->
                startActivity(new Intent(this, RegisterActivity.class)));
    }
}
