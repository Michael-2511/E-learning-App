package factories;

import collections.CourseLesson;
import collections.CourseStudent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class CourseStudentFactory {
    private static int id = 1;

    public static CourseStudent createCourseStudent(Scanner in) throws ParseException {
        return new CourseStudent(id++, in);
    }

    public static CourseStudent createCourseStudent(ResultSet in) throws SQLException {
        return new CourseStudent(in);
    }
}
