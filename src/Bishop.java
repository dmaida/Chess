
public class Bishop extends Piece {

    enum Name{
        ROOK
    }

    public boolean isValidMove(int currX, int currY, int toX, int toY) {

        if(currX == toX && currY == toY) return false;

        int xDir = 1;
        int yDir = 1;

        if(currY > toY)
            yDir = -1;

        if(currX > toX)
            xDir = -1;

        int tempX = currX + xDir;
        int tempY = currY + yDir;

        while ((tempY > -1 && tempX > -1 && tempX < 8  && tempY < 8 ) && !chessBoard.Tiles[tempY][tempX].isOccupied ){

            if (tempY == toY && tempX == toX)
                break;

            tempY = tempY + yDir;
            tempX = tempX + xDir;

        }

        if((tempX == toX && tempY == toY)) {
            if (isWhite)
                if ((!chessBoard.Tiles[toY][toX].isOccupied | (chessBoard.Tiles[toY][toX].isOccupied && !chessBoard.Tiles[toY][toX].currentPiece.isWhite)))
                    return true;

            else if ((!chessBoard.Tiles[toY][toX].isOccupied | (chessBoard.Tiles[toY][toX].isOccupied && chessBoard.Tiles[toY][toX].currentPiece.isWhite)))
                return true;
        }
        return false;
    }
}
