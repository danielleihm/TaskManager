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

import javax.swing.SwingUtilities;

public class TaskManagerDriver {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			// Initialize task queue
			TaskManagerDriver taskManager = new TaskManagerDriver();
			// Initialize GUI
			new TaskManagerGUI(taskManager);
		});
	}
}