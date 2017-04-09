package com.snake.gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Snake {

    private List<Point> body = new ArrayList<>();
    private Direction direction = Direction.RIGHT;
    private Point head;

    public enum Direction {
        LEFT,
        RIGHT,
        DOWN,
        UP
    }

    public Snake() {
        for (int i = 3; i >= 0; i--) {
            Point point = new Point(Constants.DOT * i , Constants.DOT );
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

    public void grow() {
        Point currentTail = body.get(body.size() - 1);
        Point newTail = new Point(currentTail.x + Constants.DOT, currentTail.y + Constants.DOT);
        body.add(newTail);
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
