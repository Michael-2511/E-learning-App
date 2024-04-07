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
                    case "add_course" -> elearningService.addCourseNew(in);
                    case "delete_course" -> elearningService.deleteCourseNew(in);
                    case "show_courses" -> elearningService.showCourses();
                    case "add_student" -> elearningService.addStudent(in);
                    case "exit" -> exit = true;
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

//        do {
//            System.out.println("Meniu:");
//            System.out.println("1 - Adauga curs");
//            System.out.println("2 - Adauga utilizator");
//            System.out.println("3 - Sterge curs");
//            System.out.println("4 - Afisare curs");
//            System.out.println("100 - Exit");
//            System.out.print("Alege optiunea: ");
//
//            option = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (option) {
//                case 0: {
//
//                }
//                case 1: {
//                    System.out.print("Introdu numele cursului pe care vrei sa il adaugi: ");
//                    String courseName = scanner.nextLine();
//                    elearningService.addCourse(new Course(courseName));
//                    System.out.println("Cursul " + courseName + " a fost adaugat.");
//                    break;
//                }
//                case 3: {
//                    // sterge curs
//                    System.out.print("Introdu numele cursului pe care vrei sa il stergi: ");
//                    String courseName = scanner.nextLine();
//                    elearningService.deleteCourse(courseName);
//                    break;
//                }
//                case 4: {
//                    elearningService.showCourses();
//                    break;
//                }
//                case 100: {
//                    System.out.println("Programul a fost oprit.");
//                    break;
//                }
//                default: {
//                    System.out.println("Optiune invalida. Te rog sa introduci o optiune valida.");
//                    break;
//                    }
//            }
//        } while (option != 100);

        in.close();
    }
}