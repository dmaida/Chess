import java.util.ArrayList;

public class Knight extends Piece{

    public Knight (Board board, String name, boolean isWhite, int y, int x) {
        super(board, name, isWhite, y, x);
    }

    public ArrayList<Board.Tile> getMoves(int currX, int currY) {
        moveList = new ArrayList<>();
        int x = currX;
        int y = currY;

        if ((y>1 && x>0) && (!chessBoard.Tiles[y-2][x-1].isOccupied || (chessBoard.Tiles[y-2][x-1].isOccupied && (isWhite != chessBoard.Tiles[y-2][x-1].currentPiece.isWhite))) && IsKingProtected(x-1, y-2)){
            moveList.add(chessBoard.Tiles[y-2][x-1]);
        }
        if ((y>1 && x<7) && (!chessBoard.Tiles[y-2][x+1].isOccupied || (chessBoard.Tiles[y-2][x+1].isOccupied && (isWhite != chessBoard.Tiles[y-2][x+1].currentPiece.isWhite))) && IsKingProtected(x+1, y-2)){
            moveList.add(chessBoard.Tiles[y-2][x+1]);
        }
        if ((y>0 && x>1) && (!chessBoard.Tiles[y-1][x-2].isOccupied || (chessBoard.Tiles[y-1][x-2].isOccupied && (isWhite != chessBoard.Tiles[y-1][x-2].currentPiece.isWhite))) && IsKingProtected(x-2, y-1)){
            moveList.add(chessBoard.Tiles[y-1][x-2]);
        }
        if ((y>0 && x<6) && (!chessBoard.Tiles[y-1][x+2].isOccupied || (chessBoard.Tiles[y-1][x+2].isOccupied && (isWhite != chessBoard.Tiles[y-1][x+2].currentPiece.isWhite))) && IsKingProtected(x+2, y-1)){
            moveList.add(chessBoard.Tiles[y-1][x+2]);
        }
        if ((y<7 && x<6) && (!chessBoard.Tiles[y+1][x+2].isOccupied || (chessBoard.Tiles[y+1][x+2].isOccupied && (isWhite != chessBoard.Tiles[y+1][x+2].currentPiece.isWhite))) && IsKingProtected(x+2, y+1)){
            moveList.add(chessBoard.Tiles[y+1][x+2]);
        }
        if ((y<6 && x<7) && (!chessBoard.Tiles[y+2][x+1].isOccupied || (chessBoard.Tiles[y+2][x+1].isOccupied && (isWhite != chessBoard.Tiles[y+2][x+1].currentPiece.isWhite))) && IsKingProtected(x+1, y+2)){
            moveList.add(chessBoard.Tiles[y+2][x+1]);
        }
        if ((y<6 && x>0) && (!chessBoard.Tiles[y+2][x-1].isOccupied || (chessBoard.Tiles[y+2][x-1].isOccupied && (isWhite != chessBoard.Tiles[y+2][x-1].currentPiece.isWhite))) && IsKingProtected(x-1, y+2)){
            moveList.add(chessBoard.Tiles[y+2][x-1]);
        }
        if ((y<7 && x>1) && (!chessBoard.Tiles[y+1][x-2].isOccupied || (chessBoard.Tiles[y+1][x-2].isOccupied && (isWhite != chessBoard.Tiles[y+1][x-2].currentPiece.isWhite))) && IsKingProtected(x-2, y+1)){
            moveList.add(chessBoard.Tiles[y+1][x-2]);
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
