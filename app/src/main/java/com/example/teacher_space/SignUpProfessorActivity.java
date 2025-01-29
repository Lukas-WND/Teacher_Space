package com.example.teacher_space;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.teacher_space.dtos.TeacherDTO;
import com.example.teacher_space.interfaces.TeacherAPI;

import java.util.Objects;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Tag;

public class SignUpProfessorActivity extends AppCompatActivity {

    private EditText pName, pDisciplina, pCelular, pEmail, pSenha, pConfirmarSenha;
    private Button bCadastrar;
    private TextView jaCadastrado;
    private TeacherAPI teacherAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_sign_up);

        pName = findViewById(R.id.full_name_professor);
        pDisciplina = findViewById(R.id.subject_professor);
        pCelular = findViewById(R.id.phone_professor);
        pEmail = findViewById(R.id.email_professor);
        pSenha = findViewById(R.id.password_professor);
        pConfirmarSenha = findViewById(R.id.confirm_passowrd_professor);

        bCadastrar = findViewById(R.id.sign_up_button_professor);

        jaCadastrado = findViewById(R.id.already_registered_sign_up_professor_button);

        // Inicializar Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-teacherspace.onrender.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        teacherAPI = retrofit.create(TeacherAPI.class);



        // Configurar listener para botão de cadastro
        bCadastrar.setOnClickListener(v -> handleSignUp());

        // Configurar listener para botao de 'já cadastrado'
        jaCadastrado.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpProfessorActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void handleSignUp() {
        // Validar campos obrigatórios
        String name = pName.getText().toString().trim();
        String disciplina = pDisciplina.getText().toString().trim();
        String celular = pCelular.getText().toString().trim();
        String email = pEmail.getText().toString().trim();
        String senha = pSenha.getText().toString().trim();
        String confirmarSenha = pConfirmarSenha.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(disciplina) || TextUtils.isEmpty(celular) ||
                TextUtils.isEmpty(email) || TextUtils.isEmpty(senha) || TextUtils.isEmpty(confirmarSenha)) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!senha.equals(confirmarSenha)) {
            Toast.makeText(this, "As senhas não coincidem!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Criar objeto TeacherDTO
        TeacherDTO teacherDTO = new TeacherDTO(name, disciplina, celular, email, senha);

        // Enviar dados para a API
        Call<TeacherDTO> call = teacherAPI.createTeacher(teacherDTO);
        call.enqueue(new Callback<TeacherDTO>() {
            @Override
            public void onResponse(Call<TeacherDTO> call, Response<TeacherDTO> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SignUpProfessorActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                    // Redirecionar para a tela de login que ja estava aberta anteriormente
                    Intent intent = new Intent(SignUpProfessorActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(SignUpProfessorActivity.this, "Erro no cadastro: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TeacherDTO> call, Throwable t) {
                Log.e("SignUpProfessorActivity", "onFailure: " + t.getMessage(), t);
                Toast.makeText(SignUpProfessorActivity.this, "Erro de conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}