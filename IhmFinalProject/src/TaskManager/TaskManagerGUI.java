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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskManagerGUI {
	private JFrame frame;
	private JTextField titleField, descriptionField, dueDateField, priorityField;
	private DefaultListModel<Task> taskListModel;
	private JList<Task> taskJList;
	private TaskPriorityQueue taskQueue;

	public TaskManagerGUI(TaskManagerDriver taskManager) {
		// Initialize task queue
		taskQueue = new TaskPriorityQueue();
		// Create main frame
		frame = new JFrame("Task Manager");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);
		frame.setLayout(new BorderLayout());

		// Create input panel for entering task details
		JPanel inputPanel = new JPanel(new GridLayout(5, 2));
		// Create text fields for user input for title, description, due date, priority
		titleField = createPlaceholderTextField("Enter Task Title");
		descriptionField = createPlaceholderTextField("Enter Task Description");
		dueDateField = createPlaceholderTextField("MM/DD/YYYY");
		priorityField = createPlaceholderTextField("Enter Priority (1-10)");

		// Add labels and corresponding text fields to input panel
		inputPanel.add(new JLabel("Title:"));
		inputPanel.add(titleField);
		inputPanel.add(new JLabel("Description:"));
		inputPanel.add(descriptionField);
		inputPanel.add(new JLabel("Due Date:"));
		inputPanel.add(dueDateField);
		inputPanel.add(new JLabel("Priority:"));
		inputPanel.add(priorityField);

		// Create buttons for adding, removing, sorting tasks
		JButton addButton = new JButton("Add Task");
		JButton removeButton = new JButton("Remove Selected Task");
		JButton sortButton = new JButton("Sort Tasks");

		// Add listener for Add Task button
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = titleField.getText();
				String description = descriptionField.getText();
				LocalDate dueDate;
				int priority;

				// Parse due date input
				try {
					dueDate = parseDueDate(dueDateField.getText());
					if (dueDate == null) {
						throw new DateTimeParseException("Invalid date format.", dueDateField.getText(), 0);
					}
				} catch (DateTimeParseException ex) {
					JOptionPane.showMessageDialog(frame, "Invalid date format. Please use MM/DD/YYYY.", "Input Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Parse priority input
				try {
					priority = Integer.parseInt(priorityField.getText());
					if (priority < 1 || priority > 10) {
						JOptionPane.showMessageDialog(frame, "Priority must be a number between 1 and 10.",
								"Input Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "Priority must be a valid number between 1 and 10.",
							"Input Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Try creating Task object
				try {
					Task newTask = new Task(title, description, dueDate, priority);
					// Add new task to priority queue and JList display
					taskQueue.add(newTask);
					taskListModel.addElement(newTask);

					// Format due date, update dueDateField with formatted date
					DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
					String formattedDate = dueDate.format(dateFormatter);
					dueDateField.setText(formattedDate);

					// Clear all input fields after task added
					clearInputFields();
				} catch (IllegalArgumentException ex) {
					// If task constructor validation fails, show error message
					JOptionPane.showMessageDialog(frame, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Add listener for Remove Task button
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get selected task from JList
				Task selectedTask = taskJList.getSelectedValue();
				if (selectedTask != null) {
					// Remove task from priority queue and JList
					taskQueue.remove();
					taskListModel.removeElement(selectedTask);
				} else {
					// Show error if no task is selected for removal
					JOptionPane.showMessageDialog(frame, "Please select a task to remove.", "Selection Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Add listener for Sort Tasks button
		sortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Sort tasks in priority queue
				taskQueue.insertSorted();
				// Clear current task list in JList
				taskListModel.clear();
				// Add all tasks from sorted queue back into JList
				for (Task task : taskQueue.getTasks()) {
					taskListModel.addElement(task);
				}
			}
		});

		// Panel for buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);
		buttonPanel.add(sortButton);

		// Create task display area to show tasks in GUI
		taskListModel = new DefaultListModel<>();
		taskJList = new JList<>(taskListModel);
		JScrollPane scrollPane = new JScrollPane(taskJList);

		// Add components to main frame
		frame.add(inputPanel, BorderLayout.NORTH);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);

		// Set to make visible
		frame.setVisible(true);
	}

	// Helper method to create text field with placeholder text
	private JTextField createPlaceholderTextField(String placeholder) {
		JTextField textField = new JTextField(placeholder);
		textField.setForeground(Color.GRAY);

		// Remove placeholder text when field is clicked on
		textField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (textField.getText().equals(placeholder)) {
					// Remove placeholder text
					textField.setText("");
					textField.setForeground(Color.BLACK);
				}
			}

			// Restore placeholder text if field remains empty
			public void focusLost(FocusEvent e) {
				if (textField.getText().isEmpty()) {
					// Set placeholder color
					textField.setForeground(Color.GRAY);
					// Restore placeholder text
					textField.setText(placeholder);
				}
			}
		});

		return textField;
	}

	// Method to clear input fields after task is added
	private void clearInputFields() {
		titleField.setText("Enter Task Title");
		descriptionField.setText("Enter Task Description");
		dueDateField.setText("MM/DD/YYYY");
		priorityField.setText("Enter Priority (1-10)");
	}

	// Helper method to parse due date input string into LocalDate object
	private LocalDate parseDueDate(String dueDate) throws DateTimeParseException {
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		return LocalDate.parse(dueDate, formatDate);
	}
}
