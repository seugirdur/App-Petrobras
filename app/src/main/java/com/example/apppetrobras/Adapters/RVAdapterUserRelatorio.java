package com.example.apppetrobras.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apppetrobras.Objects.UserRelatorioObj;
import com.example.apppetrobras.Objects.UserRelatorioObj;
import com.example.apppetrobras.R;
import com.example.apppetrobras.fragments.RecyclerViewInteface;

import java.util.List;

public class RVAdapterUserRelatorio extends RecyclerView.Adapter<RVAdapterUserRelatorio.MyViewHolder>{
    // Declaração das variáveis
    private final RecyclerViewInteface recyclerViewInteface;
    private final int layout;
    Context context;
    List<UserRelatorioObj> UserRelatorioObjList;

    public RVAdapterUserRelatorio(Context context, List<UserRelatorioObj> UserRelatorioObjList,
                                  RecyclerViewInteface recyclerViewInteface, int layout){
        this.context = context;
        this.UserRelatorioObjList = UserRelatorioObjList;
        this.recyclerViewInteface = recyclerViewInteface;
        this.layout = layout;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layout,parent,false);

        // Instanciação das variáveis
        int text1, visual1;

        // Estrutura de decisão de acordo com o layout(xml) da recyclerview,
        // a partir disso são atribuídos os id's que existem em cada item
        text1 = R.id.txtdata;
        visual1 = R.id.incentivoVisual;
        return new RVAdapterUserRelatorio.MyViewHolder(view, recyclerViewInteface,
                text1, visual1);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        UserRelatorioObj data = UserRelatorioObjList.get(position);
        // Há a verificação de cada variável, caso ela tenha sido definida, ela passa o valor
        // Isso é necessário pois as listas recebem diferentes quantidades de valores


        if(holder.text1 != null){ holder.text1.setText(data.getDataProcesso());}
        if(holder.text1 != null){ holder.visual1.setImageResource(R.drawable.ic_add);}
    }

    @Override
    public int getItemCount() {
        return UserRelatorioObjList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView text1;
        ImageView visual1;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInteface recyclerViewInteface,
                            int item1, int item2) {
            super(itemView);

            // Definição de variáveis com os valores dos id's
            text1 = itemView.findViewById(item1);
            visual1 = itemView.findViewById(item2);

            // Define o método onItemClik da interface para cada item da RecyclerView
            moduleOnClick(recyclerViewInteface);
        }

        // Módulo que define o método onItemClik da interface para cada item da RecyclerView
        private void moduleOnClick(RecyclerViewInteface recyclerViewInteface){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInteface != null){
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