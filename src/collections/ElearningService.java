package collections;

import database.*;
import factories.*;

import java.text.ParseException;
import java.util.*;

public class ElearningService {
    private static ElearningService instance;
    private List<Course> courseList;
    private List<Lesson> lessonList;
    private List<Student> studentList;
    private List<Professor> professorList;
    private List<Quiz> quizList;
    private List<Question> questionList;

    private List<CourseLesson> courseLessonList;
    private List<CourseStudent> courseStudentList;
    private List<CourseProfessor> courseProfessorList;
    private List<CourseQuiz> courseQuizList;
    private List<QuizQuestion> quizQuestionList;

    private CourseDatabase courseDatabase;
    private LessonDatabase lessonDatabase;
    private StudentDatabase studentDatabase;
    private ProfessorDatabase professorDatabase;
    private QuizDatabase quizDatabase;
    private QuestionDatabase questionDatabase;

    private CourseLessonDatabase courseLessonDatabase;
    private CourseStudentDatabase courseStudentDatabase;
    private CourseProfessorDatabase courseProfessorDatabase;
    private CourseQuizDatabase courseQuizDatabase;
    private QuizQuestionDatabase quizQuestionDatabase;

    public ElearningService(CourseDatabase courseDatabase,
                            LessonDatabase lessonDatabase,
                            StudentDatabase studentDatabase,
                            ProfessorDatabase professorDatabase,
                            QuizDatabase quizDatabase,
                            QuestionDatabase questionDatabase,
                            CourseLessonDatabase courseLessonDatabase,
                            CourseStudentDatabase courseStudentDatabase,
                            CourseProfessorDatabase courseProfessorDatabase,
                            CourseQuizDatabase courseQuizDatabase,
                            QuizQuestionDatabase quizQuestionDatabase
                            ) {
        this.courseDatabase = courseDatabase;
        this.lessonDatabase = lessonDatabase;
        this.studentDatabase = studentDatabase;
        this.professorDatabase = professorDatabase;
        this.quizDatabase = quizDatabase;
        this.questionDatabase = questionDatabase;

        this.courseLessonDatabase = courseLessonDatabase;
        this.courseStudentDatabase = courseStudentDatabase;
        this.courseProfessorDatabase = courseProfessorDatabase;
        this.courseQuizDatabase = courseQuizDatabase;
        this.quizQuestionDatabase = quizQuestionDatabase;

        this.courseList = courseDatabase.read();
        this.lessonList = lessonDatabase.read();
        this.studentList = studentDatabase.read();
        this.professorList = professorDatabase.read();
        this.quizList = quizDatabase.read();
        this.questionList = questionDatabase.read();

        this.courseLessonList = courseLessonDatabase.read();
        this.courseStudentList = courseStudentDatabase.read();
        this.courseProfessorList = courseProfessorDatabase.read();
        this.courseQuizList = courseQuizDatabase.read();
        this.quizQuestionList = quizQuestionDatabase.read();
    }

    public static ElearningService getInstance(CourseDatabase courseDatabase,
                                               LessonDatabase lessonDatabase,
                                               StudentDatabase studentDatabase,
                                               ProfessorDatabase professorDatabase,
                                               QuizDatabase quizDatabase,
                                               QuestionDatabase questionDatabase,
                                               CourseLessonDatabase courseLessonDatabase,
                                               CourseStudentDatabase courseStudentDatabase,
                                               CourseProfessorDatabase courseProfessorDatabase,
                                               CourseQuizDatabase courseQuizDatabase,
                                               QuizQuestionDatabase quizQuestionDatabase) {
        if (instance == null) {
            instance = new ElearningService(courseDatabase, lessonDatabase, studentDatabase, professorDatabase, quizDatabase, questionDatabase,
                    courseLessonDatabase, courseStudentDatabase, courseProfessorDatabase, courseQuizDatabase, quizQuestionDatabase);
        }
        return instance;
    }

