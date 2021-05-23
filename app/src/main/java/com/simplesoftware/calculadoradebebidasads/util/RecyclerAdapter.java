package com.simplesoftware.calculadoradebebidasads.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.simplesoftware.calculadoradebebidasads.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<String> listItens;

    public RecyclerAdapter(ArrayList<String> listItens){
        this.listItens = listItens;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        String opcaoLancada = listItens.get(position);
        holder.rv_image.setImageResource(R.drawable.icon_beer);
        holder.rv_itemText.setText(opcaoLancada);
    }

    @Override
    public int getItemCount() {
        return listItens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView rv_image;
        private TextView rv_itemText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rv_image = itemView.findViewById(R.id.rv_image);
            rv_itemText = itemView.findViewById(R.id.rv_itemText);

        }
    }
}
