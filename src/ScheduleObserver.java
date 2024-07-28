/* The ScheduleObserver (ScheduleObserver.java) interface 
defines a contract for classes that want to be notified 
when a task is added, removed, or completed
*/

public interface ScheduleObserver {
    void update(Task task, String action);
}