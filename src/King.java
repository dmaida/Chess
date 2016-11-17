import java.util.ArrayList;

public class King extends Piece {

    public King (Board board, String name, boolean isWhite, int y, int x) {
        super(board, name, isWhite, y, x);
    }

    private int globalX;
    private int globalY;

    public boolean isValidMove(int currX, int currY, int toX, int toY) {

        if (currX == toX && currY == toY) return false;

        for (int i = 0; i < moveList.size(); i++) {
            if (moveList.get(i).y == toY && moveList.get(i).x == toX) {     // the comparison is backwards due to rows(x) being toY and collumns(y) being to X.
                isFirstMove = false;
                return true;
            }
        }
        return false;
    }

    public ArrayList<Board.Tile> getMoves(int currX, int currY){

        moveList = new ArrayList<>();
        globalX = currX;
        globalY = currY;

        // check left side
        if (currX > 0){
            if ((!chessBoard.Tiles[currY][currX - 1].isOccupied | (chessBoard.Tiles[currY][currX - 1].isOccupied && isWhite != chessBoard.Tiles[currY][currX - 1].currentPiece.isWhite)) && isItSafe(currX - 1, currY)) {
                System.out.println("1");
                moveList.add(chessBoard.Tiles[currY][currX - 1]);
            }
            if (currY > 0 && (!chessBoard.Tiles[currY - 1][currX - 1].isOccupied |  (chessBoard.Tiles[currY - 1][currX - 1].isOccupied && (isWhite != chessBoard.Tiles[currY - 1][currX - 1].currentPiece.isWhite))) && isItSafe(currX - 1, currY - 1)){
                System.out.println("2");
                moveList.add(chessBoard.Tiles[currY - 1][currX - 1]);
            }
            if (currY < 7 && (!chessBoard.Tiles[currY + 1][currX - 1].isOccupied | (chessBoard.Tiles[currY + 1][currX - 1].isOccupied && (isWhite != chessBoard.Tiles[currY + 1][currX - 1].currentPiece.isWhite))) && isItSafe(currX - 1, currY + 1)){
                System.out.println("3");
                moveList.add(chessBoard.Tiles[currY + 1][currX - 1]);
            }

        }

        // check right side
        if (currX < 7){
            if ((!chessBoard.Tiles[currY][currX + 1].isOccupied | (chessBoard.Tiles[currY][currX + 1].isOccupied && isWhite != chessBoard.Tiles[currY][currX + 1].currentPiece.isWhite)) && isItSafe(currX + 1, currY)) {
                System.out.println("4");
                moveList.add(chessBoard.Tiles[currY][currX + 1]);
            }
            if (currY > 0 && (!chessBoard.Tiles[currY - 1][currX + 1].isOccupied | (chessBoard.Tiles[currY - 1][currX + 1].isOccupied && (isWhite != chessBoard.Tiles[currY - 1][currX + 1].currentPiece.isWhite))) && isItSafe(currX + 1, currY - 1)){
                System.out.println("5");
                moveList.add(chessBoard.Tiles[currY - 1][currX + 1]);
            }

            if (currY < 7 && (!chessBoard.Tiles[currY + 1][currX + 1].isOccupied | (chessBoard.Tiles[currY + 1][currX + 1].isOccupied && (isWhite != chessBoard.Tiles[currY + 1][currX + 1].currentPiece.isWhite))) && isItSafe(currX + 1, currY + 1)){
                System.out.println("6");
                moveList.add(chessBoard.Tiles[currY + 1][currX + 1]);
            }

        }

        // check directly above
        if (currY > 0 && (!chessBoard.Tiles[currY - 1][currX].isOccupied | (chessBoard.Tiles[currY - 1][currX].isOccupied && (isWhite != chessBoard.Tiles[currY - 1][currX].currentPiece.isWhite))) && isItSafe(currX, currY - 1)){
            System.out.println("7");
            moveList.add(chessBoard.Tiles[currY - 1][currX]);
        }

        // check directly below
        if (currY < 7 && (!chessBoard.Tiles[currY + 1][currX].isOccupied | (chessBoard.Tiles[currY + 1][currX].isOccupied && (isWhite != chessBoard.Tiles[currY + 1][currX].currentPiece.isWhite))) && isItSafe(currX, currY + 1)){
            System.out.println("8");
            moveList.add(chessBoard.Tiles[currY + 1][currX]);
        }

        return moveList;
    }

    private boolean isItSafe(int desiredX, int desiredY){

        chessBoard.Tiles[globalY][globalX].isOccupied = false;

        if (pawnAttack(desiredX, desiredY)){
            chessBoard.Tiles[globalY][globalX].isOccupied = true;
            return false;
        }

        if (diagnalAttack(desiredX, desiredY)) {
            chessBoard.Tiles[globalY][globalX].isOccupied = true;
            return false;
        }

        if (straightAttack(desiredX, desiredY)) {
            chessBoard.Tiles[globalY][globalX].isOccupied = true;
            return false;
        }

        chessBoard.Tiles[globalY][globalX].isOccupied = true;
        return true;
    }

