package com.codecool.PTA.service;

import com.codecool.PTA.model.course.Course;
import com.codecool.PTA.model.course.CourseType;
import com.codecool.PTA.model.quest.*;
import com.codecool.PTA.model.user.GenderEnum;
import com.codecool.PTA.model.user.Level;
import com.codecool.PTA.model.user.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codecool.PTA.model.course.CourseType.ORIENTATION;

@Component
public class ExampleEntityCreator {

    List<QuizQuestion> questionList = new ArrayList<>();
    List<PA> paList = new ArrayList<>();
    List<Kata> kataList = new ArrayList<>();
    List<FillInTheBlank> fillInTheBlankList = new ArrayList<>();
    List<FillInAnswer> fillInAnswerList = new ArrayList<>();
    List<Course> courseList = new ArrayList<>();
    List<Student> studentList = new ArrayList<>();

    private void fillStudents() {
        Student student1 = new Student("aladar", "1", "Aladar", "Aradi", "em@ail.com", courseList.get(2), GenderEnum.MALE);
        studentList.add(student1);
        Student student2 = new Student("bea", "1", "Bea", "Biro", "bea@email.hu", courseList.get(2), GenderEnum.FEMALE);
        studentList.add(student2);
        Student student3 = new Student("cecil(ia)", "1", "Cecil(ia)", "Céges", "cecil(ia)@email.com", courseList.get(2), GenderEnum.OTHER);
        studentList.add(student3);
    }

