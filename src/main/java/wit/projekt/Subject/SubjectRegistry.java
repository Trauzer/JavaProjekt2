package wit.projekt.Subject;

import wit.projekt.Database.Database;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa odpowiedzialna za zarządzanie rejestracją przedmiotów.
 */
public class SubjectRegistry {
    private List<Subject> subjects = new ArrayList<>();

    /**
     * Konstruktor klasy SubjectRegistry.
     * Inicjalizuje listę przedmiotów na podstawie dostarczonych danych.
     *
     * @param data Lista danych zawierających kody i nazwy przedmiotów.
     */
    public SubjectRegistry(List<String> data) {
        if (!data.isEmpty()) {
            for (int i = 0; i < data.size(); i += 2) {
                subjects.add(new Subject(data.get(i), data.get(i + 1)));
            }
        }
    }

    /**
     * Metoda zwracająca listę wszystkich przedmiotów.
     *
     * @return Lista obiektów klasy Subject.
     */
    public List<Subject> getSubjects() {
        return subjects;
    }

    /**
     * Metoda dodająca nowy przedmiot do rejestru.
     *
     * @param subject Obiekt klasy Subject do dodania.
     */
    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    /**
     * Metoda usuwająca przedmiot z rejestru na podstawie jego kodu.
     *
     * @param code Kod przedmiotu do usunięcia.
     */
    public void deleteSubject(String code) {
        subjects.removeIf(subject -> subject.getCode().equals(code));
    }

    /**
     * Metoda edytująca istniejący przedmiot na podstawie jego kodu.
     *
     * @param code       Kod przedmiotu do edycji.
     * @param newSubject Nowy obiekt klasy Subject z nowymi danymi.
     */
    public void editSubject(String code, Subject newSubject) {
        for (Subject subject : subjects) {
            if (subject.getCode().equals(code)) {
                deleteSubject(code);
                addSubject(newSubject);
                break;
            }
        }
    }

    /**
     * Metoda zwracająca przedmiot na podstawie jego kodu.
     *
     * @param code Kod przedmiotu do wyszukania.
     * @return Obiekt klasy Subject lub null, jeśli przedmiot nie został znaleziony.
     */
    public Subject getSubjectByCode(String code) {
        for (Subject subject : subjects) {
            if (subject.getCode().equals(code)) {
                return subject;
            }
        }
        return null;
    }

    /**
     * Metoda zapisująca dane przedmiotów do bazy danych.
     * Dane są zapisywane w postaci listy zawierającej kody i nazwy przedmiotów.
     */
    public void saveDataToDB() {
        List<String> data = new ArrayList<>();
        for (Subject subject : subjects) {
            data.add(subject.getCode());
            data.add(subject.getName());
        }
        Database.save("subjects", data);
    }
}
