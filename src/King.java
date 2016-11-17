import java.util.ArrayList;

public class King extends Piece {

    public King (Board board, String name, boolean isWhite, int y, int x) {
        super(board, name, isWhite, y, x);
    }

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

        if (isWhite && chessBoard.Tiles[desiredY-1][desiredX-1].isOccupied && chessBoard.Tiles[desiredY-1][desiredX-1].getPiece().name.compareTo("b_Pawn") == 0) {
            return false;
        }
        if (isWhite && chessBoard.Tiles[desiredY-1][desiredX+1].isOccupied && chessBoard.Tiles[desiredY-1][desiredX+1].getPiece().name.compareTo("b_Pawn") == 0) {
            return false;
        }
        if (!isWhite && chessBoard.Tiles[desiredY+1][desiredX-1].isOccupied && chessBoard.Tiles[desiredY+1][desiredX-1].getPiece().name.compareTo("w_Pawn") == 0) {
            return false;
        }
        if (!isWhite && chessBoard.Tiles[desiredY+1][desiredX+1].isOccupied && chessBoard.Tiles[desiredY+1][desiredX+1].getPiece().name.compareTo("w_Pawn") == 0) {
            return false;
        }

        if (diagnalAttack(desiredX, desiredY) == true) {
            System.out.println("NOT SAFE");

            return false;
        }
        System.out.println("SAFE");

        return true;
    }

    private boolean diagnalAttack(int currX, int currY){

        int j = currX;
        int i = currY;

        while (i < 7 && j > 0){
            i++;
            j--;
            if (chessBoard.Tiles[i][j].isOccupied && (isWhite != chessBoard.Tiles[i][j].currentPiece.isWhite)) {

                ArrayList<Board.Tile> opponentList = chessBoard.Tiles[i][j].getPiece().getMoves(j, i);
                for (int k = 0; k < opponentList.size(); k++) {
                    if (opponentList.get(k).y == currY && opponentList.get(k).x == currX) {     // the comparison is backwards due to rows(x) being toY and collumns(y) being to X.
                        return true;
                    }
                }
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

                ArrayList<Board.Tile> opponentList = chessBoard.Tiles[i][j].getPiece().getMoves(j, i);
                for (int k = 0; k < opponentList.size(); k++) {
                    if (opponentList.get(k).y == currY && opponentList.get(k).x == currX) {     // the comparison is backwards due to rows(x) being toY and collumns(y) being to X.
                        return true;
                    }
                }
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

                ArrayList<Board.Tile> opponentList = chessBoard.Tiles[i][j].getPiece().getMoves(j, i);
                for (int k = 0; k < opponentList.size(); k++) {
                    if (opponentList.get(k).y == currY && opponentList.get(k).x == currX) {     // the comparison is backwards due to rows(x) being toY and collumns(y) being to X.
                        return true;
                    }
                }
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

                ArrayList<Board.Tile> opponentList = chessBoard.Tiles[i][j].getPiece().getMoves(j, i);
                for (int k = 0; k < opponentList.size(); k++) {

                    if (opponentList.get(k).y == currY && opponentList.get(k).x == currX) {     // the comparison is backwards due to rows(x) being toY and collumns(y) being to X.
                        return true;
                    }
                }
            }
            else if (chessBoard.Tiles[i][j].isOccupied)
                break;

        }
        return false;
    }


    private boolean straightAttack(int desiredX, int desiredY){

        return false;

    }

    private boolean knightCanAttack(int desiredX, int desiredY) {

        return false;
    }
}
