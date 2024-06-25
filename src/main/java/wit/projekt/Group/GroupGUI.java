package wit.projekt.Group;

import wit.projekt.Frame.PaneController;
import wit.projekt.Student.Student;
import wit.projekt.Student.StudentRegistry;
import wit.projekt.Student.StudentGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Klasa GUI do obsługi grup.
 * Rozszerza PaneController i zawiera interakcje użytkownika z interfejsem
 * użytkownika.
 */
public class GroupGUI extends PaneController {
    /**
     * Zmienna przechowująca wszystkie grupy.
     */
    private GroupRegistry groupRegistry;
    /**
     * Zmienna przechowująca wszystkich studentów.
     */
    private StudentRegistry studentRegistry;
    /**
     * Interfejs użytkownika dla funkcjonalności związanych ze studentami.
     */
    private StudentGUI studentGUI;

    /**
     * Konstruktor klasy GroupGUI.
     * 
     * @param name            Nazwa panelu
     * @param groupRegistry   Rejestr grup
     * @param studentRegistry Rejestr studentów
     * @param studentGUI      Interfejs GUI studenta
     */
    public GroupGUI(String name, GroupRegistry groupRegistry, StudentRegistry studentRegistry, StudentGUI studentGUI) {
        super(name, new String[] { "Kod Grupy", "Specjalizacja", "Opis" });
        this.groupRegistry = groupRegistry;
        this.studentRegistry = studentRegistry;
        this.studentGUI = studentGUI;

        // Dodawanie pól do tabeli na podstawie istniejących grup
        for (Group group : groupRegistry.getGroups()) {
            addFieldToTable(group.getFields());
        }

        // Inicjalizacja pól formularza
        fields.put("groupCode", new JTextField(10));
        fields.put("specialization", new JTextField(10));
        fields.put("description", new JTextField(10));
        fields.put("studentAlbumNumber", addField("Numer albumu studenta"));

        // Dodawanie etykiet i pól tekstowych do panelu przycisków
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        buttonPanel.add(new JLabel("Kod grupy:"));
        buttonPanel.add(fields.get("groupCode"));
        buttonPanel.add(new JLabel("Specjalizacja:"));
        buttonPanel.add(fields.get("specialization"));
        buttonPanel.add(new JLabel("Opis:"));
        buttonPanel.add(fields.get("description"));

        // Tworzenie przycisków i dodawanie ich do panelu
        JButton assignButton = createButton("assignButton", "Przypisz studenta");
        fieldPanel.add(assignButton);

        JButton unassignButton = createButton("unassignButton", "Usuń przypisanie");
        fieldPanel.add(unassignButton);

        JButton addButton = createButton("addButton", "Dodaj grupę");
        buttonPanel.add(addButton);

        JButton deleteButton = createButton("deleteButton", "Usuń grupę");
        buttonPanel.add(deleteButton);

        JButton editButton = createButton("editButton", "Edytuj grupę");
        buttonPanel.add(editButton);

        JButton searchButton = createButton("searchButton", "Szukaj ucznia");
        buttonPanel.add(searchButton);
    }

    @Override
    protected String getFieldNameFromID(String id) {
        switch (id) {
            case "groupCode":
                return "Kod grupy";
            case "specialization":
                return "Specjalizacja";
            case "description":
                return "Opis";
            default:
                return "";
        }
    }

    @Override
    protected String getButtonNamesFromID(String id) {
        switch (id) {
            case "addButton":
                return "Dodaj grupę";
            case "deleteButton":
                return "Usuń grupę";
            case "assignButton":
                return "Przypisz studenta";
            case "unassignButton":
                return "Usuń przypisanie";
            case "searchButton":
                return "Szukaj grupę";
            default:
                return "";
        }
    }

    /**
     * Metoda zwracająca panel GUI.
     * 
     * @return Panel GUI
     */
    public JPanel getPanel() {
        return this;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addButton")) {
            String groupCode = fields.get("groupCode").getText();
            String specialization = fields.get("specialization").getText();
            String description = fields.get("description").getText();

            if (groupCode.isEmpty() || specialization.isEmpty() || description.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Wszystkie pola muszą być wypełnione");
                return;
            }

            Group group = new Group(groupCode, specialization, description);
            groupRegistry.addGroup(group);
            addFieldToTable(group.getFields());
        }

