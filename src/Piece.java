import java.util.ArrayList;

public abstract class Piece {

    public ArrayList<Board.Tile> moveList;
    public boolean isFirstMove = true;

    public Board chessBoard;
    public boolean isWhite;
    public String name;

    public int globalX;
    public int globalY;

    public Piece (Board chessBoard, String name, boolean isWhite, int y, int x) {
        this.chessBoard = chessBoard;
        this.name = name;
        this.isWhite = isWhite;
        this.chessBoard.Tiles[y][x].isOccupied = true;
    }
    public abstract ArrayList<Board.Tile> getMoves(int currX, int currY);

    public abstract boolean isValidMove(int currX, int currY, int toX, int toY);

    public boolean IsKingProtected(int desiredX, int desiredY) {

        System.out.println("YESSSSSSSSS");

        King king = null;

        int i;
        int j = 0;
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                if (chessBoard.Tiles[i][j].isOccupied) {
                    if (chessBoard.Tiles[i][j].getPiece().name.contains("King") && isWhite == chessBoard.Tiles[i][j].currentPiece.isWhite) {
                        king = (King) chessBoard.Tiles[i][j].getPiece();
                        break;
                    }
                }
            }
            if (king != null){
                break;
            }
        }
        boolean returnVal = false;
        Piece p = null;

        if (chessBoard.Tiles[desiredY][desiredX].isOccupied){
            p = chessBoard.Tiles[desiredY][desiredX].getPiece();
            chessBoard.Tiles[desiredY][desiredX].currentPiece = null;
        }
        Piece currPiece = chessBoard.Tiles[globalY][globalX].getPiece();

        chessBoard.Tiles[globalY][globalX].currentPiece = null;
        chessBoard.Tiles[globalY][globalX].isOccupied = false;

        chessBoard.Tiles[desiredY][desiredX].currentPiece = currPiece;
        chessBoard.Tiles[desiredY][desiredX].isOccupied = true;

        if (king != null) {
            System.out.println("SHIT");
        }
        if (king.isItSafe(j, i)) {
            returnVal = true;
        }

        chessBoard.Tiles[globalY][globalX].currentPiece = currPiece;
        chessBoard.Tiles[globalY][globalX].isOccupied = true;

        chessBoard.Tiles[desiredY][desiredX].currentPiece = null;
        chessBoard.Tiles[desiredY][desiredX].isOccupied = false;

        if (p != null){
            chessBoard.Tiles[desiredY][desiredX].currentPiece = p;
            chessBoard.Tiles[desiredY][desiredX].isOccupied = true;
        }
        return returnVal;

    }
}