    private boolean pawnAttack(int desiredX, int desiredY){
        if (isWhite && chessBoard.Tiles[desiredY-1][desiredX-1].isOccupied && chessBoard.Tiles[desiredY-1][desiredX-1].getPiece().name.compareTo("b_Pawn") == 0) {
            return true;
        }
        if (isWhite && chessBoard.Tiles[desiredY-1][desiredX+1].isOccupied && chessBoard.Tiles[desiredY-1][desiredX+1].getPiece().name.compareTo("b_Pawn") == 0) {
            return true;
        }
        if (!isWhite && chessBoard.Tiles[desiredY+1][desiredX-1].isOccupied && chessBoard.Tiles[desiredY+1][desiredX-1].getPiece().name.compareTo("w_Pawn") == 0) {
            return true;
        }
        if (!isWhite && chessBoard.Tiles[desiredY+1][desiredX+1].isOccupied && chessBoard.Tiles[desiredY+1][desiredX+1].getPiece().name.compareTo("w_Pawn") == 0) {
            return true;
        }
        return false;
    }

    private boolean checkOpponentMove(int currX, int currY, int i, int j) {

        if (chessBoard.Tiles[i][j].isOccupied && (chessBoard.Tiles[i][j].getPiece().name.compareTo("b_Pawn") == 0 | chessBoard.Tiles[i][j].getPiece().name.compareTo("w_Pawn") == 0)){
            return false;
        }

        ArrayList<Board.Tile> opponentList = chessBoard.Tiles[i][j].getPiece().getMoves(j, i);
        for (int k = 0; k < opponentList.size(); k++) {
            if (opponentList.get(k).y == currY && opponentList.get(k).x == currX) {     // the comparison is backwards due to rows(x) being toY and collumns(y) being to X.
                return true;
            }
        }
        return false;
    }

    private boolean diagnalAttack(int currX, int currY){

        int j = currX;
        int i = currY;

        while (i < 7 && j > 0){
            i++;
            j--;
            if (chessBoard.Tiles[i][j].isOccupied && (isWhite != chessBoard.Tiles[i][j].currentPiece.isWhite)) {

                return checkOpponentMove(currX, currY, i, j);
            }
            else if (chessBoard.Tiles[i][j].isOccupied)
                break;
        }

        j = currX;
        i = currY;

        while (i > 0 && j < 7){
            i--;
            j++;
            if (chessBoard.Tiles[i][j].isOccupied && (isWhite != chessBoard.Tiles[i][j].currentPiece.isWhite)) {

                return checkOpponentMove(currX, currY, i, j);
            }
            else if (chessBoard.Tiles[i][j].isOccupied)
                break;

        }

        j = currX;
        i = currY;

        while (i < 7 && j < 7){
            i++;
            j++;
            if (chessBoard.Tiles[i][j].isOccupied && (isWhite != chessBoard.Tiles[i][j].currentPiece.isWhite)) {

                return checkOpponentMove(currX, currY, i, j);
            }
            else if (chessBoard.Tiles[i][j].isOccupied)
                break;

        }

        j = currX;
        i = currY;

        while (i > 0 && j > 0){
            i--;
            j--;

            if (chessBoard.Tiles[i][j].isOccupied && (isWhite != chessBoard.Tiles[i][j].currentPiece.isWhite)) {

                return checkOpponentMove(currX, currY, i, j);
            }
            else if (chessBoard.Tiles[i][j].isOccupied)
                break;
        }
        return false;
    }


    private boolean straightAttack(int currX, int currY){

        System.out.println("reached straight attack");
        int x = currX;
        int y = currY;
        System.out.println("x = " + currX + " y = "+currY);


        for (int i = y; i < 8; i++) {
            if (chessBoard.Tiles[i][x].isOccupied && (isWhite != chessBoard.Tiles[i][x].currentPiece.isWhite)) {
                System.out.println("opponent Found For Loop 1");
                return checkOpponentMove(currX, currY, i, x);
            }
            else if (chessBoard.Tiles[i][x].isOccupied)
                break;
        }
        for (int i=y; i>=0; i--) {
            if (chessBoard.Tiles[i][x].isOccupied && (isWhite != chessBoard.Tiles[i][x].currentPiece.isWhite)) {
                System.out.println("opponent Found For Loop 2");
                return checkOpponentMove(currX, currY, i, x);
            }
            else if (chessBoard.Tiles[i][x].isOccupied)
                break;
        }

        for (int j = x; j < 8; j++) {
            System.out.println("----------------------------------------For Loop 3");
            if (chessBoard.Tiles[y][j].isOccupied && (isWhite != chessBoard.Tiles[y][j].currentPiece.isWhite)) {
                System.out.println("opponent Found For Loop 3");
                return checkOpponentMove(currX, currY, y, j);
            }
            else if (chessBoard.Tiles[y][j].isOccupied)
                break;
        }

        for (int j=x; j>=0; j--) {
            System.out.println("----------------------------------------For Loop 4");
            if (chessBoard.Tiles[y][j].isOccupied && (isWhite != chessBoard.Tiles[y][j].currentPiece.isWhite)) {
                System.out.println("opponent Found For Loop 4");
                return checkOpponentMove(currX, currY, y, j);
            }
            else if (chessBoard.Tiles[y][j].isOccupied)
                break;
        }
        return false;
    }

    private boolean knightCanAttack(int desiredX, int desiredY) {

        return false;
    }
}
