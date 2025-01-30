package com.example.teacher_space.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.teacher_space.R;
import com.example.teacher_space.SignUpStudentActivity;
import com.example.teacher_space.dtos.GetStudentDTO;
import com.example.teacher_space.entity.Student;
import com.example.teacher_space.interfaces.StudentAPI;
import com.example.teacher_space.recycler.StudentAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StudentsListFragment extends Fragment {

    private RecyclerView recyclerView;
    private StudentAdapter studentsAdapter;
    private ImageButton returnButton;
    private ImageButton registerButton;
    private List<Student> students = new ArrayList<>(); // Lista inicial vazia

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Infla o layout do fragmento
        View view = inflater.inflate(R.layout.fragment_students_list, container, false);

        // Inicializa os botões
        returnButton = view.findViewById(R.id.imageButton);
        registerButton = view.findViewById(R.id.imageButton2);

        // Configura os eventos de clique
        returnButton.setOnClickListener(v -> {
            // Código para voltar à tela anterior
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        registerButton.setOnClickListener(v -> {
            // Código para abrir uma nova Activity
            Intent intent = new Intent(requireContext(), SignUpStudentActivity.class);
            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.lista_alunos);

        if (recyclerView == null) {
            Log.e("StudentsListFragment", "Erro: RecyclerView não encontrado no layout.");
            return;
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inicializa o adapter com lista vazia
        studentsAdapter = new StudentAdapter(getContext(), students);
        recyclerView.setAdapter(studentsAdapter);

        // Chama a função para carregar os dados
        loadStudentData();
    }

    @Override
    public void onResume() {
        super.onResume();

        // Chama a função novamente ao retomar o fragmento
        loadStudentData();
    }

    private void loadStudentData() {
        SharedPreferences sp = requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String userId = sp.getString("user_id", "");

        if (userId.isEmpty()) {
            Log.e("StudentsListFragment", "Erro: ID do usuário não encontrado.");
            Toast.makeText(getContext(), "Erro: Usuário não encontrado!", Toast.LENGTH_SHORT).show();
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-teacherspace.onrender.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StudentAPI studentAPI = retrofit.create(StudentAPI.class);

        Call<List<GetStudentDTO>> call = studentAPI.getStudentByTeacherId(userId);
        call.enqueue(new Callback<List<GetStudentDTO>>() {
            @Override
            public void onResponse(Call<List<GetStudentDTO>> call, Response<List<GetStudentDTO>> response) {
                if (response.isSuccessful()) {
                    List<GetStudentDTO> studentsDTO = response.body();
                    if (studentsDTO != null && !studentsDTO.isEmpty()) {
                        students.clear(); // Limpa a lista antes de adicionar novos itens
                        for (GetStudentDTO studentDTO : studentsDTO) {
                            students.add(studentDTO.build(studentDTO));
                        }
                        studentsAdapter.notifyDataSetChanged(); // Notifica o adapter sobre mudanças
                    } else {
                        Toast.makeText(getContext(), "Nenhum estudante encontrado!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("StudentsListFragment", "Erro na resposta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<GetStudentDTO>> call, Throwable t) {
                Log.e("StudentsListFragment", "Erro na requisição: " + t.getMessage());
            }
        });
    }
}
