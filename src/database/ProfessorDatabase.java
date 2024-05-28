package database;

import collections.Professor;
import factories.ProfessorFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDatabase {
    Connection connection;
    public ProfessorDatabase(Connection connection) {
        this.connection = connection;
    }
    public List<Professor> read() {
        List<Professor> professors = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Professor");
            while (resultSet.next()) {
                Professor currentProfessor = ProfessorFactory.createProfessor(resultSet);
                professors.add(currentProfessor);
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return professors;
    }

    public void create(Professor professor) {
        try {
            String query = "INSERT INTO Professor (professorId, firstName, lastName, email, experience) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, professor.getProfessorId());
            preparedStatement.setString(2, professor.getFirstName());
            preparedStatement.setString(3, professor.getLastName());
            preparedStatement.setString(4, professor.getEmail());
            preparedStatement.setInt(5, professor.getExperience());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Professor newProfessor) {
        try {
            String query = "UPDATE Professor SET firstName = ?, lastName = ?, email = ?, experience = ? WHERE professorId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newProfessor.getFirstName());
            preparedStatement.setString(2, newProfessor.getLastName());
            preparedStatement.setString(3, newProfessor.getEmail());
            preparedStatement.setInt(4, newProfessor.getExperience());
            preparedStatement.setInt(5, newProfessor.getProfessorId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Professor professor) {
        try {
            String query = "DELETE FROM Professor WHERE professorId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, professor.getProfessorId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
