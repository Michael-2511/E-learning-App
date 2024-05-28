package collections;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Professor {
    private int professorId;
    private String firstName;
    private String lastName;
    private String email;
    private int experience;

    public Professor(int professorId, String firstName, String lastName, String email, int experience) {
        this.professorId = professorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.experience = experience;
    }

    public Professor(int professorId, Scanner in) {
        this.professorId = professorId;
        this.readData(in);
    }

    public Professor(ResultSet in) throws SQLException {
//        this.professorId = professorId;
        this.readData(in);
    }

    public void readData(Scanner in) {
        System.out.print("First Name: ");
        this.firstName = in.nextLine();
        System.out.print("Last Name: ");
        this.lastName = in.nextLine();
        System.out.print("Email: ");
        this.email = in.nextLine();
        System.out.print("Experience: ");
        this.experience = in.nextInt();
        System.out.println("Professor " + this.firstName + " has been added.");
    }

    public void readData(ResultSet in) throws SQLException {
        this.professorId = in.getInt("professorId");
        this.firstName = in.getString("firstName");
        this.lastName = in.getString("lastName");
        this.email = in.getString("email");
        this.experience = in.getInt("experience");
    }

    public int getProfessorId() {
        return professorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
