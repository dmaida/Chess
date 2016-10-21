
public class King extends Piece {

    enum Name{
        KING
    }

    public boolean isValidMove(int currX, int currY, int toX, int toY) {

        if(currX == toX && currY == toY) return false;

        if( (Math.abs(currX - toX) <= 1 && Math.abs(currY - toY) <= 1)){ // moving no more than 1 space
            if ((!chessBoard.Tiles[toY][toX].isOccupied) |
                    (chessBoard.Tiles[toY][toX].isOccupied && isWhite != chessBoard.Tiles[toY][toX].currentPiece.isWhite)
                    /*|(chessBoard.Tiles[toY][toX].isOccupied && isWhite != chessBoard.Tiles[toY][toX].currentPiece.isWhite)*/) { // if space is not taken, or if taken and opposite color
                chessBoard.Tiles[currY][currX].isOccupied = false; // remove piece off of board not 'check' isItSafe()
                if (isItSafe(toX, toY)){
                    chessBoard.Tiles[currY][currX].isOccupied = true; // put back on the board and return true
                    isFirstMove = false;
                    return true;
                }
                chessBoard.Tiles[currY][currX].isOccupied = true;
            }
        }
        if(canCastle(currX, currY, toX, toY)){
            return true;
        }
        return false;
    }

    private boolean canCastle(int currX, int currY, int toX, int toY){

        // The following code allows for a player to castle to either one of his rooks
        // It assumes that the white pieces starts at the bottom and the black at the top.
        // Assumed initial location for Black King[r][c] = [3][0]
        // Assumed initial location for White King[r][c] = [4][7]

        int xDir = 1;

        if (!isWhite){
            xDir = -1;
        }

        if (isFirstMove && chessBoard.Tiles[currY][currX + (xDir * 3)].isOccupied &&
                chessBoard.Tiles[currY][currX + (xDir * 3)].getPiece().isFirstMove &&
                !chessBoard.Tiles[currY][currX + (xDir * 2)].isOccupied &&
                !chessBoard.Tiles[currY][currX + (xDir * 1)].isOccupied &&
                (currY == toY) && (toX == currX + (xDir * 2))){
            if (isItSafe(toX, toY)) {
                chessBoard.Tiles[currY][currX + (xDir * 1)].currentPiece = chessBoard.Tiles[currY][currX + (xDir * 3)].getPiece();
                chessBoard.Tiles[currY][currX + (xDir * 1)].isOccupied = true;

                chessBoard.Tiles[currY][currX + (xDir * 3)].isOccupied = false;
                chessBoard.Tiles[currY][currX + (xDir * 3)].currentPiece = null;

                isFirstMove = false;
                return true;
            }
        }
        if (isFirstMove &&
                chessBoard.Tiles[currY][currX + (xDir * -4)].isOccupied &&
                chessBoard.Tiles[currY][currX + (xDir * -4)].getPiece().isFirstMove &&
                !chessBoard.Tiles[currY][currX + (xDir * -3)].isOccupied &&
                !chessBoard.Tiles[currY][currX + (xDir * -2)].isOccupied &&
                !chessBoard.Tiles[currY][currX + (xDir * -1)].isOccupied &&
                (currY == toY) && (toX == currX + (xDir * -2))){
            if (isItSafe(toX, toY)) {
                chessBoard.Tiles[currY][currX + (xDir * -1)].currentPiece = chessBoard.Tiles[currY][currX + (xDir * -4)].getPiece();
                chessBoard.Tiles[currY][currX + (xDir * -1)].isOccupied = true;

                chessBoard.Tiles[currY][currX + (xDir * -4)].isOccupied = false;
                chessBoard.Tiles[currY][currX + (xDir * -4)].currentPiece = null;

                isFirstMove = false;
                return true;
            }
        }
        return false;

    }

    private boolean isItSafe(int desiredX, int desiredY){

        if(knightCanAttack(desiredX, desiredY) | straightAttack(desiredX, desiredY) | diagnalAttack(desiredX, desiredY))
            return false;

        return true;
    }

