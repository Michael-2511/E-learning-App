package database;

import collections.Student;
import factories.StudentFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDatabase {
    Connection connection;
    public StudentDatabase(Connection connection) {
        this.connection = connection;
    }
    public List<Student> read() {
        List<Student> students = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Student");
            while (resultSet.next()) {
                Student currentStudent = StudentFactory.createStudent(resultSet);
                students.add(currentStudent);
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    public void create(Student student) {
        try {
            String query = "INSERT INTO Student (studentId, firstName, lastName, email, studyYear) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, student.getStudentId());
            preparedStatement.setString(2, student.getFirstName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.setString(4, student.getEmail());
            preparedStatement.setInt(5, student.getStudyYear());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Student newStudent) {
        try {
            String query = "UPDATE Student SET firstName = ?, lastName = ?, email = ?, studyYear = ? WHERE studentId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newStudent.getFirstName());
            preparedStatement.setString(2, newStudent.getLastName());
            preparedStatement.setString(3, newStudent.getEmail());
            preparedStatement.setInt(4, newStudent.getStudyYear());
            preparedStatement.setInt(5, newStudent.getStudentId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Student student) {
        try {
            String query = "DELETE FROM Student WHERE studentId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, student.getStudentId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