    public void createCourse(Scanner in) throws ParseException {
        Course newCourse = CourseFactory.createCourse(in);
        this.courseList.add(newCourse);
        this.courseDatabase.create(newCourse);
        System.out.println("Course " + newCourse.getTitle() + " has been added.");
    }

    public void showCourses() {
        if (courseList.isEmpty()) {
            System.out.println("There are no available courses.");
        } else {
            System.out.println("Courses: ");
            for (Course course : courseList) {
                System.out.println("ID: " + course.getCourseId());
                System.out.println("Title: " + course.getTitle());
            }
        }
    }

    public void updateCourse(Scanner in) {
        if (courseList.isEmpty()) {
            System.out.println("There are no available courses.");
        } else {
            System.out.print("Type in the ID of the course you want to update: ");
            int courseId = in.nextInt();
            Course course = getCourseById(courseId);
            if (course != null) {
                System.out.print("Type in the new title of the course: ");
                String newCourseTitle = in.next();
                course.setTitle(newCourseTitle);
                this.courseDatabase.update(course);
            } else {
                System.out.println("Invalid course ID.");
            }
        }
    }

    public void deleteCourse(Scanner in) {
        if (courseList.isEmpty()) {
            System.out.println("There are no available courses.");
        } else {
            System.out.print("Type in the ID of the course you want to delete: ");
            int courseId = in.nextInt();
            Course course = getCourseById(courseId);
            if (course != null) {
                deleteFromCourseLesson(courseId);
                deleteFromCourseProfessor(courseId);
                deleteFromCourseStudent(courseId);
                deleteFromCourseQuiz(courseId);
                this.courseList.remove(course);
                this.courseDatabase.delete(course);
            } else {
                System.out.println("Invalid course ID.");
            }
        }
    }

    public void deleteFromCourseLesson(int courseId) {
        Iterator<CourseLesson> iterator = courseLessonList.iterator();
        while (iterator.hasNext()) {
            CourseLesson courseLesson = iterator.next();
            if (courseLesson.courseId == courseId) {
                iterator.remove();
                courseLessonDatabase.delete(courseLesson);
            }
        }
    }

    public void deleteFromCourseStudent(int courseId) {
        Iterator<CourseStudent> iterator = courseStudentList.iterator();
        while (iterator.hasNext()) {
            CourseStudent courseStudent = iterator.next();
            if (courseStudent.courseId == courseId) {
                iterator.remove();
                courseStudentDatabase.delete(courseStudent);
            }
        }
    }

    public void deleteFromCourseProfessor(int courseId) {
        Iterator<CourseProfessor> iterator = courseProfessorList.iterator();
        while (iterator.hasNext()) {
            CourseProfessor courseProfessor = iterator.next();
            if (courseProfessor.courseId == courseId) {
                iterator.remove();
                courseProfessorDatabase.delete(courseProfessor);
            }
        }
    }

    public void deleteFromCourseQuiz(int courseId) {
        Iterator<CourseQuiz> iterator = courseQuizList.iterator();
        while (iterator.hasNext()) {
            CourseQuiz courseQuiz = iterator.next();
            if (courseQuiz.courseId == courseId) {
                iterator.remove();
                courseQuizDatabase.delete(courseQuiz);
            }
        }
    }

    public void deleteStudent(Scanner in) {
        if (studentList.isEmpty()) {
            System.out.println("There are no students.");
        } else {
            System.out.print("Type in the ID of the student you want to delete: ");
            int studentId = in.nextInt();
            Student student = getStudentById(studentId);
            if (student != null) {
                deleteStudentFromCourse(studentId);
                this.studentList.remove(student);
                this.studentDatabase.delete(student);
            } else {
                System.out.println("Invalid course ID.");
            }
        }
    }

