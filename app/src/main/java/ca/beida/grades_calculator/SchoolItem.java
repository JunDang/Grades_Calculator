package ca.beida.grades_calculator;

public class SchoolItem {
    private String schoolName;
    private int schoolImage;

    public SchoolItem(String schoolName, int schoolImage) {
        this.schoolName = schoolName;
        this.schoolImage = schoolImage;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public int getSchoolImage() {
        return schoolImage;
    }

}
