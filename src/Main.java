import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class Main {
    static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    /**
     * The main function of the program that sets up the logger, creates a ScheduleManager instance,
     * adds a ConsoleLogger observer to it, and starts a loop to display a menu and process user
     * commands until the user enters "exit".
     *
     * @param  args  the command line arguments passed to the program
     */
    public static void main(String[] args) {
        LoggerSetUp();
        LOGGER.info("Application started");
        ScheduleManager manager = ScheduleManager.getInstance();
        manager.addObserver(new ConsoleLogger());

        Scanner scanner = new Scanner(System.in);
        String command;
        do {
            displayMenu();
            command = getValidCommand(scanner);
            processCommand(command, scanner, manager);
        } while (!command.equalsIgnoreCase("exit"));

        LOGGER.info("Application exiting");
        System.out.println("Exiting...");
    }
    /**
     * Displays the available commands to the user.
     *
     * This function prints a list of available commands to the console. The user can choose one of these commands to
     * perform a specific action. The available commands are:
     * - add: Add a new task
     * - remove: Remove a task
     * - view: View all tasks
     * - priority: View tasks by priority
     * - complete: Mark a task as complete
     * - completed: View completed tasks
     * - exit: Exit the application
     *
     * After printing the commands, the function prompts the user to enter a command.
     *
     * @return void
     */
    private static void displayMenu() {
        System.out.println("\nAvailable commands:");
        System.out.println("add - Add a new task");
        System.out.println("remove - Remove a task");
        System.out.println("view - View all tasks");
        System.out.println("priority - View tasks by priority");
        System.out.println("complete - Mark a task as complete");
        System.out.println("completed - View completed tasks");
        System.out.println("exit - Exit the application");
        System.out.print("Enter a command: ");
    }
    /**
     * Prompts the user to enter a valid command until a valid command is entered.
     *
     * @param  scanner  the Scanner object used to read input from the user
     * @return           the valid command entered by the user
     */
    private static String getValidCommand(Scanner scanner) {
        String command = scanner.nextLine().trim().toLowerCase();
        while (!isValidCommand(command)) {
            LOGGER.warning("Invalid command entered: " + command);
            System.out.println("Invalid command. Please enter a valid command.");
            System.out.print("Enter a command: ");
            command = scanner.nextLine().trim().toLowerCase();
        }
        return command;
    }
    /**
     * Checks if the given command is a valid command for the application.
     *
     * @param  command  the command to be checked
     * @return           true if the command is valid, false otherwise
     */
    private static boolean isValidCommand(String command) {
        return command.equals("add") || command.equals("remove") || command.equals("view") ||
               command.equals("priority") || command.equals("complete") || command.equals("completed") ||
               command.equals("exit");
    }
    
    /**
     * Processes the given command and performs the corresponding action based on the command.
     *
     * @param  command  the command to be processed
     * @param  scanner  the Scanner object used to read input from the user
     * @param  manager  the ScheduleManager object used to manage the schedule
     */
    private static void processCommand(String command, Scanner scanner, ScheduleManager manager) {
        switch (command) {
            case "add":
                addTask(scanner, manager);
                break;
            case "remove":
                removeTask(scanner, manager);
                break;
            case "view":
                viewTasks(manager);
                break;
            case "priority":
                viewTasksByPriority(scanner, manager);
                break;
            case "complete":
                markTaskAsComplete(scanner, manager);
                break;
            case "completed":
                viewCompletedTasks(manager);
                break;
            // "exit" case is handled in the main loop
        }
    }

    /**
     * Sets up the logger to write log messages to the "astronaut_task.log" file.
     * If the file does not exist, it is created. If it exists, new log messages
     * are appended to the end of the file. The log level is set to ALL.
     *
     * @throws Exception if there is an error setting up the logger
     */
    private static void LoggerSetUp() {
        try {
            FileHandler fileHandler = new FileHandler("astronaut_task.log", true);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.ALL);
        } catch (Exception e) {
            System.err.println("Error setting up logger: " + e.getMessage());
        }
    }

    /**
     * Adds a new task to the ScheduleManager.
     *
     * @param  scanner   the Scanner object used to read user input
     * @param  manager   the ScheduleManager object used to add the task
     * @throws TaskConflictException  if the task conflicts with an existing task in the ScheduleManager
     * @throws IllegalArgumentException  if the input is invalid
     */
    private static void addTask(Scanner scanner, ScheduleManager manager) {
        String description = getUniqueTaskDescription(scanner, manager);

        LocalTime startTime = getValidTimeInput(scanner, "Enter start time (HH:mm): ");
        LocalTime endTime = getValidEndTimeInput(scanner, startTime);

        String priority = getValidPriorityInput(scanner);

        try {
            Task task = TaskFactory.createTask(description, startTime.format(TIME_FORMATTER), endTime.format(TIME_FORMATTER), priority);
            manager.addTask(task);
            System.out.println("Task added successfully.");
            LOGGER.info("Task added: " + task.getDescription());
        } catch (TaskConflictException e) {
            System.out.println("Error: " + e.getMessage());
            LOGGER.warning("Task conflict: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid input. Please check your entries.");
            LOGGER.warning("Invalid input for task creation: " + e.getMessage());
        }
    }

    /**
     * Prompts the user to enter a unique task description and checks if it already exists in the ScheduleManager.
     *
     * @param  scanner  the Scanner object used to read user input
     * @param  manager  the ScheduleManager object used to check for existing tasks
     * @return          the unique task description entered by the user
     */
    private static String getUniqueTaskDescription(Scanner scanner, ScheduleManager manager) {
        while (true) {
            System.out.print("Enter task description: ");
            String description = scanner.nextLine().trim();
            if (description.isEmpty()) {
                LOGGER.warning("Error:  Task description cannot be empty.");
                System.out.println("Error: Task description cannot be empty.");
                continue;
            }
            if (manager.getAllTasks().stream().noneMatch(t -> t.getDescription().equalsIgnoreCase(description))) {
                return description;
            }
            LOGGER.warning("Error:  A task with this name already exists. Please choose a different name");
            System.out.println("Error: A task with this name already exists. Please choose a different name.");
        }
    }

    /**
     * Retrieves a valid time input from the user.
     *
     * @param  scanner   the Scanner object used to read user input
     * @param  prompt    the prompt to display to the user
     * @return           the valid LocalTime object representing the user's input
     */
    private static LocalTime getValidTimeInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return LocalTime.parse(input, TIME_FORMATTER);
            } catch (DateTimeParseException e) {
                LOGGER.warning("Error:Invalid time format. Please use HH:mm (24-hour format)");
                System.out.println("Error: Invalid time format. Please use HH:mm (24-hour format).");
            }
        }
    }

    /**
     * Retrieves a valid end time input from the user.
     *
     * @param  scanner    the Scanner object used to read user input
     * @param  startTime  the start time to compare against the end time
     * @return            the valid end time input
     */
    private static LocalTime getValidEndTimeInput(Scanner scanner, LocalTime startTime) {
        while (true) {
            LocalTime endTime = getValidTimeInput(scanner, "Enter end time (HH:mm): ");
            if (endTime.isAfter(startTime)) {
                return endTime;
            }
            LOGGER.warning("Error:End time must be after start time.");
            System.out.println("Error: End time must be after start time.");
        }
    }

    /**
     * Retrieves a valid priority input from the user.
     *
     * @param  scanner  the Scanner object used to read user input
     * @return           the valid priority input (LOW, MEDIUM, or HIGH)
     */
    private static String getValidPriorityInput(Scanner scanner) {
        while (true) {
            System.out.print("Enter priority (LOW/MEDIUM/HIGH): ");
            String input = scanner.nextLine().toUpperCase();
           // LOGGER.info(input+" priority tasks are listed");
            if (input.equals("LOW") || input.equals("MEDIUM") || input.equals("HIGH")) {
                return input;
            }
            LOGGER.warning("Error:Invalid priority. Please enter LOW, MEDIUM, or HIGH.");
            System.out.println("Warning: Invalid priority. Please enter LOW, MEDIUM, or HIGH.");
        }
    }

    /**
     * Removes a task from the ScheduleManager based on its description.
     *
     * @param  scanner  the Scanner object used to read user input
     * @param  manager  the ScheduleManager object containing the tasks
     */
    private static void removeTask(Scanner scanner, ScheduleManager manager) {
        System.out.print("Enter task description to remove: ");
        String description = scanner.nextLine();

        for (Task task : manager.getAllTasks()) {
            if (task.getDescription().equals(description)) {
                manager.removeTask(task);
                LOGGER.info("Task removed successfully");
                System.out.println("Task removed successfully.");
                return;
            }
        }
        LOGGER.warning("Error:Task not found");
        System.out.println("Task not found.");
    }

    /**
     * Displays all tasks scheduled for the day.
     *
     * @param  manager  the ScheduleManager object containing the tasks
     */
    private static void viewTasks(ScheduleManager manager) {
        List<Task> tasks = manager.getAllTasks();
        if (tasks.isEmpty()) {
            LOGGER.warning("Error:No tasks scheduled for the day.");
            System.out.println("No tasks scheduled for the day.");
        } else {
            LOGGER.info("All task are listed");
            tasks.sort(Comparator.comparing(Task::getStartTime));
            for (Task task : tasks) {
                System.out.printf("%s - %s: %s [%s]%n",
                        task.getStartTime().format(TIME_FORMATTER),
                        task.getEndTime().format(TIME_FORMATTER),
                        task.getDescription(),
                        task.getPriority());
            }
        }
    }
    /**
     * Marks a task as complete by prompting the user for the task description and retrieving the corresponding task from the ScheduleManager.
     * If the task is found, it is marked as complete and a success message is displayed.
     * If the task is not found, an error message is displayed.
     *
     * @param  scanner  the Scanner object used to read user input
     * @param  manager  the ScheduleManager object containing the tasks
     */
    private static void markTaskAsComplete(Scanner scanner, ScheduleManager manager) {
        System.out.print("Enter task description to mark as complete: ");
        String description = scanner.nextLine();

        Task task = manager.getTaskByDescription(description);
        if (task != null) {
            manager.markTaskAsComplete(task);
            LOGGER.info("Task marked as complete: "+ task.getDescription());
            System.out.println("Task marked as complete: " + task.getDescription());
        } else {
            LOGGER.warning("task not found");
            System.out.println("Task not found.");
        }
    }
    /**
     * Displays all completed tasks.
     *
     * @param manager the ScheduleManager object containing the tasks
     */
    private static void viewCompletedTasks(ScheduleManager manager) {
        List<Task> completedTasks = manager.getCompletedTasks();
        if (completedTasks.isEmpty()) {
            LOGGER.warning("No completed tasks");
            System.out.println("No completed tasks.");
        } else {
            LOGGER.info("All completed tasks are listed");
            System.out.println("Completed tasks:");
            completedTasks.sort(Comparator.comparing(Task::getStartTime));
            for (Task task : completedTasks) {
                System.out.printf("%s - %s: %s [%s]%n",
                        task.getStartTime().format(TIME_FORMATTER),
                        task.getEndTime().format(TIME_FORMATTER),
                        task.getDescription(),
                        task.getPriority());
            }
        }
    }

    /**
     * Displays tasks with a given priority level.
     *
     * @param scanner the Scanner object used to read user input
     * @param manager the ScheduleManager object containing the tasks
     */
    private static void viewTasksByPriority(Scanner scanner, ScheduleManager manager) {
        System.out.print("Enter priority level (LOW/MEDIUM/HIGH): ");
        String priorityInput = scanner.nextLine().toUpperCase();
        
        try {
            PriorityLevel priority = PriorityLevel.valueOf(priorityInput);
            List<Task> tasks = manager.getTasksByPriority(priority);
            
            if (tasks.isEmpty()) {
                LOGGER.warning("No tasks found with priority level");
                System.out.println("No tasks found with priority level: " + priority);
            } else {
                LOGGER.info("Tasks with priority level " + priority + ":"+"are listed");
                System.out.println("Tasks with priority level " + priority + ":");
               
                tasks.sort(Comparator.comparing(Task::getStartTime));
                for (Task task : tasks) {
                    System.out.printf("%s - %s: %s%n",
                            task.getStartTime().format(TIME_FORMATTER),
                            task.getEndTime().format(TIME_FORMATTER),
                            task.getDescription());
                }
            }
        } catch (IllegalArgumentException e) {
            LOGGER.warning("Error: Invalid priority level. Please enter LOW, MEDIUM, or HIGH.");
            System.out.println("Error: Invalid priority level. Please enter LOW, MEDIUM, or HIGH.");
        }
    }
}