package factories;

import collections.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class CourseFactory {
    private static int id = 1;

    public static void incrementId(int size) {
        CourseFactory.id += size;
    }

    public static Course createCourse(Scanner in) throws ParseException {
        return new Course(id++, in);
    }

    public static Course createCourse(ResultSet in) throws SQLException {
        return new Course(in);
    }
}
