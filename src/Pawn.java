
public class Pawn extends Piece {

    public boolean isFirstMove = true;

    enum Name{
        PAWN
    }

    public boolean isValidMove(int currX, int currY, int toX, int toY) {

        if(currX == toX && currY == toY) return false;

        if (isWhite) {
            if ((currY - 1 == toY | (currY - 2 == toY && isFirstMove)) && currX == toX) {
                if (!chessBoard.Tiles[toX][toY].isOccupied) {
                    isFirstMove = false;
                    return true;
                }
            }

            if (currY - 1 == toY) {
                if (chessBoard.Tiles[toX][toY].isOccupied && !chessBoard.Tiles[toX][toY].currentPiece.isWhite) {
                    if (currX + 1 == toX | currX - 1 == toX) {
                        isFirstMove = false;
                        return true;
                    }

                }
            } return false;

        } else {
            if ((currY + 1 == toY | (currY + 2 == toY && isFirstMove)) && currX == toX) {
                if (!chessBoard.Tiles[toX][toY].isOccupied) {
                    isFirstMove = false;
                    return true;
                }
            }

            if (currY + 1 == toY) {
                if (chessBoard.Tiles[toX][toY].isOccupied && chessBoard.Tiles[toX][toY].currentPiece.isWhite) {
                    if (currX + 1 == toX | currX - 1 == toX) {
                        isFirstMove = false;
                        return true;
                    }
                }
            } else return false;
        }
        return false;
    }
}
