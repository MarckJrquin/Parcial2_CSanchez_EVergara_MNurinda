package com.example.parcial2_csanchez_evergara_mnurinda.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.parcial2_csanchez_evergara_mnurinda.R;

public class VerAsistenciaActivity extends AppCompatActivity {

    TextView txtEventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_asistencia);
        
        this.initController();
        this.DataMapping();
    }

    private void initController() {
        txtEventId = (TextView) findViewById(R.id.txtEventID);
    }

    public void DataMapping(){
        // Obtener el ID pasado de la actividad anterior
        int eventId = getIntent().getIntExtra("eventId", 0);

        // Mostrar el ID en el TextView
        txtEventId.setText(String.valueOf(eventId));
    }
}