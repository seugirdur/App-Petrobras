package com.example.apppetrobras.Adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apppetrobras.Objects.ProblemasObj;
import com.example.apppetrobras.Objects.AdminObj;
import com.example.apppetrobras.R;
import com.example.apppetrobras.fragments.RecyclerViewInteface;

import java.util.List;

public class RVAdapterEmAberto extends RecyclerView.Adapter<RVAdapterEmAberto.MyViewHolder> {
    // Declaração das variáveis
    private final RecyclerViewInteface recyclerViewInteface;
    private final int layout;
    Context context;
    List<AdminObj> AdminObjList;

    public void setFilteredList(List<AdminObj> filteredList) {
        this.AdminObjList = filteredList;
        notifyDataSetChanged();
    }

    public RVAdapterEmAberto(Context context, List<AdminObj> AdminObjList,
                             RecyclerViewInteface recyclerViewInteface, int layout) {
        this.context = context;
        this.AdminObjList = AdminObjList;
        this.recyclerViewInteface = recyclerViewInteface;
        this.layout = layout;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layout, parent, false);

        // Instanciação das variáveis
        int idTexto1, idTexto2, idTexto3, idTexto4, idVisual;

        // Estrutura de decisão de acordo com o layout(xml) da recyclerview,
        // a partir disso são atribuídos os id's que existem em cada item
        idTexto1 = R.id.dataRelatorio;
        idTexto2 = R.id.textNome;
        idTexto3 = R.id.textEmail;
        idTexto4 = R.id.textSetor;
        idVisual = R.id.imageProblema;
        return new RVAdapterEmAberto.MyViewHolder(view, recyclerViewInteface,
                idTexto1, idTexto2, idTexto3, idTexto4, idVisual);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        AdminObj data = AdminObjList.get(position);
        // Há a verificação de cada variável, caso ela tenha sido definida, ela passa o valor
        // Isso é necessário pois as listas recebem diferentes quantidades de valores


        if (holder.text1 != null) {
            holder.text1.setText(data.getDataProcesso());
        }
        if (holder.text2 != null) {
            holder.text2.setText(data.getNome());
        }
        if (holder.text3 != null) {
            holder.text3.setText(data.getEmail());
        }
        if (holder.text4 != null) {
            holder.text4.setText(data.getSetor());
        }
        if (holder.visual1 != null) {
            if (data.getMade_check() != null) {
                if (data.getMade_check().contains("2")) {
                    holder.visual1.setImageResource(R.drawable.ic_check_circle);
                } else {
                    holder.visual1.setImageResource(R.drawable.ic_cancel_circle);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return AdminObjList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text1, text2, text3, text4;
        ImageView visual1;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInteface recyclerViewInteface,
                            int item1, int item2, int item3, int item4, int itemVisual) {
            super(itemView);

            // Definição de variáveis com os valores dos id's
            text1 = itemView.findViewById(item1);
            text2 = itemView.findViewById(item2);
            text3 = itemView.findViewById(item3);
            text4 = itemView.findViewById(item4);
            visual1 = itemView.findViewById(itemVisual);

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