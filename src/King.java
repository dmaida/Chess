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

        moveList = castleMove(moveList);

        return moveList;
    }

    private ArrayList castleMove(ArrayList moveList){

        int dir = 1;
        if (!isWhite){
            dir = -1;
        }

        if (isItSafe(globalX, globalY) && isFirstMove){ //If King is not checked && King's first move
            if (!chessBoard.Tiles[globalY][globalX + (1 * dir)].isOccupied &&
                    !chessBoard.Tiles[globalY][globalX + (2 * dir)].isOccupied &&
                    chessBoard.Tiles[globalY][globalX + (3 * dir)].isOccupied &&
                    chessBoard.Tiles[globalY][globalX + (3 * dir)].getPiece().isFirstMove &&
                    isItSafe(globalX + (2 * dir), globalY)){ // If spaces in between King the Rook are free and it is the Rook's first turn
                moveList.add(chessBoard.Tiles[globalY][globalX + (2 * dir)]);
            }
            if (!chessBoard.Tiles[globalY][globalX - (1 * dir)].isOccupied &&
                    !chessBoard.Tiles[globalY][globalX - (2 * dir)].isOccupied &&
                    !chessBoard.Tiles[globalY][globalX - (3 * dir)].isOccupied &&
                    chessBoard.Tiles[globalY][globalX - (4 * dir)].isOccupied &&
                    chessBoard.Tiles[globalY][globalX - (4 * dir)].getPiece().isFirstMove &&
                    isItSafe(globalX - (2 * dir) , globalY)){
                moveList.add(chessBoard.Tiles[globalY][globalX - (2 * dir)]);
            }

        }

        return moveList;
    }

    private boolean isItSafe(int desiredX, int desiredY){
        Piece p = null;
        boolean returnVal = true;
        Piece tempKing = chessBoard.Tiles[globalY][globalX].getPiece();


        if (chessBoard.Tiles[desiredY][desiredX].isOccupied){
            p = chessBoard.Tiles[desiredY][desiredX].getPiece();
            chessBoard.Tiles[desiredY][desiredX].currentPiece = null;
        }
        chessBoard.Tiles[desiredY][desiredX].currentPiece = tempKing;
        chessBoard.Tiles[globalY][globalX].currentPiece = null;
        chessBoard.Tiles[globalY][globalX].isOccupied = false;

        if (pawnAttack(desiredX, desiredY) | diagnalAttack(desiredX, desiredY) | straightAttack(desiredX, desiredY) | knightCanAttack(desiredX, desiredY)){
            returnVal = false;
        }

        chessBoard.Tiles[desiredY][desiredX].currentPiece = null;

        if (p != null){
            chessBoard.Tiles[desiredY][desiredX].currentPiece = p;
            chessBoard.Tiles[globalY][globalX].isOccupied = false;
        }

        chessBoard.Tiles[globalY][globalX].isOccupied = true;
        chessBoard.Tiles[globalY][globalX].currentPiece = tempKing;

        return returnVal;
    }

    private boolean pawnAttack(int desiredX, int desiredY){
        if (desiredX + 1 < 8){
            if (desiredY - 1 >= 0) {
                if (isWhite && chessBoard.Tiles[desiredY - 1][desiredX + 1].isOccupied && chessBoard.Tiles[desiredY - 1][desiredX + 1].getPiece().name.compareTo("b_Pawn") == 0) {
                    return true;
                }
            }
            if (desiredY + 1 < 8)
                if (!isWhite && chessBoard.Tiles[desiredY+1][desiredX+1].isOccupied && chessBoard.Tiles[desiredY+1][desiredX+1].getPiece().name.compareTo("w_Pawn") == 0) {
                    return true;
                }
        }
        if (desiredX - 1 >= 0) {
            if (desiredY - 1 >= 0) {
                if (isWhite && chessBoard.Tiles[desiredY - 1][desiredX - 1].isOccupied && chessBoard.Tiles[desiredY - 1][desiredX - 1].getPiece().name.compareTo("b_Pawn") == 0) {
                    return true;
                }
            }

            if (desiredY + 1 < 8) {
                if (!isWhite && chessBoard.Tiles[desiredY + 1][desiredX - 1].isOccupied && chessBoard.Tiles[desiredY + 1][desiredX - 1].getPiece().name.compareTo("w_Pawn") == 0) {
                    return true;
                }
            }
        }

        return false;
    }
    private boolean otherKingAttack(int currY, int currX, int i, int j){
        if ((Math.abs(currY - i) <= 1) && (Math.abs(currX - j) <= 1)){
            return true;
        }

        return false;
    }

    private boolean checkOpponentMove(int currX, int currY, int oppY, int oppX) {

        if (chessBoard.Tiles[oppY][oppX].isOccupied && chessBoard.Tiles[oppY][oppX].getPiece().name.contains("Pawn")){
            return false;
        }
        return true;
    }
    private boolean diagnalAttack(int currX, int currY){

        int j = currX;
        int i = currY;

        while (i < 7 && j > 0){
            i++;
            j--;
            if (chessBoard.Tiles[i][j].isOccupied && (isWhite != chessBoard.Tiles[i][j].currentPiece.isWhite)) {
                if (chessBoard.Tiles[i][j].getPiece().name.contains("King")){
                    if (otherKingAttack(currY, currX, i, j)) {
                        return true;
                    }
                    else break;
                }
                else {
                    ArrayList<Board.Tile> opponentList = chessBoard.Tiles[i][j].getPiece().getMoves(j, i);
                    for (int k = 0; k < opponentList.size(); k++) {
                        if (opponentList.get(k).y == currY && opponentList.get(k).x == currX) {     // the comparison is backwards due to rows(x) being toY and collumns(y) being to X.
                            return true;
                        }
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
                if (chessBoard.Tiles[i][j].getPiece().name.contains("King")){
                    if (otherKingAttack(currY, currX, i, j)) {
                        return true;
                    }
                    else break;
                }
                else {
                    ArrayList<Board.Tile> opponentList = chessBoard.Tiles[i][j].getPiece().getMoves(j, i);
                    for (int k = 0; k < opponentList.size(); k++) {
                        if (opponentList.get(k).y == currY && opponentList.get(k).x == currX) {     // the comparison is backwards due to rows(x) being toY and collumns(y) being to X.
                            return true;
                        }
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
                if (chessBoard.Tiles[i][j].getPiece().name.contains("King")){
                    if (otherKingAttack(currY, currX, i, j)) {
                        return true;
                    }
                    else break;
                }
                else {
                    ArrayList<Board.Tile> opponentList = chessBoard.Tiles[i][j].getPiece().getMoves(j, i);
                    for (int k = 0; k < opponentList.size(); k++) {
                        if (opponentList.get(k).y == currY && opponentList.get(k).x == currX) {     // the comparison is backwards due to rows(x) being toY and collumns(y) being to X.
                            return true;
                        }
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
                if (chessBoard.Tiles[i][j].getPiece().name.contains("King")){
                    if (otherKingAttack(currY, currX, i, j)) {
                        return true;
                    }
                    else break;
                }
                else {
                    ArrayList<Board.Tile> opponentList = chessBoard.Tiles[i][j].getPiece().getMoves(j, i);
                    for (int k = 0; k < opponentList.size(); k++) {
                        if (opponentList.get(k).y == currY && opponentList.get(k).x == currX) {     // the comparison is backwards due to rows(x) being toY and collumns(y) being to X.
                            return true;
                        }
                    }
                }
            }
            else if (chessBoard.Tiles[i][j].isOccupied)
                break;
        }
        return false;
    }
    private boolean straightAttack(int currX, int currY){

        final int x = currX;
        final int y = currY;

        for (int j = x + 1; j < 8; j++) {
            if (chessBoard.Tiles[y][j].isOccupied && (isWhite != chessBoard.Tiles[y][j].currentPiece.isWhite)) {
                if (chessBoard.Tiles[y][j].getPiece().name.contains("King")){
                    if (otherKingAttack(currY,currX,y,j)){
                        return true;
                    }
                    else break;
                }
                else {
                    ArrayList<Board.Tile> opponentList = chessBoard.Tiles[currY][j].getPiece().getMoves(j, currY);
                    for (int k = 0; k < opponentList.size(); k++) {
                        if (opponentList.get(k).y == currY && opponentList.get(k).x == currX) {     // the comparison is backwards due to rows(x) being toY and collumns(y) being to X.
                            if (checkOpponentMove(currX, currY, currY, j)) {
                                return true;
                            }
                        }
                    }
                }
            }
            else if (chessBoard.Tiles[y][j].isOccupied)
                break;
        }

        for (int j = x - 1; j >= 0; j--) {
            if (chessBoard.Tiles[y][j].isOccupied && (isWhite != chessBoard.Tiles[y][j].currentPiece.isWhite)) {
                if (chessBoard.Tiles[y][j].getPiece().name.contains("King")){
                    if (otherKingAttack(currY,currX,y,j)){
                        return true;
                    }
                    else break;
                }
                else {
                    ArrayList<Board.Tile> opponentList = chessBoard.Tiles[currY][j].getPiece().getMoves(j, currY);
                    for (int k = 0; k < opponentList.size(); k++) {
                        if (opponentList.get(k).y == currY && opponentList.get(k).x == currX) {     // the comparison is backwards due to rows(x) being toY and collumns(y) being to X.
                            if (checkOpponentMove(currX, currY, currY, j)) {
                                return true;
                            }
                        }
                    }
                }
            }
            else if (chessBoard.Tiles[y][j].isOccupied)
                break;
        }

        for (int i = y + 1; i < 8; i++) {
            if (chessBoard.Tiles[i][x].isOccupied && (isWhite != chessBoard.Tiles[i][x].currentPiece.isWhite)) {
                if (chessBoard.Tiles[i][x].getPiece().name.contains("King")){
                    if (otherKingAttack(currY,currX,i,x)){
                        return true;
                    }
                    else break;
                }
                else {
                    ArrayList<Board.Tile> opponentList = chessBoard.Tiles[i][currX].getPiece().getMoves(currX, i);
                    for (int k = 0; k < opponentList.size(); k++) {
                        if (opponentList.get(k).y == currY && opponentList.get(k).x == currX) {     // the comparison is backwards due to rows(x) being toY and collumns(y) being to X.
                            if (checkOpponentMove(currX, currY, i, currX)) {
                                return true;
                            }
                        }
                    }
                }
            }
            else if (chessBoard.Tiles[i][x].isOccupied)
                break;
        }
        for (int i = y - 1; i >= 0; i--) {
            if (chessBoard.Tiles[i][x].isOccupied && (isWhite != chessBoard.Tiles[i][x].currentPiece.isWhite)) {
                if (chessBoard.Tiles[i][x].getPiece().name.contains("King")){
                    if (otherKingAttack(currY,currX,i,x)){
                        return true;
                    }
                    else break;
                }
                else {
                    ArrayList<Board.Tile> opponentList = chessBoard.Tiles[i][currX].getPiece().getMoves(currX, i);
                    for (int k = 0; k < opponentList.size(); k++) {
                        if (opponentList.get(k).y == currY && opponentList.get(k).x == currX) {     // the comparison is backwards due to rows(x) being toY and collumns(y) being to X.
                            if (checkOpponentMove(currX, currY, i, currX)) {
                                return true;
                            }
                        }
                    }
                }
            }
            else if (chessBoard.Tiles[i][x].isOccupied)
                break;
        }


        return false;
    }

    private boolean knightCanAttack(int currX, int currY) {

        int x = currX;
        int y = currY;

        if ((y>1 && x>0) && (chessBoard.Tiles[y-2][x-1].isOccupied && (isWhite != chessBoard.Tiles[y-2][x-1].currentPiece.isWhite))){
            ArrayList<Board.Tile> opponentList = chessBoard.Tiles[y-2][x-1].getPiece().getMoves(x-1, y-2);
            for (int k = 0; k < opponentList.size(); k++) {
                if (opponentList.get(k).y == currY && opponentList.get(k).x == currX) {
                    return true;
                }
            }
        }
        if ((y>1 && x<7) && (chessBoard.Tiles[y-2][x+1].isOccupied && (isWhite != chessBoard.Tiles[y-2][x+1].currentPiece.isWhite))){
            ArrayList<Board.Tile> opponentList = chessBoard.Tiles[y-2][x+1].getPiece().getMoves(x+1, y-2);
            for (int k = 0; k < opponentList.size(); k++) {
                if (opponentList.get(k).y == currY && opponentList.get(k).x == currX) {
                    return true;
                }
            }
        }
        if ((y>0 && x>1) && (chessBoard.Tiles[y-1][x-2].isOccupied && (isWhite != chessBoard.Tiles[y-1][x-2].currentPiece.isWhite))){
            ArrayList<Board.Tile> opponentList = chessBoard.Tiles[y-1][x-2].getPiece().getMoves(x-2, y-1);
            for (int k = 0; k < opponentList.size(); k++) {
                if (opponentList.get(k).y == currY && opponentList.get(k).x == currX) {
                    return true;
                }
            }
        }
        if ((y>0 && x<6) && (chessBoard.Tiles[y-1][x+2].isOccupied && (isWhite != chessBoard.Tiles[y-1][x+2].currentPiece.isWhite))){
            ArrayList<Board.Tile> opponentList = chessBoard.Tiles[y-1][x+2].getPiece().getMoves(x+2, y-1);
            for (int k = 0; k < opponentList.size(); k++) {
                if (opponentList.get(k).y == currY && opponentList.get(k).x == currX) {
                    return true;
                }
            }
        }
        if ((y<7 && x<6) && (chessBoard.Tiles[y+1][x+2].isOccupied && (isWhite != chessBoard.Tiles[y+1][x+2].currentPiece.isWhite))){
            ArrayList<Board.Tile> opponentList = chessBoard.Tiles[y+1][x+2].getPiece().getMoves(x+2, y+1);
            for (int k = 0; k < opponentList.size(); k++) {
                if (opponentList.get(k).y == currY && opponentList.get(k).x == currX) {
                    return true;
                }
            }
        }
        if ((y<6 && x<7) && (chessBoard.Tiles[y+2][x+1].isOccupied && (isWhite != chessBoard.Tiles[y+2][x+1].currentPiece.isWhite))){
            ArrayList<Board.Tile> opponentList = chessBoard.Tiles[y+2][x+1].getPiece().getMoves(x+1, y+2);
            for (int k = 0; k < opponentList.size(); k++) {
                if (opponentList.get(k).y == currY && opponentList.get(k).x == currX) {
                    return true;
                }
            }
        }
        if ((y<6 && x>0) && (chessBoard.Tiles[y+2][x-1].isOccupied && (isWhite != chessBoard.Tiles[y+2][x-1].currentPiece.isWhite))){
            ArrayList<Board.Tile> opponentList = chessBoard.Tiles[y+2][x-1].getPiece().getMoves(x-1, y+2);
            for (int k = 0; k < opponentList.size(); k++) {
                if (opponentList.get(k).y == currY && opponentList.get(k).x == currX) {
                    return true;
                }
            }
        }
        if ((y<7 && x>1) && chessBoard.Tiles[y+1][x-2].isOccupied && (isWhite != chessBoard.Tiles[y+1][x-2].currentPiece.isWhite)){
            ArrayList<Board.Tile> opponentList = chessBoard.Tiles[y+1][x-2].getPiece().getMoves(x-2, y+1);
            for (int k = 0; k < opponentList.size(); k++) {
                if (opponentList.get(k).y == currY && opponentList.get(k).x == currX) {
                    return true;
                }
            }
        }
        return false;
    }
}
