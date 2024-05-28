package collections;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Question {
    private int questionId;
    private String statement;
    private String correctAnswer;

    public Question(int questionId, String statement, String correctAnswer) {
        this.questionId = questionId;
        this.statement = statement;
        this.correctAnswer = correctAnswer;
    }

    public Question(int questionId, Scanner in) {
        this.questionId = questionId;
        this.readData(in);
    }

    public Question(ResultSet in) throws SQLException {
//        this.questionId = questionId;
        this.readData(in);
    }

    public void readData(Scanner in) {
        System.out.print("Statement: ");
        this.statement = in.nextLine();
        System.out.print("Correct Answer: ");
        this.correctAnswer = in.nextLine();
    }

    public void readData(ResultSet in) throws SQLException {
        this.questionId = in.getInt("questionId");
        this.statement = in.getString("statement");
        this.correctAnswer = in.getString("correctAnswer");
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}

