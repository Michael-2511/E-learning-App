package factories;

import collections.Lesson;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class LessonFactory {
    private static int id = 1;

    public static Lesson createLesson(Scanner in) throws ParseException {
        return new Lesson(id++, in);
    }

    public static Lesson createLesson(ResultSet in) throws SQLException {
        return new Lesson(in);
    }
}
