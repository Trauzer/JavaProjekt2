package wit.projekt.Group;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wit.projekt.Student.StudentRegistry;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        studentRegistry = new StudentRegistry(studentData, groupRegistry);
    }

    /**
     * Test dodawania grupy do rejestru.
     */
    @Test
    public void testAddGroup() {
        Group group = new Group("CS101", "Computer Science", "CS Group");
        groupRegistry.addGroup(group);

        assertEquals(1, groupRegistry.getGroups().size());
        assertEquals("Computer Science", groupRegistry.getGroupByCode("CS101").getSpecialization());
    }

    /**
     * Test edycji grupy w rejestrze.
     */
    @Test
    public void testEditGroup() {
        Group group = new Group("CS101", "Computer Science", "CS Group");
        groupRegistry.addGroup(group);

        Group editedGroup = new Group("CS101", "Advanced Computer Science", "Advanced CS Group");
        groupRegistry.editGroup("CS101", editedGroup);

        assertEquals("Advanced Computer Science", groupRegistry.getGroupByCode("CS101").getSpecialization());
    }

    /**
     * Test usuwania grupy z rejestru.
     */
    @Test
    public void testDeleteGroup() {
        Group group = new Group("CS101", "Computer Science", "CS Group");
        groupRegistry.addGroup(group);

        groupRegistry.deleteGroup("CS101");

        assertNull(groupRegistry.getGroupByCode("CS101"));
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
