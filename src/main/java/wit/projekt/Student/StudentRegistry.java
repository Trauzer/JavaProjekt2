package wit.projekt.Student;

import wit.projekt.Database.Database;
import wit.projekt.Group.Group;
import wit.projekt.Group.GroupRegistry;

import javax.swing.*;
import java.util.*;

/**
 * Klasa StudentRegistry przechowująca rejestr studentów oraz umożliwiająca
 * operacje na nich.
 */

public class StudentRegistry {
    private GroupRegistry groupRegistry;
    private List<Student> students; // Lista studentów

    /**
     * Konstruktor klasy StudentRegistry.
     *
     * @param data          Lista danych inicjalizacyjnych (imię, nazwisko, numer
     *                      albumu)
     * @param groupRegistry Rejestr grup, do którego należą studenci
     */
    public StudentRegistry(List<String> data, GroupRegistry groupRegistry) {
        this.groupRegistry = groupRegistry;
        students = new ArrayList<>();

        // Inicjalizacja listy studentów na podstawie danych
        if (!data.isEmpty()) {
            for (String line : data) {
                String[] parts = line.split(";");
                Student student = new Student(parts[0], parts[1], parts[2]);
                Group group = groupRegistry.getGroupByCode(parts[3]);
                student.setGroup(group);

                if (parts.length > 4) {
                    for (int i = 4; i < parts.length; i += 2) {
                        student.addGrade(parts[i], Integer.parseInt(parts[i + 1]));
                    }
                }
                students.add(student);
            }
        }
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
                newStudent.getAllGrades().putAll(student.getAllGrades());
                deleteStudent(albumNumber);
                students.add(newStudent);
                JOptionPane.showMessageDialog(null, "Zaktualizowano dane studenta: " + student.getName() + " "
                        + student.getSurname() + " " + student.getAlbumNumber());
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
        for (Student s : students) {
            if (s.getAlbumNumber().equals(student.getAlbumNumber())) {
                s.setGroup(group);
                JOptionPane.showMessageDialog(null, "Zaktualizowano grupę studenta: " + s.getName() + " "
                        + s.getSurname() + " na grupę " + (group != null ? group.getGroupCode() : "Brak grupy"));
                break;
            }
        }
    }

    /**
     * Metoda zapisująca dane studentów do bazy danych.
     */
    public void saveDataToDB() {
        List<String> data = new ArrayList<>();
        for (Student student : students) {
            StringBuilder sb = new StringBuilder();
            sb.append(student.getName()).append(";")
                    .append(student.getSurname()).append(";")
                    .append(student.getAlbumNumber()).append(";")
                    .append(student.getGroupCode());
            for (Map.Entry<String, Integer> entry : student.getAllGrades().entrySet()) {
                sb.append(";").append(entry.getKey()).append(";").append(entry.getValue());
            }
            data.add(sb.toString());
        }
        Database.save("students", data);
    }
}
