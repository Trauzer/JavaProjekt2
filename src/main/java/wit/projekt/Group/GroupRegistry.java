package wit.projekt.Group;

import wit.projekt.Database.Database;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa rejestru grup.
 */
public class GroupRegistry {
    private List<Group> groups = new ArrayList<>(); // Lista przechowująca grupy

    /**
     * Konstruktor klasy GroupRegistry.
     * Tworzy listę grup na podstawie dostarczonych danych.
     * 
     * @param data Lista danych w formacie: [kod grupy, specjalizacja, opis, ...]
     */
    public GroupRegistry(List<String> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i += 3) {
                groups.add(new Group(data.get(i), data.get(i + 1), data.get(i + 2)));
            }
        }
        // Przykładowe dodanie grup na potrzeby testowania
        // groups.add(new Group("G01", "Computer Science", "CS Group"));
        // groups.add(new Group("G02", "Mathematics", "Math Group"));
    }

    /**
     * Metoda zwracająca listę wszystkich grup.
     * 
     * @return Lista wszystkich grup
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * Metoda dodająca nową grupę do rejestru.
     * 
     * @param group Nowa grupa do dodania
     */
    public void addGroup(Group group) {
        groups.add(group);
    }

    /**
     * Metoda edytująca istniejącą grupę na podstawie kodu grupy.
     * 
     * @param groupCode Kod grupy do edycji
     * @param newGroup  Nowa zawartość grupy
     * @return Edytowana grupa lub null, jeżeli grupa nie została znaleziona
     */
    public Group editGroup(String groupCode, Group newGroup) {
        for (Group group : groups) {
            if (group.getGroupCode().equals(groupCode)) {
                deleteGroup(groupCode);
                groups.add(newGroup);
                return group;
            }
        }
        return null;
    }

    /**
     * Metoda usuwająca grupę na podstawie kodu grupy.
     * 
     * @param groupCode Kod grupy do usunięcia
     */
    public void deleteGroup(String groupCode) {
        groups.removeIf(group -> group.getGroupCode().equals(groupCode));
    }

    /**
     * Metoda wyszukująca grupę po kodzie grupy.
     * 
     * @param groupCode Kod grupy do wyszukania
     * @return Znaleziona grupa lub null, jeżeli grupa nie została znaleziona
     */
    public Group getGroupByCode(String groupCode) {
        for (Group group : groups) {
            if (group.getGroupCode().equals(groupCode)) {
                return group;
            }
        }
        return null;
    }

    /**
     * Metoda zapisująca dane grup do bazy danych.
     * Zapisuje kod grupy, specjalizację i opis każdej grupy z listy do bazy danych.
     */
    public void saveDataToDB() {
        List<String> data = new ArrayList<>();
        for (Group group : groups) {
            data.add(group.getGroupCode());
            data.add(group.getSpecialization());
            data.add(group.getDescription());
        }
        Database.save("groups", data);
    }
}
