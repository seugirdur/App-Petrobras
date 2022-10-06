package com.example.Navigations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.apppetrobras.Activities.Perfil;
import com.example.apppetrobras.Activities.Relatorio;
import com.example.apppetrobras.R;
import com.example.apppetrobras.Activities.SplashScreen;
import com.google.android.material.navigation.NavigationView;

    public class Drawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

        DrawerLayout drawerLayout;

        @Override
        public void setContentView(View view) {
            drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.layout_drawer, null);
            FrameLayout container = drawerLayout.findViewById(R.id.activityContainer);
            container.addView(view);
            super.setContentView(drawerLayout);


            Toolbar toolbar = drawerLayout.findViewById(R.id.toolBar);
            setSupportActionBar(toolbar);

            NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
            View headerView = navigationView.getHeaderView(0);
            TextView navUsername = (TextView) headerView.findViewById(R.id.tv_name_vand);
            navUsername.setText(settingTheName());
            navigationView.setNavigationItemSelectedListener(this);


            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.menu_drawer_open, R.string.menu_drawer_close);
            toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
        }

        private String settingTheName() {
            SharedPreferences sharedPreferences = getSharedPreferences(
                    getString(R.string.preference_file_key), Context.MODE_PRIVATE);

            String SayMyName = sharedPreferences.getString("nome", "");
            String[] fullNameArray = SayMyName.split("\\s+");
            String firstName = fullNameArray[0];
            String nome = "Olá, "+firstName;
            return nome;
        }

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            drawerLayout.closeDrawer(GravityCompat.START);

            switch (item.getItemId()) {

                case R.id.menu_principal:
                    startActivity(new Intent(this, Tabs.class));
                    overridePendingTransition(0, 0);
                    break;

                case R.id.historico:
                    startActivity(new Intent(this, Relatorio.class));
                    overridePendingTransition(0, 0);
                    break;


                case R.id.configuracoes:
                    startActivity(new Intent(this, Configuracoes.class));
                    overridePendingTransition(0, 0);
                    break;

                case R.id.perfil:
                    startActivity(new Intent(this, Perfil.class));
                    overridePendingTransition(0, 0);
                    break;

                case R.id.ajuda_suporte:
                    //startActivity(new Intent(this, Ajuda.class));
                    //overridePendingTransition(0, 0);
                    break;

                case R.id.sair:
                    startActivity(new Intent(this, SplashScreen.class));
                    overridePendingTransition(0, 0);
                    finish();
                    break;

            }

            return false;
        }

        protected void allocateActivityTitle(String titleString) {

            if (getSupportActionBar() != null) {

                getSupportActionBar().setTitle(titleString);


            }



        }

        protected void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        }
    }


