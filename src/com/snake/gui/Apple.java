package com.snake.gui;

import java.awt.*;
import java.util.Random;

public class Apple {

    private Point position;
    private Random random;

    public Apple() {
        random = new Random();
        generatePosition();
    }

    public Point generatePosition() {
        int x = random.nextInt(Constants.WIDTH - 1) * Constants.DOT;
        int y = random.nextInt(Constants.HEIGHT - 1) * Constants.DOT;
        position = new Point(x, y);
        return position;
    }

    public Point getPosition() {
        return position;
    }
}
