package wit.projekt.Subject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wit.projekt.Group.GroupRegistry;
import wit.projekt.Student.StudentRegistry;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testy jednostkowe dla klasy SubjectRegistry.
 * Klasa SubjectRegistryTest zawiera testy sprawdzające poprawność działania
 * metod klasy SubjectRegistry.
 */
public class SubjectRegistryTest {

    private SubjectRegistry subjectRegistry;
    private StudentRegistry studentRegistry;
    private GroupRegistry groupRegistry;

    /**
     * Konstruktor domyślny dla klasy testowej SubjectRegistryTest.
     * Wywołuje konstruktor klasy bazowej.
     */
    public SubjectRegistryTest() {
        super();
    }

    /**
     * Przygotowanie danych przed każdym testem.
     */
    @BeforeEach
    public void setUp() {
        List<String> subjectData = new ArrayList<>();
        List<String> studentData = new ArrayList<>();
        List<String> groupData = new ArrayList<>();

        groupRegistry = new GroupRegistry(groupData);
        subjectRegistry = new SubjectRegistry(subjectData);
        studentRegistry = new StudentRegistry(studentData, groupRegistry);
    }

    /**
     * Test dodawania przedmiotu do rejestru.
     */
    @Test
    public void testAddSubject() {
        Subject subject = new Subject("MATH101", "Mathematics");
        subjectRegistry.addSubject(subject);

        assertEquals(1, subjectRegistry.getSubjects().size());
        assertEquals("Mathematics", subjectRegistry.getSubjectByCode("MATH101").getName());
    }

    /**
     * Test edycji przedmiotu w rejestrze.
     */
    @Test
    public void testEditSubject() {
        Subject subject = new Subject("MATH101", "Mathematics");
        subjectRegistry.addSubject(subject);

        Subject editedSubject = new Subject("MATH101", "Advanced Mathematics");
        subjectRegistry.editSubject("MATH101", editedSubject);

        assertEquals("Advanced Mathematics", subjectRegistry.getSubjectByCode("MATH101").getName());
    }

    /**
     * Test usuwania przedmiotu z rejestracji.
     */
    @Test
    public void testDeleteSubject() {
        Subject subject = new Subject("MATH101", "Mathematics");
        subjectRegistry.addSubject(subject);

        subjectRegistry.deleteSubject("MATH101");

        assertNull(subjectRegistry.getSubjectByCode("MATH101"));
        assertEquals(0, subjectRegistry.getSubjects().size());
    }

    /**
     * Test dodawania oceny dla studenta.
     */
    @Test
    public void testAddGradeToStudent() {
        Student student = new Student("John", "Doe", "12345");
        studentRegistry.addStudent(student);

        Subject subject = new Subject("MATH101", "Mathematics");
        subjectRegistry.addSubject(subject);

        StudentGUI studentGUI = new StudentGUI("STUDENTS", studentRegistry, new GroupRegistry(new ArrayList<>()),
                subjectRegistry);
        studentGUI.addColumn("MATH101");

        studentGUI.updateStudentGrade("12345", "MATH101", 5);

        assertEquals(5, studentGUI.getTable().getValueAt(0, studentGUI.getModel().findColumn("MATH101")));
    }
}
