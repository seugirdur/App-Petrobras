package com.example.apppetrobras.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apppetrobras.Objects.ProblemasObj;
import com.example.apppetrobras.Objects.SolucoesObj;
import com.example.apppetrobras.R;
import com.example.apppetrobras.fragments.RecyclerViewInteface;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    // Declaração das variáveis
    private final RecyclerViewInteface recyclerViewInteface;
    private final int layout;
    Context context;
    List<SolucoesObj> solucoesObjList;
    ArrayList<ProblemasObj> dataArrayList;

    // Sobrecarga de contrutor da classe para o objeto DadosLista
    public RecyclerViewAdapter(Context context, ArrayList<ProblemasObj> dataArrayList,
                               RecyclerViewInteface recyclerViewInteface,
                               int layout) {
        this.context = context;
        this.dataArrayList = dataArrayList;
        this.recyclerViewInteface = recyclerViewInteface;
        this.layout = layout;
    }

    // Sobrecarga de contrutor da classe para o objeto Problems
    public RecyclerViewAdapter(Context context, List<SolucoesObj> solucoesObjList,
                               RecyclerViewInteface recyclerViewInteface,
                               int layout) {
        this.context = context;
        this.solucoesObjList = solucoesObjList;
        this.recyclerViewInteface = recyclerViewInteface;
        this.layout = layout;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(layout, parent, false);

        // Instanciação das variáveis
        int idTexto, idImage, idProblemaID, idTituloID;

        // Estrutura de decisão de acordo com o layout(xml) da recyclerview,
        // a partir disso são atribuídos os id's que existem em cada item
        switch (layout) {
            case R.layout.cont_list:
                idTituloID = R.id.idTitulo;
                idProblemaID = R.id.idProblema;
                idTexto = R.id.textProblema;
                return new MyViewHolder(view, recyclerViewInteface, idTexto, idTituloID, idProblemaID);
            case R.layout.item_soluction_list:
                idTexto = R.id.title_soluction;
                return new MyViewHolder(view, recyclerViewInteface, idTexto);
            case R.layout.item_ajuda_list:
                idTexto = R.id.txt1;
                return new MyViewHolder(view, recyclerViewInteface, idTexto);
            case R.layout.item_list:
            default:
                idTexto = R.id.textProblema;
                idImage = R.id.imageProblema;
                return new MyViewHolder(view, recyclerViewInteface, idTexto, idImage);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Estrutura de decisão para cada objeto diferente
        // Em cada uma, há a passagem de valores do array para cada item da lista
        if (dataArrayList != null) {
            ProblemasObj data = dataArrayList.get(position);
            // Há a verificação de cada variável, caso ela tenha sido definida, ela passa o valor
            // Isso é necessário pois as listas recebem diferentes quantidades de valores
            if (holder.textView1 != null) {
                holder.textView1.setText(data.getText());
            }
            if (holder.imageView1 != null) {
                holder.imageView1.setImageResource(data.getImage());
            }
        } else if (solucoesObjList != null) {
            SolucoesObj data = solucoesObjList.get(position);
            // Há a verificação de cada variável, caso ela tenha sido definida, ela passa o valor
            // Isso é necessário pois as listas recebem diferentes quantidades de valores
            if (holder.textView1 != null) {
                holder.textView1.setText(data.getTituloSolucao());
            }
            if (holder.textView2 != null) {
                holder.textView2.setText(data.getIdTitulo());
            }
            if (holder.textView3 != null) {
                holder.textView3.setText(data.getIdProblema());
            }
        }
    }

    @Override
    public int getItemCount() {
        // Estrutura de decisão que retorna o tamanho da array
        // Isso é necessário pois existem listas de diferentes objetos
        if (dataArrayList != null) {
            return dataArrayList.size();
        } else {
            return solucoesObjList.size();
        }
    }

    // Classe dedicada a definir os valores dos itens (imagens, texos etc.) da RecyclerView
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        // Instanciação das variáveis
        ImageView imageView1;
        TextView textView1, textView2, textView3;


        // Sobrecarga de construtor para cada layout (xml) de RecyclerView:

        public MyViewHolder(@NonNull View itemView, RecyclerViewInteface recyclerViewInteface,
                            int idTexto) {
            super(itemView);

            // Definição de variáveis com os valores dos id's
            textView1 = itemView.findViewById(idTexto);

            // Define o método onItemClik da interface para cada item da RecyclerView
            moduleOnClick(recyclerViewInteface);
        }

        public MyViewHolder(@NonNull View itemView, RecyclerViewInteface recyclerViewInteface,
                            int idTexto, int idImage) {
            super(itemView);

            // Definição de variáveis com os valores dos id's
            textView1 = itemView.findViewById(idTexto);
            imageView1 = itemView.findViewById(idImage);

            // Define o método onItemClik da interface para cada item da RecyclerView
            moduleOnClick(recyclerViewInteface);
        }

        public MyViewHolder(@NonNull View itemView, RecyclerViewInteface recyclerViewInteface,
                            int idTexto, int idTituloID, int idProblemaID) {
            super(itemView);

            // Definição de variáveis com os valores dos id's
            textView1 = itemView.findViewById(idTexto);
            textView2 = itemView.findViewById(idTituloID);
            textView3 = itemView.findViewById(idProblemaID);

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
