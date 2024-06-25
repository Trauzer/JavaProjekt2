package wit.projekt.Group;

import wit.projekt.Student.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentująca grupę.
 */

public class Group {
    private String groupCode; // Kod grupy
    private String specialization; // Specjalizacja grupy
    private String description; // Opis grupy
    private List<Student> students; // Lista studentów w grupie

    /**
     * Konstruktor klasy Group.
     * 
     * @param groupCode      Kod grupy
     * @param specialization Specjalizacja grupy
     * @param description    Opis grupy
     */

    public Group(String groupCode, String specialization, String description) {
        this.groupCode = groupCode;
        this.specialization = specialization;
        this.description = description;
        this.students = new ArrayList<>();
    }

    /**
     * Metoda zwracająca kod grupy.
     * 
     * @return Kod grupy
     */

    public String getGroupCode() {
        return groupCode;
    }

    /**
     * Metoda ustawiająca kod grupy.
     * 
     * @param groupCode Nowy kod grupy
     */

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    /**
     * Metoda zwracająca specjalizację grupy.
     * 
     * @return Specjalizacja grupy
     */

    public String getSpecialization() {
        return specialization;
    }

    /**
     * Metoda ustawiająca specjalizację grupy.
     * 
     * @param specialization Nowa specjalizacja grupy
     */

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    /**
     * Metoda zwracająca opis grupy.
     * 
     * @return Opis grupy
     */

    public String getDescription() {
        return description;
    }

    /**
     * Metoda ustawiająca opis grupy.
     * 
     * @param description Nowy opis grupy
     */

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Metoda zwracająca listę studentów w grupie.
     * 
     * @return Lista studentów w grupie
     */

    public List<Student> getStudents() {
        return students;
    }

    /**
     * Metoda dodająca studenta do grupy, jeśli nie jest już w niej zawarty.
     * Ustawia również przynależność studenta do tej grupy.
     * 
     * @param student Student do dodania
     */

    public void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
            student.setGroup(this);
        }
    }

    /**
     * Metoda usuwająca studenta z grupy, jeśli jest w niej zawarty.
     * Usuwa również przynależność studenta do tej grupy.
     * 
     * @param student Student do usunięcia
     */

    public void removeStudent(Student student) {
        if (students.contains(student)) {
            students.remove(student);
            student.setGroup(null);
        }
    }

    /**
     * Metoda zwracająca listę pól tej grupy (kod, specjalizacja, opis).
     * 
     * @return Lista pól grupy
     */

    public ArrayList<Object> getFields() {
        ArrayList<Object> fields = new ArrayList<>();
        fields.add(groupCode);
        fields.add(specialization);
        fields.add(description);
        return fields;
    }
}
