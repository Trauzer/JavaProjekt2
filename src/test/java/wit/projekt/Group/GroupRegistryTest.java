package wit.projekt.Group;

import wit.projekt.Student.Student;
import wit.projekt.Student.StudentRegistry;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testy jednostkowe dla klasy GroupRegistry.
 */

/**
 * Klasa testowa dla GroupRegistry, zawierająca testy jednostkowe sprawdzające
 * poprawność działania metod klasy GroupRegistry.
 */
public class GroupRegistryTest {

    private GroupRegistry groupRegistry; // Rejestr grup.
    private StudentRegistry studentRegistry; // Rejestr studentów.

    /**
     * Konstruktor klasy GroupRegistryTest.
     * Wywołuje konstruktor klasy bazowej.
     */
    public GroupRegistryTest() {
        super();
    }

    /**
     * Przygotowanie danych przed każdym testem.
     */
    @BeforeEach
    public void setUp() {
        List<String> groupData = new ArrayList<>();
        List<String> studentData = new ArrayList<>();

        groupRegistry = new GroupRegistry(groupData);
        studentRegistry = new StudentRegistry(studentData);
    }

    /**
     * Test dodawania grupy do rejestru.
     */
    @Test
    public void testAddGroup() {
        Group group = new Group("G01", "CS", "Computer Science Group");
        groupRegistry.addGroup(group);

        assertEquals(1, groupRegistry.getGroups().size());
        assertEquals("CS", groupRegistry.getGroupByCode("G01").getSpecialization());
    }

    /**
     * Test edycji grupy w rejestrze.
     */
    @Test
    public void testEditGroup() {
        Group group = new Group("G01", "CS", "Computer Science Group");
        groupRegistry.addGroup(group);

        Group editedGroup = new Group("G01", "Math", "Mathematics Group");
        groupRegistry.editGroup("G01", editedGroup);

        assertEquals("Math", groupRegistry.getGroupByCode("G01").getSpecialization());
    }

    /**
     * Test usuwania grupy z rejestru.
     */
    @Test
    public void testDeleteGroup() {
        Group group = new Group("G01", "CS", "Computer Science Group");
        groupRegistry.addGroup(group);

        groupRegistry.deleteGroup("G01");

        assertNull(groupRegistry.getGroupByCode("G01"));
        assertEquals(0, groupRegistry.getGroups().size());
    }

    /**
     * Test przypisywania studenta do grupy.
     */
    @Test
    public void testAssignStudentToGroup() {
        Group group = new Group("G01", "CS", "Computer Science Group");
        Student student = new Student("John", "Doe", "12345");

        groupRegistry.addGroup(group);
        studentRegistry.addStudent(student);

        group.addStudent(student);

        assertEquals("G01", student.getGroupCode());
        assertEquals(1, group.getStudents().size());
    }

    /**
     * Test odłączania studenta od grupy.
     */
    @Test
    public void testUnassignStudentFromGroup() {
        Group group = new Group("G01", "CS", "Computer Science Group");
        Student student = new Student("John", "Doe", "12345");

        groupRegistry.addGroup(group);
        studentRegistry.addStudent(student);

        group.addStudent(student);
        group.removeStudent(student);

        assertEquals("Brak grupy", student.getGroupCode());
        assertEquals(0, group.getStudents().size());
    }
}
