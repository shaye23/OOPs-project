// This exception is thrown when a task is added to a schedule that conflicts with an existing task.
public class TaskConflictException extends Exception {
    public TaskConflictException(String message) {
        super(message);
    }
}