package collections;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CourseStudent {
    public int courseId;
    public int studentId;

    public CourseStudent(int courseId, int studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public CourseStudent(int courseId, Scanner in) {
        this.courseId = courseId;
        this.readData(in);
    }

    public CourseStudent(ResultSet in) throws SQLException {
//        this.courseId = courseId;
        this.readData(in);
    }

    private void readData(Scanner in) {
        System.out.println("Student ID: ");
        this.studentId = in.nextInt();
    }

    private void readData(ResultSet in) throws SQLException {
        this.courseId = in.getInt("courseId");
        this.studentId = in.getInt("studentId");
    }
}
