package model;

import exception.EmptyListException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// ToDoList represents a list containing tasks
public class ToDoList implements Writable {
    private List<Task> taskList;


    //constructor
    public ToDoList() {
        taskList = new ArrayList<>();
    }

    //REQUIRES: task(?)
    //MODIFIES: this
    //EFFECTS: add the task to the list of tasks
    public void addTask(Task t) {
        taskList.add(t);
    }

    //REQUIRES: task in list of tasks
    //MODIFIES: this
    //EFFECTS: removes task from list of tasks
    public void removeTask(Task t) {
        taskList.remove(t);
    }


    //EFFECTS: lists tasks completed in to-do list
    public List<String> listTasksCompleted() throws EmptyListException {
        List<String> list = new ArrayList<>();
        for (Task t : taskList) {
            if (t.getStatus().equals("completed")) {
                list.add(t.getTask());
            }
        }
        if (!(list.size() == 0)) {
            return list;
        } else {
            throw new EmptyListException("No tasks completed");
        }
    }

    //Getters
    public List<Task> getTaskList() {
        return taskList;
    }

    public int getSizeTaskList() {
        return taskList.size();
    }

    public boolean getContainsTaskList(Task t) {
        return taskList.contains(t);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("tasklist", tasklistToJson());
        return json;
    }

    //EFFECTS: returns tasklist as a Json array
    private JSONArray tasklistToJson() {
        JSONArray array = new JSONArray();

        for (Task t : taskList) {
            array.put(t.toJson());
        }

        return array;
    }

}
