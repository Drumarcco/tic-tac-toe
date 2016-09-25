import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by marco on 25/09/16.
 */
public class TicTacToeView {

    private TicTacToe ticTacToe;
    private final String HUMAN_SYMBOL = "O";
    private final String CPU_SYMBOL = "X";

    private JPanel rootPanel;
    private JButton btn_1;
    private JButton btn_2;
    private JButton btn_3;
    private JButton btn_4;
    private JButton btn_5;
    private JButton btn_6;
    private JButton btn_7;
    private JButton btn_8;
    private JButton btn_9;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tic Tac Toe - Dumb implementation");
        frame.setContentPane(new TicTacToeView().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public TicTacToeView() {
        ticTacToe = new TicTacToe(true);
        ActionListener buttonListener = getButtonActionListener();

        btn_1.addActionListener(buttonListener);
        btn_2.addActionListener(buttonListener);
        btn_3.addActionListener(buttonListener);
        btn_4.addActionListener(buttonListener);
        btn_5.addActionListener(buttonListener);
        btn_6.addActionListener(buttonListener);
        btn_7.addActionListener(buttonListener);
        btn_8.addActionListener(buttonListener);
        btn_9.addActionListener(buttonListener);
    }

    private ActionListener getButtonActionListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JButton button = (JButton) actionEvent.getSource();
                int position = Integer.parseInt(button.getToolTipText());
                doMovement(position);
            }
        };
    }

    private void doMovement(int position) {
        if (ticTacToe.isMovementPossible(position)) {
            ticTacToe.doHumanMovement(position);
            paintMovement(position);

            if (ticTacToe.humanWon()) {
                endGame("Human won!");
                return;
            }

            int cpuMovement = ticTacToe.doCpuMovement();
            paintMovement(cpuMovement);

            if (ticTacToe.cpuWon()) {
                endGame("CPU won!");
                return;
            }

            if (ticTacToe.isDraw()) {
                endGame("It's a draw.");
            }
        }
    }

    private void endGame(String message) {
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), message);
        resetGame();
    }

    private void resetGame() {
        btn_1.setText("");
        btn_2.setText("");
        btn_3.setText("");
        btn_4.setText("");
        btn_5.setText("");
        btn_6.setText("");
        btn_7.setText("");
        btn_8.setText("");
        btn_9.setText("");
        this.ticTacToe = new TicTacToe(true);
    }

    private void paintMovement(int position) {
        String symbol = ticTacToe.isHumanTurn() ? HUMAN_SYMBOL : CPU_SYMBOL;
        switch (position) {
            case 1: btn_1.setText(symbol);
                    break;
            case 2: btn_2.setText(symbol);
                    break;
            case 3: btn_3.setText(symbol);
                    break;
            case 4: btn_4.setText(symbol);
                    break;
            case 5: btn_5.setText(symbol);
                    break;
            case 6: btn_6.setText(symbol);
                    break;
            case 7: btn_7.setText(symbol);
                    break;
            case 8: btn_8.setText(symbol);
                    break;
            case 9: btn_9.setText(symbol);
                    break;
        }
    }
}
