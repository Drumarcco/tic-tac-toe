import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by marco on 24/09/16.
 */
public class PlayerTest {
    Player humanPlayer;

    @Before
    public void setUp() {
        humanPlayer = new Player(true);
    }

    @Test
    public void playerShouldMove() {
        humanPlayer.setPosition(1);
        Assert.assertEquals(true, humanPlayer.hasPosition(1));
    }

    @Test
    public void playerShouldBeHuman() {
        Assert.assertEquals(true, humanPlayer.isHuman());
    }

    @Test
    public void playerShouldBeCPU() {
        Player cpuPlayer = new Player(false);
        Assert.assertEquals(false, cpuPlayer.isHuman());
    }

    @Test
    public void movementCountShouldBeCorrect() {
        humanPlayer.setPosition(1);
        humanPlayer.setPosition(2);
        humanPlayer.setPosition(3);
        Assert.assertEquals(3, humanPlayer.countMovements());
    }

    @Test
    public void shouldNotSetSamePosition() {
        humanPlayer.setPosition(1);
        humanPlayer.setPosition(1);
        Assert.assertEquals(1, humanPlayer.countMovements());
    }
}
