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

import java.util.LinkedList;

public class TaskPriorityQueue {
    private LinkedList<Task> tasks;

    // Initialize task queue with empty list
    public TaskPriorityQueue() {
        tasks = new LinkedList<>();
    }

    // Add new task to priority queuet
    public void add(Task task) {
        tasks.add(task);
        // Sort tasks using insertion sort after adding new task
        insertSorted();
    }

    // Insertion sort method to sort by priority and then by due date
    void insertSorted() {
        for (int i = 1; i < tasks.size(); i++) {
        	// The task to be inserted in the sorted part of the list
            Task key = tasks.get(i);
            // positionToInsert starts at index, before i
            int positionToInsert = i - 1;

            // Move tasks that are greater than key to one position ahead of current position, itterate backward through list
            while (positionToInsert >= 0 && isGreater(tasks.get(positionToInsert), key)) {
                positionToInsert--;
            }

            // Remove key from current position, insert at correct position
            tasks.remove(i);
            tasks.add(positionToInsert + 1, key);
        }
    }

    // Determine if task1 should come after task2 in queue
    private boolean isGreater(Task task1, Task task2) {
    	// Compare priority, tasks with lower priority values have higher importance so come first
        if (task1.getPriority() < task2.getPriority()) {
        	// task1 has higher priority, comes before task2
            return false;
        } else if (task1.getPriority() == task2.getPriority()) {
            // If priorities are equal, compare due dates, earliest due date comes first
            return task1.getDueDate().isAfter(task2.getDueDate());
        }
        // task1 has lower priority, comes after task2
        return true;
    }

    // Remove and return highest priority task from queue
    public Task remove() {
        if (!tasks.isEmpty()) {
            return tasks.removeFirst();
        }
        return null;
    }

    // Return highest priority task without removing from queue
    public Task peek() {
        if (!tasks.isEmpty()) {
            return tasks.getFirst();
        }
        return null;
    }

    // Check if queue is empty, return true if no tasks remaining
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    // Return number of tasks in queue
    public int size() {
        return tasks.size();
    }

    // Return the list of tasks in queue, ordered by priority and due date
    public LinkedList<Task> getTasks() {
        return tasks;
    }
}