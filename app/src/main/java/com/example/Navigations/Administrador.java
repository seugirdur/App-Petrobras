package com.example.Navigations;

import android.os.Bundle;

import androidx.viewpager2.widget.ViewPager2;

import com.example.apppetrobras.Adapters.VPAdministrador;
import com.example.apppetrobras.R;
import com.example.apppetrobras.databinding.LayoutAdministradorBinding;
import com.google.android.material.tabs.TabLayout;

public class Administrador extends Drawer {

    // Declaração das variáveis
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    VPAdministrador VPAdministrador;
    LayoutAdministradorBinding layoutAdministradorBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutAdministradorBinding = LayoutAdministradorBinding.inflate(getLayoutInflater());
        setContentView(layoutAdministradorBinding.getRoot());
        allocateActivityTitle("Configurações do Administrador");


        // Navigation Drawer
        //activityTabBinding = ActivityTabBinding.inflate(getLayoutInflater());
        // setContentView(activityTabBinding.getRoot());
        //allocateActivityTitle("Menu Principal");

        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.view_pager);
        VPAdministrador = new VPAdministrador(this);
        viewPager2.setAdapter(VPAdministrador);

//       settingTheName();


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });


    }

    //Captura o click no logo e vai para a tela inicial
    //public void goHome(View view) {
    //viewPager2.setCurrentItem(0);
}