    public void deleteStudentFromCourse(int studentId) {
        Iterator<CourseStudent> iterator = courseStudentList.iterator();
        while (iterator.hasNext()) {
            CourseStudent courseStudent = iterator.next();
            if (courseStudent.studentId == studentId) {
                iterator.remove();
                courseStudentDatabase.delete(courseStudent);
            }
        }
    }

    public void deleteLesson(Scanner in) {
        if (lessonList.isEmpty()) {
            System.out.println("There are no lessons.");
        } else {
            System.out.print("Type in the ID of the lesson you want to delete: ");
            int lessonId = in.nextInt();
            Lesson lesson = getLessonById(lessonId);
            if (lesson != null) {
                deleteLessonFromCourse(lessonId);
                this.lessonList.remove(lesson);
                this.lessonDatabase.delete(lesson);
            } else {
                System.out.println("Invalid lesson ID.");
            }
        }
    }

    public void deleteLessonFromCourse(int lessonId) {
        Iterator<CourseLesson> iterator = courseLessonList.iterator();
        while (iterator.hasNext()) {
            CourseLesson courseLesson = iterator.next();
            if (courseLesson.lessonId == lessonId) {
                iterator.remove();
                courseLessonDatabase.delete(courseLesson);
            }
        }
    }

    public void deleteProfessor(Scanner in) {
        if (professorList.isEmpty()) {
            System.out.println("There are no professors.");
        } else {
            System.out.print("Type in the ID of the professor you want to delete: ");
            int professorId = in.nextInt();
            Professor professor = getProfessorById(professorId);
            if (professor != null) {
                deleteProfessorFromCourse(professorId);
                this.professorList.remove(professor);
                this.professorDatabase.delete(professor);
            } else {
                System.out.println("Invalid professor ID.");
            }
        }
    }

    public void deleteProfessorFromCourse(int professorId) {
        Iterator<CourseProfessor> iterator = courseProfessorList.iterator();
        while (iterator.hasNext()) {
            CourseProfessor courseProfessor = iterator.next();
            if (courseProfessor.professorId == professorId) {
                iterator.remove();
                courseProfessorDatabase.delete(courseProfessor);
            }
        }
    }

    public void deleteQuiz(Scanner in) {
        if (quizList.isEmpty()) {
            System.out.println("There are no quizzes.");
        } else {
            System.out.print("Type in the ID of the quiz you want to delete: ");
            int quizId = in.nextInt();
            Quiz quiz = getQuizById(quizId);
            if (quiz != null) {
                deleteQuizFromCourse(quizId);
                this.quizList.remove(quiz);
                this.quizDatabase.delete(quiz);
            } else {
                System.out.println("Invalid quiz ID.");
            }
        }
    }

    public void deleteQuizFromCourse(int quizId) {
        Iterator<CourseQuiz> iterator = courseQuizList.iterator();
        while (iterator.hasNext()) {
            CourseQuiz courseQuiz = iterator.next();
            if (courseQuiz.quizId == quizId) {
                deleteFromQuizQuestion(quizId);
                iterator.remove();
                courseQuizDatabase.delete(courseQuiz);
            }
        }
    }

    public void deleteFromQuizQuestion(int quizId) {
        Iterator<QuizQuestion> iterator = quizQuestionList.iterator();
        while (iterator.hasNext()) {
            QuizQuestion quizQuestion = iterator.next();
            if (quizQuestion.quizId == quizId) {
                iterator.remove();
                quizQuestionDatabase.delete(quizQuestion);
            }
        }
    }

    public void deleteQuestion(Scanner in) {
        if (questionList.isEmpty()) {
            System.out.println("There are no questions.");
        } else {
            System.out.print("Type in the ID of the question you want to delete: ");
            int questionId = in.nextInt();
            Question question = getQuestionById(questionId);
            if (question != null) {
                deleteQuestionFromQuiz(questionId);
                this.questionList.remove(question);
                this.questionDatabase.delete(question);
            } else {
                System.out.println("Invalid question ID.");
            }
        }
    }

