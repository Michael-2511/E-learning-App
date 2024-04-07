import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ElearningService {
    private List<Course> courseList;

    private List<Student> studentList;

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public ElearningService() {
        this.courseList = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courseList.add(course);
    }

    public void addCourseNew(Scanner in) {
        System.out.print("Name: ");
        String courseName = in.nextLine();
        Course newCourse = new Course(courseName);
        courseList.add(newCourse);
        System.out.println("Course " + courseName + " has been added.");
    }

    public void addStudent(Scanner in) {
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

    public void deleteCourseNew(Scanner in) {
        System.out.print("Name: ");
        String courseName = in.nextLine();

        for (Course course : courseList) {
            if (course.getTitle().equalsIgnoreCase(courseName)) {
                courseList.remove(course);
                System.out.println("Course " + courseName + " has been deleted.");
                // iterez prin studenti pentru a sterge cursul bla bla bla

                for (Student student : studentList) {
                    for (Course studentCourse : student.getCourses()) {
                        if (studentCourse.getTitle().equalsIgnoreCase(courseName)) {
                            studentList.remove(student);
                            System.out.println(student.getName() + " was enrolled in this course.");
                            break;
                        }
                    }
                }

                return;  // Exit the method after removal
            }
        }
        // If course not found
        System.out.println("The course " + courseName + " does not exist.");
    }


    public void deleteCourse(String courseName) {
        // Iterate through the courseList to find the course by name
        for (Course course : courseList) {
            if (course.getTitle().equalsIgnoreCase(courseName)) {
                courseList.remove(course);
                System.out.println("Cursul " + courseName + " a fost sters.");
                // iterez prin studenti pentru a sterge cursul bla bla bla

                for (Student student : studentList) {
                    for (Course studentCourse : student.getCourses()) {
                        if (studentCourse.getTitle().equalsIgnoreCase(courseName)) {
                            studentList.remove(student);
                            System.out.println(student.getName() + " participa la acest curs.");
                            break;
                        }
                    }
                }

                return;  // Exit the method after removal
            }
        }
        // If course not found
        System.out.println("The course " + courseName + " does not exist.");
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void showCourses() {
        if (courseList.isEmpty()) {
            System.out.println("There are no available courses.");
        } else {
            System.out.println("Available courses: ");
            for (Course course : courseList) {
                System.out.println("-> " + course.getTitle());
            }
        }
    }
    public void showStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students to show.");
        } else {
            System.out.println("Students: ");
            for (Student student : studentList) {
                System.out.println("-> " + student.getName());
            }
        }
    }
}
