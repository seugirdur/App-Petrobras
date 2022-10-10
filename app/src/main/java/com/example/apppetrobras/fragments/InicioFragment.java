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
import com.example.apppetrobras.api.RetroFitClient;

import java.util.ArrayList;

public class InicioFragment extends Fragment implements RecyclerViewInteface{

    // Declaração das variáveis
    private ArrayList<ProblemasObj> dataArrayList;

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
                getString(R.string.internet_2),
                //lentidao
                getString(R.string.lentidao_3),
                //equipamento
                getString(R.string.equipamentos_1),
                //outros
                getString(R.string.outros_9),
                //outros
                getString(R.string.outros_7),
                //internet
                getString(R.string.internet_1),
                //equipamento
                getString(R.string.equipamentos_8)

        };

        int[] idProblemas = new int[]{
                1, 2, 3, 4, 5, 6, 7
        };

        int[] imagensProblemas = new int[]{
                R.drawable.internet,
                R.drawable.lentidao,
                R.drawable.equipamentos,
                R.drawable.outros,
                R.drawable.outros,
                R.drawable.internet,
                R.drawable.equipamentos

        };

        for(int i = 0; i < titulosProblemas.length; i++){
            ProblemasObj data = new ProblemasObj(titulosProblemas[i], idProblemas[i], imagensProblemas[i]);
            dataArrayList.add(data);
        }

    }

    @Override
    public void onItemClick(int position) {

        int idtype, posi_id;
        String titulo = "", posi_title="";
        //criação da string para armazenamento no banco
        // Esse valor é temporário. Ainda não foi definido o funcionamento da aba 1(Inicio)
        switch (position){
            case 0:
            default:
                titulo = "Internet";
                idtype = 2;
                posi_id = 2;
                posi_title = "A internet cai constantemente";
                break;
            case 1:
                 titulo = "Lentidão";
                 idtype = 1;
                 posi_id = 3;
                 posi_title = "Computador está muito lento";
                break;
            case 2:
                 titulo = "Equipamentos";
                 idtype = 3;
                posi_id = 1;
                posi_title = "Quando eu clico no botão de ligar do meu computador, ele faz barulho e fica toda hora reiniciando";
                break;
            case 3:
                 titulo = "Outros";
                idtype = 4;
                posi_id = 9;
                posi_title = "Como recuperar um arquivo que apaguei?";
                break;
            case 4:
                 titulo = "Outros";
                idtype = 4;
                posi_id = 7;
                posi_title = "O meu navegador diz que estou com problema de memória";
                break;
            case 5:
                titulo = "Internet";
                idtype = 2;
                posi_id = 1;
                posi_title = "Meu computador não consegue se conectar a rede";
                break;
            case 6:
                titulo = "Equipamentos";
                idtype = 3;
                posi_id = 8;
                posi_title = "Quando eu clico no botão de ligar do computador, não acontece nada";
                break;
        }


        // Redirecionamento para a tela do problema contendo os títulos das soluções
        Intent intent = new Intent(getActivity(), Solucoes.class);

        // Definição de valores que serão redirecionados
        // Valor do tipo é temporário. Ainda não foi definido o funcionamento da aba 1(Inicio)
        intent.putExtra("TIPO",idtype);
        intent.putExtra("titulo",titulo);
        intent.putExtra("ID_TITULO", posi_id );
        intent.putExtra("titulosProblemas", posi_title);
        startActivity(intent);
    }
}
