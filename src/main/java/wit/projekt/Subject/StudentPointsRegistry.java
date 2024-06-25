package wit.projekt.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa StudentPointsRegistry przechowująca listę punktów studentów.
 */

public class StudentPointsRegistry {
    private List<StudentPoints> studentPointsList = new ArrayList<>();

    /**
     * Metoda dodająca punkty studenta do rejestru.
     *
     * @param studentPoints Obiekt StudentPoints zawierający punkty studenta
     */

    public void addStudentPoints(StudentPoints studentPoints) {
        studentPointsList.add(studentPoints);
    }

    /**
     * Metoda usuwająca punkty studenta z rejestru na podstawie numeru albumu i
     * nazwy przedmiotu.
     *
     * @param studentAlbumNumber Numer albumu studenta
     * @param subjectName        Nazwa przedmiotu
     */

    public void deleteStudentPoints(String studentAlbumNumber, String subjectName) {
        studentPointsList.removeIf(points -> points.getStudentAlbumNumber().equals(studentAlbumNumber)
                && points.getSubjectName().equals(subjectName));
    }

    /**
     * Metoda zwracająca obiekt StudentPoints dla danego numeru albumu studenta i
     * nazwy przedmiotu.
     *
     * @param studentAlbumNumber Numer albumu studenta
     * @param subjectName        Nazwa przedmiotu
     * @return Obiekt StudentPoints lub null, jeśli nie znaleziono
     */

    public StudentPoints getStudentPoints(String studentAlbumNumber, String subjectName) {
        for (StudentPoints points : studentPointsList) {
            if (points.getStudentAlbumNumber().equals(studentAlbumNumber)
                    && points.getSubjectName().equals(subjectName)) {
                return points;
            }
        }
        return null;
    }

    /**
     * Metoda zwracająca listę wszystkich punktów studentów.
     *
     * @return Lista wszystkich punktów studentów
     */

    public List<StudentPoints> getAllStudentPoints() {
        return studentPointsList;
    }
}
