
public class Queen extends Piece{

    public Queen (Board board, String name, boolean isWhite) {
        super(board, name, isWhite);
    }


    public boolean isValidMove(int currX, int currY, int toX, int toY){


        if(currX == toX && currY == toY) return false;

        if(currX == toX | currY == toY){

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

        }
        else{

            System.out.println("reached else");

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
            System.out.println("broke out of while loop");
            System.out.println("temp[x][y] = ["+tempX+"]["+tempY+"]");



            if((tempX == toX && tempY == toY)) {
                if (isWhite) {
                    if ((!chessBoard.Tiles[toY][toX].isOccupied | (chessBoard.Tiles[toY][toX].isOccupied && !chessBoard.Tiles[toY][toX].currentPiece.isWhite))) {
                        isFirstMove = false;
                        return true;
                    }
                }
                else if ((!chessBoard.Tiles[toY][toX].isOccupied | (chessBoard.Tiles[toY][toX].isOccupied && chessBoard.Tiles[toY][toX].currentPiece.isWhite))) {
                    isFirstMove = false;
                    return true;
                }
            }

        }



        return false;
    }




}
