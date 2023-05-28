package com.example.parcial2_csanchez_evergara_mnurinda.IDGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    public static void guardarID(int id, String FILE_PATH) {
        try{

            File file = new File(FILE_PATH);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true));
            writer.write(String.valueOf(id));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void guardarAsistencia(int eventId, String userId, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(eventId + "," + userId);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
