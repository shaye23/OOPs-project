
// Student.java
public class Student implements Observer {
    private String name;
    private String email;

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public void update(String course, boolean isPresent) {
        System.out.println("Notification to " + name + " (" + email + "): " +
                           "Attendance for course " + course + " is " + (isPresent ? "present" : "absent"));
    }

    public String getName() {
        return name;
    }
}
