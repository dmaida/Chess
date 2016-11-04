import java.util.ArrayList;

public class King extends Piece {

    public King (Board board, String name, boolean isWhite, int y, int x) {
        super(board, name, isWhite, y, x);
    }

    public boolean isValidMove(int currX, int currY, int toX, int toY) {

        /*
        if(currX == toX && currY == toY) return false;

        if( (Math.abs(currX - toX) <= 1 && Math.abs(currY - toY) <= 1)){ // moving no more than 1 space
            if ((!chessBoard.Tiles[toY][toX].isOccupied) |
                    (chessBoard.Tiles[toY][toX].isOccupied && isWhite != chessBoard.Tiles[toY][toX].currentPiece.isWhite)
                    *//*|(chessBoard.Tiles[toY][toX].isOccupied && isWhite != chessBoard.Tiles[toY][toX].currentPiece.isWhite)*//*) { // if space is not taken, or if taken and opposite color
                chessBoard.Tiles[currY][currX].isOccupied = false; // remove piece off of board not 'check' isItSafe()
                if (isItSafe(toX, toY)){
                    chessBoard.Tiles[currY][currX].isOccupied = true; // put back on the board and return true
                    isFirstMove = false;
                    return true;
                }
                chessBoard.Tiles[currY][currX].isOccupied = true;
            }
        }
        *//*if(canCastle(currX, currY, toX, toY)){
            return true;
        }



        return false;
        */

        return true;
    }

    public ArrayList<Board.Tile> getMoves(int currX, int currY){

        moveList = new ArrayList<>();

        // check left side
        if (currX > 0){
            if (/*isItSafe(currX - 1, currY) &&*/ (!chessBoard.Tiles[currY][currX - 1].isOccupied | (chessBoard.Tiles[currY][currX - 1].isOccupied && isWhite != chessBoard.Tiles[currY][currX - 1].currentPiece.isWhite))) {
                System.out.println("1");
                moveList.add(chessBoard.Tiles[currY][currX - 1]);
            }
            if (/*isItSafe(currX - 1, currY - 1) && */currY > 0 && (!chessBoard.Tiles[currY - 1][currX - 1].isOccupied |  (chessBoard.Tiles[currY - 1][currX - 1].isOccupied && (isWhite != chessBoard.Tiles[currY - 1][currX - 1].currentPiece.isWhite)))){
                System.out.println("2");
                moveList.add(chessBoard.Tiles[currY - 1][currX - 1]);
            }
            if (/*isItSafe(currX - 1, currY + 1) && */currY < 7 && (!chessBoard.Tiles[currY][currX - 1].isOccupied | (chessBoard.Tiles[currY + 1][currX - 1].isOccupied && (isWhite != chessBoard.Tiles[currY + 1][currX - 1].currentPiece.isWhite)))){
                System.out.println("3");
                moveList.add(chessBoard.Tiles[currY + 1][currX - 1]);
            }

        }

        // check right side
        if (currX < 7){
            if (/*isItSafe(currX + 1, currY) && */(!chessBoard.Tiles[currY][currX + 1].isOccupied | (chessBoard.Tiles[currY][currX + 1].isOccupied && isWhite != chessBoard.Tiles[currY][currX + 1].currentPiece.isWhite))) {
                System.out.println("4");
                moveList.add(chessBoard.Tiles[currY][currX + 1]);
            }
            if (/*isItSafe(currX + 1, currY - 1) && */currY > 0 && (!chessBoard.Tiles[currY - 1][currX + 1].isOccupied | (chessBoard.Tiles[currY - 1][currX + 1].isOccupied && (isWhite != chessBoard.Tiles[currY - 1][currX + 1].currentPiece.isWhite)))){
                System.out.println("5");
                moveList.add(chessBoard.Tiles[currY - 1][currX + 1]);
            }
            System.out.println("currX = "+ (int)(currX + 1));
            System.out.println("currY = "+ (int)(currY + 1));
            if (/*isItSafe(currX + 1, currY + 1) &&*/ currY < 7 && (!chessBoard.Tiles[currY + 1][currX + 1].isOccupied | (chessBoard.Tiles[currY + 1][currX + 1].isOccupied && (isWhite != chessBoard.Tiles[currY + 1][currX + 1].currentPiece.isWhite)))){
                System.out.println("6");
                moveList.add(chessBoard.Tiles[currY + 1][currX + 1]);
            }

        }

        // check directly above
        if (/*isItSafe(currX, currY - 1) &&*/ currY > 0 && (!chessBoard.Tiles[currY - 1][currX].isOccupied | (chessBoard.Tiles[currY - 1][currX].isOccupied && (isWhite != chessBoard.Tiles[currY - 1][currX].currentPiece.isWhite)))){
            System.out.println("7");
            moveList.add(chessBoard.Tiles[currY - 1][currX]);
        }

        // check directly below
        if (/*isItSafe(currX, currY + 1) &&*/ currY < 7 && (!chessBoard.Tiles[currY + 1][currX].isOccupied | (chessBoard.Tiles[currY + 1][currX].isOccupied && (isWhite != chessBoard.Tiles[currY + 1][currX].currentPiece.isWhite)))){
            System.out.println("8");
            moveList.add(chessBoard.Tiles[currY + 1][currX]);
        }

        return moveList;
    }

    /*private boolean canCastle(int currX, int currY, int toX, int toY){

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

    }*/

    private boolean isItSafe(int desiredX, int desiredY){

        boolean test = false;

        if (test = diagnalAttack(desiredX, desiredY)) {
            if (desiredX == 1 && desiredY == 1){
                System.out.println("test = "+ test);
            }
            System.out.println("diagnal");
            return false;
        }

        if(knightCanAttack(desiredX, desiredY)) {
            System.out.println("knight");
            return false;
        }
        if (straightAttack(desiredX, desiredY)) {
            System.out.println("straight");
            return false;
        }


        return true;
    }

    private boolean diagnalAttack(int desiredX, int desiredY){

        if (desiredX < 0 | desiredX > 7 | desiredY < 0 | desiredY > 7){
            return false;
        }

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

        if (desiredX < 0 | desiredX > 7 | desiredY < 0 | desiredY > 7){
            return false;
        }

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

        if (desiredX < 0 | desiredX > 7 | desiredY < 0 | desiredY > 7){
            return false;
        }

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
