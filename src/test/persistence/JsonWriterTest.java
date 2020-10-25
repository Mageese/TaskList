package persistence;

import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//SOURCE: JSONSerializationDemo
public class JsonWriterTest extends JsonTest{
    @Test
    public void testWriterInvalidFile() {
        try {
            ToDoList td = new ToDoList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException should be result");
        } catch (IOException e) {
            System.out.println("Pass!");
        }
    }

    @Test
    public void testWriterEmptyToDoList() {
        try {
            ToDoList td = new ToDoList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyToDoList.json");
            writer.open();
            writer.write(td);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyToDoList.json");
            td = reader.read();
            assertEquals(0, td.getSizeTaskList());
        } catch (IOException e) {
            fail("Something went wrong");
        }
    }

    @Test
    public void testWriterGeneralToDoList() {
        try{
            ToDoList td = new ToDoList();
            td.addTask(new Task("Drink water"));
            td.addTask((new Task("Wash hair")));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralToDoList.json");
            writer.open();
            writer.write(td);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralToDoList.json");
            td = reader.read();
            assertEquals(2, td.getSizeTaskList());
            List<Task> tasklist = td.getTaskList();
            checkTask("Drink water", tasklist.get(0));
            checkTask("Wash hair", tasklist.get(1));

        } catch (IOException e) {
            fail("Something went wrong");
        }
    }
}

