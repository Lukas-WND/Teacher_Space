package com.example.teacher_space;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.teacher_space.databinding.ActivityHomeBinding;
import com.example.teacher_space.fragments.HomeFragment;
import com.example.teacher_space.fragments.LogoutFragment;
import com.example.teacher_space.fragments.StudentsListFragment;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Define o fragmento inicial como HomeFragment
        replaceFragment(new HomeFragment());

        // Configura o listener para mudança de fragmentos ao selecionar um item
        binding. appNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case 1000017:
                    replaceFragment(new HomeFragment());
                    return true;
                case 1000016:
                    replaceFragment(new StudentsListFragment());
                    return true;
                case 1000015:
                    replaceFragment(new LogoutFragment());
                    return true;
                default:
                    return false;
            }
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
