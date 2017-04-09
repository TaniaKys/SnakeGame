package com.snake.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game extends JPanel implements ActionListener {

    public enum State {
        GAME_OVER,
        IN_PROGRESS,
        NOT_STARTED,
        WIN
    }

    private int fieldWidth;
    private int fieldHeight;

    private Snake snake;
    private Apple apple;
    private KListener keyListener;
    private Timer timer;

    private Menu menu;
    private State state;

    public Game() {
        snake = new Snake();
        apple = new Apple();
        keyListener = new KListener();
        fieldWidth = Constants.WIDTH * Constants.DOT;
        fieldHeight = Constants.HEIGHT * Constants.DOT;
        state = State.NOT_STARTED;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Constants.COLOR_FIELD);
        g.fillRect(0, 0, fieldWidth, fieldHeight);
        paintApple(g);
        paintSnake(g);
    }

    private void paintSnake(Graphics g) {
        g.setColor(Constants.COLOR_SNAKE_HEAD);
        g.fillRect(snake.getHead().x, snake.getHead().y, Constants.DOT, Constants.DOT);
        g.setColor(Constants.COLOR_SNAKE_BODY);
        for (int i = 1; i < snake.getBody().size(); i++) {
            g.fillRect(snake.getBody().get(i).x, snake.getBody().get(i).y, Constants.DOT, Constants.DOT);
        }
    }

    private void paintApple(Graphics g) {
        g.setColor(Constants.COLOR_APPLE);
        g.fillRect(apple.getPosition().x, apple.getPosition().y, Constants.DOT, Constants.DOT);
    }

    private void placeApple() {
        apple.generatePosition();
        if (snake.getBody().contains(apple.getPosition())) {
            placeApple();
        }
    }

    private boolean isWin() {
        return snake.getBody().size() == Constants.WIDTH * Constants.HEIGHT;
    }

    private boolean isCollision() {
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

    private boolean appleIsEaten() {
        return snake.getHead().equals(apple.getPosition());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (state.equals(State.IN_PROGRESS)) {
            if (appleIsEaten()) {
                placeApple();
                snake.grow();
            }
            snake.move();
            if (isCollision()) {
                state = State.GAME_OVER;
                stop();
            } else if (isWin()) {
                state = State.WIN;
                stop();
            } else {
                repaint();
            }
        }
    }

    public void start() {
        reset();
        timer = new Timer(Constants.SPEED, this);
        state = State.IN_PROGRESS;
        placeApple();
        timer.start();
    }

    public void stop() {
        state = State.GAME_OVER;
        timer.stop();
        if (menu != null) menu.shows();
    }

    private void reset() {
        snake = new Snake();
        apple = new Apple();
        state = State.NOT_STARTED;
    }

    public void addMenu(Menu menu) {
        this.menu = menu;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    public State getState() {
        return state;
    }

    private class KListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (state.equals(State.GAME_OVER) || state.equals(State.NOT_STARTED)) {
                return;
            }
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
