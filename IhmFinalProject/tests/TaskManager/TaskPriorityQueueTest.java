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

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class TaskPriorityQueueTest {

    // Test adding task to priority queue
    @Test
    public void testAddTask() {
        TaskPriorityQueue queue = new TaskPriorityQueue();
        LocalDate dueDate = LocalDate.of(2024, 11, 25);
        Task task = new Task("Test Task", "Description", dueDate, 2);
        queue.add(task);
        assertEquals(1, queue.size());
    }

    // Test removing highest priority task from queue
    @Test
    public void testRemove() {
        TaskPriorityQueue queue = new TaskPriorityQueue();
        LocalDate dueDate = LocalDate.of(2024, 11, 25);
        Task task = new Task("Test Task", "Description", dueDate, 2);
        queue.add(task);
        Task removedTask = queue.remove();
        assertEquals(task, removedTask);
        assertTrue(queue.isEmpty());
    }

    // Test peeking at highest priority task without removing it
    @Test
    public void testPeek() {
        TaskPriorityQueue queue = new TaskPriorityQueue();
        LocalDate dueDate = LocalDate.of(2024, 11, 25);
        Task task = new Task("Test Task", "Description", dueDate, 2);
        queue.add(task);
        Task peekedTask = queue.peek();
        assertEquals(task, peekedTask);
    }

    // Test adding multiple tasks with different priorities
    @Test
    public void testAddMultipleTasks() {
        TaskPriorityQueue queue = new TaskPriorityQueue();
        LocalDate dueDate = LocalDate.of(2024, 11, 25);
        Task task1 = new Task("Task 1", "Description", dueDate, 1);
        Task task2 = new Task("Task 2", "Description", dueDate, 2);
        queue.add(task1);
        queue.add(task2);
        assertEquals(2, queue.size());
        assertEquals(task1, queue.peek());
    }

    // Test peeking when queue is empty
    @Test
    public void testPeekEmptyQueue() {
        TaskPriorityQueue queue = new TaskPriorityQueue();
        assertNull(queue.peek());
    }
}
