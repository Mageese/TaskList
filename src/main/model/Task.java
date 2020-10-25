package model;

import org.json.JSONObject;
import persistence.Writable;

// Task represents a task with its status and any additional details
public class Task implements Writable {
    private String task;
    private String status;
    private String detail;

    public Task(String task) {
        this.task = task;
        status = "not completed";
        detail = "";
    }

    //MODIFIES: this
    //EFFECTS: add details to a task
    public void addDetails(String s) {
        detail = s;
    }

    //MODIFIES: this
    //EFFECTS: if status is not completed, then status becomes completed, vice versa.
    public void changeStatus() {
        if (getStatus() == "not completed") {
            status = "completed";
        } else {
            status = "not completed";
        }
    }


// Getters
    public String getDetail() {
        return detail;
    }

    public String getStatus() {
        return status;
    }

    public String getTask() {
        return task;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("task", task);
        return json;
    }
}
