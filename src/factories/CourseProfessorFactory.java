package factories;

import collections.CourseProfessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CourseProfessorFactory {
    private static int id = 1;

    public static CourseProfessor createCourseProfessor(Scanner in) {
        return new CourseProfessor(id++, in);
    }

    public static CourseProfessor createCourseProfessor(ResultSet in) throws SQLException {
        return new CourseProfessor(in);
    }
}