    private boolean diagnalAttack(int desiredX, int desiredY){

        int tempX = desiredX + 1;
        int tempY = desiredY + 1;

        while ((tempX >= 0 && tempX < 8 && tempY >= 0 && tempY < 8) && !chessBoard.Tiles[tempY][tempX].isOccupied){
            tempX++;
            tempY++;
        }

        // check lower right diagnal
        if (((tempX >= 0 && tempX < 8 && tempY >= 0 && tempY < 8)) && chessBoard.Tiles[tempY][tempX].isOccupied && chessBoard.Tiles[tempY][tempX].getPiece().isValidMove(tempX, tempY, desiredX, desiredY)){
            return true;
        }

        tempX = desiredX + 1;
        tempY = desiredY - 1;

        while ((tempX >= 0 && tempX < 8 && tempY >= 0 && tempY < 8) && !chessBoard.Tiles[tempY][tempX].isOccupied){
            tempX++;
            tempY--;
        }

        // check upper right diagnal
        if (((tempX >= 0 && tempX < 8 && tempY >= 0 && tempY < 8)) && chessBoard.Tiles[tempY][tempX].isOccupied && chessBoard.Tiles[tempY][tempX].getPiece().isValidMove(tempX, tempY, desiredX, desiredY)){
            return true;
        }

        tempX = desiredX - 1;
        tempY = desiredY + 1;

        while ((tempX >= 0 && tempX < 8 && tempY >= 0 && tempY < 8) && !chessBoard.Tiles[tempY][tempX].isOccupied){
            tempX--;
            tempY++;
        }

        // check lower left diagnal
        if (((tempX >= 0 && tempX < 8 && tempY >= 0 && tempY < 8)) && chessBoard.Tiles[tempY][tempX].isOccupied && chessBoard.Tiles[tempY][tempX].getPiece().isValidMove(tempX, tempY, desiredX, desiredY)){
            return true;
        }

        tempX = desiredX - 1;
        tempY = desiredY - 1;

        while ((tempX >= 0 && tempX < 8 && tempY >= 0 && tempY < 8) && !chessBoard.Tiles[tempY][tempX].isOccupied){
            tempX--;
            tempY--;
        }

        // check upper left diagnal
        if (((tempX >= 0 && tempX < 8 && tempY >= 0 && tempY < 8)) && chessBoard.Tiles[tempY][tempX].isOccupied && chessBoard.Tiles[tempY][tempX].getPiece().isValidMove(tempX, tempY, desiredX, desiredY)){
            return true;
        }

        return false;
    }

    private boolean straightAttack(int desiredX, int desiredY){

        int temp = desiredY + 1;

        while((temp >= 0 && temp < 8) && !chessBoard.Tiles[temp][desiredX].isOccupied ){
            temp ++;
        }

        // check everything below
        if ((temp >= 0 && temp < 8) && chessBoard.Tiles[temp][desiredX].isOccupied &&
                chessBoard.Tiles[temp][desiredX].getPiece().isWhite != isWhite &&
                chessBoard.Tiles[temp][desiredX].getPiece().isValidMove(desiredX,temp, desiredX,desiredY)){
            return true;
        }

        temp = desiredY - 1;
        while((temp >= 0 && temp < 8) && !chessBoard.Tiles[temp][desiredX].isOccupied ){
            temp --;
        }

        // check  everything above
        if ((temp >= 0 && temp < 8) && chessBoard.Tiles[temp][desiredX].isOccupied &&
                chessBoard.Tiles[temp][desiredX].getPiece().isWhite != isWhite &&
                chessBoard.Tiles[temp][desiredX].getPiece().isValidMove(desiredX,temp, desiredX,desiredY)){
            return true;
        }

        temp = desiredX + 1;
        while((temp >= 0 && temp < 8) && !chessBoard.Tiles[desiredY][temp].isOccupied ){
            temp ++;
        }

        // check everything to the right
        if ((temp >= 0 && temp < 8) && chessBoard.Tiles[desiredY][temp].isOccupied &&
                chessBoard.Tiles[desiredY][temp].getPiece().isWhite != isWhite &&
                chessBoard.Tiles[desiredY][temp].getPiece().isValidMove(temp,desiredY, desiredX,desiredY)){
            return true;
        }

        // check everything to the left
        temp = desiredX - 1;
        while((temp >= 0 && temp < 8) && !chessBoard.Tiles[desiredY][temp].isOccupied ){
            temp --;
        }
        if ((temp >= 0 && temp < 8) && chessBoard.Tiles[desiredY][temp].isOccupied &&
                chessBoard.Tiles[desiredY][temp].getPiece().isWhite != isWhite &&
                chessBoard.Tiles[desiredY][temp].getPiece().isValidMove(temp,desiredY, desiredX,desiredY)){
            return true;
        }

        return false;
    }

