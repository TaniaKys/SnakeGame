package com.snake.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game extends JPanel implements ActionListener {

    private Snake snake;
    private KListener keyListener;
    private Timer timer;

    private int fieldWidth;
    private int fieldHeight;

    private boolean gameOver;

    public Game() {
        snake = new Snake();
        keyListener = new KListener();
        fieldWidth = Constants.WIDTH * Constants.DOT;
        fieldHeight = Constants.HEIGHT * Constants.DOT;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.cyan);
        g.fillRect(0, 0, fieldWidth, fieldHeight);

        paintSnake(g);
    }

    private void paintSnake(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(snake.getHead().x, snake.getHead().y, Constants.DOT, Constants.DOT);
        g.setColor(Color.black);
        for (int i = 1; i < snake.getBody().size(); i++) {
            g.fillRect(snake.getBody().get(i).x, snake.getBody().get(i).y, Constants.DOT, Constants.DOT);
        }
    }

    public boolean isCollision() {
        return collidedWithWall() || collidedWithItself();
    }

    private boolean collidedWithWall() {
        if (snake.getHead().x == fieldWidth
                || snake.getHead().y == fieldHeight
                || snake.getHead().x < 0
                || snake.getHead().y < 0) {
            return true;
        }
        return false;
    }

    private boolean collidedWithItself() {
        for (Point bodyPoint : snake.getBody()) {
            if (bodyPoint != snake.getHead() && snake.getHead().equals(bodyPoint)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) {
            return;
        }
        snake.move();
        if (!isCollision()) {
            repaint();
        } else {
            stop();
        }
    }

    public void start() {
        timer = new Timer(500, this);
        gameOver = false;
        timer.start();
    }

    public void stop() {
        gameOver = true;
        timer.stop();
    }

    private class KListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            switch (e.getKeyCode()) {
                case 37:
                case 65:
                    if (snake.getDirection() != Snake.Direction.RIGHT) {
                        snake.setDirection(Snake.Direction.LEFT);
                        actionPerformed(null);
                    }
                    break;
                case 39:
                case 68:
                    if (snake.getDirection() != Snake.Direction.LEFT) {
                        snake.setDirection(Snake.Direction.RIGHT);
                        actionPerformed(null);
                    }
                    break;
                case 40:
                case 83:
                    if (snake.getDirection() != Snake.Direction.UP) {
                        snake.setDirection(Snake.Direction.DOWN);
                        actionPerformed(null);
                    }
                    break;
                case 38:
                case 87:
                    if (snake.getDirection() != Snake.Direction.DOWN) {
                        snake.setDirection(Snake.Direction.UP);
                        actionPerformed(null);
                    }
                    break;
            }
        }
    }

    public KListener getKeyListener() {
        return keyListener;
    }
}
