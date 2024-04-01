package util;

public class Grade {
    private int score;
    private String letterGrade;

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }
    public int getScore() {
        return score;
    }
    public String getLetterGrade() {
        return letterGrade;
    }
    /**
     * @param score contains the student's score and if statements to determine letter grade.
     */
    public void setScore(int score) {
        this.score = score;

        if (score >= 95) {
            this.letterGrade = "A";
        } else if (score >= 90) {
            this.letterGrade = "A-";
        } else if (score >= 87) {
            this.letterGrade = "B+";
        } else if (score >= 83) {
            this.letterGrade = "B";
        } else if (score >= 80) {
            this.letterGrade = "B-";
        } else if (score >= 77) {
            this.letterGrade = "C+";
        } else if (score >= 70) {
            this.letterGrade = "C";
        } else if (score >= 60) {
            this.letterGrade = "D";
        } else {
            this.letterGrade = "F";
        }
    }
}
