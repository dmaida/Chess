import java.util.ArrayList;

public class Queen extends Piece{

    public Queen (Board board, String name, boolean isWhite, int y, int x) {
        super(board, name, isWhite, y, x);
    }

    public ArrayList<Board.Tile> getMoves(int currX, int currY) {
        moveList = new ArrayList<>();
        int x = currX;
        int y = currY;

        diagonalPath(currX, currY, moveList);
        straightPath(currX, currY, moveList);

        return moveList;
    }

    private ArrayList<Board.Tile> diagonalPath(int currX, int currY, ArrayList<Board.Tile> moveList) {
        int j = currX;
        int i = currY;

        while (i < 7 && j > 0){
            i++;
            j--;
            if (chessBoard.Tiles[i][j].isOccupied && (isWhite != chessBoard.Tiles[i][j].currentPiece.isWhite)) {
                moveList.add(chessBoard.Tiles[i][j]);
            }
            if (chessBoard.Tiles[i][j].isOccupied)
                break;
            if (!chessBoard.Tiles[i][j].isOccupied) {
                moveList.add(chessBoard.Tiles[i][j]);
            }
        }
        j = currX;
        i = currY;
        while (i > 0 && j < 7){
            i--;
            j++;
            if (chessBoard.Tiles[i][j].isOccupied && (isWhite != chessBoard.Tiles[i][j].currentPiece.isWhite)) {
                moveList.add(chessBoard.Tiles[i][j]);
            }
            if (chessBoard.Tiles[i][j].isOccupied)
                break;
            if (!chessBoard.Tiles[i][j].isOccupied) {
                moveList.add(chessBoard.Tiles[i][j]);
            }
        }

        j = currX;
        i = currY;

        while (i < 7 && j < 7){
            i++;
            j++;
            if (chessBoard.Tiles[i][j].isOccupied && (isWhite != chessBoard.Tiles[i][j].currentPiece.isWhite)) {
                moveList.add(chessBoard.Tiles[i][j]);
            }
            if (chessBoard.Tiles[i][j].isOccupied)
                break;
            if (!chessBoard.Tiles[i][j].isOccupied) {
                moveList.add(chessBoard.Tiles[i][j]);
            }
        }

        j = currX;
        i = currY;

        while (i > 0 && j > 0){
            i--;
            j--;
            if (chessBoard.Tiles[i][j].isOccupied && (isWhite != chessBoard.Tiles[i][j].currentPiece.isWhite)) {
                moveList.add(chessBoard.Tiles[i][j]);
            }
            if (chessBoard.Tiles[i][j].isOccupied)
                break;
            if (!chessBoard.Tiles[i][j].isOccupied) {
                moveList.add(chessBoard.Tiles[i][j]);
            }
        }
        return moveList;
    }

    private ArrayList<Board.Tile> straightPath(int currX, int currY, ArrayList<Board.Tile> moveList) {
        int x = currX;
        int y = currY;

        for (int i = y; i < 8; i++) {
            if (chessBoard.Tiles[i][x].isOccupied && (isWhite != chessBoard.Tiles[i][x].currentPiece.isWhite)) {
                moveList.add(chessBoard.Tiles[i][x]);
            }
            if (chessBoard.Tiles[i][x].isOccupied && i!=y)
                break;
            if (!chessBoard.Tiles[i][x].isOccupied) {
                moveList.add(chessBoard.Tiles[i][x]);
            }
        }

        for (int i=y; i>=0; i--) {
            if (chessBoard.Tiles[i][x].isOccupied && (isWhite != chessBoard.Tiles[i][x].currentPiece.isWhite)) {
                moveList.add(chessBoard.Tiles[i][x]);
            }
            if (chessBoard.Tiles[i][x].isOccupied && i!=y)
                break;
            if (!chessBoard.Tiles[i][x].isOccupied) {
                moveList.add(chessBoard.Tiles[i][x]);
            }
        }

        for (int i = x; i < 8; i++) {
            if (chessBoard.Tiles[y][i].isOccupied && (isWhite != chessBoard.Tiles[y][i].currentPiece.isWhite)) {
                moveList.add(chessBoard.Tiles[y][i]);
            }
            if (chessBoard.Tiles[y][i].isOccupied && i!=x)
                break;
            if (!chessBoard.Tiles[y][i].isOccupied) {
                moveList.add(chessBoard.Tiles[y][i]);
            }
        }

        for (int i=x; i>=0; i--) {
            if (chessBoard.Tiles[y][i].isOccupied && (isWhite != chessBoard.Tiles[y][i].currentPiece.isWhite)) {
                moveList.add(chessBoard.Tiles[y][i]);
            }
            if (chessBoard.Tiles[y][i].isOccupied && i!=x)
                break;
            if (!chessBoard.Tiles[y][i].isOccupied) {
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
