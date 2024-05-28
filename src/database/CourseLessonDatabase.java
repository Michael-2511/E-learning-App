package database;

import collections.Course;
import collections.CourseLesson;
import factories.CourseLessonFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseLessonDatabase {
    Connection connection;
    public CourseLessonDatabase(Connection connection) {
        this.connection = connection;
    }

    public List<CourseLesson> read() {
        List<CourseLesson> courseLessons = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CourseLesson");
            while (resultSet.next()) {
                CourseLesson currentCourseLesson = CourseLessonFactory.createCourseLesson(resultSet);
                courseLessons.add(currentCourseLesson);
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courseLessons;
    }

    public void create(CourseLesson courseLesson) {
        try {
            String query = "INSERT INTO CourseLesson (courseId, lessonId) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseLesson.courseId);
            preparedStatement.setInt(2, courseLesson.lessonId);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete (CourseLesson courseLesson) {
        try {
            String query = "DELETE FROM CourseLesson WHERE courseId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseLesson.courseId);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
