package com.snake.gui;

import javax.swing.*;
import java.awt.*;

public class EntryPoint {


    public static void main(String[] args) {
        createScene();
    }

    private static void createScene() {
        JFrame scene = new JFrame("Snake");

        scene.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scene.setLocationRelativeTo(null);
        scene.setResizable(false);

        Game snakeGame = new Game();
        snakeGame.setPreferredSize(new Dimension(snakeGame.getFieldWidth(), snakeGame.getFieldHeight()));

        scene.add(snakeGame);
        scene.addKeyListener(snakeGame.getKeyListener());
        scene.pack();

        Menu menu = new Menu(snakeGame);
        snakeGame.addMenu(menu);
        scene.add(menu);

        scene.setVisible(true);

    }
}
