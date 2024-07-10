package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.red;

public class TicTacToeGame implements ActionListener {
    JFrame frame = new JFrame("Tic Tac Toe");
    JPanel panel = new JPanel();
    JButton[][] buttons = new JButton[3][3];
    Boolean playerX =true;

    TicTacToeGame() {
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel label = new JLabel("Start the game", SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 24));
        label.setBackground(Color.green);
        frame.add(label, BorderLayout.NORTH);

        panel.setLayout(new GridLayout(3, 3));
        frame.add(panel, BorderLayout.CENTER);

        showButtons();

        frame.setVisible(true);
    }

    public void showButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                panel.add(buttons[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked=(JButton) e.getSource();
        if(buttonClicked.getText().equals("")){
            buttonClicked.setText(playerX ?"X":"O");
            playerX=!playerX;
            checkResult();
        }
    }

    public void checkResult(){
        for (int i=0;i<3;i++) {
            //for rows
            if (checkThree(buttons[i][0], buttons[i][1], buttons[i][2])) {
                System.out.println("Player win");
                announceWinner(buttons[i][0].getText());
            }
        }
            // Check columns
            for (int j = 0; j < 3; j++) {
                if (checkThree(buttons[0][j], buttons[1][j], buttons[2][j])) {
                    System.out.println("Player win");
                    announceWinner(buttons[0][j].getText());
                }
            }


            //for diagonal
            if (checkThree(buttons[0][0], buttons[1][1], buttons[2][2])) {
                System.out.println("Player win");
                announceWinner(buttons[0][0].getText());
            }

        if (checkThree(buttons[0][2], buttons[1][1], buttons[2][0])) {
            System.out.println("Player win");
            announceWinner(buttons[0][2].getText());
        }

        boolean draw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    draw = false;
                    break;
                }
            }
        }

        if(draw){
            System.out.println("draw");
            announceDraw();
        }

    }

    private void announceWinner(String winner) {
        JOptionPane.showMessageDialog(frame, "Player " + winner + " wins!");
//        resetBoard();
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


    public boolean checkThree(JButton b1,JButton b2,JButton b3){
        if(!b1.getText().equals("") && b1.getText().equals(b2.getText()) && b2.getText().equals(b3.getText())){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        new TicTacToeGame();
    }
}
