package com.example.Navigations;

import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import com.example.apppetrobras.R;
import com.example.apppetrobras.Adapters.VPConfigurações;
import com.example.apppetrobras.databinding.LayoutConfiguracoesBinding;
import com.google.android.material.tabs.TabLayout;

public class Configuracoes extends Drawer {

    // Declaração das variáveis
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    VPConfigurações VPConfigurações;
    LayoutConfiguracoesBinding layoutConfiguracoesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutConfiguracoesBinding = LayoutConfiguracoesBinding.inflate(getLayoutInflater());
        setContentView(layoutConfiguracoesBinding.getRoot());
        allocateActivityTitle("Configurações do Usuário");

        Boolean updatephoto = getIntent().getBooleanExtra("photo", false);


//            onCreate(savedInstanceState);
//            super.onCreate(savedInstanceState);
        if (updatephoto) {
            Intent intento = new Intent(Configuracoes.this, Configuracoes.class);
            intento.putExtra("photo", false);
            startActivity(intento);
            finish();
        }

        // Navigation Drawer
        //activityTabBinding = ActivityTabBinding.inflate(getLayoutInflater());
        // setContentView(activityTabBinding.getRoot());
        //allocateActivityTitle("Menu Principal");

        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.view_pager);
        VPConfigurações = new VPConfigurações(this);
        viewPager2.setAdapter(VPConfigurações);

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




