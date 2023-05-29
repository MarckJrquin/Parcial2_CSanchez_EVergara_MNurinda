package com.example.parcial2_csanchez_evergara_mnurinda.Admin;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcial2_csanchez_evergara_mnurinda.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AsistenciaListAdapter extends RecyclerView.Adapter<AsistenciaListAdapter.ViewHolder> {

    List<String[]> data;
    List<String[]> users;
    List<String[]> events;
    int eventId;

    public AsistenciaListAdapter(List<String[]> data, List<String[]> users, List<String[]> events,  int eventId) {
        this.data = data;
        this.users = users;
        this.events = events;
        this.eventId = eventId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_asistencia, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String[] itemData = data.get(position);
        String fieldValue = itemData[0];

        if (fieldValue.equals(String.valueOf(eventId))) {

            for (String[] user : users) {
                // Recorrer los elementos del arreglo con un bucle foreach
                if(itemData[1].equals(user[0])){
                    holder.textViewField1.setText(user[1]);
                    holder.textViewField2.setText(user[4]);
                }
            }
            holder.itemView.setVisibility(View.VISIBLE);
        } else {
            holder.itemView.setVisibility(View.INVISIBLE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewField1;
        TextView textViewField2;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewField1 = itemView.findViewById(R.id.textViewField1);
            textViewField2 = itemView.findViewById(R.id.textViewField2);
        }
    }
}
