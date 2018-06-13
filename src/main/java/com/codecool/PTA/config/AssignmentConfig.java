package com.codecool.PTA.config;

import com.codecool.PTA.model.course.Course;
import com.codecool.PTA.model.quest.*;
import com.codecool.PTA.model.user.GenderEnum;
import com.codecool.PTA.model.user.Student;
import com.codecool.PTA.persistence.PersistenceImplementation;
import com.codecool.PTA.model.user.Level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codecool.PTA.model.course.CourseType.ORIENTATION;

public class AssignmentConfig {
    
    private PersistenceImplementation persistenceImplementation;

    private List<QuizQuestion> questionList = new ArrayList<>();
    private List<PA> paList = new ArrayList<>();
    private List<Kata> kataList = new ArrayList<>();
    private List<Course> courseList = new ArrayList<>();
    private List<Student> studentList = new ArrayList<>();

    public AssignmentConfig(PersistenceImplementation persistenceImplementation) {
        this.persistenceImplementation = persistenceImplementation;
    }

    private void fillStudents() {
        Student student1 = new Student("aladar", "1", "Aladar", "Aradi", "em@ail.com", courseList.get(2), GenderEnum.MALE);
        studentList.add(student1);
        Student student2 = new Student("bea", "1", "Bea", "Biro", "bea@email.hu", courseList.get(2), GenderEnum.FEMALE);
        studentList.add(student2);
        Student student3 = new Student("cecil(ia)", "1", "Cecil(ia)", "Céges", "cecil(ia)@email.com", courseList.get(2), GenderEnum.OTHER);
        studentList.add(student3);
    }

