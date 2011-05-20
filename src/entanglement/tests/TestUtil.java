package entanglement.tests;
import entanglement.engine.BoardInterface;

public class TestUtil
{
    public static final String TEST_BOARD_CONFIG    = "4\n"
                                                            + "2\n"
                                                            + "1\n"
                                                            + "3 6 4 0 2 7 1 5\n"
                                                            + "7\n" + "7\n"
                                                            + "24\n" + "1";

    public static final String TEST_SCORE_2PERSIDE  = "4\n"
                                                            + "2\n"
                                                            + "1\n"
                                                            + "3 4 5 0 1 2 7 6\n"
                                                            + "3\n" + "3\n"
                                                            + "4\n" + "1";


    public static final String TEST_SWITCH_TILE     = "4\n"
                                                            + "2\n"
                                                            + "2\n"
                                                            + "1 0 3 2 5 4 7 6\n"
                                                            + "5 4 7 6 1 0 3 2\n"
                                                            + "5\n" + "5\n"
                                                            + "12\n" + "1";

    /**
     * @param s
     *            a string sequence of actions to do on the board
     * @param b
     *            the board object to control
     */
    public static boolean playSequence(BoardInterface b, String s)
    {
        boolean result = true;
        for (char c : s.toCharArray())
        {
            switch (c)
            {
            // rotate clockwise
            case 'c':
                result &= b.rotateTileClockwise();
                break;
            // rotate anti-clockwise
            case 'a':
                result &= b.rotateTileAntiClockwise();
                break;
            // fix active tile
            case 'f':
                result &= b.fixTile();
                break;
            // switch to the tile type of given number
            default:
                result &= b.switchTile((int) (c - '0'));
                break;
            }
        }
        return result;
    }
}
