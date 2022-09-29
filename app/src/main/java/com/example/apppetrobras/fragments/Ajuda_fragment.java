package com.example.apppetrobras.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import com.example.apppetrobras.Objects.DadosLista;
import com.example.apppetrobras.Activitys.ProblemActivity;
import com.example.apppetrobras.R;
import com.example.apppetrobras.Adapters.RecyclerViewAdapter;

import java.util.ArrayList;

public class Ajuda_fragment extends Fragment implements RecyclerViewInteface {

    private ArrayList<DadosLista> dataArrayList1, dataArrayList2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ajuda_fragment, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitialize1();

        // Essa variável recebe (por meio do id) a reciclerView no xml dessa tela
        RecyclerView recyclerview1 = view.findViewById(R.id.recyclerview1);
        recyclerview1.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview1.setHasFixedSize(true);
        // Aqui há uma instância da RecyclerViewAdapter utilizando o construtor adequado:
        // RecyclerViewAdapter(Context, Lista<Objeto>, RecyclerViewInterface, layout do item)
        RecyclerViewAdapter recyclerViewAdapter1 = new RecyclerViewAdapter(getContext(),
                dataArrayList1, this, R.layout.item_ajuda_list);
        recyclerview1.setAdapter(recyclerViewAdapter1);

        dataInitialize2();

        // Essa variável recebe (por meio do id) a reciclerView no xml dessa tela
        RecyclerView recyclerview2 = view.findViewById(R.id.recyclerview2);
        recyclerview2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview2.setHasFixedSize(true);
        // Aqui há uma instância da RecyclerViewAdapter utilizando o construtor adequado:
        // RecyclerViewAdapter(Context, Lista<Objeto>, RecyclerViewInterface, layout do item)
        RecyclerViewAdapter recyclerViewAdapter2 = new RecyclerViewAdapter(getContext(),
                dataArrayList2, this, R.layout.item_ajuda_list);
        recyclerview2.setAdapter(recyclerViewAdapter2);

        //recyclerViewAdapter.notifyDataSetChanged();
    }

    // Função para popular a lista usada na recyclerView
    private void dataInitialize1() {

        dataArrayList1 = new ArrayList<>();

        String[] titulosProblemas = new String[]{
                "Como chamar um técnico?",
                "Como buscar a solução?",
                "Como ver meus chamados?",
                "Como usar o aplicativo?"
        };

        for(int i = 0; i < titulosProblemas.length; i++){
            DadosLista data = new DadosLista(titulosProblemas[i]);
            dataArrayList1.add(data);
        }
    }

    private void dataInitialize2() {

        dataArrayList2 = new ArrayList<>();

        String[] titulosProblemas = new String[]{
                "Como funciona a tela de escolha?",
                "Como funciona a tela de chamado?"
        };

        for(int i = 0; i < titulosProblemas.length; i++){
            DadosLista data = new DadosLista(titulosProblemas[i]);
            dataArrayList2.add(data);
        }
    }

    @Override
    public void onItemClick(int position) {
        // Redirecionamento para a tela do problema contendo os títulos das soluções
        Intent intent = new Intent(getActivity(), ProblemActivity.class);

        // Definição de valores que serão redirecionados
        intent.putExtra("TIPO",3);
        intent.putExtra("ID_TITULO", dataArrayList1.get(position).getId());
        startActivity(intent);
    }

}