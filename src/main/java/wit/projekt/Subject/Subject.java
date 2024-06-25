package wit.projekt.Subject;

import java.util.ArrayList;

/**
 * Klasa Subject reprezentująca przedmiot.
 */

public class Subject {
    private String code;
    private String name;

    /**
     * Konstruktor klasy Subject.
     *
     * @param code Kod przedmiotu
     * @param name Nazwa przedmiotu
     */

    public Subject(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * Metoda zwracająca kod przedmiotu.
     *
     * @return Kod przedmiotu
     */

    public String getCode() {
        return code;
    }

    /**
     * Metoda ustawiająca kod przedmiotu.
     *
     * @param code Nowy kod przedmiotu
     */

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Metoda zwracająca nazwę przedmiotu.
     *
     * @return Nazwa przedmiotu
     */

    public String getName() {
        return name;
    }

    /**
     * Metoda zwracająca nazwę przedmiotu.
     *
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Metoda zwracająca pola obiektu jako listę.
     *
     * @return Lista pól obiektu
     */

    public ArrayList<Object> getFields() {
        ArrayList<Object> fields = new ArrayList<>();
        fields.add(code);
        fields.add(name);
        return fields;
    }
}
