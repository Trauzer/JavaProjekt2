package wit.projekt.Database;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Klasa Database odpowiada za zarządzanie bazą danych w formie plików
 * tekstowych.
 */

public class Database {
    private static HashMap<String, List<String>> tables = new HashMap<>();

    /**
     * Konstruktor klasy Database. Inicjuje listy dla poszczególnych tabel i
     * wczytuje dane z plików.
     */

    public Database() {
        tables.put("students", new ArrayList<>());
        tables.put("subjects", new ArrayList<>());
        tables.put("groups", new ArrayList<>());

        loadFile();
    }

    /**
     * Metoda pobierająca dane z określonej tabeli.
     * 
     * @param tableName Nazwa tabeli
     * @return Lista danych z tabeli
     */

    public List<String> get(String tableName) {
        List<String> data = new ArrayList<>();

        if (tables.containsKey(tableName)) {
            data = tables.get(tableName);
        }

        return data;
    }

    /**
     * Metoda statyczna zapisująca dane do określonej tabeli.
     * 
     * @param tableName Nazwa tabeli
     * @param data      Lista danych do zapisu
     */

    public static void save(String tableName, List<String> data) {
        tables.put(tableName, data);
    }

    /**
     * Metoda wczytująca dane z plików do odpowiednich tabel.
     */

    private void loadFile() {
        for (String tableName : tables.keySet()) {
            String path = "src/main/java/resources/" + tableName + ".txt";

            if (Files.notExists(Paths.get(path))) {
                System.out.println("File not found: " + path);
            } else {
                try (DataInputStream dis = new DataInputStream(new FileInputStream(path))) {
                    while (dis.available() > 0) {
                        System.out.println("Reading from file: " + path);
                        tables.get(tableName).add(dis.readUTF());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Metoda zapisująca dane z tabel do plików tekstowych.
     */

    public void saveFile() {
        for (String tableName : tables.keySet()) {
            String path = "src/main/java/resources/" + tableName + ".txt";

            if (Files.notExists(Paths.get(path))) {
                try {
                    System.out.println("Creating file: " + path);
                    Files.createFile(Paths.get(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(path))) {
                for (String line : tables.get(tableName)) {
                    dos.writeUTF(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
