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

public class TaskTest {

    // Test constructor and getter methods
    @Test
    public void testTaskConstructorAndGetters() {
        LocalDate dueDate = LocalDate.of(2024, 11, 25);
        Task task = new Task("Test Task", "This is a test task", dueDate, 2);
        assertEquals("Test Task", task.getTitle());
        assertEquals("This is a test task", task.getDescription());
        assertEquals(dueDate, task.getDueDate());
        assertEquals(2, task.getPriority());
    }

    // Test toString method
    @Test
    public void testToString() {
        LocalDate dueDate = LocalDate.of(2024, 11, 25);
        Task task = new Task("Test Task", "This is a test task", dueDate, 2);
        String expectedString = "Title: Test Task, Description: This is a test task, Due: 11-25-2024, Priority: 2";
        assertEquals(expectedString, task.toString());
    }

    // Test invalid task creation from empty title
    @Test
    public void testInvalidTaskTitle() {
        LocalDate dueDate = LocalDate.of(2024, 11, 25);
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("", "Description", dueDate, 2);
        });
    }

    // Test invalid task creation from empty description
    @Test
    public void testInvalidTaskDescription() {
        LocalDate dueDate = LocalDate.of(2024, 11, 25);
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("Test Task", "", dueDate, 2);
        });
    }

    // Test invalid task creation from null due date
    @Test
    public void testInvalidTaskDueDateNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("Test Task", "Description", null, 2);
        });
    }

    // Test invalid task creation from zero or negative priority
    @Test
    public void testInvalidTaskPriority() {
        LocalDate dueDate = LocalDate.of(2024, 11, 25);
        
        // Test edge case priority 0 is invalid
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("Test Task", "Description", dueDate, 0);
        });
        
        // Test edge case priority 11 is invalid
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("Test Task", "Description", dueDate, 11);
        });
        
        // Test priority 1 inclusive edge case is valid
        Task validTaskLow = new Task("Test Task", "Description", dueDate, 1);
        assertEquals(1, validTaskLow.getPriority());

        // Test priority 10 inclusive edge case is valid
        Task validTaskHigh = new Task("Test Task", "Description", dueDate, 10);
        assertEquals(10, validTaskHigh.getPriority());
    }
}
