package entanglement.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;

import org.junit.Test;

import entanglement.engine.Board;
import entanglement.engine.BoardInterface;

public class TestFixTile
{
    @Test(timeout = 1000)
    public void testFixActiveTile()
    {
        BoardInterface b = new Board(new StringReader(
                TestUtil.TEST_BOARD_CONFIG));
        assertTrue(
                "fixing the active tile should be accepted for the sequence of moves "
                        + "as the game is not yet over.", TestUtil
                        .playSequence(b, "fffffff"));
        assertFalse(
                "trying to fix active tiles after the game is over should not be possible",
                TestUtil.playSequence(b, "f"));
        assertFalse(
                "trying to fix active tiles after the game is over should not be possible",
                TestUtil.playSequence(b, "f"));
    }

    private String getRowName(int i)
    {
        if (i % 3 == 0)
        {
            return "top";
        }
        if (i % 3 == 1)
        {
            return "middle";
        }
        return "bottom";
    }

    @Test(timeout = 1000)
    public void testStartingRows()
    {
        final String BOARD_TEMPLATE = "4\n1\n1\n%s\n3\n3\n%d\n1";
        for (int i = 0; i < 9; i++)
        {
            BoardInterface board = new Board(new StringReader(String.format(
                    BOARD_TEMPLATE, "2 3 0 1", i)));
            for (int j = 0; j < (i / 3); j++)
            {
                TestUtil.playSequence(board, "f");
            }
            assertTrue("starting at the " + getRowName(i)
                    + " row of the board."
                    + "The path of the player should end "
                    + "(lead to game over) after fixing " + (i / 3) + " tile"
                    + (i) + ".", board.isGameOver(0));
        }
    }
}
