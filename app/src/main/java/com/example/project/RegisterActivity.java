package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project.api.ApiService;
import com.example.project.api.MockApiService;
import com.example.project.model.LoginResponse;
import com.example.project.model.User;
import retrofit2.*;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUsername, etPassword, etConfirm;
    private Button   btnRegister;
    private TextView tvGoToLogin;
    private ApiService api;

    @Override
    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_register);

        etUsername  = findViewById(R.id.etUsername);
        etPassword  = findViewById(R.id.etPassword);
        etConfirm   = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvGoToLogin = findViewById(R.id.tvGoToLogin);
        api         = new MockApiService();

        btnRegister.setOnClickListener(v -> {
            String u = etUsername.getText().toString().trim();
            String p = etPassword.getText().toString().trim();
            String c = etConfirm.getText().toString().trim();
            if (u.isEmpty()||p.isEmpty()||c.isEmpty()){
                Toast.makeText(this,"Заполните поля",Toast.LENGTH_SHORT).show(); return;
            }
            if (!p.equals(c)){
                Toast.makeText(this,"Пароли не совпадают",Toast.LENGTH_SHORT).show(); return;
            }
            User user = new User(); user.setUsername(u); user.setPassword(p);

            api.register(user).enqueue(new Callback<LoginResponse>() {
                @Override public void onResponse(Call<LoginResponse> c, Response<LoginResponse> r){
                    runOnUiThread(()->{
                        if (r.isSuccessful() && r.body()!=null && r.body().isSuccess()){
                            UserSession.loginUser(RegisterActivity.this,u);
                            Toast.makeText(RegisterActivity.this,"Регистрация успешна",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            finish();
                        }else{
                            Toast.makeText(RegisterActivity.this,"Ошибка: "+r.body().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                @Override public void onFailure(Call<LoginResponse> c, Throwable t){
                    runOnUiThread(()-> Toast.makeText(RegisterActivity.this,
                            "Ошибка: "+t.getMessage(),Toast.LENGTH_SHORT).show());
                }
            });
        });

        tvGoToLogin.setOnClickListener(v->{
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}
