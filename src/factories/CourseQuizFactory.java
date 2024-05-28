package factories;

import collections.CourseQuiz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CourseQuizFactory {
    private static int id = 1;

    public static CourseQuiz createCourseQuiz(Scanner in) {
        return new CourseQuiz(id++, in);
    }

    public static CourseQuiz createCourseQuiz(ResultSet in) throws SQLException {
        return new CourseQuiz(in);
    }
}
