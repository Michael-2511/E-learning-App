package database;

import collections.CourseQuiz;
import factories.CourseQuizFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseQuizDatabase {
    Connection connection;

    public CourseQuizDatabase(Connection connection) {
        this.connection = connection;
    }

    public List<CourseQuiz> read() {
        List<CourseQuiz> courseQuizzes = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CourseQuiz");
            while (resultSet.next()) {
                CourseQuiz currentCourseQuiz = CourseQuizFactory.createCourseQuiz(resultSet);
                courseQuizzes.add(currentCourseQuiz);
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courseQuizzes;
    }

    public void create(CourseQuiz courseQuiz) {
        try {
            String query = "INSERT INTO CourseQuiz (courseId, quizId) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseQuiz.courseId);
            preparedStatement.setInt(2, courseQuiz.quizId);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(CourseQuiz courseQuiz) {
        try {
            String query = "DELETE FROM CourseQuiz WHERE courseId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseQuiz.courseId);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}