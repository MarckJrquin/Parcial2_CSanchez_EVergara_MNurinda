package com.example.parcial2_csanchez_evergara_mnurinda;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.parcial2_csanchez_evergara_mnurinda.Models.Event;
import com.example.parcial2_csanchez_evergara_mnurinda.Models.Users;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class VerListaDeEventosActivity extends AppCompatActivity {

    ListView lstEvents;

    EventAdapter adapter;

    private List<Event> eventList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_lista_de_eventos);
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
            return R.drawable.chillout;
        } else if (Integer.parseInt(imageCode) == 2) {
            return R.drawable.ic_launcher_background;
        } else if (Integer.parseInt(imageCode) == 3){
            return R.drawable.d5to5;
        }
        return 0;
    }
}