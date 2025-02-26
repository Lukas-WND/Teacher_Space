package com.example.teacher_space;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teacher_space.MainActivity;
import com.example.teacher_space.dtos.LoginDTO;
import com.example.teacher_space.dtos.LoginResponseDTO;
import com.example.teacher_space.dtos.TeacherDTO;
import com.example.teacher_space.interfaces.TeacherAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvRegister;
    private TeacherAPI teacherAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        // Inicializa os componentes da interface
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_register);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-teacherspace.onrender.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        teacherAPI = retrofit.create(TeacherAPI.class);


        // Ação do botão de login
        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            }


            Call<LoginResponseDTO> call = teacherAPI.login(new LoginDTO(email, password));
            call.enqueue(new Callback<LoginResponseDTO>() {
                @Override
                public void onResponse(Call<LoginResponseDTO> call, Response<LoginResponseDTO> response) {
                    if (response.isSuccessful()) {
                        Log.d("Login_Activity", "Email: " + response.body().getTeacher().getEmail() + " | Name: " + response.body().getTeacher().getName());
                        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("user_id", response.body().getTeacher().getId());
                        editor.putString("user_name", response.body().getTeacher().getName());
                        editor.putString("user_email", response.body().getTeacher().getEmail());
                        editor.apply();

                        Toast.makeText(LoginActivity.this, "Usuario logado com sucesso!", Toast.LENGTH_SHORT).show();
                        // Redirecionar para a tela de login que ja estava aberta anteriormente
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Erro no login: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponseDTO> call, Throwable t) {
                    Log.e("LoginActivity", "onFailure: " + t.getMessage(), t);
                    Toast.makeText(LoginActivity.this, "Erro de conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpProfessorActivity.class);
            startActivity(intent);
        });
    }

}
