package com.example.apppetrobras.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apppetrobras.Objects.ProblemasObj;
import com.example.apppetrobras.Activities.Solucoes;
import com.example.apppetrobras.R;
import com.example.apppetrobras.Adapters.RecyclerViewAdapter;

import java.util.ArrayList;

public class EquipamentosFragment extends Fragment implements RecyclerViewInteface{

    // Declaração das variáveis
    private ArrayList<ProblemasObj> dataArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_equipamentos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitialize();

        // Essa variável recebe (por meio do id) a reciclerView no xml dessa tela
        RecyclerView recyclerview = view.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
        // Aqui há uma instância da RecyclerViewAdapter utilizando o construtor adequado:
        // RecyclerViewAdapter(Context, Lista<Objeto>, RecyclerViewInterface, layout do item)
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),
                dataArrayList, this, R.layout.item_list);
        recyclerview.setAdapter(recyclerViewAdapter);

        //recyclerViewAdapter.notifyDataSetChanged();
    }

    // Função para popular a lista usada na recyclerView
    private void dataInitialize() {

        dataArrayList = new ArrayList<>();

        String[] titulosProblemas = new String[]{
                getString(R.string.equipamentos_1),
                getString(R.string.equipamentos_2),
                getString(R.string.equipamentos_3),
                getString(R.string.equipamentos_4),
                getString(R.string.equipamentos_5),
                getString(R.string.equipamentos_6),
                getString(R.string.equipamentos_7),
                getString(R.string.equipamentos_8),
                getString(R.string.equipamentos_9),
                getString(R.string.equipamentos_10),
                getString(R.string.equipamentos_11),
                getString(R.string.equipamentos_12),
                getString(R.string.equipamentos_13),
        };

        int[] idProblemas = new int[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13
        };

        int[] imagensProblemas = new int[]{
                R.drawable.equipamentos,
                R.drawable.equipamentos,
                R.drawable.equipamentos,
                R.drawable.equipamentos,
                R.drawable.equipamentos,
                R.drawable.equipamentos,
                R.drawable.equipamentos,
                R.drawable.equipamentos,
                R.drawable.equipamentos,
                R.drawable.equipamentos,
                R.drawable.equipamentos,
                R.drawable.equipamentos,
                R.drawable.equipamentos
        };

        for(int i = 0; i < titulosProblemas.length; i++){
            ProblemasObj data = new ProblemasObj(titulosProblemas[i], idProblemas[i], imagensProblemas[i]);
            dataArrayList.add(data);
        }

    }

    @Override
    public void onItemClick(int position) {
        //criação da string para armazenamento no banco
        String titulo = "Equipamento";

        // Redirecionamento para a tela do problema contendo os títulos das soluções
        Intent intent = new Intent(getActivity(), Solucoes.class);

        // Definição de valores que serão redirecionados
        intent.putExtra("TIPO",3);
        intent.putExtra("titulo",titulo);
        intent.putExtra("ID_TITULO", dataArrayList.get(position).getId());
        intent.putExtra("titulosProblemas", dataArrayList.get(position).getText());
        startActivity(intent);
    }

}
