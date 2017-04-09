package com.snake.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu extends JPanel {

    private JButton button;
    private Game game;
    private JLabel message;

    public Menu(Game game) {
        this.game = game;
        this.setLayout(null);
        initButton();
        initMessage();
        shows();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Constants.COLOR_MENU);
        g.fillRect(0, 0, game.getFieldWidth(), game.getFieldHeight());
    }

    private void initButton() {
        button = new JButton("START");
        int width = game.getFieldWidth() / 2;
        int height = game.getFieldHeight() / 6;
        int posX = (game.getFieldWidth() - width) / 2;
        int posY = (game.getFieldHeight() - height) / 2;
        button.setBounds(posX, posY, width, height);
        button.addMouseListener(new BtnListener());
        button.setFocusable(false);
        this.add(button);
    }

    public void initMessage() {
        message = new JLabel();
        int height = button.getY() + button.getWidth() / 2;
        message.setBounds(0, 0, game.getFieldWidth(), height);
        message.setFont(new Font("Arial", Font.BOLD, 16));
        message.setForeground(Constants.COLOR_MSG_START);
        message.setHorizontalAlignment(SwingConstants.CENTER);

        this.add(message);
    }

    public void shows() {
        if (game.getState().equals(Game.State.GAME_OVER)) {
            message.setForeground(Constants.COLOR_MSG_LOSE);
            message.setText(Constants.MSG_TRY_AGAIN);
        } else if (game.getState().equals(Game.State.WIN)) {
            message.setForeground(Constants.COLOR_MSG_WIN);
            message.setText(Constants.MSG_WIN);
        } else {
            message.setForeground(Constants.COLOR_MSG_START);
            message.setText(Constants.MSG_START_GAME);
        }
        repaint();
        game.setVisible(false);
        this.setVisible(true);
    }

    private class BtnListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            game.setVisible(true);
            setVisible(false);
            game.start();
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
