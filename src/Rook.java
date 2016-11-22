import java.util.ArrayList;

public class Rook extends Piece {

    public Rook (Board board, String name, boolean isWhite, int y, int x) {
        super(board, name, isWhite, y, x);
    }

    public ArrayList<Board.Tile> getMoves(int currX, int currY) {
        moveList = new ArrayList<>();
        int x = currX;
        int y = currY;

        globalX = currX;
        globalY = currY;


        for (int i = y; i < 8; i++) {
            if (chessBoard.Tiles[i][x].isOccupied && (isWhite != chessBoard.Tiles[i][x].currentPiece.isWhite)  && IsKingProtected(x, i)) {
                moveList.add(chessBoard.Tiles[i][x] );
            }
            if (chessBoard.Tiles[i][x].isOccupied && i!=y)
                break;
            if (!chessBoard.Tiles[i][x].isOccupied  && IsKingProtected(x, i)) {
                moveList.add(chessBoard.Tiles[i][x]);
            }
        }

        for (int i=y; i>=0; i--) {
            if (chessBoard.Tiles[i][x].isOccupied && (isWhite != chessBoard.Tiles[i][x].currentPiece.isWhite)  && IsKingProtected(x, i)) {
                moveList.add(chessBoard.Tiles[i][x]);
            }
            if (chessBoard.Tiles[i][x].isOccupied && i!=y)
                break;
            if (!chessBoard.Tiles[i][x].isOccupied   && IsKingProtected(x, i)) {
                moveList.add(chessBoard.Tiles[i][x]);
            }
        }

        for (int i = x; i < 8; i++) {
            if (chessBoard.Tiles[y][i].isOccupied && (isWhite != chessBoard.Tiles[y][i].currentPiece.isWhite)  && IsKingProtected(i, y)) {
                moveList.add(chessBoard.Tiles[y][i]);
            }
            if (chessBoard.Tiles[y][i].isOccupied && i!=x)
                break;
            if (!chessBoard.Tiles[y][i].isOccupied  && IsKingProtected(i, y)) {
                moveList.add(chessBoard.Tiles[y][i] );
            }
        }

        for (int i=x; i>=0; i--) {
            if (chessBoard.Tiles[y][i].isOccupied && (isWhite != chessBoard.Tiles[y][i].currentPiece.isWhite)  && IsKingProtected(i, y)) {
                moveList.add(chessBoard.Tiles[y][i]);
            }
            if (chessBoard.Tiles[y][i].isOccupied && i!=x)
                break;
            if (!chessBoard.Tiles[y][i].isOccupied && IsKingProtected(i, y)) {
                moveList.add(chessBoard.Tiles[y][i]);
            }
        }

        return moveList;
    }

    public boolean isValidMove(int currX, int currY, int toX, int toY) {
        if (currX == toX && currY == toY) return false;

        for (int i = 0; i < moveList.size(); i++) {
            if (moveList.get(i).y == toY && moveList.get(i).x == toX) {     // the comparison is backwards due to rows(x) being toY and collumns(y) being to X.
                isFirstMove = false;
                return true;
            }
        }
        return false;
    }
}
