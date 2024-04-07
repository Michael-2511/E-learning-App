import java.util.ArrayList;
import java.util.List;
public class Student extends User{
    private int studyYear;
    private List<Course> courses;

    public Student(String name, String email, int studyYear) {
        super(name, email);
        this.studyYear = studyYear;
        this.courses = new ArrayList<>();
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

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("An studiu: " + studyYear);
    }
}
