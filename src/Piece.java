
public abstract class Piece {

    public boolean isFirstMove = true;

    public Board chessBoard;
    public boolean isWhite;
    public String name;

    public Piece (Board chessBoard, String name, boolean isWhite) {
        this.chessBoard = chessBoard;
        this.name = name;
        this.isWhite = isWhite;
    }

    public abstract boolean isValidMove(int currX, int currY, int toX, int toY);


}
