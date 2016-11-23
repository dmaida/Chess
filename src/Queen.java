import java.util.ArrayList;

public class Queen extends Piece{

    public Queen (Board board, String name, boolean isWhite, int y, int x) {
        super(board, name, isWhite, y, x);
    }

    public ArrayList<Board.Tile> getMoves(int currX, int currY) {
        moveList = new ArrayList<>();

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

        int x = currX;
        int y = currY;

        for (i = y; i < 8; i++) {
            if (chessBoard.Tiles[i][x].isOccupied && (isWhite != chessBoard.Tiles[i][x].currentPiece.isWhite)  && IsKingProtected(x, i)) {
                moveList.add(chessBoard.Tiles[i][x] );
            }
            if (chessBoard.Tiles[i][x].isOccupied && i!=y)
                break;
            if (!chessBoard.Tiles[i][x].isOccupied  && IsKingProtected(x, i)) {
                moveList.add(chessBoard.Tiles[i][x]);
            }
        }

        for (i=y; i>=0; i--) {
            if (chessBoard.Tiles[i][x].isOccupied && (isWhite != chessBoard.Tiles[i][x].currentPiece.isWhite)  && IsKingProtected(x, i)) {
                moveList.add(chessBoard.Tiles[i][x]);
            }
            if (chessBoard.Tiles[i][x].isOccupied && i!=y)
                break;
            if (!chessBoard.Tiles[i][x].isOccupied   && IsKingProtected(x, i)) {
                moveList.add(chessBoard.Tiles[i][x]);
            }
        }

        for (i = x; i < 8; i++) {
            if (chessBoard.Tiles[y][i].isOccupied && (isWhite != chessBoard.Tiles[y][i].currentPiece.isWhite)  && IsKingProtected(i, y)) {
                moveList.add(chessBoard.Tiles[y][i]);
            }
            if (chessBoard.Tiles[y][i].isOccupied && i!=x)
                break;
            if (!chessBoard.Tiles[y][i].isOccupied  && IsKingProtected(i, y)) {
                moveList.add(chessBoard.Tiles[y][i] );
            }
        }

        for (i=x; i>=0; i--) {
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
            if (moveList.get(i).y == toY && moveList.get(i).x == toX) {
                return true;
            }
        }
        return false;
    }
}