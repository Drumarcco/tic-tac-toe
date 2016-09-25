import java.util.Random;

/**
 * Created by marco on 21/09/16.
 */
public class TicTacToe {
    private Player human;
    private Player cpu;
    private int currentTurn = 0;
    private boolean humanStarted;
    private final String[] WINNING_CASES = new String[] {
        "123",
        "456",
        "789",
        "147",
        "258",
        "369",
        "139",
        "357"
    };

    public TicTacToe(boolean humanStarts) {
        human = new Player(true);
        cpu = new Player(false);
        humanStarted = humanStarts;
    }

    public Player getHuman() {
        return this.human;
    }

    public Player getCpu() {
        return this.cpu;
    }

    public boolean isHumanTurn() {
        if (humanStarted) {
            return currentTurn % 2 == 0;
        } else {
            return currentTurn % 2 != 0;
        }
    }

    public boolean isMovementPossible(int position) {
        if (position < 1 || position > 9) return false;
        return !(human.hasPosition(position) || cpu.hasPosition(position));
    }

    public void doHumanMovement(int position) {
        if (isMovementPossible(position)) {
            human.setPosition(position);
            currentTurn++;
        }
    }

    public void doCpuMovement() {
        int optimalPosition = 0;
        while (!isMovementPossible(optimalPosition)) {
            optimalPosition = getOptimalPosition();
        }

        cpu.setPosition(optimalPosition);
        currentTurn++;
    }

    private int getOptimalPosition() {
        Random random = new Random();
        return random.nextInt(10) + 1;
    }

    public boolean playerWon(Player player) {
        if (player.countMovements() < 3) return false;

        for (String winningCase : WINNING_CASES) {
            boolean won = true;
            String[] winningPositions = winningCase.split("");

            for (String winningPosition : winningPositions) {
                 won &= player.hasPosition(Integer.parseInt(winningPosition));
            }

            if (won) return true;
        }

        return false;
    }
}
