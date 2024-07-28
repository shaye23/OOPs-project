# Exercise 1

# 1. Creational Design Patterns

## 1.1 Factory Method

**Definition:** Provides an interface for creating objects in a superclass, allowing subclasses to alter the type of objects created.

**Use Case:** "Dynamic Subscription Plan Generator for Streaming Service"

**Description:**
- Defines interface for object creation in superclass
- Subclasses decide which class to instantiate
- Uses `Subscription` interface and concrete classes
- `SubscriptionFactory` creates objects based on input

**How it works:**
1. User chooses subscription type
2. Factory creates appropriate `Subscription` object
3. Client uses object to display info

## 1.2 Singleton

**Definition:** Ensures a class has only one instance and provides a global point of access to that instance.

**Use Case:** "Centralized Event Dispatch System for Multi-Module Application"

**Description:**
- Ensures single instance of `EventManager`
- Private constructor, static `getInstance()` method
- Implements event management system
- `Listener` classes handle events

**How it works:**
1. Access `EventManager` via `getInstance()`
2. Add/remove listeners
3. Dispatch events to notify listeners

# 2. Behavioral Design Patterns

## 2.1 Observer

**Definition:** Defines a subscription mechanism to notify multiple objects about any events that happen to the object they're observing.

**Use Case:** "Real-time Student Attendance Notification System"

**Description:**
- `Subject` class manages observers
- `Observer` interface defines `update` method
- `AttendanceSystem` (concrete subject) updates state
- `Student` (concrete observer) receives notifications

**How it works:**
1. Students register with `AttendanceSystem`
2. System updates attendance
3. Registered students are notified

## 2.2 State

**Definition:** Allows an object to alter its behavior when its internal state changes, appearing to change its class.

**Use Case:** "Dynamic Laptop Power State Management"

**Description:**
- `Laptop` class maintains current state
- `LaptopState` interface defines common methods
- Concrete states implement specific behaviors
- `Main` class demonstrates state transitions

**How it works:**
1. Laptop starts in shutdown state
2. State changes based on user actions
3. Each state handles actions differently

# 3. Structural Design Patterns

## 3.1 Adapter

**Definition:** Allows objects with incompatible interfaces to collaborate by wrapping its own interface around that of an existing class.

**Use Case:** "Currency Conversion Adapter for Payment Processing"

**Description:**
- `INRTransaction` interface for INR payments
- `USDTransaction` processes USD payments
- `CurrencyAdapter` bridges INR and USD transactions
- Converts INR to USD using exchange rate

**How it works:**
1. Client creates `USDTransaction` and `CurrencyAdapter`
2. Client calls `processINRPayment` on adapter
3. Adapter converts currency and calls `USDTransaction`

## 3.2 Composite

**Definition:** Composes objects into tree structures to represent part-whole hierarchies, allowing clients to treat individual objects and compositions uniformly.

**Use Case:** "Flexible Restaurant Menu System"

**Description:**
- `MealItem` interface defines common operations
- `Dish` class represents individual items
- `ComboMeal` can contain other `MealItem` objects
- `Restaurant` demonstrates usage

**How it works:**
1. Create individual dishes and combo meals
2. Combo meals can contain dishes and other combos
3. Calling methods on combos recursively calculates totals

# Exercise 2
# Astronaut Daily Schedule Organizer

## Overview

Astronaut Daily Schedule Organizer is a task management system written in Java. The system allows users to add, remove, view, and mark tasks as complete. Tasks can be prioritized and viewed by priority level.

## Features

- **Add Tasks**: Include description, start time, end time, and priority level.
- **Remove Tasks**: Remove tasks by description.
- **View Tasks**: View all tasks, completed tasks, and tasks by priority level.
- **Mark Tasks as Complete**: Mark tasks as complete.
- **Prioritize Tasks**: Assign LOW, MEDIUM, or HIGH priority levels to tasks.

## Classes

### Main

