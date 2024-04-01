package main;
import java.util.List;
import java.util.Scanner;

import util.*;

public class Main {
    public static void main(String[] args) {
        // Initializing the main class and Gradebook object:
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        Gradebook gb = new Gradebook();

        // Prompting the user to enter the information of the FIRST student:
        System.out.printf("Welcome to my grade book!\n" +
        "Please enter the information of the first student using the following format:\n" +
        "\"firstName lastName pid grade\"\n" +
        "Press Enter when you are done.\n");
        
        /**
         * This while loop ensures the first input is in the correct format.
         * 
         * @param inputCheck is a bool that updates to true when the input format is correct, breaking the while loop.
         * @param inputLine is the array that stores the user's input. 
         * The split() method is initialized with " " to separate the line by space, storing each string as an element in the array.*/
        boolean inputCheck = false;
        while (!inputCheck) {
            String userInput = sc.nextLine();
            String[] inputLine = userInput.split(" ");
            /**
             * The array uses the matches() method to compare the input array to a regex.
             * If the input's format matches the regex, the addStudentToGradebook() method is called.*/ 
            if (inputLine.length == 4 && userInput.matches("[A-Z]([a-zA-Z]*\\.?[a-zA-z]*) [A-Z]([a-zA-z]*\\.?[a-zA-Z]*) \\d+ \\d+")) {
                main.addStudentToGradebook(gb, inputLine);
                inputCheck = true;
            } else {
                System.out.println("Invalid input. Please try again.");              
            }
        }
        
        /* 
         * A copy of the first loop with some modifications to continue to ask for input until the user enters "DONE":
         * @param inputCheck is reset to false to start the loop.
         * @param inputLine is an array of strings that come from the user's input.
         * Each if statement checks the length of the array and if the userInput matches the regex.
        */ 
        inputCheck = false;
            while (!inputCheck) {
                System.out.printf("Please enter the information of the next student using the same format.\n" +
                "If there are no more students, please enter the keyword \"DONE\".\n" +
                "Press Enter when you are done.\n");
                String userInput = sc.nextLine();
                String[] inputLine = userInput.split(" ");
                if (inputLine.length == 4 || userInput.matches("[A-Z]([a-zA-Z]*\\.?[a-zA-z]*) [A-Z]([a-zA-z]*\\.?[a-zA-Z]*) \\d+ \\d+")) {
                    main.addStudentToGradebook(gb, inputLine);
                } else if (inputLine[0].equalsIgnoreCase("DONE")) {
                    inputCheck = true;
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            }
        /*
         * Test to check if the students were added to the gradebook correctly.
         * gb.printAllStudents();
         */

        /*
         * The main while loop that handles all user commands.
         * @param inputCheck is reset to false to start the loop.
         * @param inputLine is an array of strings that come from the user's input.
         * The elements of inputLine are compared to the commands and the regex to determine the output.
         * equalsIgnoreCase() is used to provide case-insensitive commands.
         */
        inputCheck = false;
            while (!inputCheck) {
                System.out.println("Please enter a new command:");
                String userInput = sc.nextLine();
                String[] inputLine = userInput.split(" ");
                // prints the lowest score.
                if (inputLine.length == 2 && inputLine[0].equalsIgnoreCase("min") && inputLine[1].equalsIgnoreCase("score")) {
                    int min = gb.getMin();
                    System.out.println(min);
                // prints the lowest letter grade.
                } else if (inputLine.length == 2 && inputLine[0].equalsIgnoreCase("min") && inputLine[1].equalsIgnoreCase("letter")) {
                    int min = gb.getMin();
                    Grade grade = new Grade();
                    grade.setScore(min);
                    String letter = grade.getLetterGrade();
                    System.out.println(letter);
                // prints the highest score.
                } else if (inputLine.length == 2 && inputLine[0].equalsIgnoreCase("max") && inputLine[1].equalsIgnoreCase("score")) {
                    int max = gb.getMax();
                    System.out.println(max);
                // prints the highest letter grade.
                } else if (inputLine.length == 2 && inputLine[0].equalsIgnoreCase("max") && inputLine[1].equalsIgnoreCase("letter")) {
                    int max = gb.getMax();
                    Grade grade = new Grade();
                    grade.setScore(max);
                    String letter = grade.getLetterGrade();
                    System.out.println(letter);

                // prints the letter grade of a student with a given panther ID.
                } else if (inputLine.length == 2 && inputLine[0].equalsIgnoreCase("letter") && inputLine[1].matches("^[0-9]{7}$")) {
                    int pid = Integer.parseInt(inputLine[1]); 
                    Student s = gb.getStudent(pid);
                    if (s != null) {
                        Grade grade = s.getGrade();
                        String letter = grade.getLetterGrade();
                        System.out.println(letter);
                    } else {
                        System.out.println("Student not found.");
                    }
                // prints the name of a student with a given panther ID.
                } else if (inputLine.length == 2 && inputLine[0].equalsIgnoreCase("name") && inputLine[1].matches("^[0-9]{7}$")) {
                    int pid = Integer.parseInt(inputLine[1]);
                    Student s = gb.getStudent(pid);
                    if (s != null) {
                        System.out.printf("%s %s\n", s.getFirstName(), s.getLastName());
                    } else {
                        System.out.println("Student not found.");
                    }
                // changes the grade of a student with a given panther ID.
                } else if (inputLine.length == 3 && inputLine[0].equalsIgnoreCase("change") && inputLine[1].matches("^[0-9]{7}$") && inputLine[2].matches("^[A-D][+-]?|F$")) {
                    int pid = Integer.parseInt(inputLine[1]);
                    String grade = inputLine[2];
                    gb.updateStudentGrade(pid, grade);
                // prints the average score.
                } else if (inputLine.length == 2 && inputLine[0].equalsIgnoreCase("average") && inputLine[1].equalsIgnoreCase("score")) {
                    System.out.println(gb.calculateAvg());
                // prints the average letter grade.
                } else if (inputLine.length == 2 && inputLine[0].equalsIgnoreCase("average") && inputLine[1].equalsIgnoreCase("letter")) {
                    double avg = gb.calculateAvg();
                    int rounded = (int) Math.round(avg);
                    Grade grade = new Grade();
                    grade.setScore(rounded);
                    String letter = grade.getLetterGrade();
                    System.out.println(letter);
                // prints the median score.
                } else if (inputLine.length == 2 && inputLine[0].equalsIgnoreCase("median") && inputLine[1].equalsIgnoreCase("score")) {
                    float median = gb.calculateMedian();
                    System.out.println(median);
                // prints the median letter grade.
                } else if (inputLine.length == 2 && inputLine[0].equalsIgnoreCase("median") && inputLine[1].equalsIgnoreCase("letter")) {
                    float median = gb.calculateMedian();
                    int rounded = Math.round(median);
                    Grade grade = new Grade();
                    grade.setScore(rounded);
                    String letter = grade.getLetterGrade();
                    System.out.println(letter);
                // prints the list of students and their scores.
                } else if (inputLine.length == 2 && inputLine[0].equalsIgnoreCase("tab") && inputLine[1].equalsIgnoreCase("scores")) {
                    gb.printAllStudents();
                // prints the list of students and their letter grades.
                } else if (inputLine.length == 2 && inputLine[0].equalsIgnoreCase("tab") && inputLine[1].equalsIgnoreCase("letters")) {
                    gb.printAllLetters();
                // quits the program.
                } else if (inputLine.length == 1 && inputLine[0].equalsIgnoreCase("quit")) {
                    inputCheck = true;
                // if the input is not recognized, the user is prompted to try again.
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            }
        System.exit(0);
    }

// Helper Functions:

    // Test method to check if the students were added to the gradebook correctly.
    public void test(Gradebook gb) {
        gb.printAllStudents();
    }

    // Method to add a student to the gradebook.
    public void addStudentToGradebook (Gradebook gb, String[] inputLine) {
        String firstName;
        String lastName;
        int pid;
        int grade;

        firstName = inputLine[0];
        lastName = inputLine[1];
        pid = Integer.parseInt(inputLine[2]);
        grade = Integer.parseInt(inputLine[3]);
        
        Student s = new Student();
        s.setFirstName(firstName);
        s.setLastName(lastName);
        s.setPid(pid);
        
        Grade g = new Grade();
        g.setScore(grade);
        
        s.setGrade(g);
        gb.addStudent(s);


        
    }
    

}
