package model;

import java.util.ArrayList;
import java.util.List;

// ToDoList represents a list containing tasks
public class ToDoList {
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
    public List<String> listTasksCompleted() {
        List<String> list = new ArrayList<>();
        for (Task t: taskList) {
            if (t.getStatus().equals("completed")) {
                list.add(t.getTask());
            }
        }
        return list;
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


}
