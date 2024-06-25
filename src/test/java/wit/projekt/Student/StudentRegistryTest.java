package wit.projekt.Student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wit.projekt.Group.GroupRegistry;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Ta klasa jest odpowiedzialna za testowanie funkcjonalności rejestru
 * studentów.
 * Obejmuje testy dodawania, edytowania, usuwania oraz przypisywania grup do
 * studentów.
 */
public class StudentRegistryTest {
    private StudentRegistry studentRegistry;
    private GroupRegistry groupRegistry;

    /**
     * Konstruktor klasy StudentRegistryTest.
     * Wywołuje konstruktor klasy bazowej.
     */
    public StudentRegistryTest() {
        super();
    }

    @BeforeEach
    public void setUp() {
        List<String> groupData = new ArrayList<>();
        List<String> studentData = new ArrayList<>();

        groupRegistry = new GroupRegistry(groupData);
        studentRegistry = new StudentRegistry(studentData, groupRegistry);
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
     * Test dodawania studenta do rejestru.
     */
    @Test
    public void testAddStudent() {
        Student student = new Student("John", "Doe", "12345");
        studentRegistry.addStudent(student);

        assertEquals(1, studentRegistry.getStudents().size());
        assertEquals("John", studentRegistry.getStudentByAlbumNumber("12345").getName());
    }

    /**
     * Test edycji studenta w rejestrze.
     */
    @Test
    public void testEditStudent() {
        Student student = new Student("John", "Doe", "12345");
        studentRegistry.addStudent(student);

        Student editedStudent = new Student("John", "Doe", "12345");
        editedStudent.addGrade("MATH101", 4);
        studentRegistry.editStudent("12345", editedStudent);

        assertEquals(4, studentRegistry.getStudentByAlbumNumber("12345").getGrade("MATH101").intValue());
    }

    /**
     * Test usuwania studenta z rejestru.
     */
    @Test
    public void testDeleteStudent() {
        Student student = new Student("John", "Doe", "12345");
        studentRegistry.addStudent(student);

        studentRegistry.deleteStudent("12345");

        assertNull(studentRegistry.getStudentByAlbumNumber("12345"));
        assertEquals(0, studentRegistry.getStudents().size());
    }
}
