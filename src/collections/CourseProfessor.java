package collections;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CourseProfessor {
    public int courseId;
    public int professorId;

    public CourseProfessor(int courseId, int professorId) {
        this.courseId = courseId;
        this.professorId = professorId;
    }

    public CourseProfessor(int courseId, Scanner in) {
        this.courseId = courseId;
        this.readData(in);
    }

    public CourseProfessor(ResultSet in) throws SQLException {
//        this.courseId = courseId;
        this.readData(in);
    }

    private void readData(Scanner in) {
        System.out.println("Professor ID: ");
        this.professorId = in.nextInt();
    }

    private void readData(ResultSet in) throws SQLException {
        this.courseId = in.getInt("courseId");
        this.professorId = in.getInt("professorId");
    }
}
