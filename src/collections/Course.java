package collections;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

public class Course {
    private int courseId;
    private String title;
    public Course(int courseId, String title) {
        this.courseId = courseId;
        this.title = title;
    }

    public Course(int courseId, Scanner in) throws ParseException {
        this.courseId = courseId;
        this.readData(in);
    }

    public Course(ResultSet in) throws SQLException {
//        this.courseId = courseId;
        this.readData(in);
    }

    public void readData(Scanner in) {
        System.out.print("Name: ");
        this.title = in.nextLine();
        System.out.println("Course " + this.title + " has been added.");
    }

    public void readData(ResultSet in) throws SQLException {
        this.courseId = in.getInt("courseId");
        this.title = in.getString("title");
    }

    public int getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
