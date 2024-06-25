package wit.projekt.Student;

import wit.projekt.Database.Database;
import wit.projekt.Group.Group;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa StudentRegistry przechowująca rejestr studentów oraz umożliwiająca
 * operacje na nich.
 */
public class StudentRegistry {
    private List<Student> students; // Lista studentów

    /**
     * Konstruktor klasy StudentRegistry.
     *
     * @param data Lista danych inicjalizacyjnych (imię, nazwisko, numer albumu)
     */
    public StudentRegistry(List<String> data) {
        students = new ArrayList<>();

        // Inicjalizacja listy studentów na podstawie danych
        if (!data.isEmpty()) {
            for (int i = 0; i < data.size(); i += 4) {
                Student student = new Student(data.get(i), data.get(i + 1), data.get(i + 2));
                students.add(student);
            }
        }
        // Przykładowe dodanie studentów (zakomentowane)
        // students.add(new Student("Jan", "Kowalski", "123456"));
        // students.add(new Student("Adam", "Nowak", "654321"));
    }

    /**
     * Metoda zwracająca listę wszystkich studentów.
     *
     * @return Lista studentów
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Metoda dodająca nowego studenta do rejestru.
     *
     * @param student Obiekt studenta do dodania
     */
    public void addStudent(Student student) {
        students.add(student);
        JOptionPane.showMessageDialog(null, "Dodano studenta: " + student.getName() + " " + student.getSurname());
    }

    /**
     * Metoda edytująca dane studenta na podstawie numeru albumu.
     *
     * @param albumNumber Numer albumu studenta do edycji
     * @param newStudent  Nowy obiekt studenta z zaktualizowanymi danymi
     * @return Zaktualizowany obiekt studenta lub null, jeśli nie znaleziono
     */
    public Student editStudent(String albumNumber, Student newStudent) {
        for (Student student : students) {
            if (student.getAlbumNumber().equals(albumNumber)) {
                deleteStudent(albumNumber);
                students.add(newStudent);

                JOptionPane.showMessageDialog(null,
                        "Zaktualizowano dane studenta: " + student.getName() + " " + student.getSurname() + " "
                                + student.getAlbumNumber());
                return student;
            }
        }
        return null;
    }

    /**
     * Metoda usuwająca studenta na podstawie numeru albumu.
     *
     * @param albumNumber Numer albumu studenta do usunięcia
     */
    public void deleteStudent(String albumNumber) {
        students.removeIf(student -> student.getAlbumNumber().equals(albumNumber));
    }

    /**
     * Metoda zwracająca studenta na podstawie numeru albumu.
     *
     * @param albumNumber Numer albumu studenta do wyszukania
     * @return Obiekt studenta lub null, jeśli nie znaleziono
     */
    public Student getStudentByAlbumNumber(String albumNumber) {
        for (Student student : students) {
            if (student.getAlbumNumber().equals(albumNumber)) {
                return student;
            }
        }
        return null;
    }

    /**
     * Metoda przypisująca grupę do studenta.
     *
     * @param student Student, któremu ma być przypisana grupa
     * @param group   Grupa do przypisania
     */
    public void assignGroupToStudent(Student student, Group group) {
        System.out.println("Assigning group " + (group != null ? group.getGroupCode() : "null") + " to student "
                + student.getAlbumNumber());
        for (Student s : students) {
            if (s.getAlbumNumber().equals(student.getAlbumNumber())) {
                s.setGroup(group);
                System.out.println("Student " + s.getAlbumNumber() + " group set to " + s.getGroupCode());
                JOptionPane.showMessageDialog(null, "Zaktualizowano grupę studenta: " + s.getName() + " "
                        + s.getSurname() + " na grupę " + (group != null ? group.getGroupCode() : "Brak grupy"));
                break;
            }
        }
        System.out.println("After assignment:");
        for (Student s : students) {
            System.out.println(s.getFields());
        }
    }

    /**
     * Metoda zapisująca dane studentów do bazy danych.
     */
    public void saveDataToDB() {
        List<String> data = new ArrayList<>();
        for (Student student : students) {
            data.add(student.getName());
            data.add(student.getSurname());
            data.add(student.getAlbumNumber());
            data.add(student.getGroupCode());
        }
        Database.save("students", data);
    }
}