    boolean fillData() {
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
        QuizQuestion quizQuestion1 = new QuizQuestion("What is the built-in function for replacing a substring in a string?", "Replacing substrings", Level.BEGINNER, CourseType.PYTHON, answers1);
        questionList.add(quizQuestion1);

        Map<String, Boolean> answers2 = new HashMap<>();
        answers2.put("str.upper()", false);
        answers2.put("str.upper", true);
        answers2.put("str.toUpperCase()", false);
        QuizQuestion quizQuestion2 = new QuizQuestion("What is the built-in function for converting a string to uppercase?", "Converting to uppercase", Level.BEGINNER, CourseType.PYTHON, answers2);
        questionList.add(quizQuestion2);

        Map<String, Boolean> answers3 = new HashMap<>();
        answers3.put("sqrt(x)", false);
        answers3.put("math.sqrt()", false);
        answers3.put("math.sqrt(number)", false);
        answers3.put("math.sqrt(x)", true);
        QuizQuestion quizQuestion3 = new QuizQuestion("What is the built-in math function for calculating the square root of a number if my number is named \"x\"?", "Calculating square root", Level.BEGINNER, CourseType.PYTHON, answers3);
        questionList.add(quizQuestion3);

        Map<String, Boolean> answers4 = new HashMap<>();
        answers4.put("array.insert()", false);
        answers4.put("array.insert", false);
        answers4.put("array.insert(i, x)", true);
        answers4.put("array.insert(position, x)", false);
        answers4.put("array.insert(i,x)", false);
        QuizQuestion quizQuestion4 = new QuizQuestion("What is the built-in function for inserting a new item with value x into a certain position in an array?", "Inserting into an array", Level.BEGINNER, CourseType.PYTHON, answers4);
        questionList.add(quizQuestion4);

        Map<String, Boolean> answers5 = new HashMap<>();
        answers5.put("List", true);
        answers5.put("Dictionary", false);
        answers5.put("Tuple", false);
        answers5.put("Array", false);
        QuizQuestion quizQuestion5 = new QuizQuestion("What data type is the object below ? <br> L = [1, 23, \'hello\', 1]?", "Data Types", Level.BEGINNER, CourseType.PYTHON, answers5);
        questionList.add(quizQuestion5);

        Map<String, Boolean> answers6 = new HashMap<>();
        answers6.put("curses.echo()", true);
        answers6.put("curses.echo", false);
        QuizQuestion quizQuestion6 = new QuizQuestion("What is the built-in function for entering echo mode in curses?", "Entering echo mode", Level.INTERMEDIATE, CourseType.PYTHON, answers6);
        questionList.add(quizQuestion6);

        Map<String, Boolean> answers7 = new HashMap<>();
        answers7.put("1 only", false);
        answers7.put("2 and 3 only", false);
        answers7.put("1 and 3 only", true);
        answers7.put("2 only", false);
        answers7.put("None", false);
        QuizQuestion quizQuestion7 = new QuizQuestion("Which of the following statement(s) is TRUE?" + "<br>" +
                "1. A hash function takes a message of arbitrary length and generates a fixed length code." + "<br>" +
                "2. A hash function takes a message of fixed length and generates a code of variable length." + "<br>" +
                "3. A hash function may give the same hash value for distinct messages.", "PasswordHashing function", Level.INTERMEDIATE, CourseType.PYTHON, answers7);
        questionList.add(quizQuestion7);

        Map<String, Boolean> answers8 = new HashMap<>();
        answers8.put("Hello (\'foo\', \'bin\') and (\'foo\', \'bin\')", false);
        answers8.put("Error", false);
        answers8.put("Hello foo and bin", true);
        answers8.put("None of the above", false);
        QuizQuestion quizQuestion8 = new QuizQuestion("What is the output of the following?\n" +
                "<br>" +
                "print(\"Hello {0[0]} and {0[1]}\".format((\'foo\', \'bin\')))\n", "Formatting strings", Level.INTERMEDIATE, CourseType.PYTHON, answers8);
        questionList.add(quizQuestion8);

        Map<String, Boolean> answers9 = new HashMap<>();
        answers9.put("[\'a\',\'b\',\'c\',\'d\'].<br> [\'a\',\'b\',\'c\',\'d\'].", false);
        answers9.put("[\'a\',\'@\',\'b\',\'@\',\'c\',\'@\',\'d\'].<br> [\'a\',\'b\',\'c\',\'d\'].", false);
        answers9.put("[\'a\',\'@\',\'b@c@d\'].<br> [\'a\',\'b\',\'c\',\'d\'].", true);
        answers9.put("[\'a\',\'@\',\'b@c@d\'].<br> [\'a\',\'@\',\'b\',\'@\',\'c\',\'@\',\'d\'].", false);
        QuizQuestion quizQuestion9 = new QuizQuestion("What is the output of the following piece of code?\n" +
                "<br>" +
                "s=\"a@b@c@d\"<br>" +
                "a=list(s.partition(\"@\"))<br>" +
                "print(a)<br>" +
                "b=list(s.split(\"@\",3))<br>" +
                "print(b)", "Split v Partition", Level.ADVANCED, CourseType.PYTHON, answers9);
        questionList.add(quizQuestion9);

        Map<String, Boolean> answers10 = new HashMap<>();
        answers10.put("Functions with an at sign in their names", false);
        answers10.put("Functions that return themselves", false);
        answers10.put("Functions that modify other functions", true);
        QuizQuestion quizQuestion10 = new QuizQuestion("What are decorators?", "Decorators", Level.ADVANCED, CourseType.PYTHON, answers10);
        questionList.add(quizQuestion10);

        Map<String, Boolean> answers11 = new HashMap<>();
        answers11.put("An error occurs", false);
        answers11.put("[0, 1, 4]", false);
        answers11.put("[1, 25, 81]", true);
        answers11.put("[1, 25]", false);
        QuizQuestion quizQuestion11 = new QuizQuestion("What is the output of this code?<br>" +
                "sqs = [0, 1, 4, 9, 16, 25, 36, 49, 64, 81]<br>" +
                "print(sqs[1::4])", "List slicing", Level.ADVANCED, CourseType.PYTHON, answers11);
        questionList.add(quizQuestion11);
    }

