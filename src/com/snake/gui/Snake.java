package com.snake.gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Snake {

    private List<Point> body = new ArrayList<>();
    private Direction direction = Direction.UP;
    private Point head;

    public enum Direction {
        LEFT,
        RIGHT,
        DOWN,
        UP
    }

    public Snake() {
        int startX = Constants.DOT * 20;
        int startY = Constants.DOT * 20;
        for (int i = 0; i < 10; i++) {
            Point point = new Point(Constants.DOT * i + startX, Constants.DOT + startY);
            body.add(point);
        }
        head = body.get(0);
    }

    public void move() {
        for (int i = body.size() - 1; i > 0; i--) {
            Point currentPoint = body.get(i);
            Point newPoint = body.get(i - 1);
            currentPoint.move(newPoint.x, newPoint.y);
        }

        switch (direction) {
            case LEFT:
                head.translate(-Constants.DOT, 0);
                break;
            case RIGHT:
                head.translate(Constants.DOT, 0);
                break;
            case DOWN:
                head.translate(0, Constants.DOT);
                break;
            case UP:
                head.translate(0, -Constants.DOT);
                break;
        }
    }

    public List<Point> getBody() {
        return body;
    }

    public Point getHead() {
        return head;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
