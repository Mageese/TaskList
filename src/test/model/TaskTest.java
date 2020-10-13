package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    Task task;

    @BeforeEach
    public void setUp() {
        task = new Task("Fold the clothes");
    }

    @Test
    public void testConstructor() {
        assertEquals("Fold the clothes", task.getTask());
        assertEquals("", task.getDetail());
        assertEquals("not completed", task.getStatus());
    }

    @Test
    public void testAddDetails() {
        String detail = "Separate the folded clothes by color";

        task.addDetails(detail);

        assertEquals(detail, task.getDetail());
    }

    @Test
    public void testChangeStatus() {
        assertEquals("not completed", task.getStatus());

        task.changeStatus();

        assertEquals("completed",task.getStatus());

        task.changeStatus();

        assertEquals("not completed", task.getStatus());
    }

}
