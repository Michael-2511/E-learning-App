import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ElearningService elearningService = new ElearningService();
        Scanner in = new Scanner(System.in);
        int option;

        boolean exit = false;

        while (!exit) {
            System.out.print(">> ");
            String command = in.nextLine().toLowerCase();
            try {
                switch (command) {
                    case "create_course" -> elearningService.createCourse(in);
                    case "remove_course" -> elearningService.removeCourse(in);
                    case "show_courses" -> elearningService.showCourses();
                    case "create_student" -> elearningService.createStudent(in);
                    case "remove_student" -> elearningService.removeStudent(in);
                    case "enroll_student" -> elearningService.enrollStudent(in);
                    case "show_students" -> elearningService.showStudents();
                    case "create_professor" -> elearningService.createProfessor(in);
                    case "assign_professor" -> elearningService.assignProfessor(in);
                    case "add_lesson_to_course" -> elearningService.addLessonToCourse(in);
                    case "add_quiz_to_course" -> elearningService.addQuizToCourse(in);
                    case "exit" -> exit = true;
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

        in.close();
    }
}