package model;

import exception.EmptyListException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoTest {
    ToDoList toDoList;
    Task taskOne;
    Task taskTwo;
    Task taskThree;

    @BeforeEach
    public void setUp() {
        toDoList = new ToDoList();
        taskOne = new Task("Do your homework!");
        taskTwo = new Task("Wash the dishes!");
        taskThree = new Task("Paint the walls!");
    }

    @Test
    public void testConstructor() {
        ToDoList todo = new ToDoList();
        assertEquals(0, todo.getSizeTaskList());
    }

    @Test
    public void testAddOneTask() {
        toDoList.addTask(taskOne);

        assertEquals(1, toDoList.getSizeTaskList());

    }

    @Test
    public void testAddMultipleTasks() {
        toDoList.addTask(taskOne);
        toDoList.addTask(taskTwo);
        toDoList.addTask(taskThree);

        assertEquals(3, toDoList.getSizeTaskList());
        assertTrue(toDoList.getContainsTaskList(taskTwo));
        assertTrue(toDoList.getContainsTaskList(taskThree));

    }

    @Test
    public void testRemoveOneTask() {
        toDoList.addTask(taskOne);
        assertEquals(1, toDoList.getSizeTaskList());
        
        toDoList.removeTask(taskOne);
        assertFalse(toDoList.getContainsTaskList(taskOne));
        assertEquals(0,toDoList.getSizeTaskList());
    }

    @Test
    public void testRemoveMultipleTasks() {
        toDoList.addTask(taskOne);
        toDoList.addTask(taskTwo);
        toDoList.addTask(taskThree);
        assertEquals(3, toDoList.getSizeTaskList());

        toDoList.removeTask(taskOne);
        toDoList.removeTask(taskTwo);
        assertEquals(1, toDoList.getSizeTaskList());
        assertTrue(toDoList.getContainsTaskList(taskThree));
        assertFalse(toDoList.getContainsTaskList(taskOne));
        assertFalse(toDoList.getContainsTaskList(taskTwo));
    }

    @Test
    public void testListNone() {
        List<String> list = null;
        try {
            list = toDoList.listTasksCompleted();
            fail("error");
        } catch (EmptyListException e) {
            System.out.println("cool");
        }
    }

    @Test
    public void testListCompletedOne() {
        taskOne.changeStatus();
        toDoList.addTask(taskOne);

        List<String> list = null;
        try {
            list = toDoList.listTasksCompleted();
        } catch (EmptyListException e) {
            e.printStackTrace();
        }

        assertEquals(1, list.size());
    }

    @Test
    public void testListCompletedMultiple() {
        taskOne.changeStatus();
        taskTwo.changeStatus();
        taskThree.changeStatus();

        toDoList.addTask(taskOne);
        toDoList.addTask(taskTwo);
        toDoList.addTask(taskThree);

        List<String> list = null;
        try {
            list = toDoList.listTasksCompleted();
        } catch (EmptyListException e) {
            e.printStackTrace();
        }
        assertEquals(3, list.size());
    }

    @Test
    public void testListCompletedFew() {
        taskOne.changeStatus();
        taskTwo.changeStatus();

        toDoList.addTask(taskOne);
        toDoList.addTask(taskTwo);
        toDoList.addTask(taskThree);

        List<String> list = null;
        try {
            list = toDoList.listTasksCompleted();
        } catch (EmptyListException e) {
            e.printStackTrace();
        }

        assertEquals(2, list.size());
        assertFalse(list.contains(taskThree.getTask()));
        assertTrue(toDoList.getTaskList().contains(taskThree));


    }


}
