import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop (Board board, String name, boolean isWhite, int y, int x) {
        super(board, name, isWhite, y, x);
    }

    public ArrayList<Board.Tile> getMoves(int currX, int currY) {
        moveList = new ArrayList<>();

        globalX = currX;
        globalY = currY;

        int j = currX;
        int i = currY;

        while (i < 7 && j > 0){
            i++;
            j--;
            if (chessBoard.Tiles[i][j].isOccupied && (isWhite != chessBoard.Tiles[i][j].currentPiece.isWhite) && IsKingProtected(j, i)) {
                moveList.add(chessBoard.Tiles[i][j]);
            }
            if (chessBoard.Tiles[i][j].isOccupied)
                break;
            if (!chessBoard.Tiles[i][j].isOccupied && IsKingProtected(j, i)) {
                moveList.add(chessBoard.Tiles[i][j]);
            }
        }
        j = currX;
        i = currY;
        while (i > 0 && j < 7){
            i--;
            j++;
            if (chessBoard.Tiles[i][j].isOccupied && (isWhite != chessBoard.Tiles[i][j].currentPiece.isWhite) && IsKingProtected(j, i)) {
                moveList.add(chessBoard.Tiles[i][j]);
            }
            if (chessBoard.Tiles[i][j].isOccupied)
                break;
            if (!chessBoard.Tiles[i][j].isOccupied && IsKingProtected(j, i)) {
                moveList.add(chessBoard.Tiles[i][j]);
            }
        }

        j = currX;
        i = currY;

        while (i < 7 && j < 7){
            i++;
            j++;
            if (chessBoard.Tiles[i][j].isOccupied && (isWhite != chessBoard.Tiles[i][j].currentPiece.isWhite) && IsKingProtected(j, i)) {
                moveList.add(chessBoard.Tiles[i][j]);
            }
            if (chessBoard.Tiles[i][j].isOccupied)
                break;
            if (!chessBoard.Tiles[i][j].isOccupied && IsKingProtected(j, i)) {
                moveList.add(chessBoard.Tiles[i][j]);
            }
        }

        j = currX;
        i = currY;

        while (i > 0 && j > 0){
            i--;
            j--;
            if (chessBoard.Tiles[i][j].isOccupied && (isWhite != chessBoard.Tiles[i][j].currentPiece.isWhite) && IsKingProtected(j, i)) {
                moveList.add(chessBoard.Tiles[i][j]);
            }
            if (chessBoard.Tiles[i][j].isOccupied)
                break;
            if (!chessBoard.Tiles[i][j].isOccupied && IsKingProtected(j, i)) {
                moveList.add(chessBoard.Tiles[i][j]);
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
