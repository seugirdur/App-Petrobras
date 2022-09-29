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

import com.example.apppetrobras.DadosLista;
import com.example.apppetrobras.ProblemActivity;
import com.example.apppetrobras.R;
import com.example.apppetrobras.RecyclerViewAdapter;

import java.util.ArrayList;

public class InicioFragment extends Fragment implements RecyclerViewInteface{

    // Declaração das variáveis
    private ArrayList<DadosLista> dataArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio, container, false);
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
                getString(R.string.inicio_1),
                getString(R.string.inicio_2),
                getString(R.string.inicio_3),
                getString(R.string.inicio_4),
                getString(R.string.inicio_5),
                getString(R.string.inicio_6)
        };

        int[] idProblemas = new int[]{
                1, 2, 3, 4, 5, 6
        };

        int[] imagensProblemas = new int[]{
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background
        };

        for(int i = 0; i < titulosProblemas.length; i++){
            DadosLista data = new DadosLista(titulosProblemas[i], idProblemas[i], imagensProblemas[i]);
            dataArrayList.add(data);
        }

    }

    @Override
    public void onItemClick(int position) {
        //criação da string para armazenamento no banco
        // Esse valor é temporário. Ainda não foi definido o funcionamento da aba 1(Inicio)
        String titulo = "Lentidão";

        // Redirecionamento para a tela do problema contendo os títulos das soluções
        Intent intent = new Intent(getActivity(), ProblemActivity.class);

        // Definição de valores que serão redirecionados
        // Valor do tipo é temporário. Ainda não foi definido o funcionamento da aba 1(Inicio)
        intent.putExtra("TIPO",1);
        intent.putExtra("titulo",titulo);
        intent.putExtra("ID_TITULO", dataArrayList.get(position).getId());
        intent.putExtra("titulosProblemas", dataArrayList.get(position).getText());
        startActivity(intent);
    }
}
