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
        ticTacToe.doMovement(1);
        ticTacToe.doMovement(4);
        ticTacToe.doMovement(2);
        ticTacToe.doMovement(5);
        ticTacToe.doMovement(3);

        Player human = ticTacToe.getHuman();
        Player cpu = ticTacToe.getCpu();

        Assert.assertEquals(true, ticTacToe.playerWon(human));
        Assert.assertEquals(false, ticTacToe.playerWon(cpu));
    }

    @Test
    public void cpuShouldWin() {
        ticTacToe.doMovement(1);
        ticTacToe.doMovement(4);
        ticTacToe.doMovement(7);
        ticTacToe.doMovement(5);
        ticTacToe.doMovement(8);
        ticTacToe.doMovement(6);

        Player human = ticTacToe.getHuman();
        Player cpu = ticTacToe.getCpu();

        Assert.assertEquals(true, ticTacToe.playerWon(cpu));
        Assert.assertEquals(false, ticTacToe.playerWon(human));
    }

    @Test
    public void movementShouldNotBeRepeated() {
        ticTacToe.doMovement(1);
        boolean movementSucceded = ticTacToe.doMovement(1);

        Assert.assertEquals(false, movementSucceded);
    }

}
