package wit.projekt.Frame;

import wit.projekt.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

/**
 * Klasa Frame reprezentuje główne okno aplikacji z zakładkami.
 */
public class Frame extends JFrame {

    /**
     * Komponent zakładek do organizacji różnych paneli w interfejsie użytkownika.
     */
    private final JTabbedPane tabbedPane = new JTabbedPane();

    /**
     * Konstruktor klasy Frame.
     */
    public Frame() {
        setTitle("Dziennik elektroniczny");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Main.saveOnExit();

                System.exit(0);
            }
        });

        add(tabbedPane, BorderLayout.CENTER);
    }

    /**
     * Metoda dodająca panel do zakładek w oknie.
     * 
     * @param name  Nazwa zakładki
     * @param panel Panel do dodania
     */
    public void addPanelToPane(String name, JPanel panel) {
        tabbedPane.addTab(name, panel);
    }
}