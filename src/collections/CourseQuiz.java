package collections;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CourseQuiz {
    public final int courseId;
    public int quizId;

    public CourseQuiz(int courseId, int quizId) {
        this.courseId = courseId;
        this.quizId = quizId;
    }

    public CourseQuiz(int courseId, Scanner in) {
        this.courseId = courseId;
        this.readData(in);
    }

    public CourseQuiz(int courseId, ResultSet in) throws SQLException {
        this.courseId = courseId;
        this.readData(in);
    }

    private void readData(Scanner in) {
        System.out.println("Quiz ID: ");
        this.quizId = in.nextInt();
    }

    private void readData(ResultSet in) throws SQLException {
        this.quizId = in.getInt("quizId");
    }
}