        if (e.getActionCommand().equals("deleteButton")) {
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Nie wybrano grupy do usunięcia");
                return;
            }

            String groupCode = table.getValueAt(selectedRow, 0).toString();
            groupRegistry.deleteGroup(groupCode);
            deleteRow(selectedRow);
        }

        if (e.getActionCommand().equals("assignButton")) {
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Nie wybrano grupy do przypisania studenta");
                return;
            }

            String groupCode = table.getValueAt(selectedRow, 0).toString();
            String studentAlbumNumber = fields.get("studentAlbumNumber").getText();

            if (studentAlbumNumber.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Numer albumu studenta nie może być pusty");
                return;
            }

            Group group = groupRegistry.getGroupByCode(groupCode);
            Student student = studentRegistry.getStudentByAlbumNumber(studentAlbumNumber);

            if (group != null && student != null) {
                group.addStudent(student);
                studentRegistry.assignGroupToStudent(student, group);
                JOptionPane.showMessageDialog(null, "Przypisano studenta: " + student.getName() + " "
                        + student.getSurname() + " do grupy " + group.getGroupCode());
                studentGUI.refreshTable();
            } else {
                JOptionPane.showMessageDialog(null, "Nie znaleziono grupy lub studenta");
            }
        }

        if (e.getActionCommand().equals("unassignButton")) {
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Nie wybrano grupy do usunięcia przypisania studenta");
                return;
            }

            String groupCode = table.getValueAt(selectedRow, 0).toString();
            String studentAlbumNumber = fields.get("studentAlbumNumber").getText();

            if (studentAlbumNumber.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Numer albumu studenta nie może być pusty");
                return;
            }

            Group group = groupRegistry.getGroupByCode(groupCode);
            Student student = studentRegistry.getStudentByAlbumNumber(studentAlbumNumber);

            if (group != null && student != null) {
                group.removeStudent(student);
                studentRegistry.assignGroupToStudent(student, null);
                JOptionPane.showMessageDialog(null, "Usunięto przypisanie studenta: " + student.getName() + " "
                        + student.getSurname() + " z grupy " + group.getGroupCode());
                studentGUI.refreshTable();
            } else {
                JOptionPane.showMessageDialog(null, "Nie znaleziono grupy lub studenta");
            }
        }

        if (e.getActionCommand().equals("editButton")) {
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Nie wybrano grupy do edycji");
                return;
            }

            String oldGroupCode = table.getValueAt(selectedRow, 0).toString();

            String groupCode = fields.get("groupCode").getText();
            String specialization = fields.get("specialization").getText();
            String description = fields.get("description").getText();

            if (groupCode.isEmpty() || specialization.isEmpty() || description.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Wszystkie pola muszą być wypełnione");
                return;
            }

            Group group = new Group(groupCode, specialization, description);

            groupRegistry.editGroup(oldGroupCode, group);
            editRow(group.getFields(), selectedRow);
        }

        if (e.getActionCommand().equals("searchButton")) {
            String groupCode = fields.get("groupCode").getText();
            if (!groupCode.isEmpty()) {
                searchGroup(groupCode);
            } else {
                JOptionPane.showMessageDialog(null, "Wprowadź nazwę grupy do wyszukania.");
            }
        }

    }

    /**
     * Metoda wyszukująca i wyświetlająca informacje o grupie na podstawie kodu
     * grupy.
     * 
     * @param groupCode Kod grupy do wyszukania
     */
    private void searchGroup(String groupCode) {
        Group group = groupRegistry.getGroupByCode(groupCode);

        if (group == null) {
            JOptionPane.showMessageDialog(null, "Nie znaleziono grupy o nazwie: " + groupCode);
            return;
        } else {
            JOptionPane.showMessageDialog(null, "Znaleziono grupę:\n" +
                    "Nazwa: " + group.getGroupCode() + "\n" +
                    "Specjalizacja: " + group.getSpecialization() + "\n" +
                    "Opis: " + group.getDescription());
        }
    }
}
