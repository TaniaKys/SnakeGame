package com.snake.gui;

import javax.swing.*;

/**
 * Created by Tania on 09.01.2017.
 */
public class EntryPoint {


    public static void main(String[] args) {
        createScene();
    }

    private static void createScene() {
        JFrame scene = new JFrame("Snake");

        scene.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scene.setSize(Constants.WIDTH * Constants.DOT, Constants.HEIGHT * Constants.DOT);
        scene.setLocationRelativeTo(null);

        Game snakeGame = new Game();

        scene.add(snakeGame);
        scene.addKeyListener(snakeGame.getKeyListener());
        //snakeGame.repaint();

        scene.setVisible(true);

        snakeGame.start();
    }
}
