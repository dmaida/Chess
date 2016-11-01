import java.util.ArrayList;

public abstract class Piece {

    public ArrayList<Board.Tile> moveList;
    public boolean isFirstMove = true;

    public Board chessBoard;
    public boolean isWhite;
    public String name;

    public Piece (Board chessBoard, String name, boolean isWhite, int y, int x) {
        this.chessBoard = chessBoard;
        this.name = name;
        this.isWhite = isWhite;
        this.chessBoard.Tiles[y][x].isOccupied = true;
    }
    public abstract ArrayList<Board.Tile> getMoves(int currX, int currY);

    public abstract boolean isValidMove(int currX, int currY, int toX, int toY);


}
