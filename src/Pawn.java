import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn (Board board, String name, boolean isWhite, int y, int x) {
        super(board, name, isWhite, y, x);
    }

    public ArrayList<Board.Tile> getMoves(int currX, int currY) {
        moveList = new ArrayList<>();
        int x = currX;
        int y = currY;

        int dir = 1;
        if (!isWhite){
            dir = -1;
        }

        if (y > 0 && y < 7){
            if (!chessBoard.Tiles[y-(1 * dir)][x].isOccupied && IsKingProtected(x, y-(1 * dir))){
                moveList.add(chessBoard.Tiles[y-(1 * dir)][x]);
            }
            if (isFirstMove && !chessBoard.Tiles[y-(2 * dir)][x].isOccupied && !chessBoard.Tiles[y-(1 * dir)][x].isOccupied && IsKingProtected(x, y-(2 * dir))){
                moveList.add(chessBoard.Tiles[y-(2 * dir)][x]);
            }
            if (x > 0 && chessBoard.Tiles[y-(1 * dir)][x-1].isOccupied && isWhite != chessBoard.Tiles[y-(1 * dir)][x-1].currentPiece.isWhite && IsKingProtected(x-1, y-(1 * dir))){
                moveList.add(chessBoard.Tiles[y-(1 * dir)][x-1]);
            }
            if (x < 7 && chessBoard.Tiles[y-(1 * dir)][x+1].isOccupied && isWhite != chessBoard.Tiles[y-(1 * dir)][x+1].currentPiece.isWhite && IsKingProtected(x+1, y-(1 * dir))){
                moveList.add(chessBoard.Tiles[y-(1 * dir)][x+1]);
            }


        }

        return moveList;
    }

    public boolean isValidMove(int currX, int currY, int toX, int toY) {
        if (currX == toX && currY == toY) return false;

        for (int i = 0; i < moveList.size(); i++) {
            if (moveList.get(i).y == toY && moveList.get(i).x == toX) {
                isFirstMove = false;
                return true;
            }
        }
        return false;
    }
}
