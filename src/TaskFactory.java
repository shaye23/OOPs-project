import java.time.LocalTime;

/*TaskFactory class follows the Factory Design Pattern 
In this case, the TaskFactory class provides a static method createTask that
creates a new Task object based on the given parameters. 
This encapsulates the creation logic and allows for flexibility in how Task objects are created. */

public class TaskFactory {
    /**
     * Creates a new Task object with the given description, start time, end time, and priority.
     *
     * @param description the description of the task
     * @param startTime the start time of the task in the format HH:mm
     * @param endTime the end time of the task in the format HH:mm
     * @param priority the priority level of the task, which can be LOW, MEDIUM, or HIGH
     * @return a new Task object with the given parameters
     * @throws IllegalArgumentException if the priority is not one of LOW, MEDIUM, or HIGH
     */

    public static Task createTask(String description, String startTime, String endTime, String priority) {
        LocalTime start = LocalTime.parse(startTime, Main.TIME_FORMATTER);
        LocalTime end = LocalTime.parse(endTime, Main.TIME_FORMATTER);
        
        PriorityLevel priorityLevel = PriorityLevel.valueOf(priority.toUpperCase());
        return new Task(description, start, end, priorityLevel);
    }
}