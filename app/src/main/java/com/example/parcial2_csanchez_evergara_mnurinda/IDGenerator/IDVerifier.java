package com.example.parcial2_csanchez_evergara_mnurinda.IDGenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IDVerifier {
    //private static final String FILE_PATH = "eventIDs.txt"; // Ruta del archivo de texto

    public static boolean verificarID(int id, String FILE_PATH) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int existingID = Integer.parseInt(line);
                if (existingID == id) {
                    return true; // El ID ya existe en el archivo
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // El ID no está repetido
    }
}
