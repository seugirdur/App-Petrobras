package com.example.apppetrobras.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apppetrobras.Objects.ProblemasObj;
import com.example.apppetrobras.Objects.SolucoesObj;
import com.example.apppetrobras.R;
import com.example.apppetrobras.fragments.RecyclerViewInteface;

import java.util.ArrayList;
import java.util.List;

public class RVAdapterSolucoes extends RecyclerView.Adapter<RVAdapterSolucoes.MyViewHolder> {

    // Declaração das variáveis
    private final RecyclerViewInteface recyclerViewInteface;
    private final int layout;
    Context context;
    List<SolucoesObj> solucoesObjList;
    String check;

    // Sobrecarga de contrutor da classe para o objeto Problems
    public RVAdapterSolucoes(Context context, List<SolucoesObj> solucoesObjList,
                             RecyclerViewInteface recyclerViewInteface,
                             int layout, String check) {
        this.context = context;
        this.solucoesObjList = solucoesObjList;
        this.recyclerViewInteface = recyclerViewInteface;
        this.layout = layout;
        this.check = check;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(layout, parent, false);

        // Instanciação das variáveis
        int idText, idVisual;

        // Estrutura de decisão de acordo com o layout(xml) da recyclerview,
        // a partir disso são atribuídos os id's que existem em cada item
        idText = R.id.title_soluction;
        idVisual = R.id.container;
        return new MyViewHolder(view, recyclerViewInteface, idText, idVisual);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SolucoesObj data = solucoesObjList.get(position);

        holder.textView1.setText(data.getTituloSolucao());

        int cor = ContextCompat.getColor(context, R.color.white);
        // Verifica se a solução já foi vizualida e então altera a cor desse item da recyclerView
        if (check.charAt(position) != '0'){
            cor = ContextCompat.getColor(context, R.color.Tomate);
        }
        holder.container.setBackgroundColor(cor);
    }

    @Override
    public int getItemCount() {
        return solucoesObjList.size();
    }

    // Classe dedicada a definir os valores dos itens (imagens, texos etc.) da RecyclerView
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        // Instanciação das variáveis
        ImageView imageView1;
        ConstraintLayout container;
        TextView textView1;


        // Sobrecarga de construtor para cada layout (xml) de RecyclerView:

        public MyViewHolder(@NonNull View itemView, RecyclerViewInteface recyclerViewInteface,
                            int id1, int id2) {
            super(itemView);

            // Definição de variáveis com os valores dos id's
            textView1 = itemView.findViewById(id1);
            container = itemView.findViewById(id2);

            // Define o método onItemClik da interface para cada item da RecyclerView
            moduleOnClick(recyclerViewInteface);
        }


        // Módulo que define o método onItemClik da interface para cada item da RecyclerView
        private void moduleOnClick(RecyclerViewInteface recyclerViewInteface) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInteface != null) {
                        int pos = getBindingAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInteface.onItemClick(pos);
                        }
                    }
                }
            });
        }


    }
}

