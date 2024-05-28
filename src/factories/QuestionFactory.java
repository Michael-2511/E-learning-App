package factories;

import collections.Question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class QuestionFactory {
    private static int id = 1;

    public static Question createQuestion(Scanner in) {
        return new Question(id++, in);
    }

    public static Question createQuestion(ResultSet in) throws SQLException {
        return new Question(in);
    }
}