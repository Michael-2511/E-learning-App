package database;

import collections.Course;
import com.mysql.cj.x.protobuf.MysqlxPrepare;
import factories.CourseFactory;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CourseDatabase {
    Connection connection;

    public CourseDatabase(Connection connection) {
        this.connection = connection;
    }

    public List<Course> read() {
        List<Course> courses = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Course");
            while(resultSet.next()) {
                Course currentCourse = CourseFactory.createCourse(resultSet);
                courses.add(currentCourse);
            }
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courses;
    }

    public void create(Course course) {
        try {
            String query = "INSERT INTO Course (courseId, title) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, course.getCourseId());
            preparedStatement.setString(2, course.getTitle());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Course newCourse) {
        try {
            String query = "UPDATE Course SET title = ? WHERE courseId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newCourse.getTitle());
            preparedStatement.setInt(2, newCourse.getCourseId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete (Course course) {
        try {
            String query = "DELETE FROM Course WHERE courseId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, course.getCourseId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
