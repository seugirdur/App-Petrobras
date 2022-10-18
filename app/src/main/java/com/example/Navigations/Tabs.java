package com.example.Navigations;

import androidx.activity.OnBackPressedCallback;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.popupcheck), Context.MODE_PRIVATE);

        Boolean isFirstOpen = sharedPref.getBoolean("firstopentab", true);

        if(isFirstOpen) {
            mDialog = new Dialog(this);

            // Defini o click dentro do popup
            mDialog.setContentView(R.layout.popup_cadeado_solucoes);
            mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mDialog.show();

            afterYou();
        }





        // Zera o check das soluções
        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("check","");
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

    private void afterYou() {
        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.popupcheck), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("firstopentab",false);
        editor.apply();
    }

    //Captura o click no logo e vai para a tela inicial
    public void goHome(View view) {
        viewPager2.setCurrentItem(0);
    }

    public void OnBackPressedCallback() {
        finish();
    }

    
}
