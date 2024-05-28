package collections;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Lesson {
    private int lessonId;
    private String title;
    private String description;

    public Lesson(int lessonId, String title, String description) {
        this.lessonId = lessonId;
        this.title = title;
        this.description = description;
    }

    public Lesson(int lessonId, Scanner in) {
        this.lessonId = lessonId;
        this.readData(in);
    }

    public Lesson(ResultSet in) throws SQLException {
//        this.lessonId = lessonId;
        this.readData(in);
    }

    public void readData(Scanner in) {
        System.out.print("Title: ");
        this.title = in.nextLine();
        System.out.print("Description: ");
        this.description = in.nextLine();
        System.out.println("Lesson " + this.title + " has been added.");
    }

    public void readData(ResultSet in) throws SQLException {
        this.lessonId = in .getInt("lessonId");
        this.title = in.getString("title");
        this.description = in.getString("description");
    }

    public int getLessonId() {
        return lessonId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
