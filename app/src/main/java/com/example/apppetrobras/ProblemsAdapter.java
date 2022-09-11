package com.example.apppetrobras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProblemsAdapter extends RecyclerView.Adapter<ProblemsAdapter.ProblemsViewHolder>{


    List<Problems> problemsList;
    Context context;

    public ProblemsAdapter(Context context, List<Problems> problems) {
        this.context = context;
        problemsList = problems;
    }
    @Override
    public ProblemsViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cont_list, parent, false);
        return new ProblemsViewHolder(view);

    }

    @Override
    public void onBindViewHolder( ProblemsViewHolder holder, int position) {

        Problems problems = problemsList.get(position);
        holder.idTitulo_tv.setText(problems.getIdTitulo());
        holder.idProblema_tv.setText(problems.getIdProblema());
        holder.tituloSolucao_tv.setText(problems.getTituloSolucao());


    }

    @Override
    public int getItemCount() {
        return problemsList.size();
    }

    public class ProblemsViewHolder extends RecyclerView.ViewHolder{
        TextView idTitulo_tv, idProblema_tv, tituloSolucao_tv;

        public ProblemsViewHolder(@NonNull View itemView) {
            super(itemView);

            idTitulo_tv = itemView.findViewById(R.id.idTitulo_tv);
            idProblema_tv = itemView.findViewById(R.id.idProblema_tv);
            tituloSolucao_tv = itemView.findViewById(R.id.tituloSolucao_tv);

        }
    }
}
