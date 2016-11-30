import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;

public class BasicTest {

    /** ===Test 1 ===
     * Test initialization when Controller is created and
     * and game board is initialized.
     *
     * Create a Controller instance and initialize game board.
     */

    @Test
    public void test_Initialization() {
        Controller chessGUI = new Controller();
        chessGUI.initializeGame();
        Board chessBoard = chessGUI.B;

        assertNotNull(chessGUI);
        assertNotNull(chessBoard);
        System.out.println("Testing initialization.....pass");
    }

    /** ===Test 2 ===
     * Test the initialization of every chess piece.
     *
     * Create a Controller instance and initialize game board.
     * Initialize every piece and assert the pieces are not null;
     */

    @Test
    public void test_PieceInitialization() {
        Controller chessGUI = new Controller();
        chessGUI.initializeGame();
        Board chessBoard = chessGUI.B;

        assertNotNull(chessBoard.Tiles);

        assertNotNull(chessBoard.Tiles[0][0].getPiece());
        assertNotNull(chessBoard.Tiles[0][1].getPiece());
        assertNotNull(chessBoard.Tiles[0][2].getPiece());
        assertNotNull(chessBoard.Tiles[0][3].getPiece());
        assertNotNull(chessBoard.Tiles[0][4].getPiece());
        assertNotNull(chessBoard.Tiles[0][5].getPiece());
        assertNotNull(chessBoard.Tiles[0][6].getPiece());
        assertNotNull(chessBoard.Tiles[0][7].getPiece());

        assertNotNull(chessBoard.Tiles[1][0].getPiece());
        assertNotNull(chessBoard.Tiles[1][1].getPiece());
        assertNotNull(chessBoard.Tiles[1][2].getPiece());
        assertNotNull(chessBoard.Tiles[1][3].getPiece());
        assertNotNull(chessBoard.Tiles[1][4].getPiece());
        assertNotNull(chessBoard.Tiles[1][5].getPiece());
        assertNotNull(chessBoard.Tiles[1][6].getPiece());
        assertNotNull(chessBoard.Tiles[1][7].getPiece());

        assertNotNull(chessBoard.Tiles[6][0].getPiece());
        assertNotNull(chessBoard.Tiles[6][1].getPiece());
        assertNotNull(chessBoard.Tiles[6][2].getPiece());
        assertNotNull(chessBoard.Tiles[6][3].getPiece());
        assertNotNull(chessBoard.Tiles[6][4].getPiece());
        assertNotNull(chessBoard.Tiles[6][5].getPiece());
        assertNotNull(chessBoard.Tiles[6][6].getPiece());
        assertNotNull(chessBoard.Tiles[6][7].getPiece());

        assertNotNull(chessBoard.Tiles[7][0].getPiece());
        assertNotNull(chessBoard.Tiles[7][1].getPiece());
        assertNotNull(chessBoard.Tiles[7][2].getPiece());
        assertNotNull(chessBoard.Tiles[7][3].getPiece());
        assertNotNull(chessBoard.Tiles[7][4].getPiece());
        assertNotNull(chessBoard.Tiles[7][5].getPiece());
        assertNotNull(chessBoard.Tiles[7][6].getPiece());
        assertNotNull(chessBoard.Tiles[7][7].getPiece());
        System.out.println("Testing Piece initialization.....pass");
    }

    /** ===Test 3 ===
     * The purpose of this test is to test the Pawn's allowable moves.
     * Does the Pawn piece correctly move within the game board, and is it
     * correctly placed in the new game board position.
     */

    @Test
    public void  test_Pawn() {
        Controller chessGUI = new Controller();

        chessGUI.initializeGame();
        Board chessBoard = chessGUI.B;

        Main main = new Main();

        assertNotNull(chessBoard.Tiles[6][3].getPiece());
        assertEquals(chessBoard.Tiles[6][3].getPiece().name, "w_Pawn");

        main.cY = 6;
        main.cX = 3;

        main.nY = 5;
        main.nX = 3;

        main.makeMove(chessBoard);
        assertNotNull(chessBoard.Tiles[5][3].getPiece());
        System.out.println("Testing Pawn allowable moves.....pass");
    }

    /** ===Test 4 ===
     * The purpose of this test is to test the Bishop's allowable moves.
     * Does the Bishop piece correctly move within the game board, and is it
     * correctly placed in the new game board position.
     */

    @Test
    public void test_Bishop() {

        Controller chessGUI = new Controller();

        chessGUI.initializeGame();
        Board chessBoard = chessGUI.B;

        Main main = new Main();

        //Move the pawn out of the way
        main.cY = 6;
        main.cX = 3;

        main.nY = 5;
        main.nX = 3;
        main.makeMove(chessBoard);
        assertNotNull(chessBoard.Tiles[5][3].getPiece());

        //Now move the Bishop

        main.cY = 7;
        main.cX = 2;

        main.nY = 2;
        main.nX = 7;

        assertEquals(chessBoard.Tiles[7][2].getPiece().name, "w_Bishop");

        main.makeMove(chessBoard);
        assertNotNull(chessBoard.Tiles[2][7].getPiece());
        assertEquals(chessBoard.Tiles[2][7].getPiece().name, "w_Bishop");
        System.out.println("Testing bishop allowable moves.....pass");
    }

