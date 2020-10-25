package persistence;

import model.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

//SOURCE: JSONSerializationDemo
public class JsonTest {
    protected void checkTask(String t, Task task) {
        assertEquals(t, task.getTask());

    }
}
