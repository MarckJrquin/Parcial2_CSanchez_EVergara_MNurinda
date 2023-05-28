package com.example.parcial2_csanchez_evergara_mnurinda.IDGenerator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    //private static final String FILE_PATH = "eventIDs.txt"; // Ruta del archivo de texto

    public static void guardarID(int id, String FILE_PATH) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(String.valueOf(id));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
