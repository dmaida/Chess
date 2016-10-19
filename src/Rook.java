
public class Rook extends Piece {

    public boolean isFirstMove = true;

    enum Name{
        ROOK
    }

    public boolean isValidMove(int currX, int currY, int toX, int toY) {
        if (isWhite) {
            if(currX == toX){
                int dir =1;
                if(currY > toY){dir = -1;}
                int tempY = currY + dir;
                while(!chessBoard.Tiles[currX][tempY].isOccupied){
                    if (tempY == toY){
                        break;
                    }
                    tempY = tempY + dir;
                }
                if(tempY == toY && (!chessBoard.Tiles[toX][toY].isOccupied | (chessBoard.Tiles[toX][toY].isOccupied && !chessBoard.Tiles[toX][toY].currentPiece.isWhite))){
                    return true;
                }
            }
            if(currY == toY){
                int dir =1;
                if(currX > toX){dir = -1;}
                int tempX = currX + dir;
                while(!chessBoard.Tiles[tempX][currY].isOccupied){
                    if (tempX == toX){
                        break;
                    }
                    tempX = tempX + dir;
                }
                if(tempX == toX && (!chessBoard.Tiles[toX][toY].isOccupied | (chessBoard.Tiles[toX][toY].isOccupied && !chessBoard.Tiles[toX][toY].currentPiece.isWhite))){
                    return true;
                }
            }
        }
        else {
            if(currX == toX){
                int dir =1;
                if(currY > toY){dir = -1;}
                int tempY = currY + dir;
                while(!chessBoard.Tiles[currX][tempY].isOccupied){
                    if (tempY == toY){
                        break;
                    }
                    tempY = tempY + dir;
                }
                if(tempY == toY && (!chessBoard.Tiles[toX][toY].isOccupied | (chessBoard.Tiles[toX][toY].isOccupied && chessBoard.Tiles[toX][toY].currentPiece.isWhite))){
                    return true;
                }
            }
            if(currY == toY){
                int dir =1;
                if(currX > toX){dir = -1;}
                int tempX = currX + dir;
                while(!chessBoard.Tiles[tempX][currY].isOccupied){
                    if (tempX == toX){
                        break;
                    }
                    tempX = tempX + dir;
                }
                if(tempX == toX && (!chessBoard.Tiles[toX][toY].isOccupied | (chessBoard.Tiles[toX][toY].isOccupied && chessBoard.Tiles[toX][toY].currentPiece.isWhite))){
                    return true;
                }
            }
        }
        return false;
    }

}
