import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class Student extends User implements Comparable<Student> {
    private int studyYear;
    private List<Course> courses;
    private List<Grade> grades;

    public Student(String name, String email, int studyYear) {
        super(name, email);
        this.studyYear = studyYear;
        this.courses = new ArrayList<>();
        this.grades = new ArrayList<>();
    }

    public int getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void addGrade(Grade grade) {
        this.grades.add(grade);
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("An studiu: " + studyYear);
    }

    @Override
    public int compareTo(Student otherstudent) {
        return this.getName().compareTo(otherstudent.getName());
    }
}
