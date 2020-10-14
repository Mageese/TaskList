package model;

// Task represents a task with its status and any additional details
public class Task {
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

    public void changeStatus() {
        if (getStatus() == "not completed") {
            status = "completed";
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

}
