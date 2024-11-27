package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.databinding.MainFragmentBinding;
import com.example.myapplication.utils.AppSharedPreferences;

public class MainFragment extends AppCompatActivity {

    private MainFragmentBinding binding;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       binding = MainFragmentBinding.inflate(getLayoutInflater());
       setContentView(binding.getRoot());

       String token = getIntent().getStringExtra("token");

       if (token != null) {
           AppSharedPreferences.getInstance(this).saveToken(token);
       }

       Bundle bundle = new Bundle();
       bundle.putString("token", token);

       DashboardFragment dashboardFragment = new DashboardFragment();
       dashboardFragment.setArguments(bundle);
       replaceFragment(dashboardFragment);

       binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
           Fragment selectedFragment = null;

           if(item.getItemId() == R.id.inicio){
               selectedFragment = new DashboardFragment();
           } else if (item.getItemId() == R.id.perfil) {
               selectedFragment = new PerfilFragment();
           } else if (item.getItemId() == R.id.buscar) {
               selectedFragment = new MapaActivity();
           }else if (item.getItemId() == R.id.lembrete) {
               selectedFragment = new LembretesFragment();
           }

           if (selectedFragment != null) {
               selectedFragment.setArguments(bundle);
               replaceFragment(selectedFragment);
           }

           return true;
       });



   }


   public void replaceFragment(Fragment fragment){
       FragmentManager fragmentManager = getSupportFragmentManager();
       FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
       fragmentTransaction.replace(R.id.frameLayout,fragment);
       fragmentTransaction.commit();
   }


}
