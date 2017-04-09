package com.snake.gui;

import javax.swing.*;
import java.awt.*;

public class EntryPoint {


    public static void main(String[] args) {
        createScene();
    }

    private static void createScene() {
        JFrame scene = new JFrame("Snake");

        Game snakeGame = new Game();
        snakeGame.setPreferredSize(new Dimension(snakeGame.getFieldWidth(), snakeGame.getFieldHeight()));

        scene.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scene.setLocationRelativeTo(null);
        scene.setResizable(false);

        scene.add(snakeGame);
        scene.addKeyListener(snakeGame.getKeyListener());
        scene.pack();
        scene.setVisible(true);

        snakeGame.start();
    }
}