    private void fillPAs() {
        PA pa = new PA(Level.BEGINNER, CourseType.PYTHON, "Simple calculator",
                "Write a calculator script, that waits for the user to enter a number, " +
                        "then a sign (plus, minus, multiplication and division), then a number again. " +
                        "After the 2nd number input, the script should calculate the addition or subtraction and print it out. " +
                        "Then the program should run again with asking for the first number. " +
                        "The script should exit when the user enters a letter instead of a number. " +
                        "Submit your python script file.", true);
        paList.add(pa);

        PA pa2 = new PA(Level.INTERMEDIATE, CourseType.PYTHON, "Codewars assignment",
                "You have to collect at least 10 points by completing a subset of the katas below. We count point as follows: " +
                        "8 kyu kata scores 1 point, " +
                        "7 kyu kata scores 2 points and " +
                        "6 kyu kata scores 4 points. " +
                        "For practice and getting more familiar with Python tricks we suggest you to check the solutions " +
                        "(after completing a kata), understand the top ranked ones and rewrite your code from scratch based on the new insight.", true);
        paList.add(pa2);

        PA pa3 = new PA(Level.ADVANCED, CourseType.JAVA, "Practice JPA",
                "This assignment is a step by step introduction to the most widely used JPA annotations. " +
                        "The instructions are attached in the README.md file. " +
                        "It starts with a code you can run after some basic setup (step 1). " +
                        "Check the configurations in persistence.xml! " +
                        "The following steps hint the annotation to use or to modify to get to the expected result. " +
                        "Read about those annotations in the documentation or in any other resource, and play with the options, " +
                        "always checking the consequences in the database and in the state of the in-memory objects!", true);
        paList.add(pa3);
    }

    private void fillFillInTheBlankDb() {
        FillInTheBlank toFill1 = new FillInTheBlank(Level.BEGINNER,
                CourseType.PYTHON,
                "Please fill in the blank to print!",
                "<input type=\"text\" class=\"answer\" size=\"5\" >(\"Hello World!\")");

        FillInAnswer answer1 = new FillInAnswer("print", toFill1);
        fillInTheBlankList.add(toFill1);
        fillInAnswerList.add(answer1);

        FillInTheBlank toFill2 = new FillInTheBlank(Level.BEGINNER,
                CourseType.PYTHON,
                "Please fill in the blank to print: Hello World!",
                "<input type=\"text\" class=\"answer\" size=\"5\" >(\"Hello \" + <input type=\"text\" class=\"answer\" size=\"5\" >)");

        FillInAnswer answer2 = new FillInAnswer("print", toFill2);
        FillInAnswer answer3 = new FillInAnswer("\"World!\"", toFill2);

        fillInTheBlankList.add(toFill2);
        fillInAnswerList.add(answer2);
        fillInAnswerList.add(answer3);

        FillInTheBlank toFill3 = new FillInTheBlank(Level.INTERMEDIATE,
                CourseType.PYTHON,
                "Fill in the blanks to read the contents of a file using the \"with\" statement.",
                "<input type=\"text\" class=\"answer\" size=\"5\" > \n open(filename) <input type=\"text\" class=\"answer\" size=\"5\" > f:<br>" +
                        "   text = f.read()");

        FillInAnswer answer4 = new FillInAnswer("with", toFill3);
        FillInAnswer answer5 = new FillInAnswer("as", toFill3);

        fillInTheBlankList.add(toFill3);
        fillInAnswerList.add(answer4);
        fillInAnswerList.add(answer5);

        FillInTheBlank toFill4 = new FillInTheBlank(Level.INTERMEDIATE,
                CourseType.PYTHON,
                "Create a list of multiples of 3 from 0 to 20.",
                "a = [i for i in range(20) <input type=\"text\" class=\"answer\" size=\"5\" > i% <input type=\"text\" class=\"answer\" size=\"5\" > ==0]");

        FillInAnswer answer7 = new FillInAnswer("if", toFill4);
        FillInAnswer answer8 = new FillInAnswer("3", toFill4);

        fillInTheBlankList.add(toFill4);
        fillInAnswerList.add(answer7);
        fillInAnswerList.add(answer8);

        FillInTheBlank toFill5 = new FillInTheBlank(Level.ADVANCED,
                CourseType.PYTHON,
                "Fill in the blanks to import the cycle function from the itertools module.",
                "<input type=\"text\" class=\"answer\" size=\"5\" > itertools import <input type=\"text\" class=\"answer\" size=\"5\" >");

        FillInAnswer answer9 = new FillInAnswer("from", toFill5);
        FillInAnswer answer10 = new FillInAnswer("cycle", toFill5);

        fillInTheBlankList.add(toFill5);
        fillInAnswerList.add(answer9);
        fillInAnswerList.add(answer10);

        FillInTheBlank toFill6 = new FillInTheBlank(Level.ADVANCED,
                CourseType.PYTHON,
                "Fill in the blanks to create a class and its constructor, taking one argument and assigning it to the \"name\" attribute. Then create an object of the class.",
                "class Student:<br> def <input type=\"text\" class=\"answer\" size=\"5\" >(self, name):<br> " +
                        "self <input type=\"text\" class=\"answer\" size=\"5\" > = name<br>" +
                        "<br>" +
                        "test = Student(\"Bob\")");

        FillInAnswer answer12 = new FillInAnswer("__init__", toFill6);
        FillInAnswer answer13 = new FillInAnswer(".name", toFill6);

        fillInTheBlankList.add(toFill6);
        fillInAnswerList.add(answer12);
        fillInAnswerList.add(answer13);

        FillInTheBlank toFill7 = new FillInTheBlank(Level.BEGINNER,
                CourseType.PYTHON,
                "Please fill the missing parts!",
                "temperature = 30<br>" +
                        "<input type=\"text\" class=\"answer\" size=\"5\" > temperatute >= 30:<br>" +
                        ">>> print(\"It is hot!\")<br>" +
                        "<input type=\"text\" class=\"answer\" size=\"5\" >:<br>" +
                        ">>> print(\"It is ok.\")");

        FillInAnswer answer14 = new FillInAnswer("if", toFill7);
        FillInAnswer answer15 = new FillInAnswer("else", toFill7);

        fillInTheBlankList.add(toFill7);
        fillInAnswerList.add(answer14);
        fillInAnswerList.add(answer15);

        FillInTheBlank toFill8 = new FillInTheBlank(Level.BEGINNER,
                CourseType.PYTHON,
                "Create a for loop to sum the numbers!",
                "numbers = [1, 2, 3]<br>" +
                        "sum = 0<br>" +
                        "<input type=\"text\" class=\"answer\" size=\"5\" > number <input type=\"text\" class=\"answer\" size=\"5\" > numbers:<br>" +
                        ">>> sum += number");

        FillInAnswer answer16 = new FillInAnswer("for", toFill8);
        FillInAnswer answer17 = new FillInAnswer("in", toFill8);

        fillInTheBlankList.add(toFill8);
        fillInAnswerList.add(answer16);
        fillInAnswerList.add(answer17);

        FillInTheBlank toFill9 = new FillInTheBlank(Level.BEGINNER,
                CourseType.PYTHON,
                "Create a function which adds two numbers",
                "<input type=\"text\" class=\"answer\" size=\"5\" > sum_numbers<input type=\"text\" class=\"answer\" size=\"5\" ><br>" +
                        ">>> return a + b");

        FillInAnswer answer18 = new FillInAnswer("def", toFill9);
        FillInAnswer answer19 = new FillInAnswer("(a, b):", toFill9);

        fillInTheBlankList.add(toFill9);
        fillInAnswerList.add(answer18);
        fillInAnswerList.add(answer19);

    }

