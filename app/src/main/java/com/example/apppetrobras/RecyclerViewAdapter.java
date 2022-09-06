package com.example.apppetrobras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apppetrobras.fragments.RecyclerViewInteface;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private final RecyclerViewInteface recyclerViewInteface;

    Context context;
    ArrayList<DadosLista> dataArrayList;

    public RecyclerViewAdapter(Context context, ArrayList<DadosLista> dataArrayList,
                               RecyclerViewInteface recyclerViewInteface) {
        this.context = context;
        this.dataArrayList = dataArrayList;
        this.recyclerViewInteface = recyclerViewInteface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);

        return new MyViewHolder(view, recyclerViewInteface);
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

        public MyViewHolder(@NonNull View itemView,RecyclerViewInteface recyclerViewInteface) {
            super(itemView);

            imagemLista = itemView.findViewById(R.id.imageProblema);
            textoLista = itemView.findViewById(R.id.textProblema);

            itemView.setOnClickListener(new View.OnClickListener() {

                //teste
                @Override
                public void onClick(View view) {
                    if(recyclerViewInteface != null){

                        // getAbsoluteAdapterPosition() leva em conta todas as listas
                        // getBidingAdapterPosition() só considera aquela em que eles está inserido

                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInteface.onItemClick(pos);
                        }

                    }
                }
            });
        }
    }

}
