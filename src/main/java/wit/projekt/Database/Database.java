package wit.projekt.Database;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.*;

/**
 * Klasa Database odpowiada za zarządzanie bazą danych w formie plików
 * tekstowych.
 */
public class Database {
    private static final HashMap<String, List<String>> tables = new HashMap<>();

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
        return tables.getOrDefault(tableName, new ArrayList<>());
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
        String currentDirectory = System.getProperty("user.dir");

        for (String tableName : tables.keySet()) {
            String path = currentDirectory + "/" + tableName + ".txt";

            if (Files.notExists(Paths.get(path))) {
                System.out.println("File not found: " + path);
            } else {
                try (DataInputStream dis = new DataInputStream(new FileInputStream(path))) {
                    int numberOfEntries = dis.readInt();
                    List<String> data = new ArrayList<>();
                    for (int i = 0; i < numberOfEntries; i++) {
                        data.add(dis.readUTF());
                    }
                    tables.put(tableName, data);
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
        String currentDirectory = System.getProperty("user.dir");

        for (String tableName : tables.keySet()) {
            try {
                File file = new File(currentDirectory, tableName + ".txt");

                if (!file.exists()) {
                    System.out.println("Creating file: " + file.getPath());
                    file.createNewFile();
                }

                try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
                    List<String> data = tables.get(tableName);
                    dos.writeInt(data.size());
                    for (String line : data) {
                        dos.writeUTF(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
