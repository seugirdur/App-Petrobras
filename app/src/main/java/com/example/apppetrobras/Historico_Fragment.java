package com.example.apppetrobras;

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

import com.example.apppetrobras.fragments.RecyclerViewInteface;

import java.util.ArrayList;

public class Historico_Fragment extends Fragment implements RecyclerViewInteface {

    private ArrayList<DadosLista> dataArrayList4, dataArrayList5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_historico_, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitialize1();

        // Essa variável recebe (por meio do id) a reciclerView no xml dessa tela
        RecyclerView recyclerview4 = view.findViewById(R.id.recyclerviewhst);
        recyclerview4.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview4.setHasFixedSize(true);
        // Aqui há uma instância da RecyclerViewAdapter utilizando o construtor adequado:
        // RecyclerViewAdapter(Context, Lista<Objeto>, RecyclerViewInterface, layout do item)
        RecyclerViewAdapter recyclerViewAdapter1 = new RecyclerViewAdapter(getContext(),
                dataArrayList4, this, R.layout.item_historico);
        recyclerview4.setAdapter(recyclerViewAdapter1);

//        dataInitialize2();
//
//        // Essa variável recebe (por meio do id) a reciclerView no xml dessa tela
//        RecyclerView recyclerview5 = view.findViewById(R.id.recyclerviewhst);
//        recyclerview5.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerview5.setHasFixedSize(true);
//        // Aqui há uma instância da RecyclerViewAdapter utilizando o construtor adequado:
//        // RecyclerViewAdapter(Context, Lista<Objeto>, RecyclerViewInterface, layout do item)
//        RecyclerViewAdapter recyclerViewAdapter2 = new RecyclerViewAdapter(getContext(),
//                dataArrayList5, this, R.layout.item_historico);
//        recyclerview5.setAdapter(recyclerViewAdapter2);

        //recyclerViewAdapter.notifyDataSetChanged();
    }

    // Função para popular a lista usada na recyclerView
    private void dataInitialize1() {

        dataArrayList4 = new ArrayList<>();

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
            dataArrayList4.add(data);
        }
    }

//    private void dataInitialize2() {
//
//        dataArrayList5 = new ArrayList<>();
//
//        String[] titulosProblemas = new String[]{
//                "Relatório do dia --/--/----",
//                "Relatório do dia --/--/----"
//        };
//
//        for(int i = 0; i < titulosProblemas.length; i++){
//            DadosLista data = new DadosLista(titulosProblemas[i]);
//            dataArrayList5.add(data);
//        }
//    }

    @Override
    public void onItemClick(int position) {
        // Redirecionamento para a tela do problema contendo os títulos das soluções
        Intent intent = new Intent(getActivity(), Tela_de_escolha.class);

        // Definição de valores que serão redirecionados
        intent.putExtra("TIPO",3);
        intent.putExtra("ID_TITULO", dataArrayList4.get(position).getId());
        startActivity(intent);
    }

}