    private boolean knightCanAttack(int desiredX, int desiredY){

        int tempX = desiredX + 2;
        int tempY = desiredY + 1;
        // right 2 down 1
        if ((tempY >= 0 && tempY < 8 && tempX >= 0 && tempX < 8) && chessBoard.Tiles[tempY][tempX].isOccupied && chessBoard.Tiles[tempY][tempX].getPiece().isValidMove(tempX,tempY, desiredX,desiredY)){
            return true;
        }

        tempY = desiredY - 1;
        // right 2 up 1
        if ((tempY >= 0 && tempY < 8 && tempX >= 0 && tempX < 8) && chessBoard.Tiles[tempY][tempX].isOccupied && chessBoard.Tiles[tempY][tempX].getPiece().isValidMove(tempX,tempY, desiredX,desiredY)){
            return true;
        }

        tempX = desiredX - 2;
        // left 2 up 1
        if ((tempY >= 0 && tempY < 8 && tempX >= 0 && tempX < 8) && chessBoard.Tiles[tempY][tempX].isOccupied && chessBoard.Tiles[tempY][tempX].getPiece().isValidMove(tempX,tempY, desiredX,desiredY)){
            return true;
        }

        tempY = desiredY + 1;
        // left 2 down 1
        if ((tempY >= 0 && tempY < 8 && tempX >= 0 && tempX < 8) && chessBoard.Tiles[tempY][tempX].isOccupied && chessBoard.Tiles[tempY][tempX].getPiece().isValidMove(tempX,tempY, desiredX,desiredY)){
            return true;
        }

        tempY = desiredY + 2;
        tempX = desiredX + 1;
        // down 2 right 1
        if ((tempY >= 0 && tempY < 8 && tempX >= 0 && tempX < 8) && chessBoard.Tiles[tempY][tempX].isOccupied && chessBoard.Tiles[tempY][tempX].getPiece().isValidMove(tempX,tempY, desiredX,desiredY)){
            return true;
        }

        tempX = desiredX - 1;
        // down 2 left 1
        if ((tempY >= 0 && tempY < 8 && tempX >= 0 && tempX < 8) && chessBoard.Tiles[tempY][tempX].isOccupied && chessBoard.Tiles[tempY][tempX].getPiece().isValidMove(tempX,tempY, desiredX,desiredY)){
            return true;
        }

        tempY = desiredY - 2;
        // up 2 left 1
        if ((tempY >= 0 && tempY < 8 && tempX >= 0 && tempX < 8) && chessBoard.Tiles[tempY][tempX].isOccupied && chessBoard.Tiles[tempY][tempX].getPiece().isValidMove(tempX,tempY, desiredX,desiredY)){
            return true;
        }

        tempX = desiredX + 1;
        // up 2 right 1
        if ((tempY >= 0 && tempY < 8 && tempX >= 0 && tempX < 8) && chessBoard.Tiles[tempY][tempX].isOccupied && chessBoard.Tiles[tempY][tempX].getPiece().isValidMove(tempX,tempY, desiredX,desiredY)){
            return true;
        }
        return false;
    }

}
