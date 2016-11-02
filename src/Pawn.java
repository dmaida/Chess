import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn (Board board, String name, boolean isWhite, int y, int x) {
        super(board, name, isWhite, y, x);
    }

    public ArrayList<Board.Tile> getMoves(int currX, int currY) {
        moveList = new ArrayList<>();
        int x = currX;
        int y = currY;

        if (isWhite) {
            if (y > 0 && !chessBoard.Tiles[y-1][x].isOccupied) {
                moveList.add(chessBoard.Tiles[y-1][x]);
            }

            if (y > 1 && isFirstMove && !chessBoard.Tiles[y-2][x].isOccupied && !chessBoard.Tiles[y-1][x].isOccupied)
                moveList.add(chessBoard.Tiles[y-2][x]);

            if ((y > 0 && x > 0) && chessBoard.Tiles[y-1][x-1].isOccupied && !chessBoard.Tiles[y-1][x-1].currentPiece.isWhite)
                moveList.add(chessBoard.Tiles[y-1][x-1]);

            if ((y > 0 && x < 7) && chessBoard.Tiles[y-1][x+1].isOccupied && !chessBoard.Tiles[y-1][x+1].currentPiece.isWhite)
                moveList.add(chessBoard.Tiles[y-1][x+1]);
        }

        if (!isWhite) {
            if (y < 7 && !chessBoard.Tiles[y+1][x].isOccupied) {
                moveList.add(chessBoard.Tiles[y+1][x]);
            }
            if (y < 6 && isFirstMove && !chessBoard.Tiles[y+2][x].isOccupied && !chessBoard.Tiles[y+1][x].isOccupied)
                moveList.add(chessBoard.Tiles[y+2][x]);

            if ((y < 7 && x > 0) && chessBoard.Tiles[y+1][x-1].isOccupied && chessBoard.Tiles[y+1][x-1].currentPiece.isWhite)
                moveList.add(chessBoard.Tiles[y+1][x-1]);

            if ((y < 7 && x < 7) && chessBoard.Tiles[y+1][x+1].isOccupied && chessBoard.Tiles[y+1][x+1].currentPiece.isWhite)
                moveList.add(chessBoard.Tiles[y+1][x+1]);
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
