package ui;

import model.Task;
import model.ToDoList;

import java.util.Scanner;


// To-do list application
//SOURCE: TellerApp
public class ToDoListApp {
    private Scanner scan;
    private String user;
    private ToDoList toDoList;

    // run the application
    public ToDoListApp() {
        runList();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    public void runList() {
        boolean keepGoing = true;
        user = null;
        scan = new Scanner(System.in);

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

        System.out.println("Thanks for looking");

    }


    //EFFECTS: shows options from the to-do list.
    private void displayMenu() {
        System.out.println("\nWhat do you want to do?");
        System.out.println("\ta -> Add task");
        System.out.println("\td -> Delete task");
        System.out.println("\tc -> See completed tasks");
        System.out.println("\tq -> Quit");
    }

    //MODIFIES: this
    //EFFECTS: process user input
    private void processUser(String user) {
        if (user.equals("a")) {
            addTask();
        } else if (user.equals("d")) {
            if (toDoList.getSizeTaskList() > 0) {
                deleteTask();
            }
            System.out.println("No tasks are present in the list!");

        } else if (user.equals("c")) {
            listCompletedTasks();
        } else {
            System.out.println("Option not available");
        }
    }


    //MODIFIES: this
    //EFFECTS: adds task to to-do list and details to task
    private void addTask() {
        System.out.println("What task would you like to add?");
        String taskInput = scan.next();
        Task task = new Task(taskInput);
        toDoList.addTask(task);

        System.out.println("Add further detail?");
        System.out.println("\ty -> Yes");
        System.out.println("\tn -> No");

        String confirm = scan.next();
        confirm = confirm.toLowerCase();

        if (confirm.equals("yes")) {
            System.out.println("Provide details");
            task.addDetails(scan.next());
        }

        System.out.println("Alright, done!");
    }

    //REQUIRES: more than one task in to-do list
    //MODIFIES: this
    //EFFECTS: removes task from to-do list
    private void deleteTask() {
        System.out.println("What task would you like to remove?" + "Input the task selected");
        String selectedTask = scan.next();

        for (Task t: toDoList.getTaskList()) {
            if (t.getTask().equals(selectedTask)) {
                toDoList.removeTask(t);
            }
        }

        System.out.println("Removed " + selectedTask + " !");
    }

    //EFFECTS: prints out the tasks in to-do list
    private void listCompletedTasks() {
        System.out.println(toDoList.listTasksCompleted());
    }

}
