// Main.java
public class Main {
    public static void main(String[] args) {
        AttendanceSystem attendanceSystem = new AttendanceSystem();

        Student student1 = new Student("Raj", "raj@student.tce.edu");
        Student student2 = new Student("Ramani", "ramani@student.tce.edu");

        attendanceSystem.attach(student1);
        attendanceSystem.attach(student2);

        // Update attendance for a course
        attendanceSystem.updateAttendance("CS101", true);

        // Detach a student
        attendanceSystem.detach(student1);

        // Update attendance for another course
        attendanceSystem.updateAttendance("CS102", false);
    }
}
