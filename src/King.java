import java.util.ArrayList;

public class King extends Piece {

    public King (Board board, String name, boolean isWhite, int y, int x) {
        super(board, name, isWhite, y, x);
    }

    public ArrayList<Board.Tile> getMoves(int currX, int currY) {

        int x = currX;
        int y = currY;

       // moveList = check();
        //moveList = check();
        return moveList;
    }

    private ArrayList<Board.Tile> check() {
        ArrayList<Board.Tile> dontMoveHere = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessBoard.Tiles[i][j].currentPiece != null && isWhite != chessBoard.Tiles[i][j].currentPiece.isWhite) {
                    Piece p = chessBoard.Tiles[i][j].currentPiece;
                    ArrayList<Board.Tile> move = p.moveList;
                    if (move != null) {
                        for (int k = 0; k < move.size(); k++) {

                            dontMoveHere.add(move.get(k));
                        }
                    }
                }
            }
        }
        return dontMoveHere;
    }

    public boolean isValidMove(int currX, int currY, int toX, int toY) {

      return true;
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
