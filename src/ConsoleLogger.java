public class ConsoleLogger implements ScheduleObserver {
        /**
         * Updates the console logger with the given task and action.
         *
         * @param  task    the task to be updated
         * @param  action  the action to be performed on the task
         */
    @Override
    public void update(Task task, String action) {
        System.out.println("Task " + action + ": " + task.getDescription());
    }
}