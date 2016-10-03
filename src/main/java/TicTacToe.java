import java.util.Random;

/**
 * Created by marco on 21/09/16.
 */
public class TicTacToe {
    private Player human;
    private Player cpu;
    private int currentTurn = 0;
    private boolean humanStarted;
    private final int BEST_POSITION = 5;
    private final String[] WINNING_CASES = new String[] {
        "123",
        "456",
        "789",
        "147",
        "258",
        "369",
        "357",
        "159"
    };

    public TicTacToe(boolean humanStarts) {
        human = new Player(true);
        cpu = new Player(false);
        humanStarted = humanStarts;
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

    public int doCpuMovement(int lastHumanMovement) {
        int optimalPosition = 0;
        while (!isMovementPossible(optimalPosition) && currentTurn < 9) {
            optimalPosition = getOptimalPosition(lastHumanMovement);
        }

        cpu.setPosition(optimalPosition);
        currentTurn++;
        return optimalPosition;
    }

    private int getOptimalPosition(int lastHumanMovement) {
        if (isMovementPossible(BEST_POSITION)) {
            return BEST_POSITION;
        }

        int winningMovement = getWinningMovement();

        if (winningMovement > 0) {
            return winningMovement;
        }

        int blockingMovement = getBlockingMovement(lastHumanMovement);

        if (blockingMovement > 0) {
            return blockingMovement;
        }

        int corner = getCornerPosition(lastHumanMovement);

        if (corner > 0) {
            return corner;
        }


        Random random = new Random();
        return random.nextInt(10) + 1;
    }

    private int getCornerPosition(int lastHumanMovement) {
        int cornerOpposite = getCornetOpposite(lastHumanMovement);
        if (cornerOpposite > 0) return cornerOpposite;

        if (isMovementPossible(1)) return 1;
        if (isMovementPossible(3)) return 3;
        if (isMovementPossible(7)) return 7;
        if (isMovementPossible(9)) return 9;

        return 0;
    }

    private int getCornetOpposite(int lastHumanMovement) {
        int corner = 0;
        if (lastHumanMovement == 1) corner = 9;
        else if (lastHumanMovement == 3) corner = 7;
        else if (lastHumanMovement == 7) corner = 3;
        else if (lastHumanMovement == 9) corner = 1;

        if (!isMovementPossible(corner)) corner = 0;
        return corner;
    }

    private int getWinningMovement() {
        if (cpu.countMovements() < 2) return 0;

        for (String winningCase : WINNING_CASES) {
            int possibleWinningPosition = 0;
            int matchingPositionsCount = 0;

            for (String positionString : winningCase.split("")) {
                int position = Integer.valueOf(positionString);

                if (cpu.hasPosition(position)) {
                    matchingPositionsCount++;
                } else {
                    if (isMovementPossible(position)) {
                        possibleWinningPosition = position;
                    }
                }

                if (matchingPositionsCount == 2 && possibleWinningPosition != 0) {
                    return possibleWinningPosition;
                }
            }
        }

        return 0;
    }

    private int getBlockingMovement(int lastHumanMovement) {
        if (human.countMovements() < 2) return 0;

        for (String winningCase : WINNING_CASES) {
            if (!winningCase.contains(String.valueOf(lastHumanMovement))) continue;

            int blockingPosition = 0;
            int matchingPositionsCount = 0;

            for (String positionString : winningCase.split("")) {
                int position = Integer.valueOf(positionString);

                if (human.hasPosition(position)) {
                    matchingPositionsCount++;
                } else {
                    if (isMovementPossible(position)) {
                        blockingPosition = position;
                    }
                }

                if (matchingPositionsCount == 2 && blockingPosition != 0) {
                    return blockingPosition;
                }
            }
        }

        return 0;
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

    public boolean humanWon() {
        return playerWon(human);
    }

    public boolean cpuWon() {
        return playerWon(cpu);
    }

    public boolean isDraw() {
        return currentTurn > 9;
    }

}
