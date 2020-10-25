package persistence;

import model.Task;
import model.ToDoList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//SOURCE: JSONSerializationDemo
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads todolist from file and returns it,
    // throws IOException if an error occurs reading data from file
    public ToDoList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject((jsonData));
        return parseToDoList(jsonObject);
    }

    //EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parses todolist from JSON object and returns it
    private ToDoList parseToDoList(JSONObject jsonObject) {
        ToDoList td = new ToDoList();
        addTasksJson(td, jsonObject);
        return td;
    }

    //MODIFIES: td
    //EFFECTS: parses list of tasks from JSON object and adds to todolist
    private void addTasksJson(ToDoList td, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("tasklist");
        for (Object json: jsonArray) {
            JSONObject nextTask = (JSONObject) json;
            addTaskJson(td, nextTask);
        }
    }

    //MODIFIES: td
    //EFFECTS: parses task from JSON object and adds to todolist
    private void addTaskJson(ToDoList td, JSONObject jsonObject) {
        String task = jsonObject.getString("task");
        Task taskNew = new Task(task);
        td.addTask(taskNew);
    }
}
