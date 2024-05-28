package database;

import collections.Quiz;
import factories.QuizFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizDatabase {
    Connection connection;

    public QuizDatabase(Connection connection) {
        this.connection = connection;
    }

    public List<Quiz> read() {
        List<Quiz> quizzes = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Quiz");
            while (resultSet.next()) {
                Quiz currentQuiz = QuizFactory.createQuiz(resultSet);
                quizzes.add(currentQuiz);
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quizzes;
    }

    public void create(Quiz quiz) {
        try {
            String query = "INSERT INTO Quiz (quizId, title) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, quiz.getQuizId());
            preparedStatement.setString(2, quiz.getTitle());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Quiz newQuiz) {
        try {
            String query = "UPDATE Quiz SET title = ? WHERE quizId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newQuiz.getTitle());
            preparedStatement.setInt(2, newQuiz.getQuizId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Quiz quiz) {
        try {
            String query = "DELETE FROM Quiz WHERE quizId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, quiz.getQuizId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
