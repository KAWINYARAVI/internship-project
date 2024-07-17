import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Student {
    private String id;
    private String name;
    private String email;
    private ArrayList<String> courses;
    private HashMap<String, Double> grades;

    public Student(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.courses = new ArrayList<>();
        this.grades = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void enrollCourse(String course) {
        if (!courses.contains(course)) {
            courses.add(course);
            grades.put(course, 0.0);
        }
    }

    public void setGrade(String course, double grade) {
        if (courses.contains(course)) {
            grades.put(course, grade);
        } else {
            System.out.println("Student is not enrolled in the course: " + course);
        }
    }

    public double getGrade(String course) {
        return grades.getOrDefault(course, 0.0);
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public double getGPA() {
        double total = 0.0;
        for (double grade : grades.values()) {
            total += grade;
        }
        return grades.size() > 0 ? total / grades.size() : 0.0;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", email=" + email + ", courses=" + courses + ", GPA=" + getGPA() + "]";
    }
}

class StudentInformationSystem {
    private HashMap<String, Student> students;

    public StudentInformationSystem() {
        this.students = new HashMap<>();
    }

    public void addStudent(String id, String name, String email) {
        students.put(id, new Student(id, name, email));
    }

    public void enrollCourse(String studentId, String course) {
        Student student = students.get(studentId);
        if (student != null) {
            student.enrollCourse(course);
            System.out.println(student.getName() + " enrolled in " + course);
        } else {
            System.out.println("Student not found: " + studentId);
        }
    }

    public void setGrade(String studentId, String course, double grade) {
        Student student = students.get(studentId);
        if (student != null) {
            student.setGrade(course, grade);
            System.out.println("Grade updated for " + student.getName() + " in " + course);
        } else {
            System.out.println("Student not found: " + studentId);
        }
    }

    public void viewStudent(String studentId) {
        Student student = students.get(studentId);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found: " + studentId);
        }
    }

    public void viewAllStudents() {
        for (Student student : students.values()) {
            System.out.println(student);
        }
    }

    public void removeStudent(String studentId) {
        if (students.containsKey(studentId)) {
            students.remove(studentId);
            System.out.println("Student removed: " + studentId);
        } else {
            System.out.println("Student not found: " + studentId);
        }
    }

    public static void main(String[] args) {
        StudentInformationSystem sis = new StudentInformationSystem();
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Student Information System");
        System.out.println("Commands: addStudent, enrollCourse, setGrade, viewStudent, viewAllStudents, removeStudent, exit");

        while (true) {
            System.out.print("Enter command: ");
            command = scanner.nextLine();

            switch (command) {
                case "addStudent":
                    System.out.print("Enter student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student email: ");
                    String email = scanner.nextLine();
                    sis.addStudent(id, name, email);
                    break;
                case "enrollCourse":
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter course name: ");
                    String course = scanner.nextLine();
                    sis.enrollCourse(studentId, course);
                    break;
                case "setGrade":
                    System.out.print("Enter student ID: ");
                    studentId = scanner.nextLine();
                    System.out.print("Enter course name: ");
                    course = scanner.nextLine();
                    System.out.print("Enter grade: ");
                    double grade = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline character
                    sis.setGrade(studentId, course, grade);
                    break;
                case "viewStudent":
                    System.out.print("Enter student ID: ");
                    studentId = scanner.nextLine();
                    sis.viewStudent(studentId);
                    break;
                case "viewAllStudents":
                    sis.viewAllStudents();
                    break;
                case "removeStudent":
                    System.out.print("Enter student ID: ");
                    studentId = scanner.nextLine();
                    sis.removeStudent(studentId);
                    break;
                case "exit":
                    System.out.println("Exiting Student Information System.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid command.");
                    break;
            }
        }
    }
}
