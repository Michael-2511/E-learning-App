package database;

import collections.CourseStudent;
import factories.CourseStudentFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseStudentDatabase {
    Connection connection;
    public CourseStudentDatabase(Connection connection) {
        this.connection = connection;
    }

    public List<CourseStudent> read() {
        List<CourseStudent> courseStudents = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CourseStudent");
            while (resultSet.next()) {
                CourseStudent currentCourseStudent = CourseStudentFactory.createCourseStudent(resultSet);
                courseStudents.add(currentCourseStudent);
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courseStudents;
    }

    public void delete(CourseStudent courseStudent) {
        try {
            String query = "DELETE FROM CourseStudent WHERE studentId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseStudent.studentId);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
