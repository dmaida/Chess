import java.util.ArrayList;

public class Pawn extends Piece {
    public boolean BlackOrWhite;
    public boolean isFirstMove;

    public int currentRow;
    public int currentCol;

    public ArrayList<Board.Tile> listOfMoves;


    public boolean isValidMove(int row, int col) {

        return false;
    }
}
