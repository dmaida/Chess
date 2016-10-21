
public abstract class Piece {

    public boolean isFirstMove = true;

    public Board chessBoard;
    public boolean isWhite;

    public abstract boolean isValidMove(int currX, int currY, int toX, int toY);


}