    /** ===Test 5 ===
     * The purpose of this test is to test the King's allowable moves.
     * Does the King piece correctly move within the game board, and is it
     * correctly placed in the new game board position.
     */

    @Test
    public void test_King() {

        Controller chessGUI = new Controller();

        chessGUI.initializeGame();
        Board chessBoard = chessGUI.B;

        Main main = new Main();

        assertNotNull(chessBoard.Tiles[6][4].getPiece());
        assertEquals(chessBoard.Tiles[6][4].getPiece().name, "w_Pawn");

        // Move the pawn out of the way
        main.cY = 6;
        main.cX = 4;

        main.nY = 5;
        main.nX = 4;

        main.makeMove(chessBoard);
        assertNotNull(chessBoard.Tiles[5][4].getPiece());

        main.cY = 7;
        main.cX = 4;

        main.nY = 6;
        main.nX = 4;

        main.makeMove(chessBoard);

        assertNotNull(chessBoard.Tiles[6][4].getPiece());
        assertEquals(chessBoard.Tiles[6][4].getPiece().name, "w_King");
        System.out.println("Testing King allowable moves.....pass");
    }

    /** ===Test 6 ===
     * The purpose of this test is to test the Knight's allowable moves.
     * Does the Knight piece correctly move within the game board, and is it
     * correctly placed in the new game board position.
     */

    @Test
    public void test_Knight() {
        Controller chessGUI = new Controller();

        chessGUI.initializeGame();
        Board chessBoard = chessGUI.B;

        Main main = new Main();

        assertNotNull(chessBoard.Tiles[7][1].getPiece());
        assertEquals(chessBoard.Tiles[7][1].getPiece().name, "w_Knight");

        // Move the knight
        main.cY = 7;
        main.cX = 1;

        main.nY = 5;
        main.nX = 0;

        main.makeMove(chessBoard);
        assertNotNull(chessBoard.Tiles[5][0].getPiece());
        assertEquals(chessBoard.Tiles[5][0].getPiece().name, "w_Knight");
        System.out.println("Testing Knight allowable moves.....pass");

    }

    /** ===Test 6 ===
     * The purpose of this test is to test the Queen's allowable moves.
     * Does the Queen piece correctly move within the game board, and is it
     * correctly placed in the new game board position.
     */

    @Test
    public void test_Queen() {
        Controller chessGUI = new Controller();

        chessGUI.initializeGame();
        Board chessBoard = chessGUI.B;

        Main main = new Main();

        //Move the pawn out of the way
        main.cY = 6;
        main.cX = 4;

        main.nY = 5;
        main.nX = 4;
        main.makeMove(chessBoard);
        assertNotNull(chessBoard.Tiles[5][4].getPiece());

        //Now move the queen diagonal
        assertNotNull(chessBoard.Tiles[7][3].getPiece());
        assertEquals(chessBoard.Tiles[7][3].getPiece().name, "w_Queen");

        main.cY = 7;
        main.cX = 3;

        main.nY = 5;
        main.nX = 5;

        main.makeMove(chessBoard);
        assertNotNull(chessBoard.Tiles[5][5].getPiece());
        assertEquals(chessBoard.Tiles[5][5].getPiece().name, "w_Queen");

        //Now move the queen forward

        main.cY = 5;
        main.cX = 5;

        main.nY = 3;
        main.nX = 5;

        main.makeMove(chessBoard);
        assertNotNull(chessBoard.Tiles[3][5].getPiece());
        assertEquals(chessBoard.Tiles[3][5].getPiece().name, "w_Queen");
        System.out.println("Testing Queen allowable moves.....pass");
    }

    @Test

    public void test_Rook () {
        Controller chessGUI = new Controller();

        chessGUI.initializeGame();
        Board chessBoard = chessGUI.B;

        Main main = new Main();

        //Move the pawn out of the way
        main.cY = 6;
        main.cX = 7;

        main.nY = 4;
        main.nX = 7;
        main.makeMove(chessBoard);
        assertNotNull(chessBoard.Tiles[4][7].getPiece());
        assertEquals(chessBoard.Tiles[4][7].getPiece().name, "w_Pawn");

        //Now move the Rook forward

        assertNotNull(chessBoard.Tiles[7][7].getPiece());
        assertEquals(chessBoard.Tiles[7][7].getPiece().name, "w_Rook");

        main.cY = 7;
        main.cX = 7;

        main.nY = 5;
        main.nX = 7;

        main.makeMove(chessBoard);
        assertNotNull(chessBoard.Tiles[5][7].getPiece());
        assertEquals(chessBoard.Tiles[5][7].getPiece().name, "w_Rook");


        main.cY = 5;
        main.cX = 7;

        main.nY = 5;
        main.nX = 0;

        main.makeMove(chessBoard);
        assertNotNull(chessBoard.Tiles[5][0].getPiece());
        assertEquals(chessBoard.Tiles[5][0].getPiece().name, "w_Rook");
        System.out.println("Testing Rook allowable moves.....pass");
    }


}
