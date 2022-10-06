package com.example.apppetrobras.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.apppetrobras.fragments.Ajuda_fragment;
import com.example.apppetrobras.fragments.History;
import com.example.apppetrobras.fragments.Opcoes_Fragment;

public class VPConfigurações extends FragmentStateAdapter {

    public VPConfigurações(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    //Retorna a tela(fragment) atual
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new History();
            case 1:
                return new Opcoes_Fragment();
            case 2:
                return new Ajuda_fragment();
            default:
                return new History();
        }
    }

    //Retorna a quantidade de telas(fragment)
    @Override
    public int getItemCount() {
        return 3;
    }
}