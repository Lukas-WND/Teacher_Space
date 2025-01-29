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
            int itemId = item.getItemId();
            if (itemId == R.id.home_icon_menu) {
                replaceFragment(new HomeFragment());
                return true;
            } else if (itemId == R.id.list_icon_menu) {
                replaceFragment(new StudentsListFragment());
                return true;
            } else if (itemId == R.id.logout_icon_menu) {
                replaceFragment(new LogoutFragment());
                return true;
            } return false;
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
