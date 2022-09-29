package com.example.apppetrobras.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.apppetrobras.DadosLista;
import com.example.apppetrobras.R;
import com.example.apppetrobras.RecyclerViewAdapter;
import com.example.apppetrobras.Tela_de_escolha;
import com.example.apppetrobras.fragments.RecyclerViewInteface;

import java.util.ArrayList;

public class Historico_Fragment extends Fragment implements RecyclerViewInteface {

    private ArrayList<DadosLista> dataArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_historico_, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitialize();

        // Essa variável recebe (por meio do id) a reciclerView no xml dessa tela
        RecyclerView recyclerview = view.findViewById(R.id.recyclerviewhst);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
        // Aqui há uma instância da RecyclerViewAdapter utilizando o construtor adequado:
        // RecyclerViewAdapter(Context, Lista<Objeto>, RecyclerViewInterface, layout do item)
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),
                dataArrayList, this, R.layout.item_historico);
        recyclerview.setAdapter(recyclerViewAdapter);
    }

    // Função para popular a lista usada na recyclerView
    private void dataInitialize() {

        dataArrayList = new ArrayList<>();

        String[] titulosProblemas = new String[]{
                "Relatório do dia --/--/----",
                "Relatório do dia --/--/----",
                "Relatório do dia --/--/----",
                "Relatório do dia --/--/----",
                "Relatório do dia --/--/----",
                "Relatório do dia --/--/----",
                "Relatório do dia --/--/----",
                "Relatório do dia --/--/----",
                "Relatório do dia --/--/----",
                "Relatório do dia --/--/----",
                "Relatório do dia --/--/----",
                "Relatório do dia --/--/----",
                "Relatório do dia --/--/----",
                "Relatório do dia --/--/----",
        };

        for(int i = 0; i < titulosProblemas.length; i++){
            DadosLista data = new DadosLista(titulosProblemas[i]);
            dataArrayList.add(data);
        }
    }

    @Override
    public void onItemClick(int position) {
        // Redirecionamento para a tela do problema contendo os títulos das soluções
        Intent intent = new Intent(getActivity(), Tela_de_escolha.class);

        // Definição de valores que serão redirecionados
        intent.putExtra("TIPO",3);
        intent.putExtra("ID_TITULO", dataArrayList.get(position).getId());
        startActivity(intent);
    }

}