    public void deleteQuestionFromQuiz(int questionId) {
        Iterator<QuizQuestion> iterator = quizQuestionList.iterator();
        while (iterator.hasNext()) {
            QuizQuestion quizQuestion = iterator.next();
            if (quizQuestion.questionId == questionId) {
                iterator.remove();
                quizQuestionDatabase.delete(quizQuestion);
            }
        }
    }

    public void updateLesson(Scanner in) {
        if (lessonList.isEmpty()) {
            System.out.println("There are no available lessons.");
        } else {
            System.out.print("Type in the ID of the lesson you want to update: ");
            int lessonId = in.nextInt();
            Lesson lesson = getLessonById(lessonId);
            if (lesson != null) {
                System.out.print("Type in the new title of the lesson: ");
                String newLessonTitle = in.next();
                System.out.print("Type in the new description of the lesson: ");
                String newLessonDescription = in.next();
                lesson.setTitle(newLessonTitle);
                lesson.setDescription(newLessonDescription);
                this.lessonDatabase.update(lesson);
            } else {
                System.out.println("Invalid lesson ID.");
            }
        }
    }

    public void updateProfessor(Scanner in) {
        if (professorList.isEmpty()) {
            System.out.println("There are no available professors.");
        } else {
            System.out.print("Type in the ID of the professor you want to update: ");
            int professorId = in.nextInt();
            Professor professor = getProfessorById(professorId);
            if (professor != null) {
                System.out.print("Type in the new first name of the professor: ");
                String newFirstName = in.next();
                System.out.print("Type in the new last name of the professor: ");
                String newLastName = in.next();
                System.out.print("Type in the new email of the professor: ");
                String newEmail = in.next();
                System.out.print("Type in the new experience of the professor: ");
                int newExperience = in.nextInt();

                professor.setFirstName(newFirstName);
                professor.setLastName(newLastName);
                professor.setEmail(newEmail);
                professor.setExperience(newExperience);

                this.professorDatabase.update(professor);
            } else {
                System.out.println("Invalid professor ID.");
            }
        }
    }

    public void updateStudent(Scanner in) {
        if (studentList.isEmpty()) {
            System.out.println("There are no available students.");
        } else {
            System.out.print("Type in the ID of the student you want to update: ");
            int studentId = in.nextInt();
            Student student = getStudentById(studentId);
            if (student != null) {
                System.out.print("Type in the new first name of the student: ");
                String newFirstName = in.next();
                System.out.print("Type in the new last name of the student: ");
                String newLastName = in.next();
                System.out.print("Type in the new email of the student: ");
                String newEmail = in.next();
                System.out.print("Type in the new study year of the student: ");
                int newStudyYear = in.nextInt();

                student.setFirstName(newFirstName);
                student.setLastName(newLastName);
                student.setEmail(newEmail);
                student.setStudyYear(newStudyYear);

                this.studentDatabase.update(student);
            } else {
                System.out.println("Invalid student ID.");
            }
        }
    }

    public void updateQuiz(Scanner in) {
        if (quizList.isEmpty()) {
            System.out.println("There are no available quizzes.");
        } else {
            System.out.print("Type in the ID of the quiz you want to update: ");
            int quizId = in.nextInt();
            Quiz quiz = getQuizById(quizId);
            if (quiz != null) {
                System.out.print("Type in the new title of the quiz: ");
                String newTitle = in.next();

                quiz.setTitle(newTitle);

                this.quizDatabase.update(quiz);
            } else {
                System.out.println("Invalid quiz ID.");
            }
        }
    }

    public void updateQuestion(Scanner in) {
        if (questionList.isEmpty()) {
            System.out.println("There are no available questions.");
        } else {
            System.out.print("Type in the ID of the question you want to update: ");
            int questionId = in.nextInt();
            Question question = getQuestionById(questionId);
            if (question != null) {
                System.out.print("Type in the new statement of the question: ");
                String newStatement = in.next();
                System.out.print("Type in the new correct answer of the question: ");
                String newCorrectAnswer = in.next();

                question.setStatement(newStatement);
                question.setCorrectAnswer(newCorrectAnswer);

                this.questionDatabase.update(question);
            } else {
                System.out.println("Invalid question ID.");
            }
        }
    }


