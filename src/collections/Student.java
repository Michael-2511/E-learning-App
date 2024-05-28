package collections;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Student implements Comparable<Student> {
    private int studentId;
    private String firstName;
    private String lastName;
    private String email;
    private int studyYear;

    public Student(int studentId, String firstName, String lastName, String email, int studyYear) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.studyYear = studyYear;
    }

    public Student(int studentId, Scanner in) {
        this.studentId = studentId;
        this.readData(in);
    }

    public Student(ResultSet in) throws SQLException {
//        this.studentId = studentId;
        this.readData(in);
    }

    public void readData(Scanner in) {
        System.out.print("First Name: ");
        this.firstName = in.nextLine();
        System.out.print("Last Name: ");
        this.lastName = in.nextLine();
        System.out.print("Email: ");
        this.email = in.nextLine();
        System.out.print("Study year: ");
        this.studyYear = in.nextInt();
        System.out.println("Student " + this.firstName + " has been added.");
    }

    public void readData(ResultSet in) throws SQLException {
        this.studentId = in.getInt("studentId");
        this.firstName = in.getString("firstName");
        this.lastName = in.getString("lastName");
        this.email = in.getString("email");
        this.studyYear = in.getInt("studyYear");
    }

    public int getStudentId() {
        return studentId;
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

    public int getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

//    public Set<Course> getCourses() {
//        return courses;
//    }
//
//    public boolean addCourse(Course course) {
//        return this.courses.add(course);
//    }
//
//    public List<Grade> getGrades() {
//        return grades;
//    }
//
//    public void addGrade(Grade grade) {
//        this.grades.add(grade);
//    }

    @Override
    public int compareTo(Student otherstudent) {
        return this.getFirstName().compareTo(otherstudent.getFirstName());
    }
}