    private boolean fillData() {
        try {
            fillQuizQuestions();
            fillPAs();
            fillKatas();
            fillCourses();
            fillFillInTheBlankDb();
            fillStudents();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void fillQuizQuestions() {
        Map<String, Boolean> answers1 = new HashMap<>();
        answers1.put("str.replace", true);
        answers1.put("str.replace()", false);
        answers1.put("str.replace(old, new[, count])", false);
        answers1.put("str.replace(old, new)", false);
        QuizQuestion quizQuestion1 = new QuizQuestion("What is the built-in function for replacing a substring in a string?", "Replacing substrings", Level.BEGINNER, CourseType.Python, answers1);
        questionList.add(quizQuestion1);

        Map<String, Boolean> answers2 = new HashMap<>();
        answers2.put("str.upper()", false);
        answers2.put("str.upper", true);
        answers2.put("str.toUpperCase()", false);
        QuizQuestion quizQuestion2 = new QuizQuestion("What is the built-in function for converting a string to uppercase?", "Converting to uppercase", Level.BEGINNER, CourseType.Python, answers2);
        questionList.add(quizQuestion2);

        Map<String, Boolean> answers3 = new HashMap<>();
        answers3.put("sqrt(x)", false);
        answers3.put("math.sqrt()", false);
        answers3.put("math.sqrt(number)", false);
        answers3.put("math.sqrt(x)", true);
        QuizQuestion quizQuestion3 = new QuizQuestion("What is the built-in math function for calculating the square root of a number if my number is named \"x\"?", "Calculating square root", Level.BEGINNER, CourseType.Python, answers3);
        questionList.add(quizQuestion3);

        Map<String, Boolean> answers4 = new HashMap<>();
        answers4.put("array.insert()", false);
        answers4.put("array.insert", false);
        answers4.put("array.insert(i, x)", true);
        answers4.put("array.insert(position, x)", false);
        answers4.put("array.insert(i,x)", false);
        QuizQuestion quizQuestion4 = new QuizQuestion("What is the built-in function for inserting a new item with value x into a certain position in an array?", "Inserting into an array", Level.BEGINNER, CourseType.Python, answers4);
        questionList.add(quizQuestion4);

        Map<String, Boolean> answers5 = new HashMap<>();
        answers5.put("curses.echo()", true);
        answers5.put("curses.echo", false);
        QuizQuestion quizQuestion5 = new QuizQuestion("What is the built-in function for entering echo mode in curses?", "Entering echo mode", Level.BEGINNER, CourseType.Python, answers5);
        questionList.add(quizQuestion5);
    }

    private void fillPAs() {
        PA pa = new PA(Level.BEGINNER, CourseType.Python, "Simple calculator",
                "Write a calculator script, that waits for the user to enter a number, " +
                        "then a sign (plus, minus, multiplication and division), then a number again. " +
                        "After the 2nd number input, the script should calculate the addition or subtraction and print it out. " +
                        "Then the program should run again with asking for the first number. " +
                        "The script should exit when the user enters a letter instead of a number. " +
                        "Submit your python script file.");
        paList.add(pa);

        PA pa2 = new PA(Level.INTERMEDIATE, CourseType.Python, "Codewars assignment",
                "You have to collect at least 10 points by completing a subset of the katas below. We count point as follows: " +
                        "8 kyu kata scores 1 point, " +
                        "7 kyu kata scores 2 points and " +
                        "6 kyu kata scores 4 points. " +
                        "For practice and getting more familiar with Python tricks we suggest you to check the solutions " +
                        "(after completing a kata), understand the top ranked ones and rewrite your code from scratch based on the new insight.");
        paList.add(pa2);

        PA pa3 = new PA(Level.ADVANCED, CourseType.Java, "Practice JPA",
                "This assignment is a step by step introduction to the most widely used JPA annotations. " +
                        "The instructions are attached in the README.md file. " +
                        "It starts with a code you can run after some basic setup (step 1). " +
                        "Check the configurations in persistence.xml! " +
                        "The following steps hint the annotation to use or to modify to get to the expected result. " +
                        "Read about those annotations in the documentation or in any other resource, and play with the options, " +
                        "always checking the consequences in the database and in the state of the in-memory objects!");
        paList.add(pa3);
    }

    private void fillFillInTheBlankDb() {
        FillInTheBlank toFill1 = new FillInTheBlank(Level.BEGINNER,
                                                    CourseType.Python,
                                                    "Please fill in the blank to print!",
                                                    "<input type=\"text\" class=\"answer\" size=\"5\" >(\"Hello World!\")");

        FillInAnswer answer1 = new FillInAnswer("print", toFill1);

        persistenceImplementation.persist(toFill1);
        persistenceImplementation.persist(answer1);

        FillInTheBlank toFill2 = new FillInTheBlank(Level.BEGINNER,
                                                    CourseType.Python,
                                                    "Please fill in the blank to print: Hello World!",
                                                    "<input type=\"text\" class=\"answer\" size=\"5\" >(\"Hello  + \" <input type=\"text\" class=\"answer\" size=\"5\" >)");

        FillInAnswer answer2 = new FillInAnswer("print", toFill2);
        FillInAnswer answer3 = new FillInAnswer(" World!\"", toFill2);

        persistenceImplementation.persist(toFill2);
        persistenceImplementation.persist(answer2);
        persistenceImplementation.persist(answer3);
    }

    private void fillKatas() {
        Kata kata = new Kata(Level.BEGINNER, CourseType.Python, "Find Multiples of a Number",
                "In this simple exercise, you will build a program that takes a value, integer, " +
                        "and returns a list of its multiples up to another value, limit. If limit is a multiple of integer, " +
                        "it should be included as well. There will only ever be positive integers passed into the function, " +
                        "not consisting of 0. The limit will always be higher than the base. " +
                        "For example, if the parameters passed are (2, 6), the function should return [2, 4, 6] " +
                        "as 2, 4, and 6 are the multiples of 2 up to 6. " +
                        "If you can, try writing it in only one line of code.");
        kataList.add(kata);
        Kata kata2 = new Kata(Level.BEGINNER, CourseType.Python, "Reverse words",
                "Complete the function that accepts a string parameter, and reverses each word in the string. " +
                        "All spaces in the string should be retained.");
        kataList.add(kata2);
    }

    private void fillCourses(){
        Course course1 = new Course(com.codecool.PTA.model.course.CourseType.JAVA, "Java is a simple and yet "+
                "powerful object oriented programming language and it is in many respects similar to C++.");
        courseList.add(course1);

        Course course2 = new Course(com.codecool.PTA.model.course.CourseType.PYTHON, "Python is a programming "+
                "language, as are C, Fortran, BASIC, PHP, etc. Some specific features of Python are as follows: "+
                "an interpreted (as opposed to compiled) language.");
        courseList.add(course2);
        Course course3 = new Course(ORIENTATION, "Please choose a course "+
                "to pursue!");
        courseList.add(course3);
    }

    public void fillDB() {
        fillData();
        for (QuizQuestion question : questionList) {
            persistenceImplementation.persist(question);
        }
        for (PA pa : paList) {
            persistenceImplementation.persist(pa);
        }
        for (Kata kata : kataList) {
            persistenceImplementation.persist(kata);
        }
        for (Course course : courseList) {
            persistenceImplementation.persist(course);
        }
        for (Student student : studentList) {
            persistenceImplementation.persist(student);
        }
    }
}
