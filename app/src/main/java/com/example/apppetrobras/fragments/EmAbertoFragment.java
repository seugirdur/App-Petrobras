package com.example.apppetrobras.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apppetrobras.Activities.Inicio;
import com.example.apppetrobras.Activities.Solucoes;
import com.example.apppetrobras.Adapters.RecyclerViewAdapter;
import com.example.apppetrobras.Objects.ProblemasObj;
import com.example.apppetrobras.Objects.RelatorioObj;
import com.example.apppetrobras.Objects.SolucoesObj;
import com.example.apppetrobras.R;
import com.example.apppetrobras.api.RetroFitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmAbertoFragment extends Fragment implements RecyclerViewInteface{

    // Declaração das variáveis
    private ArrayList<ProblemasObj> dataArrayList;
    List<RelatorioObj> relatorioObjList;
    Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layout_tela_admin_chamadas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitialize();

        // Essa variável recebe (por meio do id) a reciclerView no xml dessa tela
        RecyclerView recyclerview = view.findViewById(R.id.recyclerviewadmin);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
        // Aqui há uma instância da RecyclerViewAdapter utilizando o construtor adequado:
        // RecyclerViewAdapter(Context, Lista<Objeto>, RecyclerViewInterface, layout do item)
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),
                dataArrayList, this, R.layout.item_list_admin);
        recyclerview.setAdapter(recyclerViewAdapter);

        //recyclerViewAdapter.notifyDataSetChanged();
    }

    // Função para popular a lista usada na recyclerView
    private void dataInitialize() {

        dataArrayList = new ArrayList<>();

        String[] titulosProblemas = new String[]{
                "Data do Relatório dd/mm/yyyy",
                "Data do Relatório dd/mm/yyyy",
                "Data do Relatório dd/mm/yyyy",
                "Data do Relatório dd/mm/yyyy",
                "Data do Relatório dd/mm/yyyy",
                "Data do Relatório dd/mm/yyyy",
                "Data do Relatório dd/mm/yyyy",
                "Data do Relatório dd/mm/yyyy",
                "Data do Relatório dd/mm/yyyy",
                "Data do Relatório dd/mm/yyyy",
                "Data do Relatório dd/mm/yyyy",
                "Data do Relatório dd/mm/yyyy",
                "Data do Relatório dd/mm/yyyy",
                "Data do Relatório dd/mm/yyyy",
        };

        for(int i = 0; i < titulosProblemas.length; i++){
            ProblemasObj data = new ProblemasObj(titulosProblemas[i]);
            dataArrayList.add(data);
        }
    }

    @Override
    public void onItemClick(int position) {
        // Redirecionamento para a tela do problema contendo os títulos das soluções
        Intent intent = new Intent(getActivity(), Inicio.class);

        // Definição de valores que serão redirecionados
        intent.putExtra("TIPO",3);
        intent.putExtra("ID_TITULO", dataArrayList.get(position).getId());
        startActivity(intent);
    }

    private void listen(){

    Call<List<RelatorioObj>> callme = RetroFitClient
            .getInstance()
            .getAPI()
            .getAllRelatoriosOpen();

    callme.enqueue(new Callback<List<RelatorioObj>>() {
        @Override
        public void onResponse(Call<List<RelatorioObj>> call, Response<List<RelatorioObj>> response) {
            if (!response.isSuccessful()){
                Toast.makeText(getContext(), "wassup", Toast.LENGTH_SHORT).show();
                return;
            }

            //tentando guardar num objeto para que seja depois visivel no item_list_admin do dionisio
            relatorioObjList = response.body();


        }

        @Override
        public void onFailure(Call<List<RelatorioObj>> call, Throwable t) {

        }
    });

    }



}