    private void fillKatas() {
        Kata kata = new Kata(Level.BEGINNER, CourseType.PYTHON, "Find Multiples of a Number",
                "In this simple exercise, you will build a program that takes a value, integer, " +
                        "and returns a list of its multiples up to another value, limit. If limit is a multiple of integer, " +
                        "it should be included as well. There will only ever be positive integers passed into the function, " +
                        "not consisting of 0. The limit will always be higher than the base. " +
                        "For example, if the parameters passed are (2, 6), the function should return [2, 4, 6] " +
                        "as 2, 4, and 6 are the multiples of 2 up to 6. " +
                        "If you can, try writing it in only one line of code.", true);
        kataList.add(kata);
        Kata kata2 = new Kata(Level.BEGINNER, CourseType.PYTHON, "Reverse words",
                "Complete the function that accepts a string parameter, and reverses each word in the string. " +
                        "All spaces in the string should be retained.", true);
        kataList.add(kata2);
    }

    private void fillCourses() {
        Course course1 = new Course(com.codecool.PTA.model.course.CourseType.JAVA, "Java is a simple and yet " +
                "powerful object oriented programming language and it is in many respects similar to C++.");
        courseList.add(course1);

        Course course2 = new Course(com.codecool.PTA.model.course.CourseType.PYTHON, "Python is a programming " +
                "language, as are C, Fortran, BASIC, PHP, etc. Some specific features of Python are as follows: " +
                "an interpreted (as opposed to compiled) language.");
        courseList.add(course2);
        Course course3 = new Course(ORIENTATION, "Please choose a course " +
                "to pursue!");
        courseList.add(course3);
    }

}
