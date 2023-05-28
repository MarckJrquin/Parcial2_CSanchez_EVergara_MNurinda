package com.example.parcial2_csanchez_evergara_mnurinda.Cliente;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.parcial2_csanchez_evergara_mnurinda.IDGenerator.FileManager;
import com.example.parcial2_csanchez_evergara_mnurinda.Models.Event;
import com.example.parcial2_csanchez_evergara_mnurinda.Models.Users;
import com.example.parcial2_csanchez_evergara_mnurinda.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

public class ListaClienteAdapter extends ArrayAdapter<Event> {
    String userID;
    List<Event> eventList;

    public ListaClienteAdapter(Context context, List<Event> events, String userID){
        super(context, R.layout.listview_client_events, events);
        eventList = events;
        this.userID = userID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.listview_client_events, null);

        ImageView image = (ImageView) item.findViewById(R.id.imageViewEventPhoto);
        image.setImageResource(eventList.get(position).getImage());

        TextView eventName = (TextView) item.findViewById(R.id.textViewNameEvent);
        eventName.setText(eventList.get(position).getName());

        TextView eventDescription = (TextView) item.findViewById(R.id.textViewDescEvent);
        eventDescription.setText(eventList.get(position).getDescription());

        TextView eventPlace = (TextView) item.findViewById(R.id.textViewPlaceEvent);
        eventPlace.setText(eventList.get(position).getPlace());

        TextView eventContact = (TextView) item.findViewById(R.id.textViewContactEvent);
        eventContact.setText(eventList.get(position).getContact());

        return item;
    }
}
