package com.example.apppetrobras.fragments;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.TextView;

import com.example.apppetrobras.Activities.Perfil;
import com.example.apppetrobras.Activities.PerfilAtualizar;
import com.example.apppetrobras.Objects.ProblemasObj;
import com.example.apppetrobras.Activities.Solucoes;
import com.example.apppetrobras.R;
import com.example.apppetrobras.Adapters.RecyclerViewAdapter;

import java.util.ArrayList;

public class Ajuda_fragment extends Fragment {
    Button btn;
//    SharedPreferences sharedPreferences = getSharedPreferences(
//            getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    // private ArrayList<ProblemasObj> dataArrayList1, dataArrayList2;
    TextView nomecompleto,num_tel,email1,num_chave, nome1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //LayoutInflater lf = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_ajuda_fragment, container, false);

        btn = view.findViewById(R.id.btn_tela_perfil);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), PerfilAtualizar.class);
                startActivity(i);
            }
        });


//        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(
//                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
//
//        String nome = sharedPreferences.getString("nome", "");
//        String tel = sharedPreferences.getString("tel", "");
//        String email = sharedPreferences.getString("email", "");
//        String chave = sharedPreferences.getString("chave", "");
//
//
//        String[] fullNameArray = nome.split("\\s+");
//        String firstName = fullNameArray[0];
//        nome1 = this.getActivity().findViewById(R.id.nome_login);
//        nome1.setText(firstName);
//
//        nomecompleto = this.getActivity().findViewById(R.id.nomecompleto);
//        nomecompleto.setText(nome);
//
//        num_tel = this.getActivity().findViewById(R.id.num_tel);
//        num_tel.setText(tel);
//
//        email1 = this.getActivity().findViewById(R.id.email);
//        email1.setText(email);
//
//        num_chave = this.getActivity().findViewById(R.id.num_chave);
//        num_chave.setText(chave);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);





//        dataInitialize1();
//
//        // Essa variável recebe (por meio do id) a reciclerView no xml dessa tela
//        RecyclerView recyclerview1 = view.findViewById(R.id.recyclerview1);
//        recyclerview1.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerview1.setHasFixedSize(true);
//        // Aqui há uma instância da RecyclerViewAdapter utilizando o construtor adequado:
//        // RecyclerViewAdapter(Context, Lista<Objeto>, RecyclerViewInterface, layout do item)
//        RecyclerViewAdapter recyclerViewAdapter1 = new RecyclerViewAdapter(getContext(),
//                dataArrayList1, this, R.layout.item_ajuda_list);
//        recyclerview1.setAdapter(recyclerViewAdapter1);
//
//        dataInitialize2();
//
//        // Essa variável recebe (por meio do id) a reciclerView no xml dessa tela
//        RecyclerView recyclerview2 = view.findViewById(R.id.recyclerview2);
//        recyclerview2.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerview2.setHasFixedSize(true);
//        // Aqui há uma instância da RecyclerViewAdapter utilizando o construtor adequado:
//        // RecyclerViewAdapter(Context, Lista<Objeto>, RecyclerViewInterface, layout do item)
//        RecyclerViewAdapter recyclerViewAdapter2 = new RecyclerViewAdapter(getContext(),
//                dataArrayList2, this, R.layout.item_ajuda_list);
//        recyclerview2.setAdapter(recyclerViewAdapter2);

        //recyclerViewAdapter.notifyDataSetChanged();
    }

    // Função para popular a lista usada na recyclerView
//    private void dataInitialize1() {
//
//        dataArrayList1 = new ArrayList<>();
//
//        String[] titulosProblemas = new String[]{
//                "Como chamar um técnico?",
//                "Como buscar a solução?",
//                "Como ver meus chamados?",
//                "Como usar o aplicativo?"
//        };
//
//        for(int i = 0; i < titulosProblemas.length; i++){
//            ProblemasObj data = new ProblemasObj(titulosProblemas[i]);
//            dataArrayList1.add(data);
//        }
//    }
//
//    private void dataInitialize2() {
//
//        dataArrayList2 = new ArrayList<>();
//
//        String[] titulosProblemas = new String[]{
//                "Como funciona a tela de escolha?",
//                "Como funciona a tela de chamado?"
//        };
//
//        for(int i = 0; i < titulosProblemas.length; i++){
//            ProblemasObj data = new ProblemasObj(titulosProblemas[i]);
//            dataArrayList2.add(data);
//        }
//    }
//
//    @Override
//    public void onItemClick(int position) {
//        // Redirecionamento para a tela do problema contendo os títulos das soluções
//        Intent intent = new Intent(getActivity(), Solucoes.class);
//
//        // Definição de valores que serão redirecionados
//        intent.putExtra("TIPO",3);
//        intent.putExtra("ID_TITULO", dataArrayList1.get(position).getId());
//        startActivity(intent);
//    }

}