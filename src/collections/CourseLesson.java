package collections;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CourseLesson {
    public int courseId;
    public int lessonId;

    public CourseLesson(int courseId, int lessonId) {
        this.courseId = courseId;
        this.lessonId = lessonId;
    }

    public CourseLesson(int courseId, Scanner in) {
        this.courseId = courseId;
        this.readData(in);
    }

    public CourseLesson(ResultSet in) throws SQLException {
//        this.courseId = courseId;
        this.readData(in);
    }

    private void readData(Scanner in) {
        System.out.println("Lesson ID: ");
        this.lessonId = in.nextInt();
    }

    private void readData(ResultSet in) throws SQLException {
        this.courseId = in.getInt("courseId");
        this.lessonId = in.getInt("lessonId");
    }
}
