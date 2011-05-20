package entanglement.tests;

import java.io.StringReader;

import org.junit.Test;

import entanglement.engine.Board;

public class BoardConstructorTest
{
    /**
     * only tests the existence of a constructor for the class board with the
     * needed signature Board(java.io.Reader)
     */
    @Test(timeout = 1000)
    public void testHasInitializationConstructor()
    {
        new Board(new StringReader(TestUtil.TEST_BOARD_CONFIG));
    }
}
