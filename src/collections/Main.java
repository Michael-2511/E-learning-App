package collections;

import database.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static Connection getConnection() {
        try {
            final String URL = "jdbc:mysql://localhost:3306/elearning";
            final String USER = "root";
            final String PASSWORD = "pass123";

            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {

        Connection connection = getConnection();
        CourseDatabase courseDatabase = new CourseDatabase(connection);
        LessonDatabase lessonDatabase = new LessonDatabase(connection);
        StudentDatabase studentDatabase = new StudentDatabase(connection);
        ProfessorDatabase professorDatabase = new ProfessorDatabase(connection);
        QuizDatabase quizDatabase = new QuizDatabase(connection);
        QuestionDatabase questionDatabase = new QuestionDatabase(connection);

        CourseLessonDatabase courseLessonDatabase = new CourseLessonDatabase(connection);
        CourseStudentDatabase courseStudentDatabase = new CourseStudentDatabase(connection);
        CourseProfessorDatabase courseProfessorDatabase = new CourseProfessorDatabase(connection);
        CourseQuizDatabase courseQuizDatabase = new CourseQuizDatabase(connection);
        QuizQuestionDatabase quizQuestionDatabase = new QuizQuestionDatabase(connection);

        ElearningService elearningService = ElearningService.getInstance(
                courseDatabase, lessonDatabase,
                studentDatabase, professorDatabase,
                quizDatabase, questionDatabase,
                courseLessonDatabase, courseStudentDatabase,
                courseProfessorDatabase, courseQuizDatabase,
                quizQuestionDatabase
        );

        Scanner in = new Scanner(System.in);
        boolean exit = false;

        File file = new File("commands_log.csv");
        boolean fileExists = file.exists();

        try (FileWriter csvWriter = new FileWriter(file, true)) {
            if (!fileExists) {
                csvWriter.append("Timestamp,Command\n");
            }

            while (!exit) {
                System.out.print("elearning>> ");
                String command = in.nextLine().toLowerCase();
                LocalDateTime timestamp = LocalDateTime.now();
                try {
                    csvWriter.append(timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                            .append(",")
                            .append(command)
                            .append("\n");

                    switch (command) {
                        case "create_course" -> elearningService.createCourse(in);  // (C)reate
                        case "show_courses" -> elearningService.showCourses();      // (R)ead
                        case "update_course" -> elearningService.updateCourse(in);  // (U)pdate
                        case "delete_course" -> elearningService.deleteCourse(in);  // (D)elete

                        case "create_lesson" -> elearningService.createLesson(in);
                        case "show_lessons" -> elearningService.showLessons();
                        case "update_lesson" -> elearningService.updateLesson(in);
                        case "delete_lesson" -> elearningService.deleteLesson(in);

                        case "create_professor" -> elearningService.createProfessor(in);
                        case "show_professors" -> elearningService.showProfessors();
                        case "update_professor" -> elearningService.updateProfessor(in);
                        case "delete_professor" -> elearningService.deleteProfessor(in);

                        case "create_student" -> elearningService.createStudent(in);
                        case "show_students" -> elearningService.showStudents();
                        case "update_student" -> elearningService.updateStudent(in);
                        case "delete_student" -> elearningService.deleteStudent(in);

                        case "create_quiz" -> elearningService.createQuiz(in);
                        case "show_quizzes" -> elearningService.showQuizzes();
                        case "update_quiz" -> elearningService.updateQuiz(in);
                        case "delete_quiz" -> elearningService.deleteQuiz(in);

                        case "create_question" -> elearningService.createQuestion(in);
                        case "show_questions" -> elearningService.showQuestions();
                        case "update_question" -> elearningService.updateQuestion(in);
                        case "delete_question" -> elearningService.deleteQuestion(in);

                        case "add_lesson_to_course" -> elearningService.addLessonToCourse(in);
                        case "show_course_lesson" -> elearningService.showCourseLesson();

                        case "add_student_to_course" -> elearningService.addStudentToCourse(in);
                        case "show_course_student" -> elearningService.showCourseStudent();

                        case "assign_professor_to_course" -> elearningService.assignProfessorToCourse(in);
                        case "show_course_professor" -> elearningService.showCourseProfessor();

                        case "add_quiz_to_course" -> elearningService.addQuizToCourse(in);
                        case "show_course_quiz" -> elearningService.showCourseQuiz();

                        case "add_question_to_quiz" -> elearningService.addQuestionToQuiz(in);
                        case "show_quiz_question" -> elearningService.showQuizQuestion();

                        case "exit" -> exit = true;
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
        in.close();
    }
}

//        while (!exit) {
//            System.out.print("elearning>> ");
//            String command = in.nextLine().toLowerCase();
//            try {
//                switch (command) {
//                    case "create_course" -> elearningService.createCourse(in);  // (C)reate
//                    case "show_courses" -> elearningService.showCourses();      // (R)ead
//                    case "update_course" -> elearningService.updateCourse(in);  // (U)pdate
//                    case "delete_course" -> elearningService.deleteCourse(in);  // (D)elete
//
//                    case "create_lesson" -> elearningService.createLesson(in);
//                    case "show_lessons" -> elearningService.showLessons();
//                    case "update_lesson" -> elearningService.updateLesson(in);
//                    case "delete_lesson" -> elearningService.deleteLesson(in);
//
//                    case "create_professor" -> elearningService.createProfessor(in);
//                    case "show_professors" -> elearningService.showProfessors();
//                    case "update_professor" -> elearningService.updateProfessor(in);
//                    case "delete_professor" -> elearningService.deleteProfessor(in);
//
//                    case "create_student" -> elearningService.createStudent(in);
//                    case "show_students" -> elearningService.showStudents();
//                    case "update_student" -> elearningService.updateStudent(in);
//                    case "delete_student" -> elearningService.deleteStudent(in);
//
//                    case "create_quiz" -> elearningService.createQuiz(in);
//                    case "show_quizzes" -> elearningService.showQuizzes();
//                    case "update_quiz" -> elearningService.updateQuiz(in);
//                    case "delete_quiz" -> elearningService.deleteQuiz(in);
//
//                    case "create_question" -> elearningService.createQuestion(in);
//                    case "show_questions" -> elearningService.showQuestions();
//                    case "update_question" -> elearningService.updateQuestion(in);
//                    case "delete_question" -> elearningService.deleteQuestion(in);
//
//                    case "add_lesson_to_course" -> elearningService.addLessonToCourse(in);
//                    case "show_course_lesson" -> elearningService.showCourseLesson();
//
//                    case "add_student_to_course" -> elearningService.addStudentToCourse(in);
//                    case "show_course_student" -> elearningService.showCourseStudent();
//
//                    case "assign_professor_to_course" -> elearningService.assignProfessorToCourse(in);
//                    case "show_course_professor" -> elearningService.showCourseProfessor();
//
//                    case "add_quiz_to_course" -> elearningService.addQuizToCourse(in);
//                    case "show_course_quiz" -> elearningService.showCourseQuiz();
//
//                    case "add_question_to_quiz" -> elearningService.addQuestionToQuiz(in);
//                    case "show_quiz_question" -> elearningService.showQuizQuestion();
//
//                    case "exit" -> exit = true;
//                }
//            } catch (Exception e) {
//                System.out.println(e.toString());
//            }
//        }
