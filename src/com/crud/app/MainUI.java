package com.crud.app;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

public class MainUI {

    private static Student student = new Student();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n--- Student CRUD App ---");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. View All Students");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    updateStudent();
                    break;
                case "3":
                    deleteStudent();
                    break;
                case "4":
                    viewStudents();
                    break;
                case "5":
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addStudent() {
        try {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter age: ");
            int age = Integer.parseInt(scanner.nextLine());

            student.insertStudent(name, age);
            System.out.println("Student added successfully.");
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    private static void updateStudent() {
        try {
            System.out.print("Enter ID of student to update: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new age: ");
            int age = Integer.parseInt(scanner.nextLine());

            student.updateStudent(id, name, age);
            System.out.println("Student updated successfully.");
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    private static void deleteStudent() {
        try {
            System.out.print("Enter ID of student to delete: ");
            int id = Integer.parseInt(scanner.nextLine());

            student.deleteStudent(id);
            System.out.println("Student deleted successfully.");
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }

    private static void viewStudents() {
        try {
            Vector<Vector<Object>> students = student.getAllStudents();

            System.out.println("\n--- Student List ---");
            System.out.printf("%-5s %-20s %-5s\n", "ID", "Name", "Age");
            System.out.println("-------------------------------");

            for (Vector<Object> student : students) {
                System.out.printf("%-5s %-20s %-5s\n",
                        student.get(0), student.get(1), student.get(2));
            }
        } catch (SQLException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
    }
}
