import java.time.LocalTime;

public class Task {
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private PriorityLevel priority;
    private boolean completed;

    public Task(String description, LocalTime startTime, LocalTime endTime, PriorityLevel priority) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.completed = false;
    }
    
    //Setters and Getters for setting and getting the private attributes of Task

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setPriority(PriorityLevel priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public PriorityLevel getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Checks if this task overlaps with another task.
     *
     * @param other The task to compare with.
     * @return True if this task overlaps with the other task, false otherwise.
     */
    public boolean overlapsWith(Task other) {
        return (this.startTime.isBefore(other.endTime) && other.startTime.isBefore(this.endTime));
    }
}


enum PriorityLevel {
    LOW, MEDIUM, HIGH
}