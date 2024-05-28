package factories;

import collections.QuizQuestion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class QuizQuestionFactory {
    private static int id = 1;

    public static QuizQuestion createQuizQuestion(Scanner in) {
        return new QuizQuestion(id++, in);
    }

    public static QuizQuestion createQuizQuestion(ResultSet in) throws SQLException {
        return new QuizQuestion(in);
    }
}

