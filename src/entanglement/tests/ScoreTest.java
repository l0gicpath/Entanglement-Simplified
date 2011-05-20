package entanglement.tests;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;

import org.junit.Test;

import entanglement.engine.Board;
import entanglement.engine.BoardInterface;

public class ScoreTest
{
    @Test(timeout = 1000)
    public void testPathWithoutLoopBackToOlderTiles()
    {
        BoardInterface b = new Board(new StringReader(
                TestUtil.TEST_BOARD_CONFIG));
        assertEquals("Score should be initially 0", 0, b.getScore(0));

        TestUtil.playSequence(b, "fffffff");
        assertEquals(
                "each fix of a tile should increment score by 1, totalling 7.",
                7, b.getScore(0));
    }

    @Test(timeout = 1000)
    public void testPathWithLoopBackToOlderTiles()
    {
        BoardInterface test2PerSide = new Board(new StringReader(
                TestUtil.TEST_SCORE_2PERSIDE));
        assertEquals("Score should be initially 0", 0, test2PerSide.getScore(0));

        // path goes through 1 tile in 1 steps
        TestUtil.playSequence(test2PerSide, "f");
        assertEquals("fixing tile increments score by 1, totalling 1.", 1,
                test2PerSide.getScore(0));

        // path goes through 2 tiles in 1 steps
        TestUtil.playSequence(test2PerSide, "f");
        assertEquals("fixing tile increments score by 2, totalling 3.", 3,
                test2PerSide.getScore(0));
    }
}
