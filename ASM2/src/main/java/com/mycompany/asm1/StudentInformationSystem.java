package com.mycompany.asm1;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentInformationSystem {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public void addStudent() {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter marks: ");
        float marks = scanner.nextFloat();
        scanner.nextLine();
        students.add(new Student(id, name, marks));
        System.out.println("Student added successfully.");
    }

    public void editStudent() {
        System.out.print("Enter ID of the student to edit: ");
        String id = scanner.nextLine();
        Student student = findStudentById(id);

        if (student != null) {
            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new grade: ");
            float marks = scanner.nextFloat();
            scanner.nextLine(); 
            student.setName(name);
            student.setMarks(marks);
            System.out.println("Student details updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void deleteStudent() {
        System.out.print("Enter ID of the student to delete: ");
        String id = scanner.nextLine();
        Student student = findStudentById(id);

        if (student != null) {
            students.remove(student);
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void sortStudents() {
        System.out.println("Sort by:");
        System.out.println("1.Grade");
        System.out.print("Choose an option: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                bubbleSortByMarks();
                System.out.println("Students sorted successfully by Grade (highest to lowest).");
                displayStudents();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private void bubbleSortByMarks() {
        int n = students.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (students.get(j).getMarks() < students.get(j + 1).getMarks()) {
                    // Swap students[j] and students[j+1]
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                    swapped = true;
                }
            }
            // If no two elements were swapped by inner loop, then break
            if (!swapped) break;
        }
    }
    private String getRank(float marks) {
        if (marks >= 0.0 && marks < 5.0) {
            return "Fail";
        } else if (marks >= 5.0 && marks < 6.5) {
            return "Medium";
        } else if (marks >= 6.5 && marks < 7.5) {
            return "Good";
        } else if (marks >= 7.5 && marks < 9.0) {
            return "Very Good";
        } else if (marks >= 9.0 && marks <= 10.0) {
            return "Excellent";
        } else {
            return "Invalid grade";
        }
    }

    public void searchStudent() {
        System.out.print("Enter ID of the student to search: ");
        String id = scanner.nextLine();
        Student student = findStudentById(id);

        if (student != null) {
            System.out.println("Student found: " + student + ", Rank: " + getRank(student.getMarks()));
        } else {
            System.out.println("Student not found.");
        }
    }

    private Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                return student;
            }
        }
        return null;
    }

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            for (Student student : students) {
                System.out.println(student + ", Rank: " + getRank(student.getMarks()));
            }
        }
    }

    public static void main(String[] args) {
        // Assuming you have a list of students and a scanner object
        StudentInformationSystem sms = new StudentInformationSystem();
        sms.addStudent();  // Add students here as needed
        sms.sortStudents();  // Sort and display students
    }
}
