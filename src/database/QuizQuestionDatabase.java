package database;

import collections.QuizQuestion;
import factories.QuizQuestionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizQuestionDatabase {
    Connection connection;

    public QuizQuestionDatabase(Connection connection) {
        this.connection = connection;
    }

    public List<QuizQuestion> read() {
        List<QuizQuestion> quizQuestions = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM QuizQuestion");
            while (resultSet.next()) {
                QuizQuestion currentQuizQuestion = QuizQuestionFactory.createQuizQuestion(resultSet);
                quizQuestions.add(currentQuizQuestion);
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quizQuestions;
    }

    public void create(QuizQuestion quizQuestion) {
        try {
            String query = "INSERT INTO QuizQuestion (quizId, questionId) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, quizQuestion.quizId);
            preparedStatement.setInt(2, quizQuestion.questionId);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(QuizQuestion quizQuestion) {
        try {
            String query = "DELETE FROM QuizQuestion WHERE questionId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, quizQuestion.questionId);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

