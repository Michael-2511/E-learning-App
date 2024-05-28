package collections;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class QuizQuestion {
    public int quizId;
    public int questionId;

    public QuizQuestion(int quizId, int questionId) {
        this.quizId = quizId;
        this.questionId = questionId;
    }

    public QuizQuestion(int quizId, Scanner in) {
        this.quizId = quizId;
        this.readData(in);
    }

    public QuizQuestion(ResultSet in) throws SQLException {
//        this.quizId = quizId;
        this.readData(in);
    }

    private void readData(Scanner in) {
        System.out.println("Question ID: ");
        this.questionId = in.nextInt();
    }

    private void readData(ResultSet in) throws SQLException {
        this.quizId = in.getInt("quizId");
        this.questionId = in.getInt("questionId");
    }
}

