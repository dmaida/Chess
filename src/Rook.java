
public class Rook extends Piece {

    enum Name{
        ROOK
    }

    public boolean isValidMove(int currX, int currY, int toX, int toY) {

        if(currX == toX && currY == toY) return false;

        int dir = 1;

        if(currX == toX){
            if(currY > toY){dir = -1;}
            int tempY = currY + dir;
            while(!chessBoard.Tiles[tempY][currX].isOccupied){
                if (tempY == toY){
                    break;
                }
                tempY = tempY + dir;
            }
            if(tempY == toY && (!chessBoard.Tiles[toY][toX].isOccupied | (chessBoard.Tiles[toY][toX].isOccupied && !chessBoard.Tiles[toY][toX].currentPiece.isWhite))){
                isFirstMove = false;
                return true;
            }
        }
        if(currY == toY){

            if(currX > toX)
                dir = -1;

            int tempX = currX + dir;

            while(!chessBoard.Tiles[currY][tempX].isOccupied){
                if (tempX == toX)
                    break;
                tempX = tempX + dir;
            }

            if(isWhite) {
                if (tempX == toX && (!chessBoard.Tiles[toY][toX].isOccupied | (chessBoard.Tiles[toY][toX].isOccupied && !chessBoard.Tiles[toY][toX].currentPiece.isWhite))) {
                    isFirstMove = false;
                    return true;
                }
            }
            else if (tempX == toX && (!chessBoard.Tiles[toY][toX].isOccupied | (chessBoard.Tiles[toY][toX].isOccupied && chessBoard.Tiles[toY][toX].currentPiece.isWhite))) {
                isFirstMove = false;
                return true;
            }
        }
        return false;
    }

}
