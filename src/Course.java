import java.util.ArrayList;
import java.util.List;
public class Course {
    private String title;
    private List<Lesson> lessons;               // adaug lesson la course
    private List<Quiz> quizzes;                 // adaug quiz la course
    private List<Student> enrolledStudents;     // adaug course la student
    private Professor professor;

    public Course(String title) {
        this.title = title;
        this.lessons = new ArrayList<>();
        this.quizzes = new ArrayList<>();
        this.enrolledStudents = new ArrayList<>();
        this.professor = null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void addLesson(Lesson lesson) {
        this.lessons.add(lesson);
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void addQuiz(Quiz quiz) {
        this.quizzes.add(quiz);
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void addStudent(Student student) {
        this.enrolledStudents.add(student);
    }

    @Override
    public String toString() {
        return "Curs{" +
                "title='" + title + '\'' +
                ", lessons=" + lessons +
                ", quizzes=" + quizzes +
                '}';
    }
}
