package com.example.apppetrobras;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apppetrobras.Objects.EtapasRelatorioObj;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder> {
    private List<EtapasRelatorioObj> listaRel;

    public adapter(List<EtapasRelatorioObj> lista){
        this.listaRel = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_relatorio, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        EtapasRelatorioObj passos = listaRel.get(position);

        holder.titulo.setText(passos.getTitulo());
        holder.subtitulo.setText(passos.getSubtitulo());
        holder.confirmado.setImageResource(passos.getImagem());
    }

    @Override
    public int getItemCount() {
        return listaRel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titulo;
        TextView subtitulo;
        ImageView confirmado;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.txtTitulo);
            subtitulo = itemView.findViewById(R.id.txtSubtitulo);
            confirmado = itemView.findViewById(R.id.imgConfirmado);
        }
    }
}
