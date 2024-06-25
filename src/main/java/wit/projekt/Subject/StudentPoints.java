package wit.projekt.Subject;

/**
 * Klasa StudentPoints przechowująca punkty studenta dla danego przedmiotu.
 */

public class StudentPoints {
    private final String studentAlbumNumber; // Numer albumu studenta
    private final String subjectName; // Nazwa przedmiotu
    private int thirdGradePoints; // Punkty zaliczenia za ocenę trzy
    private int fourthGradePoints; // Punkty zaliczenia za ocenę cztery
    private int fifthGradePoints; // Punkty zaliczenia za ocenę pięć

    /**
     * Konstruktor klasy StudentPoints.
     *
     * @param studentAlbumNumber Numer albumu studenta
     * @param subjectName        Nazwa przedmiotu
     * @param thirdGradePoints   Punkty za trzecią ocenę
     * @param fourthGradePoints  Punkty za czwartą ocenę
     * @param fifthGradePoints   Punkty za piątą ocenę
     */

    public StudentPoints(String studentAlbumNumber, String subjectName, int thirdGradePoints, int fourthGradePoints,
            int fifthGradePoints) {
        this.studentAlbumNumber = studentAlbumNumber;
        this.subjectName = subjectName;
        this.thirdGradePoints = thirdGradePoints;
        this.fourthGradePoints = fourthGradePoints;
        this.fifthGradePoints = fifthGradePoints;
    }

    /**
     * Metoda zwracająca numer albumu studenta.
     *
     * @return Numer albumu studenta
     */
    public String getStudentAlbumNumber() {
        return studentAlbumNumber;
    }

    /**
     * Metoda zwracająca nazwę przedmiotu.
     *
     * @return Nazwa przedmiotu
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * Metoda zwracająca punkty za trzecią ocenę.
     *
     * @return Punkty za trzecią ocenę
     */
    public int getThirdGradePoints() {
        return thirdGradePoints;
    }

    /**
     * Metoda ustawiająca punkty za trzecią ocenę.
     *
     * @param points Punkty do ustawienia
     */
    public void setThirdGradePoints(int points) {
        this.thirdGradePoints = points;
    }

    /**
     * Metoda zwracająca punkty za czwartą ocenę.
     *
     * @return Punkty za czwartą ocenę
     */

    public int getFourthGradePoints() {
        return fourthGradePoints;
    }

    /**
     * Metoda ustawiająca punkty za czwartą ocenę.
     *
     * @param points Punkty do ustawienia
     */

    public void setFourthGradePoints(int points) {
        this.fourthGradePoints = points;
    }

    /**
     * Metoda zwracająca punkty za piątą ocenę.
     *
     * @return Punkty za piątą ocenę
     */
    public int getFifthGradePoints() {
        return fifthGradePoints;
    }

    /**
     * Metoda ustawiająca punkty za piątą ocenę.
     *
     * @param points Punkty do ustawienia
     */
    public void setFifthGradePoints(int points) {
        this.fifthGradePoints = points;
    }
}
