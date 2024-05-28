package database;

import collections.Question;
import factories.QuestionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDatabase {
    Connection connection;

    public QuestionDatabase(Connection connection) {
        this.connection = connection;
    }

    public List<Question> read() {
        List<Question> questions = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Question");
            while (resultSet.next()) {
                Question currentQuestion = QuestionFactory.createQuestion(resultSet);
                questions.add(currentQuestion);
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return questions;
    }

    public void create(Question question) {
        try {
            String query = "INSERT INTO Question (questionId, statement, correctAnswer) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, question.getQuestionId());
            preparedStatement.setString(2, question.getStatement());
            preparedStatement.setString(3, question.getCorrectAnswer());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Question newQuestion) {
        try {
            String query = "UPDATE Question SET statement = ?, correctAnswer = ? WHERE questionId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newQuestion.getStatement());
            preparedStatement.setString(2, newQuestion.getCorrectAnswer());
            preparedStatement.setInt(3, newQuestion.getQuestionId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Question question) {
        try {
            String query = "DELETE FROM Question WHERE questionId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, question.getQuestionId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

