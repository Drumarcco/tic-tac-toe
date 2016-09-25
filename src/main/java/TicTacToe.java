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

    private boolean isHumanTurn() {
        if (humanStarted) {
            return currentTurn % 2 == 0;
        } else {
            return currentTurn % 2 != 0;
        }
    }

    public boolean isMovementPossible(int position) {
        return human.hasPosition(position) || cpu.hasPosition(position);
    }

    public boolean doMovement(int position) {
        if (isMovementPossible(position)) return false;

        if (isHumanTurn()) {
            human.setPosition(position);
        } else {
            cpu.setPosition(position);
        }

        currentTurn++;
        return true;
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
