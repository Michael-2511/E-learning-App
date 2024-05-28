package collections;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Quiz {
    private int quizId;
    private String title;

    public Quiz(int quizId, String title) {
        this.quizId = quizId;
        this.title = title;
    }

    public Quiz(int quizId, Scanner in) {
        this.quizId = quizId;
        this.readData(in);
    }

    public Quiz(ResultSet in) throws SQLException {
//        this.quizId = quizId;
        this.readData(in);
    }

    public void readData(Scanner in) {
        System.out.print("Title: ");
        this.title = in.nextLine();
        System.out.println("Quiz " + this.title + " has been added.");
    }

    public void readData(ResultSet in) throws SQLException {
        this.quizId = in.getInt("quizId");
        this.title = in.getString("title");
    }
    public int getQuizId() {
        return quizId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
