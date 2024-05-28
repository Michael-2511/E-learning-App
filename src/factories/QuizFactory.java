package factories;

import collections.Quiz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class QuizFactory {
    private static int id = 1;

    public static Quiz createQuiz(Scanner in) {
        return new Quiz(id++, in);
    }

    public static Quiz createQuiz(ResultSet in) throws SQLException {
        return new Quiz(in);
    }
}