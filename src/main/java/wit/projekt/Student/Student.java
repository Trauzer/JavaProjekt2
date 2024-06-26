package wit.projekt.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import wit.projekt.Group.Group;

/**
 * Klasa reprezentująca studenta.
 */
public class Student {
    private String name; // Imię studenta
    private String surname; // Nazwisko studenta
    private String albumNumber; // Numer albumu studenta
    private Group group; // Grupa studenta
    private final Map<String, Integer> subjectGrades; // Mapa ocen studenta z korespondującymi kodami przedmiotów

    /**
     * Konstruktor klasy Student.
     *
     * @param name        Imię studenta
     * @param surname     Nazwisko studenta
     * @param albumNumber Numer albumu studenta
     */
    public Student(String name, String surname, String albumNumber) {
        this.name = name;
        this.surname = surname;
        this.albumNumber = albumNumber;
        this.group = null; // Domyślnie student nie należy do żadnej grupy
        this.subjectGrades = new HashMap<>();
    }

    /**
     * Metoda zwracająca imię studenta.
     *
     * @return Imię studenta
     */
    public String getName() {
        return name;
    }

    /**
     * Metoda ustawiająca imię studenta.
     *
     * @param name Nowe imię studenta
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Metoda zwracająca nazwisko studenta.
     *
     * @return Nazwisko studenta
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Metoda ustawiająca nazwisko studenta.
     *
     * @param surname Nowe nazwisko studenta
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Metoda ustawiająca numer albumu studenta.
     *
     * @param albumNumber Nowy numer albumu studenta
     */
    public void setAlbumNumber(String albumNumber) {
        this.albumNumber = albumNumber;
    }

    /**
     * Metoda zwracająca numer albumu studenta.
     *
     * @return Numer albumu studenta
     */
    public String getAlbumNumber() {
        return albumNumber;
    }

    /**
     * Metoda zwracająca grupę studenta.
     *
     * @return Grupa studenta
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Metoda ustawiająca grupę, do której należy student.
     * 
     * @param group Nowa grupa studenta
     */
    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * Metoda zwracająca kod grupy, do której należy student.
     * Jeżeli student nie należy do żadnej grupy, zwracany jest tekst "Brak grupy".
     * 
     * @return Kod grupy lub "Brak grupy"
     */
    public String getGroupCode() {
        return group != null ? group.getGroupCode() : "Brak grupy";
    }

    /**
     * Metoda dodająca ocenę dla danego przedmiotu.
     * 
     * @param subjectCode Kod przedmiotu
     * @param grade       Ocena do dodania
     */
    public void addGrade(String subjectCode, int grade) {
        subjectGrades.put(subjectCode, grade);
    }

    /**
     * Metoda zwracająca ocenę studenta dla danego przedmiotu.
     * 
     * @param subjectCode Kod przedmiotu, dla którego ma zostać zwrócona ocena.
     * @return Ocenę studenta dla podanego kodu przedmiotu lub null, jeśli ocena nie
     *         istnieje.
     */
    public Integer getGrade(String subjectCode) {
        return subjectGrades.get(subjectCode);
    }

    /**
     * Metoda zwracająca wszystkie oceny studenta.
     * 
     * @return Mapa zawierająca kod przedmiotu jako klucz i ocenę jako wartość.
     */
    public Map<String, Integer> getAllGrades() {
        return subjectGrades;
    }

    /**
     * Metoda zwracająca listę pól obiektu studenta (imię, nazwisko, numer albumu,
     * kod grupy).
     * 
     * @return Lista pól obiektu studenta
     */
    public ArrayList<Object> getFields() {
        ArrayList<Object> fields = new ArrayList<>();
        fields.add(name);
        fields.add(surname);
        fields.add(albumNumber);
        fields.add(getGroupCode());
        return fields;
    }
}
