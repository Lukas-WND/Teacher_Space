package com.example.teacher_space.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teacher_space.R;
import com.example.teacher_space.entity.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private final Context context;
    private final List<Student> studentList;

    public StudentAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_aluno, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.bind(student);
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView tvStudentName, tvIcon;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStudentName = itemView.findViewById(R.id.aluno_nome_card);
            tvIcon = itemView.findViewById(R.id.txt_icon); // Certifique-se de que este ID existe no XML
        }

        public void bind(Student student) {
            if (student.getName() != null && !student.getName().isEmpty()) {
                tvIcon.setText(String.valueOf(student.getName().charAt(0)));
                tvStudentName.setText(student.getName());
            } else {
                tvIcon.setText("?");
                tvStudentName.setText("Nome não disponível");
            }
        }
    }
}