    public void createLesson(Scanner in) throws ParseException {
        Lesson newLesson = LessonFactory.createLesson(in);
        this.lessonList.add(newLesson);
        this.lessonDatabase.create(newLesson);
        System.out.println("Lesson " + newLesson.getTitle() + " has been added.");
    }

    public void createStudent(Scanner in) throws ParseException {
        Student newStudent = StudentFactory.createStudent(in);
        this.studentList.add(newStudent);
        this.studentDatabase.create(newStudent);
        System.out.println("Student " + newStudent.getFirstName() + " has been added");
    }

    public void createProfessor(Scanner in) throws ParseException {
        Professor newProfessor = ProfessorFactory.createProfessor(in);
        this.professorList.add(newProfessor);
        this.professorDatabase.create(newProfessor);
        System.out.println("Professor " + newProfessor.getFirstName() + " has been added");
    }

    public void createQuiz(Scanner in) {
        Quiz newQuiz = QuizFactory.createQuiz(in);
        this.quizList.add(newQuiz);
        this.quizDatabase.create(newQuiz);
        System.out.println("Quiz " + newQuiz.getTitle() + " has been added");
    }

    public void createQuestion(Scanner in) {
        Question newQuestion = QuestionFactory.createQuestion(in);
        this.questionList.add(newQuestion);
        this.questionDatabase.create(newQuestion);
        System.out.println("Question " + newQuestion.getStatement() + " has been added");
    }

    public void showLessons() {
        if (lessonList.isEmpty()) {
            System.out.println("There are no available lessons.");
        } else {
            System.out.println("Lessons: ");
            for (Lesson lesson : lessonList) {
                System.out.println("ID: " + lesson.getLessonId());
                System.out.println("Title: " + lesson.getTitle());
                System.out.println("Description: " + lesson.getDescription());
            }
        }
    }

    public void showStudents() {
        if (studentList.isEmpty()) {
            System.out.println("There are no students.");
        } else {
            System.out.println("Students: ");
            for (Student student : studentList) {
                System.out.println("ID: " + student.getStudentId());
                System.out.println("First Name: " + student.getFirstName());
                System.out.println("Last Name: " + student.getLastName());
                System.out.println("Email: " + student.getEmail());
                System.out.println("Study year: " + student.getStudyYear());
            }
        }
    }

    public void showProfessors() {
        if (professorList.isEmpty()) {
            System.out.println("There are no professors.");
        } else {
            System.out.println("Professors: ");
            for (Professor professor : professorList) {
                System.out.println("ID: " + professor.getProfessorId());
                System.out.println("First Name: " + professor.getFirstName());
                System.out.println("Last Name: " + professor.getLastName());
                System.out.println("Email: " + professor.getEmail());
                System.out.println("Study year: " + professor.getExperience());
            }
        }
    }

    public void showQuizzes() {
        if (quizList.isEmpty()) {
            System.out.println("There are no quizzes.");
        } else {
            System.out.println("Quizzes: ");
            for (Quiz quiz : quizList) {
                System.out.println("ID: " + quiz.getQuizId());
                System.out.println("Title: " + quiz.getTitle());
            }
        }
    }

    public void showQuestions() {
        if (questionList.isEmpty()) {
            System.out.println("There are no questions.");
        } else {
            System.out.println("Questions: ");
            for (Question question : questionList) {
                System.out.println("ID: " + question.getQuestionId());
                System.out.println("Statement: " + question.getStatement());
                System.out.println("Correct Answer: " + question.getCorrectAnswer());
            }
        }
    }

