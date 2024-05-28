package database;

import collections.Lesson;
import factories.LessonFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonDatabase {
    Connection connection;
    public LessonDatabase(Connection connection) {
        this.connection = connection;
    }

    public List<Lesson> read() {
        List<Lesson> lessons = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Lesson");
            while (resultSet.next()) {
                Lesson currentLesson = LessonFactory.createLesson(resultSet);
                lessons.add(currentLesson);
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lessons;
    }

    public void create(Lesson lesson) {
        try {
            String query = "INSERT INTO Lesson (lessonId, title, description) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, lesson.getLessonId());
            preparedStatement.setString(2, lesson.getTitle());
            preparedStatement.setString(3, lesson.getDescription());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Lesson newLesson) {
        try {
            String query = "UPDATE Lesson SET title = ?, description = ? WHERE lessonId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newLesson.getTitle());
            preparedStatement.setString(2, newLesson.getDescription());
            preparedStatement.setInt(3, newLesson.getLessonId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Lesson lesson) {
        try {
            String query = "DELETE FROM Lesson WHERE lessonId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, lesson.getLessonId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
