import java.util.*;

class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}

public class UniversityEnrollmentSystem {
    private static final int MAX_STUDENTS = 2; // Max students allowed
    private static int enrolledStudents = 1;  // Current enrolled students

    // Required skills for Advanced Java
    private static final Set<String> requiredSkills = new HashSet<>(Arrays.asList("Java", "OOP", "DSA"));

    public static void enrollStudent(Set<String> userSkills) throws CourseFullException, PrerequisiteNotMetException {
        if (enrolledStudents >= MAX_STUDENTS) {
            throw new CourseFullException("Error: CourseFullException - Advanced Java is already full.");
        }

        if (!userSkills.containsAll(requiredSkills)) {
            Set<String> missingSkills = new HashSet<>(requiredSkills);
            missingSkills.removeAll(userSkills);
            throw new PrerequisiteNotMetException("Error: PrerequisiteNotMetException - You are missing the following skills: " + missingSkills);
        }

        enrolledStudents++;
        System.out.println("✅ Enrollment successful in Advanced Java");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Ask user for their skills
            System.out.print("Enter your skills (comma-separated, e.g., Java, OOP, SQL): ");
            String input = scanner.nextLine();
            Set<String> userSkills = new HashSet<>();
            for (String skill : input.split(",")) {
                userSkills.add(skill.trim());
            }

            enrollStudent(userSkills);
        } catch (CourseFullException | PrerequisiteNotMetException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
