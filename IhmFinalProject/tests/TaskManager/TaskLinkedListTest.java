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

public class TaskLinkedListTest {

    // Test adding task to linked list
    @Test
    public void testAddTask() {
        TaskLinkedList taskList = new TaskLinkedList();
        LocalDate dueDate = LocalDate.of(2024, 11, 25);
        Task task = new Task("Test Task", "Description", dueDate, 1);
        taskList.addTask(task);
        assertEquals(1, taskList.getTasks().size());
    }

    // Test removing task from linked list
    @Test
    public void testRemoveTask() {
        TaskLinkedList taskList = new TaskLinkedList();
        LocalDate dueDate = LocalDate.of(2024, 11, 25);
        Task task = new Task("Test Task", "Description", dueDate, 1);
        taskList.addTask(task);
        assertTrue(taskList.removeTask(task));
        assertFalse(taskList.removeTask(task));
    }

    // Test removing task by title
    @Test
    public void testRemoveTaskByTitle() {
        TaskLinkedList taskList = new TaskLinkedList();
        LocalDate dueDate = LocalDate.of(2024, 11, 25);
        Task task = new Task("Test Task", "Description", dueDate, 1);
        taskList.addTask(task);
        assertTrue(taskList.removeTaskByTitle("Test Task"));
        assertFalse(taskList.removeTaskByTitle("Nonexistent Task"));
    }

    // Test removing task when list is empty
    @Test
    public void testRemoveTaskFromEmptyList() {
        TaskLinkedList taskList = new TaskLinkedList();
        Task task = new Task("Test Task", "Description", LocalDate.of(2024, 11, 25), 1);
        assertFalse(taskList.removeTask(task));
    }

    // Test removing task by title from list with multiple tasks
    @Test
    public void testRemoveTaskByTitleMultipleTasks() {
        TaskLinkedList taskList = new TaskLinkedList();
        LocalDate dueDate = LocalDate.of(2024, 11, 25);
        Task task1 = new Task("Task 1", "Description", dueDate, 1);
        Task task2 = new Task("Task 2", "Description", dueDate, 2);
        taskList.addTask(task1);
        taskList.addTask(task2);
        assertTrue(taskList.removeTaskByTitle("Task 1"));
        assertFalse(taskList.removeTaskByTitle("Nonexistent Task"));
    }
}