1. `main(String[] args)`: Entry point of the program. Sets up the logger, creates a `ScheduleManager` instance, adds a `ConsoleLogger` observer, and starts a loop to display a menu and process user commands until the user enters "exit".
2. `displayMenu()`: Displays available commands to the user.
3. `getValidCommand(Scanner scanner)`: Prompts the user to enter a valid command until one is entered.
4. `isValidCommand(String command)`: Checks if the given command is valid.
5. `processCommand(String command, Scanner scanner, ScheduleManager manager)`: Processes the command and performs the corresponding action.
6. `LoggerSetUp()`: Sets up the logger to write log messages to the "astronaut_task.log" file.
7. `addTask(Scanner scanner, ScheduleManager manager)`: Adds a new task to the `ScheduleManager`.
8. `getUniqueTaskDescription(Scanner scanner, ScheduleManager manager)`: Prompts the user for a unique task description and checks its uniqueness.
9. `getValidTimeInput(Scanner scanner, String prompt)`: Retrieves a valid time input from the user.
10. `getValidEndTimeInput(Scanner scanner, LocalTime startTime)`: Retrieves a valid end time input from the user.
11. `getValidPriorityInput(Scanner scanner)`: Retrieves a valid priority input from the user.
12. `removeTask(Scanner scanner, ScheduleManager manager)`: Removes a task based on its description.
13. `viewTasks(ScheduleManager manager)`: Displays all tasks scheduled for the day.
14. `markTaskAsComplete(Scanner scanner, ScheduleManager manager)`: Marks a task as complete by its description.
15. `viewCompletedTasks(ScheduleManager manager)`: Displays all completed tasks.
16. `viewTasksByPriority(Scanner scanner, ScheduleManager manager)`: Displays tasks by priority level.

### ConsoleLogger

Implements the `ScheduleObserver` interface with the following method:
- `update(Task task, String action)`: Updates the console with a message indicating the action performed on the task and its description.

### ScheduleManager

Singleton class managing tasks. Methods include:
1. `getInstance()`: Returns a singleton instance.
2. `addTask(Task task)`: Adds a task, checking for conflicts.
3. `removeTask(Task task)`: Removes a task and notifies observers.
4. `getAllTasks()`: Retrieves all tasks not completed, sorted by start time.
5. `getTasksByPriority(PriorityLevel priority)`: Retrieves tasks by priority level, sorted by start time.
6. `getTaskByDescription(String description)`: Retrieves a task by its description.
7. `markTaskAsComplete(Task task)`: Marks a task as complete and notifies observers.
8. `getCompletedTasks()`: Returns a list of completed tasks.
9. `addObserver(ScheduleObserver observer)`: Adds an observer to the list.
10. `notifyObservers(Task task, String action)`: Notifies observers of a task's action.

### ScheduleObserver

Java interface defining a contract for classes that want to be notified when a task is added, removed, or completed. Contains:
- `update(Task task, String action)`: Called by `ScheduleManager` when a task is added, removed, or completed.

### Task

Represents a task with attributes such as description, start time, end time, priority, and completion status. Methods include:
- Constructor: Initializes task attributes.
- Setters/Getters: Set and get attribute values.
- `isCompleted`: Returns the completion status.
- `overlapsWith`: Checks for overlapping with another task.

### TaskConflictException

Custom exception extending `Exception`. Methods include:
- `TaskConflictException(String message)`: Constructor to set the message for the exception.

### TaskFactory

Utility class providing a static method to create `Task` objects. Methods include:
- `createTask(String description, String startTime, String endTime, String priority)`: Creates a new `Task` object with the provided parameters, parsing times and converting priority levels.

## Logging

The system uses Java's built-in logging mechanism to log:
- **Errors**: Invalid input, task conflicts, and other errors.
- **Warnings**: Invalid priority levels, tasks not found, and other warnings.
- **Info**: Tasks added, removed, marked as complete, and other informational messages.

Log messages are written to a file named "astronaut_task.log" with a log level set to ALL. The logging mechanism can be customized for specific needs, such as changing the log level or log file location.


