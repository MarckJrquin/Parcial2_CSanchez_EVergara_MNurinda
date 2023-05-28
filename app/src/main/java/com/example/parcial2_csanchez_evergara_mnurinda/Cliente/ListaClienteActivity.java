package com.example.parcial2_csanchez_evergara_mnurinda.Cliente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.parcial2_csanchez_evergara_mnurinda.Admin.EventAdapter;
import com.example.parcial2_csanchez_evergara_mnurinda.Models.Event;
import com.example.parcial2_csanchez_evergara_mnurinda.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ListaClienteActivity extends AppCompatActivity {

    ListView lstEvents;

    EventAdapter adapter;

    private List<Event> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cliente);

        this.initControllers();
        this.DataMapping();
    }

    private void initControllers() {
        lstEvents = (ListView)findViewById(R.id.lstEvents);
    }

    private void DataMapping() {

        adapter = new EventAdapter(getApplicationContext(), FileToList());
        lstEvents.setAdapter(adapter);
    }

    private void Notify(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private List<Event> FileToList() {
        List<Event> events = new ArrayList<Event>();

        try {
            BufferedReader fin = new BufferedReader( new InputStreamReader( openFileInput("listaDeEventos.txt") ));
            String data = fin.readLine();

            String[] arrEvents = data.split("~");

            for( String strEvent : arrEvents ){

                String[] userFields = strEvent.split("\\|");

                Event event = new Event(
                        Integer.parseInt(userFields[0]),
                        imageCode(userFields[0]),
                        userFields[1],
                        userFields[2],
                        userFields[3],
                        userFields[4]
                );

                events.add(event);
            }

        }catch (Exception e) {
            this.Notify("Error => " + e.getMessage());
        }

        return events;
    }

    public int imageCode(String imageCode) {
        if(Integer.parseInt(imageCode) == 1){
            return R.drawable.d5to5;
        } else if (Integer.parseInt(imageCode) == 2) {
            return R.drawable.casajaguar_logo;
        } else if (Integer.parseInt(imageCode) == 3){
            return R.drawable.teatro_amador;
        }
        return 0;
    }
}