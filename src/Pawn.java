
public class Pawn extends Piece {

    public Pawn (Board board, String name, boolean isWhite) {
        super(board, name, isWhite);
    }

    public boolean isValidMove(int currX, int currY, int toX, int toY) {

        if(currX == toX && currY == toY) return false;

        if (isWhite) {
            if ((currY - 1 == toY | (currY - 2 == toY && isFirstMove)) && currX == toX) {
                if (!chessBoard.Tiles[toY][toX].isOccupied) {
                    isFirstMove = false;
                    return true;
                }
            }

            if (currY - 1 == toY) {
                if (chessBoard.Tiles[toY][toX].isOccupied && !chessBoard.Tiles[toY][toX].currentPiece.isWhite) {
                    if (currX + 1 == toX | currX - 1 == toX) {
                        isFirstMove = false;
                        return true;
                    }

                }
            }
        }

        else {
            if ((currY + 1 == toY | (currY + 2 == toY && isFirstMove)) && currX == toX) {
                if (!chessBoard.Tiles[toY][toX].isOccupied) {
                    isFirstMove = false;
                    return true;
                }
            }

            if (currY + 1 == toY) {
                if (chessBoard.Tiles[toY][toX].isOccupied && chessBoard.Tiles[toY][toX].currentPiece.isWhite) {
                    if (currX + 1 == toX | currX - 1 == toX) {
                        isFirstMove = false;
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
