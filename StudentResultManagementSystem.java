import java.util.InputMismatchException;
import java.util.Scanner;

// Custom Exception Class
class InvalidMarksException extends Exception {
    public InvalidMarksException(String message) {
        super(message);
    }
}

// Student Class
class Student {
    private int rollNumber;
    private String studentName;
    private int[] marks = new int[3];

    public Student(int rollNumber, String studentName, int[] marks) {
        this.rollNumber = rollNumber;
        this.studentName = studentName;
        this.marks = marks;
    }

    public void validateMarks() throws InvalidMarksException {
        for (int i = 0; i < marks.length; i++) {
            if (marks[i] < 0 || marks[i] > 100) {
                throw new InvalidMarksException("Invalid marks for Subject " + (i + 1) + ": " + marks[i]);
            }
        }
    }

    public double calculateAverage() {
        int sum = 0;
        for (int mark : marks) {
            sum += mark;
        }
        return sum / 3.0;
    }

    public void displayResult() {
        System.out.println("\n---- Student Result ----");
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Name: " + studentName);
        System.out.println("Marks: ");
        for (int i = 0; i < marks.length; i++) {
            System.out.println("Subject " + (i + 1) + ": " + marks[i]);
        }

        double avg = calculateAverage();
        System.out.println("Average Marks: " + avg);

        if (avg >= 40)
            System.out.println("Result: PASS");
        else
            System.out.println("Result: FAIL");
        System.out.println("------------------------");
    }

    public int getRollNumber() {
        return rollNumber;
    }
}


// Main Class
public class StudentResultManagementSystem {

    private static Scanner sc = new Scanner(System.in);
    private static Student[] students = new Student[100];
    private static int count = 0;

    public static void main(String[] args) {
        mainMenu();
        sc.close();
    }

    public static void mainMenu() {
        while (true) {
            try {
                System.out.println("\n===== Student Result Management System =====");
                System.out.println("1. Add Student");
                System.out.println("2. Show Student Details");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> showStudentDetails();
                    case 3 -> {
                        System.out.println("Closing System... Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice! Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number!");
                sc.nextLine();
            } finally {
                System.out.println("-- Operation Completed --");
            }
        }
    }

    private static void addStudent() {
        try {
            System.out.print("Enter Roll Number: ");
            int roll = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            int[] marks = new int[3];
            for (int i = 0; i < 3; i++) {
                System.out.print("Enter marks for subject " + (i + 1) + ": ");
                marks[i] = sc.nextInt();
            }

            Student st = new Student(roll, name, marks);
            st.validateMarks(); // Validate using custom exception

            students[count++] = st;
            System.out.println("Student added successfully!");

        } catch (InvalidMarksException e) {
            System.out.println("Error: " + e.getMessage() + ". Returning to main menu...");
        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        }
    }

    private static void showStudentDetails() {
        System.out.print("Enter Roll Number to search: ");
        int roll = sc.nextInt();

        for (int i = 0; i < count; i++) {
            if (students[i].getRollNumber() == roll) {
                students[i].displayResult();
                return;
            }
        }
        System.out.println("Student Not Found!");
    }
}