    public void addLessonToCourse(Scanner in) {
        System.out.print("Type in the name of the course: ");
        String courseName = in.nextLine();
        boolean addNewLesson = true;
        while (addNewLesson) {
            for (Course course : courseList) {
                if (course.getTitle().equalsIgnoreCase(courseName)) {
                    System.out.print("Type in the name of the lesson you want to add to this course: ");
                    String lessonName = in.nextLine();
                    boolean lessonFound = false;
                    for (Lesson lesson : lessonList) {
                        if (lesson.getTitle().equalsIgnoreCase(lessonName)) {
                            lessonFound = true;
                            CourseLesson courseLesson = new CourseLesson(course.getCourseId(), lesson.getLessonId());
                            this.courseLessonList.add(courseLesson);
                            this.courseLessonDatabase.create(courseLesson);
                            break;
                        }
                    }
                    if (lessonFound) {
                        System.out.println(lessonName + " has been added to couse " + courseName);
                    } else {
                        System.out.println("Lesson " + lessonName + " not found");
                    }
                    System.out.print("Would you like to add more lessons? (Y/N): ");
                    addNewLesson = addAgain(in);
                }
            }
        }
    }

    public void showCourseLesson() {
        for (CourseLesson courseLesson : courseLessonList) {
            System.out.println(courseLesson.courseId + " " + courseLesson.lessonId);
        }
    }

    public void addStudentToCourse(Scanner in) {
        System.out.print("Type in the name of the course: ");
        String courseName = in.nextLine();
        boolean addNewStudent = true;

        while (addNewStudent) {
            for (Course course : courseList) {
                if (course.getTitle().equalsIgnoreCase(courseName)) {
                    System.out.print("Type in the name of the student you want to add to this course: ");
                    String studentName = in.nextLine();
                    boolean studentFound = false;
                    for (Student student : studentList) {
                        if (student.getFirstName().equalsIgnoreCase(studentName)) {
                            studentFound = true;
                            CourseStudent courseStudent = new CourseStudent(course.getCourseId(), student.getStudentId());
                            this.courseStudentList.add(courseStudent);
                            this.courseStudentDatabase.create(courseStudent);
                            break;
                        }
                    }
                    if (studentFound) {
                        System.out.println(studentName + " has been added to couse " + courseName);
                    } else {
                        System.out.println("Student " + studentName + " not found");
                    }
                    System.out.print("Would you like to add more students? (Y/N): ");
                    addNewStudent = addAgain(in);
                }
            }
        }
    }

    public void showCourseStudent() {
        for (CourseStudent courseStudent : courseStudentList) {
            System.out.println(courseStudent.courseId + " " + courseStudent.studentId);
        }
    }

    public void assignProfessorToCourse(Scanner in) {
        System.out.print("Type in the name of the course: ");
        String courseName = in.nextLine();
        boolean addNewProfessor = true;

        while (addNewProfessor) {
            for (Course course : courseList) {
                if (course.getTitle().equalsIgnoreCase(courseName)) {
                    System.out.print("Type in the name of the professor you want to add to this course: ");
                    String professorName = in.nextLine();
                    boolean professorFound = false;
                    for (Professor professor : professorList) {
                        if (professor.getFirstName().equalsIgnoreCase(professorName)) {
                            professorFound = true;
                            CourseProfessor courseProfessor = new CourseProfessor(course.getCourseId(), professor.getProfessorId());
                            this.courseProfessorList.add(courseProfessor);
                            this.courseProfessorDatabase.create(courseProfessor);
                            break;
                        }
                    }
                    if (professorFound) {
                        System.out.println(professorName + " has been added to course " + courseName);
                        return;
                    } else {
                        System.out.println("Professor " + professorName + " not found");
                        System.out.print("Would you like to try again to assign a professor? (Y/N): ");
                        addNewProfessor = addAgain(in);
                    }
                }
            }
        }
    }

