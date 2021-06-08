package com.simplesoftware.calculadoradebebidasads.adapters;

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

    public RecyclerAdapter(ArrayList<String> listItens) {
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

        holder.rv_itemText.setText(opcaoLancada);

        if (listItens.get(position).toLowerCase().trim().contains("brahma")) {
            holder.rv_image.setImageResource(R.drawable.brahma_icon2);
        } else if (listItens.get(position).toLowerCase().trim().contains("skol")) {
            holder.rv_image.setImageResource(R.drawable.skol_icon2);
        } else if (listItens.get(position).toLowerCase().trim().contains("heineken")) {
            holder.rv_image.setImageResource(R.drawable.heineken_icon);
        } else if (listItens.get(position).toLowerCase().trim().contains("bohemia")) {
            holder.rv_image.setImageResource(R.drawable.bohemia_icon);
        } else if (listItens.get(position).toLowerCase().trim().contains("bud")) {
            holder.rv_image.setImageResource(R.drawable.budweiser_icon);
        } else if (listItens.get(position).toLowerCase().trim().contains("original")) {
            holder.rv_image.setImageResource(R.drawable.original_icon);
        } else if (listItens.get(position).toLowerCase().trim().contains("sub zero")) {
            holder.rv_image.setImageResource(R.drawable.subzero_icon);
        } else if (listItens.get(position).toLowerCase().trim().contains("itaipava")) {
            holder.rv_image.setImageResource(R.drawable.itaipava_icon);
        } else if (listItens.get(position).toLowerCase().trim().contains("amstel")) {
            holder.rv_image.setImageResource(R.drawable.amstel_icon);
        } else if (listItens.get(position).toLowerCase().trim().contains("devassa")) {
            holder.rv_image.setImageResource(R.drawable.devassa_icon);
        } else if (listItens.get(position).toLowerCase().trim().contains("sol")) {
            holder.rv_image.setImageResource(R.drawable.sol_icon);
        } else if (listItens.get(position).toLowerCase().trim().contains("kaiser")) {
            holder.rv_image.setImageResource(R.drawable.kaiser_icon);
        } else if (listItens.get(position).toLowerCase().trim().contains("duplo malte")) {
            holder.rv_image.setImageResource(R.drawable.duplomalte_icon);
        } else {
            holder.rv_image.setImageResource(R.drawable.icon_beer);
        }
    }

    @Override
    public int getItemCount() {
        return listItens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView rv_image;
        private final TextView rv_itemText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rv_image = itemView.findViewById(R.id.rv_image);
            rv_itemText = itemView.findViewById(R.id.rv_itemText);

        }
    }
}
