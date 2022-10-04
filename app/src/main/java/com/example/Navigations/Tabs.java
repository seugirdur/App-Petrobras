package com.example.Navigations;

import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.example.apppetrobras.Adapters.VPTabs;
import com.example.apppetrobras.R;
import com.example.apppetrobras.databinding.LayoutTabBinding;
import com.google.android.material.tabs.TabLayout;

public class Tabs extends Drawer {

    // Declaração das variáveis
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    VPTabs VPTabs;
    LayoutTabBinding layoutTabBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Zera o check das soluções
        String check = "";
        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("check", check);
        editor.apply();


        // Navigation Drawer
        layoutTabBinding = LayoutTabBinding.inflate(getLayoutInflater());
        setContentView(layoutTabBinding.getRoot());
        allocateActivityTitle("Menu Principal");

        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_pager);
        VPTabs = new VPTabs(this);
        viewPager2.setAdapter(VPTabs);

//        settingTheName();

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
    public void goHome(View view) {
        viewPager2.setCurrentItem(0);
    }



    
}
