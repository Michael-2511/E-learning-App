package factories;

import collections.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class StudentFactory {
    private static int id = 100;

    public static Student createStudent(Scanner in) throws ParseException {
        return new Student(id++, in);
    }
    public static Student createStudent(ResultSet in) throws SQLException {
        return new Student(in);
    }
}
