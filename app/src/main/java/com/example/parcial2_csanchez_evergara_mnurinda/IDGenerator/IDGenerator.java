package com.example.parcial2_csanchez_evergara_mnurinda.IDGenerator;

import java.util.Random;

public class IDGenerator {
    public static int generarID() {
        Random random = new Random();
        int id = random.nextInt(90000) + 10000; // Genera un número aleatorio de 5 cifras (entre 10000 y 99999)
        return id;
    }
}
