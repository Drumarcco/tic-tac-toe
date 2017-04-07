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
    public void movementShouldNotBeRepeated() {
        ticTacToe.doHumanMovement(1);

        Assert.assertEquals(false, ticTacToe.isMovementPossible(1));
    }

}
