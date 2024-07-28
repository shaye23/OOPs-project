import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ScheduleManager {
    private static ScheduleManager instance;
    private static final Logger LOGGER = Logger.getLogger(ScheduleManager.class.getName());
    private List<Task> tasks;
    private List<Task> completedTasks;
    private List<ScheduleObserver> observers;

    private ScheduleManager() {
        tasks = new ArrayList<>();
        completedTasks = new ArrayList<>();
        observers = new ArrayList<>();
        LOGGER.info("ScheduleManager initialized");
    }

    /**
     * Returns a singleton instance of the ScheduleManager class. If no instance exists, a new one is created.
     *
     * @return the singleton instance of the ScheduleManager class
     */
    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    /**
     * Adds a task to the schedule, checking for conflicts with existing tasks.
     *
     * @param  task  the task to be added
     * @throws TaskConflictException if the task conflicts with an existing task
     */
    public void addTask(Task task) throws TaskConflictException {
        for (Task existingTask : tasks) {
            if (task.overlapsWith(existingTask)) {
                LOGGER.warning("Task conflict: " + task.getDescription() + " conflicts with " + existingTask.getDescription());
                throw new TaskConflictException("Task conflicts with existing task: " + existingTask.getDescription());
            }
        }
        tasks.add(task);
        LOGGER.info("Task added: " + task.getDescription());
        notifyObservers(task, "added");
    }

    /**
     * Removes a task from the list of tasks and notifies observers of the removal.
     *
     * @param  task  the task to be removed
     */
    public void removeTask(Task task) {
        tasks.remove(task);
        LOGGER.info("Task removed: " + task.getDescription());
        notifyObservers(task, "removed");
    }

    /**
     * Retrieves a list of all tasks that are not completed and sorted by start time.
     *
     * @return a list of tasks sorted by start time
     */
    public List<Task> getAllTasks() {
        return tasks.stream()
                .filter(task -> !task.isCompleted())
                .sorted(Comparator.comparing(Task::getStartTime))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of tasks with the specified priority that have not been completed, sorted by start time.
     *
     * @param  priority  the priority level of the tasks to retrieve
     * @return           a list of tasks with the specified priority that have not been completed, sorted by start time
     */
    public List<Task> getTasksByPriority(PriorityLevel priority) {
        return tasks.stream()
                .filter(task -> task.getPriority() == priority && !task.isCompleted())
                .sorted(Comparator.comparing(Task::getStartTime))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a task from the list of tasks by its description.
     *
     * @param  description  the description of the task to retrieve
     * @return               the task with the matching description, or null if not found
     */
    public Task getTaskByDescription(String description) {
        return tasks.stream()
                .filter(task -> task.getDescription().equalsIgnoreCase(description) && !task.isCompleted())
                .findFirst()
                .orElse(null);
    }

    /**
     * Marks a task as complete by setting its completed status to true, adding it to the list of completed tasks,
     * removing it from the list of tasks, and notifying all observers of the task's completion.
     *
     * @param  task  the task to mark as complete
     */
    public void markTaskAsComplete(Task task) {
        task.setCompleted(true);
        completedTasks.add(task);
        tasks.remove(task);
        LOGGER.info("Task completed: " + task.getDescription());
        notifyObservers(task, "completed");
    }

    /**
     * Returns a list of completed tasks.
     *
     * @return a new ArrayList containing all completed tasks
     */
    public List<Task> getCompletedTasks() {
        return new ArrayList<>(completedTasks);
    }

    /**
     * Adds an observer to the list of observers.
     *
     * @param  observer  the observer to be added
     */
    public void addObserver(ScheduleObserver observer) {
        observers.add(observer);
    }


    /**
     * Notifies all observers of a task's action by calling their update method.
     *
     * @param  task    the task that triggered the notification
     * @param  action  the action that occurred with the task
     */
    private void notifyObservers(Task task, String action) {
        for (ScheduleObserver observer : observers) {
            observer.update(task, action);
        }
    }
}