package persistence;

import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//SOURCE: JSONSerializationDemo
public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNoFile() {
        JsonReader reader= new JsonReader("./data/noSuchFile.json");
        try {
            ToDoList td = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }

    @Test
    void testReaderEmptyToDoList() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyToDoList.json");
        try {
            ToDoList td = reader.read();
            assertEquals(0, td.getSizeTaskList());
        } catch (IOException e) {
            fail("Something went wrong");
        }
    }

    @Test
    public void testReaderGeneralWorkroom() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralToDoList.json");
        try {
            ToDoList td = reader.read();
            List<Task> tasklist = td.getTaskList();
            assertEquals(2, tasklist.size());
            checkTask("Drink water", tasklist.get(0));
            checkTask("Wash hair", tasklist.get(1));
        } catch (IOException e) {
            fail("Something went wrong");
        }
    }
}
