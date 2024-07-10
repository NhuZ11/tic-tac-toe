package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe implements ActionListener {
    private JFrame frame = new JFrame("Tic Tac Toe");
    private JButton[][] buttons = new JButton[3][3];
    private boolean playerX = true;

    public TicTacToe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(3, 3));

        initializeButtons();

        frame.setVisible(true);
    }

    private void initializeButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                frame.add(buttons[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();

        if (buttonClicked.getText().equals("")) {
            buttonClicked.setText(playerX ? "X" : "O");
            playerX = !playerX;
            checkForWin();
        }
    }

    private void checkForWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (checkThree(buttons[i][0], buttons[i][1], buttons[i][2])) {
                announceWinner(buttons[i][0].getText());
                return;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (checkThree(buttons[0][j], buttons[1][j], buttons[2][j])) {
                announceWinner(buttons[0][j].getText());
                return;
            }
        }

        // Check diagonals
        if (checkThree(buttons[0][0], buttons[1][1], buttons[2][2])) {
            announceWinner(buttons[0][0].getText());
            return;
        }

        if (checkThree(buttons[0][2], buttons[1][1], buttons[2][0])) {
            announceWinner(buttons[0][2].getText());
            return;
        }

        // Check for draw
        boolean draw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    draw = false;
                    break;
                }
            }
        }

        if (draw) {
            announceDraw();
        }
    }

    private boolean checkThree(JButton b1, JButton b2, JButton b3) {
        return !b1.getText().equals("") && b1.getText().equals(b2.getText()) && b2.getText().equals(b3.getText());
    }

    private void announceWinner(String winner) {
        JOptionPane.showMessageDialog(frame, "Player " + winner + " wins!");
        resetBoard();
    }

    private void announceDraw() {
        JOptionPane.showMessageDialog(frame, "It's a draw!");
        resetBoard();
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        playerX = true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}

