package ui;

import exception.EmptyListException;
import model.Task;
import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


// To-do list application
//SOURCE: TellerApp
//SOURCE: JSONSerializationDemo
public class ToDoListApp {
    private Scanner scan;
    private ToDoList toDoList;
    private JsonWriter jsonwriter;
    private JsonReader jsonreader;
    private static final String JSON_STORE = "./data/list.json";

    // run the application
    public ToDoListApp() {
        runList();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    public void runList() {
        boolean keepGoing = true;
        String user = null;
        scan = new Scanner(System.in);
        jsonwriter = new JsonWriter(JSON_STORE);
        jsonreader = new JsonReader(JSON_STORE);

        toDoList = new ToDoList();

        while (keepGoing) {
            displayMenu();
            user = scan.next();
            user = user.toLowerCase();

            if (user.equals("q")) {
                keepGoing = false;
            } else {
                processUser(user);
            }
        }

        System.out.println("\nThanks for looking");

    }

    //EFFECTS: shows options from the to-do list.
    private void displayMenu() {
        System.out.println("\nWhat do you want to do?");
        System.out.println("\ta -> Add task");
        System.out.println("\td -> Delete task");
        System.out.println("\tf -> Mark task as completed/ not completed");
        System.out.println("\tc -> See completed tasks");
        System.out.println("\ts -> save list to file");
        System.out.println("\tl -> load list from file");
        System.out.println("\tq -> Quit");
    }

    //MODIFIES: this
    //EFFECTS: process user input
    private void processUser(String user) {
        if (user.equals("a")) {
            addTask();
        } else if (user.equals("d") && (toDoList.getSizeTaskList() > 0)) {
            deleteTask();
        } else if (user.equals("f")) {
            changeStatus();
        } else if (user.equals("c")) {
            listCompletedTasks();
        } else if (user.equals("s")) {
            saveList();
        } else if (user.equals("l")) {
            loadList();
        } else {
            System.out.println("Option not available");
        }
    }

    //EFFECTS: saves list to designated file
    private void saveList() {
        try {
            jsonwriter.open();
            jsonwriter.write(toDoList);
            jsonwriter.close();
            System.out.println("Your list is now saved in " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot write file to " + JSON_STORE);
        }
    }

    //EFFECTS: reads list from designated file
    private void loadList() {
        try {
            toDoList = jsonreader.read();
            System.out.println("Loaded " + toDoList + "from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Cannot read from the file ");
        }
    }


    //MODIFIES: this
    //EFFECTS: adds task to to-do list and details to task
    private void addTask() {
        System.out.println("What task would you like to add?");
        String newTask = scan.next() + scan.nextLine();
        Task task = new Task(newTask);
        toDoList.addTask(task);

        if (toDoList.getContainsTaskList(task)) {
            System.out.println("Add further detail?");
            System.out.println("\ty -> Yes");
            System.out.println("\tn -> No");

            String confirm = scan.next() + scan.nextLine();
            confirm = confirm.toLowerCase();

            if (confirm.equals("y")) {
                System.out.println("Provide details");
                String details = scan.next() + scan.nextLine();
                task.addDetails(details);

                System.out.println("The details are: " + task.getDetail());
            }
        }

        System.out.println("Alright, done!");
    }

    //REQUIRES: more than one task in to-do list
    //MODIFIES: this
    //EFFECTS: removes task from to-do list
    private void deleteTask() {
        System.out.println("What task would you like to remove?" + " Input the task selected");
        String selectedTask = scan.next() + scan.nextLine();

        for (Task t: toDoList.getTaskList()) {
            if (t.getTask().equals(selectedTask)) {
                toDoList.removeTask(t);
            }
        }

        System.out.println("Removed " + selectedTask + " !");
    }

    //MODIFIES: this
    //EFFECTS: changes status of task to completed
    private void changeStatus() {
        System.out.println("What task would you like to change status?" + " Input the task selected");
        String selectedTask = scan.nextLine() + scan.nextLine();
        String status = null;

        for (Task t: toDoList.getTaskList()) {
            if (t.getTask().equals(selectedTask)) {
                t.changeStatus();
                status = t.getStatus();
            }
        }

        System.out.println(selectedTask + " is " + status);

    }

    //EFFECTS: prints out the completed tasks in to-do list
    private void listCompletedTasks() {
        try {
            System.out.println(toDoList.listTasksCompleted());
        } catch (EmptyListException e) {
            e.printStackTrace();
        }
    }

}
