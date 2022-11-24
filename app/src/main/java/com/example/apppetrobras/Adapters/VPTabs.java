package com.example.apppetrobras.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.apppetrobras.fragments.EquipamentosFragment;
import com.example.apppetrobras.fragments.InicioFragment;
import com.example.apppetrobras.fragments.InternetFragment;
import com.example.apppetrobras.fragments.LentidaoFragment;
import com.example.apppetrobras.fragments.OutrosFragment;

public class VPTabs extends FragmentStateAdapter {

    public VPTabs(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    //Retorna a tela(fragment) atual
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new InicioFragment();
            case 1:
                return new LentidaoFragment();
            case 2:
                return new InternetFragment();
            case 3:
                return new EquipamentosFragment();
            case 4:
                return new OutrosFragment();
            default:
                return new InicioFragment();
        }
    }

    //Retorna a quantidade de telas(fragment)
    @Override
    public int getItemCount() {
        return 5;
    }
}
