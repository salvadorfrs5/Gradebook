package util;

import java.util.*;

public class Gradebook {
    private ArrayList<Student> listOfStudents;
	
	public Gradebook() {
		listOfStudents = new ArrayList<Student>();
	}
    public double calculateAvg() {
	double sum = 0;
	for(Student s: listOfStudents)
	    sum += s.getGrade().getScore();
	return sum / listOfStudents.size();
    }
    
	public float calculateMedian() {
	int i = 0, n = listOfStudents.size();
	int[] scores = new int[n];
	for(Student s: listOfStudents)
	    scores[i++] = s.getGrade().getScore();
	Arrays.sort(scores);
	if (n % 2 == 0)
	    return (scores[n / 2] + scores[n / 2 - 1]) / 2.0f;
	else
	    return scores[n / 2];
    }
    
	public void printAllStudents() {
		// The -10 in the format specifier is used to left-align the output and specify the width of the field.
		System.out.printf("%-10s\t%-10s\t%-10s\t%-10s\n","First Name", "Last Name", "PID", "Score");
		for(Student s: listOfStudents)
	    System.out.printf("%-10s\t%-10s\t%-10d\t%-10d\n", s.getFirstName(), s.getLastName(), s.getPid(), s.getGrade().getScore());
    }

	public void printAllLetters() {
		System.out.printf("%-10s\t%-10s\t%-10s\t%-10s\n", "First Name", "Last Name", "PID", "Letter Grade");
		for(Student s: listOfStudents)
			System.out.printf("%-10s\t%-10s\t%-10d\t%-10s\n", s.getFirstName(), s.getLastName(), s.getPid(), s.getGrade().getLetterGrade());
		}
	
	public void addStudent(Student s) {
		listOfStudents.add(s);
	}

	public int getMin() {
		int i = 0;
		int n = listOfStudents.size();
		int[] scores = new int[n];
		int min = 0;
		for(Student s: listOfStudents) {
			scores[i++] = s.getGrade().getScore();
		}
		Arrays.sort(scores);
		min = scores[0];
		return min;
	}

	public int getMax() {
		int i = 0;
		int n = listOfStudents.size();
		int[] scores = new int[n];
		int max = 0;
		// Uses for each loop and a post-increment modifier to increase the index after the value is assigned to the array.
		for(Student s: listOfStudents) {
			scores[i++] = s.getGrade().getScore();
		}
		Arrays.sort(scores);
		max = scores[n-1];
		return max;
	}

	public ArrayList<Student> getListOfStudents() {
		return listOfStudents;
	}

	/*
	 * Returns the student with the given pid.
	 * @param pid contains the student's pid.
	 * @return the student with the given pid.
	 * @return null if the student with the given pid is not found.
	 */
	public Student getStudent(int pid) {
		for(Student s: listOfStudents) {
			if(s.getPid() == pid) {
				return s;
			}
		}
		return null;
	}

	/*
	 * Updates the score of a student with the given pid.
	 * @param pid contains the student's pid.
	 * @param score contains the student's score.
	 */
	public void updateStudentGrade(int pid, String letterGrade) {
		for(Student s: listOfStudents) {
			if(s.getPid() == pid) {
				Grade grade = s.getGrade();
				grade.setLetterGrade(letterGrade);
				s.setGrade(grade);
			}
		}
	}
}
