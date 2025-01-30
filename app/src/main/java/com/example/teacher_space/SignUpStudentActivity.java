package com.example.teacher_space;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teacher_space.dtos.GetStudentDTO;
import com.example.teacher_space.dtos.SendStudentDTO;
import com.example.teacher_space.interfaces.StudentAPI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpStudentActivity extends AppCompatActivity {

    private EditText sName, sPhone, sEmail, sCpf, sBirthDate, sCategory;
    private Button bCadastrar;
    private ImageButton retornar;
    private StudentAPI studentAPI;
    private final Calendar calendar = Calendar.getInstance();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);

        sName = findViewById(R.id.full_name_aluno);
        sCategory = findViewById(R.id.category_student);
        sPhone = findViewById(R.id.phone_aluno);
        sEmail = findViewById(R.id.email_aluno);
        sCpf = findViewById(R.id.cpf_aluno);
        sBirthDate = findViewById(R.id.dataNascimento_aluno);
        retornar = findViewById(R.id.return_button_sign_up_student);
        bCadastrar = findViewById(R.id.sign_up_button_aluno);

        // Inicializar Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-teacherspace.onrender.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        studentAPI = retrofit.create(StudentAPI.class);

        // Configurar listener para botão de cadastro
        bCadastrar.setOnClickListener(v -> handleSignUp());

        // Configurar botão de retorno
        retornar.setOnClickListener(v -> {
            finish();
        });

        // Configurar o DatePicker para o campo de data de nascimento
        sBirthDate.setOnClickListener(v -> showDatePicker());
    }

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            sBirthDate.setText(dateFormat.format(calendar.getTime()));
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private String convertDateToISO8601(Date date) {
        SimpleDateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        return iso8601Format.format(date);
    }

    private void handleSignUp() {
        // Validar campos obrigatórios
        String name = sName.getText().toString().trim();
        String category = sCategory.getText().toString().trim();
        String phone = sPhone.getText().toString().trim();
        String email = sEmail.getText().toString().trim();
        String cpf = sCpf.getText().toString().trim();
        String birthDateString = sBirthDate.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(category) || TextUtils.isEmpty(phone) ||
                TextUtils.isEmpty(email) || TextUtils.isEmpty(cpf) || TextUtils.isEmpty(birthDateString)) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cpf.length() != 11) {
            Toast.makeText(this, "CPF deve ter 11 dígitos!", Toast.LENGTH_SHORT).show();
            return;
        }

        Date birthDate;
        try {
            birthDate = dateFormat.parse(birthDateString);
        } catch (ParseException e) {
            Toast.makeText(this, "Formato de data inválido!", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sp = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String userId = sp.getString("user_id", "Usuário");

        // Criar objeto SendStudentDTO
        SendStudentDTO studentDTO = new SendStudentDTO(name, category, phone, email, convertDateToISO8601(birthDate), cpf, userId);

        // Enviar dados para a API

        Call<GetStudentDTO> call = studentAPI.createStudent(studentDTO);
        call.enqueue(new Callback<GetStudentDTO>() {
            @Override
            public void onResponse(Call<GetStudentDTO> call, Response<GetStudentDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(SignUpStudentActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();

                    finish();
                } else {
                    Toast.makeText(SignUpStudentActivity.this, "Erro no cadastro: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetStudentDTO> call, Throwable t) {
                Log.e("SignUpStudentActivity", "onFailure: " + t.getMessage(), t);
                Toast.makeText(SignUpStudentActivity.this, "Erro de conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
