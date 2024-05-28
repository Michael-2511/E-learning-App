package factories;

import collections.CourseLesson;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class CourseLessonFactory {

    private static int id = 1;

    public static CourseLesson createCourseLesson(Scanner in) throws ParseException {
        return new CourseLesson(id++, in);
    }

    public static CourseLesson createCourseLesson(ResultSet in) throws SQLException {
        return new CourseLesson(in);
    }
}
