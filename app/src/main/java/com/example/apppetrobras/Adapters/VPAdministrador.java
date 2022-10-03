package com.example.apppetrobras.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.apppetrobras.fragments.EmAbertoFragment;
import com.example.apppetrobras.fragments.SolucionadoFragment;

public class VPAdministrador extends FragmentStateAdapter {

    public VPAdministrador(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    //Retorna a tela(fragment) atual
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new SolucionadoFragment();

            default:
                return new EmAbertoFragment();
        }
    }

    //Retorna a quantidade de telas(fragment)
    @Override
    public int getItemCount() {
        return 2;
    }
}