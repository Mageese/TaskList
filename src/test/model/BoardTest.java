package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    public Board b;
    public Image i;


    @BeforeEach
    public void setUp() {
        Board b = new Board();
        Image i = new Image();
    }

    @Test
    public void testAddImage() {
        b.addImage(i);

    }

    @Test
    public void testRemoveImage() {

    }

    @Test
    public void testChangeBackground() {}

}