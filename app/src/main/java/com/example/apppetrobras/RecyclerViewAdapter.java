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

    private final int layout;

    Context context;
    ArrayList<DadosLista> dataArrayList;

    public RecyclerViewAdapter(Context context, ArrayList<DadosLista> dataArrayList,
                               RecyclerViewInteface recyclerViewInteface,
                               int layout) {
        this.context = context;
        this.dataArrayList = dataArrayList;
        this.recyclerViewInteface = recyclerViewInteface;
        this.layout = layout;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(layout,parent,false);

        int idTexto, idImage;

        switch(layout) {

            case  R.layout.item_soluction_list:
                idTexto = R.id.title_soluction;
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

        DadosLista data = dataArrayList.get(position);
        holder.textoLista.setText(data.text);
        if(holder.imagemLista!=null){
            holder.imagemLista.setImageResource(data.image);
        }

    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imagemLista;
        TextView textoLista;


        public MyViewHolder(@NonNull View itemView,RecyclerViewInteface recyclerViewInteface,
                            int idTexto) {
            super(itemView);

            textoLista = itemView.findViewById(idTexto);


            itemView.setOnClickListener(new View.OnClickListener() {

                //teste
                @Override
                public void onClick(View view) {
                    if(recyclerViewInteface != null){

                        // getAbsoluteAdapterPosition() leva em conta todas as listas
                        // getBidingAdapterPosition() só considera aquela em que eles está inserido

                        int pos = getBindingAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInteface.onItemClick(pos);
                        }

                    }
                }
            });
        }

        public MyViewHolder(@NonNull View itemView,RecyclerViewInteface recyclerViewInteface,
                            int idTexto, int idImage) {
            super(itemView);

            imagemLista = itemView.findViewById(idImage);
            textoLista = itemView.findViewById(idTexto);

            itemView.setOnClickListener(new View.OnClickListener() {

                //teste
                @Override
                public void onClick(View view) {
                    if(recyclerViewInteface != null){

                        // getAbsoluteAdapterPosition() leva em conta todas as listas
                        // getBidingAdapterPosition() só considera aquela em que eles está inserido

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
