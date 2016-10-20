
public class Bishop extends Piece {

    enum Name{
        ROOK
    }

    public boolean isValidMove(int currX, int currY, int toX, int toY) {

        if(currX == toX && currY == toY) return false;

        if(isWhite){

            int xDir = 1;
            int yDir = 1;

            if(currY > toY)
                yDir = -1;

            if(currX > toX)
                xDir = -1;
            System.out.println("xDir = "+xDir);
            System.out.println("yDir = "+yDir);
            int tempX = currX + xDir;
            int tempY = currY + yDir;
            System.out.println("Start");
            System.out.println("curr[x][y] = ["+currX+"]["+currY+"]");
            System.out.println("temp[x][y] = ["+tempX+"]["+tempY+"]");
            System.out.println("to[x][y] = ["+toX+"]["+toY+"]");
            System.out.println("==================");
            int i = 1;


            while ((tempX < 8 && tempX > -1 && tempY < 8 && tempY > -1) && !chessBoard.Tiles[tempX][tempY].isOccupied ){


                if (tempY == toY && tempX == toX){
                    System.out.println("breaking loop with temp[x][y] = ["+tempX+"]["+tempY+"]");

                    break;
                }
                tempY = tempY + yDir;
                tempX = tempX + xDir;
                System.out.println(""+i+"temp[x][y] = ["+tempX+"]["+tempY+"]");
                i++;

            }

            System.out.println("Final");
            System.out.println("temp[x][y] = ["+tempX+"]["+tempY+"]");
            System.out.println("to[x][y] = ["+toX+"]["+toY+"]");

            if (chessBoard.Tiles[tempX][tempY].isOccupied) {
                System.out.println("It thinks ["+tempX+"]["+tempY+"] is occupied");
            }
            if (chessBoard.Tiles[tempX][tempY].isOccupied) {
                System.out.println("It should be occupied at ["+tempY+"]["+tempX+"]");
            }

            if((tempX == toX && tempY == toY) && (!chessBoard.Tiles[toX][toY].isOccupied | (chessBoard.Tiles[toX][toY].isOccupied && !chessBoard.Tiles[toX][toY].currentPiece.isWhite))){
                System.out.println("Valid Move");
                return true;
            }
        }

        else {

        }
        System.out.println("Invalid Move");
        return false;
    }
}
