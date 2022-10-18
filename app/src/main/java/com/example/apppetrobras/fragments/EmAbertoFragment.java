package com.example.apppetrobras.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apppetrobras.Activities.Inicio;
import com.example.apppetrobras.Activities.Relatorio;
import com.example.apppetrobras.Activities.Solucoes;
import com.example.apppetrobras.Adapters.RVAdapterEmAberto;
import com.example.apppetrobras.Adapters.RVAdapterUserRelatorio;
import com.example.apppetrobras.Adapters.RecyclerViewAdapter;
import com.example.apppetrobras.Adapters.RelatorioAdapter;
import com.example.apppetrobras.Objects.ProblemasObj;
import com.example.apppetrobras.Objects.AdminObj;
import com.example.apppetrobras.Objects.RelatorioObj;
import com.example.apppetrobras.Objects.SolucoesObj;
import com.example.apppetrobras.R;
import com.example.apppetrobras.api.RetroFitClient;
import com.example.apppetrobras.databinding.LayoutSolucoesBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmAbertoFragment extends Fragment implements RecyclerViewInteface{

    // Declaração das variáveis
    private ArrayList<ProblemasObj> dataArrayList;
    List<AdminObj> AdminObjList;
    List<RelatorioObj> relatorioObjList;

    private SearchView searchView;
    private RecyclerView recyclerview;
    private Context context;
    private RecyclerViewInteface recyclerViewInteface;
    RVAdapterEmAberto recyclerViewAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layout_tela_admin_chamadas, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();
        recyclerViewInteface = this;

        searchView = view.findViewById(R.id.searchview);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        recyclerview = view.findViewById(R.id.recyclerviewadmin);
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setHasFixedSize(true);

        listen();
    }

        private void filterList(String text) {
            List<AdminObj> filteredList = new ArrayList<>();
            for (AdminObj adminObj: AdminObjList){
                if(adminObj.getNome().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(adminObj);
                }
            }

            if (filteredList.isEmpty()) {

            } else {
                recyclerViewAdapter.setFilteredList(filteredList);
            }


    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), Relatorio.class);

        // Definição de valores que serão redirecionados
        int idRelatorio = AdminObjList.get(position).getIdRelatorio();
        intent.putExtra("idRelatorio", idRelatorio);
        intent.putExtra("notnotlmao", 1);
        startActivity(intent);
    }

    private String sayMyChave(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String chave = sharedPreferences.getString("chave", "");

        return chave;
    }

    private void listen(){

        Call<List<AdminObj>> callme = RetroFitClient
                .getInstance()
                .getAPI()
                .getAllRelatoriosOpen();

        callme.enqueue(new Callback<List<AdminObj>>() {
            @Override
            public void onResponse(Call<List<AdminObj>> call, Response<List<AdminObj>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getContext(), "wassup", Toast.LENGTH_SHORT).show();
                    return;
                }

                //tentando guardar num objeto para que seja depois visivel no item_list_admin do dionisio
                AdminObjList = response.body();

                recyclerViewAdapter = new RVAdapterEmAberto(context,
                        AdminObjList, recyclerViewInteface, R.layout.item_list_admin);
                recyclerview.setAdapter(recyclerViewAdapter);
                recyclerViewAdapter.notifyDataSetChanged();

            }



            @Override
            public void onFailure(Call<List<AdminObj>> call, Throwable t) {

            }


        });


    }



}