    public void showCourseProfessor() {
        for (CourseProfessor courseProfessor : courseProfessorList) {
            System.out.println(courseProfessor.courseId + " " + courseProfessor.professorId);
        }
    }

    public void addQuizToCourse(Scanner in) {
        System.out.print("Type in the name of the course: ");
        String courseName = in.nextLine();
        boolean addNewQuiz = true;

        while (addNewQuiz) {
            for (Course course : courseList) {
                if (course.getTitle().equalsIgnoreCase(courseName)) {
                    System.out.print("Type in the title of the quiz you want to add to this course: ");
                    String quizTitle = in.nextLine();
                    boolean quizFound = false;
                    for (Quiz quiz : quizList) {
                        if (quiz.getTitle().equalsIgnoreCase(quizTitle)) {
                            quizFound = true;
                            CourseQuiz courseQuiz = new CourseQuiz(course.getCourseId(), quiz.getQuizId());
                            this.courseQuizList.add(courseQuiz);
                            this.courseQuizDatabase.create(courseQuiz);
                            break;
                        }
                    }
                    if (quizFound) {
                        System.out.println(quizTitle + " has been added to course " + courseName);
                    } else {
                        System.out.println("Quiz " + quizTitle + " not found");
                    }
                    System.out.print("Would you like to add more quizzes? (Y/N): ");
                    addNewQuiz = addAgain(in);
                }
            }
        }
    }

    public void showCourseQuiz() {
        for (CourseQuiz courseQuiz : courseQuizList) {
            System.out.println(courseQuiz.courseId + " " + courseQuiz.quizId);
        }
    }

    public void addQuestionToQuiz(Scanner in) {
        System.out.print("Type in the title of the quiz: ");
        String quizTitle = in.nextLine();
        boolean addNewQuestion = true;

        while (addNewQuestion) {
            for (Quiz quiz : quizList) {
                if (quiz.getTitle().equalsIgnoreCase(quizTitle)) {
                    System.out.print("Type in the statement of the question you want to add to this quiz: ");
                    String questionStatement = in.nextLine();
                    boolean questionFound = false;


                    for (Question question : questionList) {
                        if (question.getStatement().equalsIgnoreCase(questionStatement)) {
                            questionFound = true;
                            QuizQuestion quizQuestion = new QuizQuestion(quiz.getQuizId(), question.getQuestionId());
                            this.quizQuestionList.add(quizQuestion);
                            this.quizQuestionDatabase.create(quizQuestion);
                            break;
                        }
                    }

                    if (questionFound) {
                        System.out.println("Question added to quiz " + quizTitle);
                    } else {
                        System.out.println("Question not found");
                    }
                    System.out.print("Would you like to add more questions? (Y/N): ");
                    addNewQuestion = addAgain(in);
                }
            }
        }
    }

    public void showQuizQuestion() {
        for (QuizQuestion quizQuestion : quizQuestionList) {
            System.out.println(quizQuestion.quizId + " " + quizQuestion.questionId);
        }
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

    public Course getCourseById(int courseId) {
        for (Course course : courseList) {
            if (course.getCourseId() == courseId) {
                return course;
            }
        }
        return null;
    }
    public Lesson getLessonById(int lessonId) {
        for (Lesson lesson : lessonList) {
            if (lesson.getLessonId() == lessonId) {
                return lesson;
            }
        }
        return null;
    }
    public Professor getProfessorById(int professorId) {
        for (Professor professor : professorList) {
            if (professor.getProfessorId() == professorId) {
                return professor;
            }
        }
        return null;
    }
    public Question getQuestionById(int questionId) {
        for (Question question : questionList) {
            if (question.getQuestionId() == questionId) {
                return question;
            }
        }
        return null;
    }
    public Quiz getQuizById(int quizId) {
        for (Quiz quiz : quizList) {
            if (quiz.getQuizId() == quizId) {
                return quiz;
            }
        }
        return null;
    }
    public Student getStudentById(int studentId) {
        for (Student student : studentList) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }
}
