package entanglement.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.StringReader;

import org.junit.Test;

import entanglement.engine.Board;
import entanglement.engine.BoardInterface;

public class TestGameOver
{
    @Test(timeout = 1000)
    public void testBounds()
    {
        BoardInterface b = new Board(new StringReader(
                TestUtil.TEST_BOARD_CONFIG));
        assertFalse(
                "Game is not initially over as there is still a path to go for the player",
                b.isGameOver(0));
        try
        {
            assertFalse("Out of range access to players should return false", b
                    .isGameOver(-1));
            assertFalse("Out of range access to players should return false", b
                    .isGameOver(2));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("Accessing out of range players should "
                    + "be handled smoothly without exception");
        }
    }

    @Test(timeout = 1000)
    public void testGameSequence()
    {
        BoardInterface b = new Board(new StringReader(
                TestUtil.TEST_BOARD_CONFIG));

        TestUtil.playSequence(b, "fff");
        assertFalse(
                "after fixing some tiles on the board, the path did not face a deadend yet",
                b.isGameOver(0));

        TestUtil.playSequence(b, "ffff");
        assertTrue(
                "Game should be over once the last tile is fixed that leads to a deadend",
                b.isGameOver(0));
    }

    @Test(timeout = 1000)
    public void testActionsAfterGameOver()
    {
        BoardInterface b = new Board(new StringReader(
                TestUtil.TEST_BOARD_CONFIG));

        TestUtil.playSequence(b, "fffffff");

        assertFalse(
                "Any actions on the board should not be possible when the game is over "
                        + "for all the players", TestUtil.playSequence(b, "c"));
        assertTrue("Game should still be over for the "
                + "player even after doing any actions on the board", b
                .isGameOver(0));

        assertFalse(
                "Any actions on the board should not be possible when the game is over "
                        + "for all the players", TestUtil.playSequence(b, "a"));
        assertTrue("Game should still be over for the "
                + "player even after doing any actions on the board", b
                .isGameOver(0));

        assertFalse(
                "Any actions on the board should not be possible when the game is over "
                        + "for all the players", TestUtil.playSequence(b, "f"));
        assertTrue("Game should still be over for the "
                + "player even after doing any actions on the board", b
                .isGameOver(0));
        assertFalse(
                "Any actions on the board should not be possible when the game is over "
                        + "for all the players", TestUtil.playSequence(b, "0"));
        assertTrue("Game should still be over for the "
                + "player even after doing any actions on the board", b
                .isGameOver(0));
    }
    
    @Test(timeout = 1000)
    public void testLoopBackToStartGameOver()
    {
        BoardInterface board = new Board(new StringReader("4\n" + "2\n" + "1\n"
                + "1 0 3 2 5 4 7 6\n" + "3\n" + "3\n" + "4\n" + "1"));
		assertFalse("Initially game is not over.", board
                .isGameOver(0));
        TestUtil.playSequence(board, "f");
        assertTrue("Game should be over when the path of a player "
                + "goes back to the start tile", board.isGameOver(0));
    }
}
