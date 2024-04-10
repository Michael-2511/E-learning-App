import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ElearningService {
    private List<Course> courseList;
    private List<Student> studentList;
    private List<Quiz> quizList;
    private List<Professor> professorList;
    // nu uita sa adaugi la constructor noile liste!!!!!

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
    public void setQuizList(List<Quiz> quizList) {
        this.quizList = quizList;
    }
    public void setProfessorList(List<Professor> professorList) {
        this.professorList = professorList;
    }
    public List<Course> getCourseList() {
        return courseList;
    }
    public List<Student> getStudentList() {
        return studentList;
    }
    public List<Quiz> getQuizList() {
        return quizList;
    }
    public List<Professor> getProfessorList() {
        return professorList;
    }

    public ElearningService() {
        this.courseList = new ArrayList<>();
        this.studentList = new ArrayList<>();
        this.quizList = new ArrayList<>();
        this.professorList = new ArrayList<>();
    }

    public void createCourse(Scanner in) {
        System.out.print("Name: ");
        String courseName = in.nextLine();
        Course newCourse = new Course(courseName);
        courseList.add(newCourse);
        System.out.println("Course " + courseName + " has been added.");
    }
    public void createStudent(Scanner in) {
        System.out.print("Name: ");
        String name = in.nextLine();
        System.out.print("Email: ");
        String email = in.nextLine();
        System.out.print("Study year: ");
        int studyYear = Integer.parseInt(in.nextLine());
        Student newStudent = new Student(name, email, studyYear);
        studentList.add(newStudent);
        System.out.println("Student " + name + " has been added.");
    }

    public void removeCourse(Scanner in) {
        System.out.print("Name: ");
        String courseName = in.nextLine();

        for (Course course : courseList) {
            if (course.getTitle().equalsIgnoreCase(courseName)) {
                courseList.remove(course);
                System.out.println("Course " + courseName + " has been remove.");

                for (Student student : studentList) {
                    for (Course studentCourse : student.getCourses()) {
                        if (studentCourse.getTitle().equalsIgnoreCase(courseName)) {
                            studentList.remove(student);
                            System.out.println(student.getName() + " was enrolled in this course.");
                            break;
                        }
                    }
                }
                return;
            }
        }
        System.out.println("Course " + courseName + " does not exist.");
    }

    public void removeStudent(Scanner in) {
        System.out.print("Email: ");
        String studentEmail = in.nextLine();

        for (Student student : studentList) {
            if (student.getEmail().equals(studentEmail)) {
                studentList.remove(student);
                System.out.println("Student " + student.getName() + " has been removed.");

                for (Course course : courseList) {
                    for (Student enrolledStudent : course.getEnrolledStudents()) {
                        if (enrolledStudent.getEmail().equals(studentEmail)) {
                            courseList.remove(enrolledStudent);
                            System.out.println(studentEmail + " was removed from course " + course);
                            break;
                        }
                    }
                }
                return;
            }
        }
        System.out.println(studentEmail + " does not exist.");
    }

    public void enrollStudent(Scanner in) {
        System.out.print("Type in the name of the student: ");
        String studentName = in.nextLine();

        System.out.print("Type in the name of the course: ");
        String courseName = in.nextLine();

        for (Student student : studentList) {
            if (student.getName().equalsIgnoreCase(studentName)) {
                for (Course course : courseList) {
                    if (course.getTitle().equalsIgnoreCase(courseName)) {
                        student.addCourse(course);
                        course.addStudent(student);
                        System.out.println("Course added.");
                        return;
                    }
                }
                System.out.println("Course" + courseName + " does not exist.");
                return;
            }
        }
        System.out.println("Student " + studentName + " does not exist.");
    }

    public void addLessonToCourse(Scanner in) {
        System.out.print("Type in the name of the course: ");
        String courseName = in.nextLine();
        boolean addNewLesson = true;

        for (Course course : courseList) {
            while (addNewLesson) {
                if (course.getTitle().equalsIgnoreCase(courseName)) {
                    Lesson newLesson = createLesson(in);
                    course.addLesson(newLesson);
                    System.out.println("Lesson added.");
                    System.out.print("Would you like to add more lessons? (Y/N): ");
                    addNewLesson = addAgain(in);
                }
            }
        }
    }
    public Quiz createQuiz(Scanner in) {
        System.out.print("Title: ");
        String title = in.nextLine();
        System.out.println("Type the questions and the correct answer: ");

        boolean addNewQuestion = true;
        Quiz quiz = new Quiz(title);

        while (addNewQuestion) {
            Question newQuestion = addNewQuestion(in);
            quiz.addQuestion(newQuestion);
            System.out.print("Would you like to add more questions? (Y/N): ");
            addNewQuestion = addAgain(in);
        }
        quizList.add(quiz);
        quizList.add(quiz);
        return quiz;
    }

    public void createProfessor(Scanner in) {
        System.out.print("Name: ");
        String name = in.nextLine();
        System.out.print("Email: ");
        String email = in.nextLine();
        System.out.print("Experience: ");
        int experience = Integer.parseInt(in.nextLine());
        Professor professor = new Professor(name, email, experience);
        professorList.add(professor);
        System.out.println("Professor added.");
    }

    public void assignProfessor(Scanner in) {
        System.out.print("Type in the name of the professor: ");
        String professorName = in.nextLine();
        System.out.print("Type in the name of the course: ");
        String courseName = in.nextLine();

        for (Professor professor : professorList) {
            if (professor.getName().equalsIgnoreCase(professorName)) {
                for (Course course : courseList) {
                    if (course.getTitle().equalsIgnoreCase(courseName)) {
                        professor.setCourse(course);
                        course.setProfessor(professor);
                        System.out.println("Professor " + professorName +
                                " will teach " + courseName);
                        return;
                    }
                }
                System.out.println("Course " + courseName + " does not exist.");
                return;
            }
        }
        System.out.println("Professor " + professorName + " does not exist.");
    }

    public Question addNewQuestion(Scanner in) {
        System.out.print("Statement: ");
        String statement = in.nextLine();
        System.out.print("Correct answer: ");
        String correctAnswer = in.nextLine();

        return new Question(statement, correctAnswer);
    }

    public Lesson createLesson(Scanner in) {
        System.out.print("Title: ");
        String title = in.nextLine();
        System.out.print("Description: ");
        String description = in.nextLine();

        return new Lesson(title, description);
    }

    public boolean addAgain(Scanner in) {
        String addAnother = in.nextLine();

        while (true) {
            if (addAnother.equalsIgnoreCase("y")) {
                return true;
            } else if (addAnother.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Invalid command.");
            }
        }
    }

    public void addQuizToCourse(Scanner in) {
        System.out.print("Type in the name of the course: ");
        String courseName = in.nextLine();
        boolean addNewQuiz = true;

        for (Course course : courseList) {
            while (addNewQuiz) {
                if (course.getTitle().equalsIgnoreCase(courseName)) {
                    Quiz quiz = createQuiz(in);
                    course.addQuiz(quiz);
                    System.out.println("Quiz added.");
                    System.out.print("Would you like to add another quiz? (Y/N): ");
                    String addAgain = in.nextLine();
                    if (addAgain.equalsIgnoreCase("N")) {
                        addNewQuiz = false;
                    }
                }
            }
        }
    }

    public void showCourses() {
        if (courseList.isEmpty()) {
            System.out.println("There are no available courses.");
        } else {
            System.out.println("Courses: ");
            for (Course course : courseList) {
                System.out.println("Title: " + course.getTitle());
                System.out.println("Lessons: ");
                for (Lesson lesson : course.getLessons()) {
                    System.out.println("    -> " + lesson.getTitle());
                }
                System.out.println("Quizzes: ");
                for (Quiz quiz : course.getQuizzes()) {
                    System.out.println("    -> " + quiz.getTitle());
                }
                System.out.println("Students: ");
                for (Student student : course.getEnrolledStudents()) {
                    System.out.println("    -> " + student.getName());
                }
                System.out.println("Professor: " + course.getProfessor());
            }
        }
    }
    public void showStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students to show.");
        } else {
            // Sortez studentii alfabetic, dupa nume
            Collections.sort(studentList);

            System.out.println("Students: ");
            for (Student student : studentList) {
                System.out.println("Name: " + student.getName());
                System.out.println("Email: " + student.getEmail());
                System.out.println("StudyYear: " + student.getStudyYear());
                System.out.println("Courses:");
                for (Course course : student.getCourses()) {
                    System.out.println("    -> " + course.getTitle());
                }
            }
        }
    }
}
