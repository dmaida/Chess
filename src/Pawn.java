
public class Pawn extends Piece {
    public boolean isFirstMove = true;

    public boolean isValidMove(int currX, int currY, int toX, int toY) {
            if (isWhite) {
                if (currY - 1 == toY | (currY - 2 == toY && isFirstMove)) {
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
                } else return false;

            } else {
                if (currY + 1 == toY | (currY + 2 == toY && isFirstMove)) {
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
