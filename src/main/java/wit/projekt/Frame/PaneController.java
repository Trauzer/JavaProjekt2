package wit.projekt.Frame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Abstrakcyjna klasa PaneController implementująca panel GUI dla kontrolerów.
 * Klasa ta zawiera podstawowe funkcje do zarządzania tabelą, polami tekstowymi
 * i przyciskami.
 */

public abstract class PaneController extends JPanel implements ActionListener {
    protected JPanel fieldPanel = new JPanel(); // Panel z polami tekstowymi
    protected JPanel buttonPanel = new JPanel(); // Panel z przyciskami
    protected JTable table = new JTable(); // Tabela
    protected DefaultTableModel model; // Model tabeli
    protected HashMap<String, JTextField> fields = new HashMap<>(); // Mapa pól tekstowych
    protected int selectedRow = -1; // Numer zaznaczonego wiersza (-1 oznacza brak zaznaczenia)

    /**
     * Konstruktor klasy PaneController.
     * 
     * @param name        Nazwa panelu
     * @param columnNames Tablica nazw kolumn tabeli
     */

    protected PaneController(String name, String[] columnNames) {
        setLayout(new BorderLayout());
        model = new DefaultTableModel(columnNames, 0);
        table.setModel(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    for (String key : fields.keySet()) {
                        if (model.findColumn(key) != -1) {
                            fields.get(key).setText(table.getValueAt(selectedRow, model.findColumn(key)).toString());
                        }
                    }
                }
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(fieldPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Metoda dodająca wiersz danych do tabeli.
     * 
     * @param rowData Lista obiektów reprezentujących dane w wierszu
     */

    protected void addFieldToTable(ArrayList<Object> rowData) {
        model.addRow(rowData.toArray());
    }

    /**
     * Metoda dodająca pole tekstowe do panelu.
     * 
     * @param name Nazwa pola
     * @return Utworzone pole tekstowe
     */

    protected JTextField addField(String name) {
        JLabel label = new JLabel(name);
        JTextField textField = new JTextField(10);
        fieldPanel.add(label);
        fieldPanel.add(textField);
        fields.put(name, textField);
        return textField;
    }

    /**
     * Metoda tworząca przycisk.
     * 
     * @param actionCommand Komenda akcji przycisku
     * @param name          Nazwa przycisku
     * @return Utworzony przycisk
     */

    protected JButton createButton(String actionCommand, String name) {
        JButton button = new JButton(name);
        button.setActionCommand(actionCommand);
        button.addActionListener(this);
        return button;
    }

    /**
     * Metoda usuwająca wiersz z tabeli.
     * 
     * @param row Numer wiersza do usunięcia
     */

    protected void deleteRow(int row) {
        model.removeRow(row);
    }

    /**
     * Metoda edytująca wiersz w tabeli.
     * 
     * @param rowData Lista obiektów reprezentujących nowe dane w wierszu
     * @param row     Numer wiersza do edycji
     */

    protected void editRow(ArrayList<Object> rowData, int row) {
        for (int i = 0; i < rowData.size(); i++) {
            model.setValueAt(rowData.get(i), row, i);
        }
    }

    /**
     * Metoda abstrakcyjna do pobierania nazwy pola na podstawie jego
     * identyfikatora.
     * 
     * @param id Identyfikator pola
     * @return Nazwa pola
     */

    protected abstract String getFieldNameFromID(String id);

    /**
     * Metoda abstrakcyjna do pobierania nazwy przycisku na podstawie jego
     * identyfikatora.
     * 
     * @param id Identyfikator przycisku
     * @return Nazwa przycisku
     */

    protected abstract String getButtonNamesFromID(String id);

    // Publiczne metody getter

    /**
     * Metoda zwracająca tabelę.
     * 
     * @return Tabela
     */

    public JTable getTable() {
        return table;
    }

    /**
     * Metoda zwracająca model danych tabeli.
     * 
     * @return Model danych tabeli
     */

    public DefaultTableModel getModel() {
        return model;
    }
}
