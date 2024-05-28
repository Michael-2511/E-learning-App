package factories;

import collections.Professor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class ProfessorFactory {
    private static int id = 1;

    public static Professor createProfessor(Scanner in) throws ParseException {
        return new Professor(id++, in);
    }
    public static Professor createProfessor(ResultSet in) throws SQLException {
        return new Professor(in);
    }
}
