package ui;

import model.Task;
import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


// SOURCE: initializeGraphics from SimpleDrawingPlayer
// referenced from Teller
public class Main extends JFrame {
    private ToDoList list;
    private JsonWriter jsonwriter;
    private JsonReader jsonreader;
    private JLabel label;
    private JPanel panel;
    private ImageIcon icon;
    private static final String JSON_STORE = "./data/list.json";


    public Main() {
        super("Task List");

        initializeFields();
        initializeGraphics();
        initializeLayout();
//        initializeImage();

    }

    // MODIFIES: this
    // EFFECTS: creates new object for fields
    private void initializeFields() {
        list = new ToDoList();
        jsonwriter = new JsonWriter(JSON_STORE);
        jsonreader = new JsonReader(JSON_STORE);
        label = new JLabel();
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(100, 100));
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this DrawingEditor will operate
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

//    // SOURCE: c3 lecture lab
//    // EFFECTS: produces image
//    private void initializeImage() {
//        icon = new ImageIcon("Me.png");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: adds image to GUI
//    public void setCheck() {
//        label = new JLabel(icon);
//        panel.add(label);
//        label.setIcon(icon);
//    }

    // MODIFIES: this
    // EFFECTS: adds function and image to GUI
    private void initializeLayout() {
        setLayout(new FlowLayout());
        add(makeButton("Add task"));
        add(makeButton("List of completed tasks"));
        add(makeButton("Change status of task"));
        add(makeButton("Remove task"));
        add(makeButton("Save list"));
        add(makeButton("Load list"));
        add(new JLabel(new ImageIcon("Me.png")));
        pack();
    }

    // EFFECTS: makes a JButton for functions
    private JButton makeButton(String name) {
        JButton b = new JButton();
        b.setText(name);
        b.setBounds(80, 80, 5000, 1150);
        if (name.equals("Add task")) {
            addTaskToList(b);
        } else if (name.equals("List of completed tasks")) {
            listCompleteTasks(b);
        } else if (name.equals("Change status of task")) {
            statusChange(b);
        } else if (name.equals("Remove task")) {
            removeTaskFromList(b);
        } else if (name.equals("Save list")) {
            saveList(b);
        } else if (name.equals("Load list")) {
            loadList(b);
        }
        return b;
    }

    // EFFECTS: reads list from file
    private void loadList(JButton b) {
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent f) {
                try {
                    list = jsonreader.read();
                    JOptionPane.showMessageDialog(null, "Loaded");
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Cannot read from the file ");
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: writes list into file
    private void saveList(JButton b) {
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent f) {
                try {
                    jsonwriter.open();
                    jsonwriter.write(list);
                    jsonwriter.close();
                    JOptionPane.showMessageDialog(null, "List is saved");
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "Cannot write file to " + JSON_STORE);
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: removes task from list
    private void removeTaskFromList(JButton b) {
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String removingTask = promptUser("What task would you like to remove?");
                for (Task t : list.getTaskList()) {
                    if (t.getTask().equals(removingTask)) {
                        list.removeTask(t);
                    }
                }
                JOptionPane.showMessageDialog(b, "Task removed");
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: changes status
    private void statusChange(JButton b) {
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String changeStatus = promptUser("Which task would you like to change?");
                String status = null;
                for (Task t : list.getTaskList()) {
                    if (t.getTask().equals(changeStatus)) {
                        t.changeStatus();
                        status = t.getStatus();
                    }
                }
                JOptionPane.showMessageDialog(b, "Done, " + changeStatus + " is " + status);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: adds task to list of tasks
    private void addTaskToList(JButton b) {
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String addingTask = promptUser("What task would you like to add?");
                Task task = new Task(addingTask);
                list.addTask(task);
                addDetailToTask(task);
            }
        });
    }

    // EFFECTS: returns list of complete tasks
    private void listCompleteTasks(JButton b) {
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> completedTasks = (ArrayList<String>) list.listTasksCompleted();
                JOptionPane.showMessageDialog(null, completedTasks,
                        "Printing results", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: adds detail to the task
    private void addDetailToTask(Task task) {
        int input = JOptionPane.showConfirmDialog(
                null,
                "Would you like to add details?", "Question", JOptionPane.YES_NO_OPTION);
        if (input == JOptionPane.YES_OPTION) {
            String addDetail = promptUser("Insert details");
            task.addDetails(addDetail);
        }
    }

    // EFFECTS: receives user input
    private String promptUser(String prompt) {
        return JOptionPane.showInputDialog(prompt);
    }

    public static void main(String[] args) {
        new Main();
    }
}
