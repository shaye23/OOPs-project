// AttendanceSystem.java
public class AttendanceSystem extends Subject {
    public void updateAttendance(String course, boolean isPresent) {
        notifyObservers(course, isPresent);
    }
}
