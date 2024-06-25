package wit.projekt;

import wit.projekt.Database.Database;
import wit.projekt.Frame.Frame;
import wit.projekt.Group.GroupGUI;
import wit.projekt.Group.GroupRegistry;
import wit.projekt.Student.StudentGUI;
import wit.projekt.Student.StudentRegistry;
import wit.projekt.Subject.SubjectGUI;
import wit.projekt.Subject.SubjectRegistry;

import javax.swing.*;

/**
 * Główna klasa aplikacji.
 */
public class Main {
    // Obiekt do zarządzania bazą danych
    static Database database = new Database();

    // Rejestry dla danych głównych
    static GroupRegistry groupRegistry = new GroupRegistry(database.get("groups"));
    static StudentRegistry studentRegistry = new StudentRegistry(database.get("students"), groupRegistry);
    static SubjectRegistry subjectRegistry = new SubjectRegistry(database.get("subjects"));

    /**
     * Metoda główna aplikacji.
     * Tworzy interfejs graficzny i uruchamia główne panele.
     *
     * @param args Argumenty wiersza poleceń (nie używane w tej aplikacji).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Frame frame = new Frame();

            StudentGUI studentGUI = new StudentGUI("STUDENTS", studentRegistry, groupRegistry, subjectRegistry);
            GroupGUI groupGUI = new GroupGUI("GROUPS", groupRegistry, studentRegistry, studentGUI);
            SubjectGUI subjectGUI = new SubjectGUI("SUBJECTS", subjectRegistry, studentRegistry, studentGUI);

            frame.addPanelToPane("Students", studentGUI.getPanel());
            frame.addPanelToPane("Groups", groupGUI.getPanel());
            frame.addPanelToPane("Subjects", subjectGUI.getPanel());

            frame.setVisible(true);
        });
    }

    /**
     * Metoda do zapisywania danych przy zamknięciu aplikacji.
     * Zapisuje dane studentów, grup i przedmiotów do bazy danych.
     */
    public static void saveOnExit() {
        studentRegistry.saveDataToDB();
        groupRegistry.saveDataToDB();
        subjectRegistry.saveDataToDB();

        // Zapisuje plik bazy danych na dysku
        database.saveFile();

    }
}