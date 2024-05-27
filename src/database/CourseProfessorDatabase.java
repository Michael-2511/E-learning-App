package database;

import collections.CourseProfessor;
import factories.CourseProfessorFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseProfessorDatabase {
    Connection connection;

    public CourseProfessorDatabase(Connection connection) {
        this.connection = connection;
    }

    public List<CourseProfessor> read() {
        List<CourseProfessor> courseProfessors = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CourseProfessor");
            while (resultSet.next()) {
                CourseProfessor currentCourseProfessor = CourseProfessorFactory.createCourseProfessor(resultSet);
                courseProfessors.add(currentCourseProfessor);
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courseProfessors;
    }
    public void delete(CourseProfessor courseProfessor) {
        try {
            String query = "DELETE FROM CourseProfessor WHERE professorId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseProfessor.professorId);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

