package entanglement.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.StringReader;

import org.junit.Test;

import entanglement.engine.Board;
import entanglement.engine.BoardInterface;

public class TestSwitchTile
{
    @Test(timeout = 1000)
    public void testBounds()
    {
        BoardInterface b = new Board(new StringReader(
                TestUtil.TEST_BOARD_CONFIG));
        try
        {
            assertFalse("Out of range of possible tile types", b.switchTile(1));
            assertFalse("Out of range of possible tile types", b.switchTile(-1));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("Access to tiles out of possible range "
                    + "should be handled without exceptions being thrown");
        }
    }

    @Test(timeout = 1000)
    public void testSwitchAfterGameOver()
    {
        BoardInterface testSwitch = new Board(new StringReader(
                TestUtil.TEST_SWITCH_TILE));
        assertTrue("changing the tile should be possible", TestUtil
                .playSequence(testSwitch, "0"));
        TestUtil.playSequence(testSwitch, "f");
        assertTrue("switching to the required tile was not successful",
                testSwitch.isGameOver(0));
    }

    @Test(timeout = 1000)
    public void testSwitchActiveTile()
    {
        BoardInterface testSwitch = new Board(new StringReader(
                TestUtil.TEST_SWITCH_TILE));
        assertTrue("changing the tile should be possible", TestUtil
                .playSequence(testSwitch, "1"));
        TestUtil.playSequence(testSwitch, "f");
        assertFalse("switching to the required tile was not successful",
                testSwitch.isGameOver(0));
        assertTrue("changing the tile should be possible", TestUtil
                .playSequence(testSwitch, "0"));
        TestUtil.playSequence(testSwitch, "f");
        assertTrue("switching to the required tile was not successful",
                testSwitch.isGameOver(0));

    }
}
