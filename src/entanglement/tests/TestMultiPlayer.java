package entanglement.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.StringReader;

import org.junit.Test;

import entanglement.engine.Board;
import entanglement.engine.BoardInterface;

public class TestMultiPlayer
{
    @Test(timeout = 1000)
    public void testBounds()
    {
        BoardInterface testPlayerAccess = new Board(new StringReader("4\n"
                + "1\n" + "1\n" + "2 3 0 1\n" + "3\n" + "3\n" + "4\n" + "4"));
        try
        {
            assertFalse(testPlayerAccess.isGameOver(-1));
            assertFalse(testPlayerAccess.isGameOver(6));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("Accessing out of range players should not throw exceptions");
        }
    }

    @Test(timeout = 1000)
    public void testPlayer1GameOversBeforePlayer2()
    {
        BoardInterface testMultiplayerGameOver = new Board(new StringReader(
                "4\n" + "2\n" + "1\n" + "5 6 3 2 7 0 1 4\n" + "3\n" + "3\n"
                        + "4\n" + "2"));

        assertEquals("Score should be initially 0 for both players", 0,
                testMultiplayerGameOver.getScore(0));
        assertEquals("Score should be initially 0 for both players", 0,
                testMultiplayerGameOver.getScore(1));

        // player 1 fixed 1 tile incrementing path by 1 step
        TestUtil.playSequence(testMultiplayerGameOver, "f");

        assertTrue("game should be over for player 1 and not for player 2",
                testMultiplayerGameOver.isGameOver(0)
                        && !testMultiplayerGameOver.isGameOver(1));
        assertTrue(
                "Score Should be 1 for player 1, and 0 for player 2 as player 2 has not played yet",
                testMultiplayerGameOver.getScore(0) == 1
                        && testMultiplayerGameOver.getScore(1) == 0);

        // player 2 fixes tiles 3 times. the first 2 increment the path by 1
        // step each
        // the last tile fixed increments path by 2 steps.
        // This results in a total of a 4 step increment of the path length
        TestUtil.playSequence(testMultiplayerGameOver, "fff");
        assertTrue("game should be over for player 1 and player 2",
                testMultiplayerGameOver.isGameOver(0)
                        && testMultiplayerGameOver.isGameOver(1));
        assertTrue("Score Should be 1 for player 1, and 4 for player 2 "
                + "after the game is over for both of them",
                testMultiplayerGameOver.getScore(0) == 1
                        && testMultiplayerGameOver.getScore(1) == 4);
    }

    @Test(timeout = 1000)
    public void testForcingPathOnAnotherPlayer()
    {
        BoardInterface testForcingOtherPlayer = new Board(new StringReader(
                "4\n" + "3\n" + "1\n" + "11 3 7 1 10 8 9 2 5 6 4 0\n" + "3\n"
                        + "3\n" + "4\n" + "2"));

        // the 2 players fix their tiles respectively
        TestUtil.playSequence(testForcingOtherPlayer, "ff");
        assertTrue(
                "game should not be over for players after the sequence of moves.",
                !testForcingOtherPlayer.isGameOver(0)
                        && !testForcingOtherPlayer.isGameOver(1));

        TestUtil.playSequence(testForcingOtherPlayer, "f");
        assertTrue("game should be over for player 2 after the last move "
                + "because of player 1 forcing a path,"
                + "but should not be over for player 1.",
                !testForcingOtherPlayer.isGameOver(0)
                        && testForcingOtherPlayer.isGameOver(1));
        assertTrue("scores of players should be 3 and 2 respectively, "
                + "after player 1 forces player 2 to a dead end.",
                testForcingOtherPlayer.getScore(0) == 3
                        && testForcingOtherPlayer.getScore(1) == 2);
    }
}
