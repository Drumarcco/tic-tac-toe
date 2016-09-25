import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by marco on 21/09/16.
 */
public class TicTacToeTest {
    TicTacToe ticTacToe;

    @Before
    public void setUp() {
        ticTacToe = new TicTacToe(true);
    }

    @Test
    public void humanShouldWin() {
        ticTacToe.doHumanMovement(1);
        ticTacToe.doCpuMovement();
        ticTacToe.doHumanMovement(2);
        ticTacToe.doCpuMovement();
        ticTacToe.doHumanMovement(3);

        Player human = ticTacToe.getHuman();
        Player cpu = ticTacToe.getCpu();

        Assert.assertEquals(true, ticTacToe.playerWon(human));
        Assert.assertEquals(false, ticTacToe.playerWon(cpu));
    }

    @Test
    public void movementShouldNotBeRepeated() {
        ticTacToe.doHumanMovement(1);

        Assert.assertEquals(false, ticTacToe.isMovementPossible(1));
    }

}
