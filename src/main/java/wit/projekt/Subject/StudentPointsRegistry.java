package wit.projekt.Subject;

import java.util.ArrayList;
import java.util.List;

import wit.projekt.Student.Student;

/**
 * Klasa StudentPointsRegistry zarządza rejestrem punktów studentów.
 * Ta klasa nie posiada domyślnego konstruktora.
 */
public class StudentPointsRegistry {
    // Lista przechowująca punkty wszystkich studentów
    private List<StudentPoints> studentPointsList = new ArrayList<>();

    /**
     * Domyślny konstruktor klasy StudentPointsRegistry.
     * Inicjalizuje listę punktów studentów.
     */
    public StudentPointsRegistry() {
        super(); // Wywołanie konstruktora klasy nadrzędnej, jeśli istnieje
    }

    /**
     * Metoda dodająca punkty studenta do rejestru.
     * Jeśli obiekt StudentPoints jest poprawny, zostaje dodany do listy
     * studentPointsList.
     *
     * @param studentPoints Obiekt StudentPoints zawierający punkty studenta
     */
    public void addStudentPoints(StudentPoints studentPoints) {
        studentPointsList.add(studentPoints);
    }

    /**
     * Metoda usuwająca punkty studenta z rejestru na podstawie numeru albumu i
     * nazwy przedmiotu.
     * Przeszukuje listę studentPointsList w celu znalezienia i usunięcia punktów
     * studenta
     * spełniających kryteria.
     *
     * @param studentAlbumNumber Numer albumu studenta
     * @param subjectName        Nazwa przedmiotu
     */
    public void deleteStudentPoints(String studentAlbumNumber, String subjectName) {
        // Implementacja metody powinna przeszukiwać listę studentPointsList
        // i usuwać obiekt StudentPoints, który odpowiada podanemu numerowi albumu i
        // nazwie przedmiotu.
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
