package TaskManager;

/**
 * @author Danielle Ihm
 * @version 1.0
 * @since 1.0
 /** OS: Windows 11
 * IDE: Eclipse 2023-06
 * Academic Honesty: I attest that this is my original work. I have not used unauthorized
 * source code, either modified or unmodified, nor used generative AI as a final draft.
 * I have not given other fellow student(s) access to my program.
 * References:
 * “Priority Queue Using Linked List.” GeeksforGeeks, GeeksforGeeks, 16 July 2024, www.geeksforgeeks.org/priority-queue-using-linked-list/. 
 * “Linked List Data Structure.” GeeksforGeeks, GeeksforGeeks, 15 Sept. 2024, www.geeksforgeeks.org/linked-list-data-structure/. 
 * “Insertion Sort Algorithm.” GeeksforGeeks, GeeksforGeeks, 7 Oct. 2024, www.geeksforgeeks.org/insertion-sort-algorithm/?ref=shm. 
 * “Introduction to Java Swing.” GeeksforGeeks, GeeksforGeeks, 30 July 2024, www.geeksforgeeks.org/introduction-to-java-swing/. 
 * “Java Development Kit Version 17 API Specification.” BasicOptionPaneUI.ButtonActionListener (Java SE 17 & JDK 17), 10 Oct. 2024,
 * docs.oracle.com/en/java/javase/17/docs/api/java.desktop/javax/swing/plaf/basic/BasicOptionPaneUI.ButtonActionListener.html.
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
	// Initialize variables
	private String taskTitle;
	private String taskDescription;
	private LocalDate taskDueDate;
	private int taskPriority;

	// Constructor with validation
	/**
	 * @param
	 * @param
	 * @param
	 * @param
	 */
	public Task(String title, String description, LocalDate dueDate, int priority) {
		// Validate task title
		if (title == null || title.trim().isEmpty() || title.equals("Enter Task Title")) {
			throw new IllegalArgumentException("Task title cannot be empty or invalid.");
		}
		if (title.length() > 100) {
			throw new IllegalArgumentException("Task title cannot exceed 100 characters.");
		}

		// Validate task description
		if (description == null || description.trim().isEmpty() || description.equals("Enter Task Description")) {
			throw new IllegalArgumentException("Task description cannot be empty or invalid.");
		}
		if (description.length() > 500) {
			throw new IllegalArgumentException("Task description cannot exceed 500 characters.");
		}

		// Validate due date
		if (dueDate == null) {
			throw new IllegalArgumentException("Due date cannot be null.");
		}

		// Validate priority
		if (priority < 1 || priority > 10) {
			throw new IllegalArgumentException("Priority must be a number between 1 and 10.");
		}

		this.taskTitle = title;
		this.taskDescription = description;
		this.taskDueDate = dueDate;
		this.taskPriority = priority;
	}

	// Getter methods
	public String getTitle() {
		return taskTitle;
	}

	public String getDescription() {
		return taskDescription;
	}

	public LocalDate getDueDate() {
		return taskDueDate;
	}

	public int getPriority() {
		return taskPriority;
	}

	// Return task information as string
	@Override
	public String toString() {
		// Format due date
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		String formattedDate = taskDueDate.format(dateFormatter);

		// Return string with title, description, due date, priority
		return "Title: " + taskTitle + ", Description: " + taskDescription + ", Due: " + formattedDate + ", Priority: "
				+ taskPriority;
	}
}
