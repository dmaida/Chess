
public class Knight extends Piece{

    public boolean isValidMove(int currX, int currY, int toX, int toY){

        if(currX == toX && currY == toY) return false;

        int xDir = 1;
        int yDir = 1;

        if(currX > toX)
            xDir = -1;

        if (currY > toY)
            yDir = -1;

        if( ((toX == currX + (2*xDir)) && (toY == currY + yDir)) | ((toY == currY + (2*yDir)) && (toX == currX + xDir)) ){

            if ((!chessBoard.Tiles[toY][toX].isOccupied) |
                    (isWhite && !chessBoard.Tiles[toY][toX].currentPiece.isWhite) |
                    (chessBoard.Tiles[toY][toX].isOccupied && !isWhite && chessBoard.Tiles[toY][toX].currentPiece.isWhite)) {
                isFirstMove = false;
                return true;
            }

            /*if (isWhite && !chessBoard.Tiles[toY][toX].currentPiece.isWhite){
                return true;
            }

            if (!isWhite && chessBoard.Tiles[toY][toX].currentPiece.isWhite){
                return true;
            }*/

        }

        return false;
    }


}
