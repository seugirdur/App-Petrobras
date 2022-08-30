package com.example.apppetrobras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    Context context;
    ArrayList<DadosLista> dataArrayList;

    public RecyclerViewAdapter(Context context, ArrayList<DadosLista> dataArrayList) {
        this.context = context;
        this.dataArrayList = dataArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        DadosLista data = dataArrayList.get(position);
        holder.textoLista.setText(data.text);
        holder.imagemLista.setImageResource(data.image);
    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imagemLista;
        TextView textoLista;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imagemLista = itemView.findViewById(R.id.imageProblema);
            textoLista = itemView.findViewById(R.id.textProblema);
        }
    }
}
