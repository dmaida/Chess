import java.util.ArrayList;

public class Rook extends Piece {

    public Rook (Board board, String name, boolean isWhite, int y, int x) {
        super(board, name, isWhite, y, x);
    }

    public ArrayList<Board.Tile> getMoves(int currX, int currY) {
        moveList = new ArrayList<>();
        int x = currX;
        int y = currY;

        for (int i = y; i < 8; i++) {
            if (chessBoard.Tiles[i][x].isOccupied && (isWhite != chessBoard.Tiles[i][x].currentPiece.isWhite)) {
                moveList.add(chessBoard.Tiles[i][x]);
            }
            if (chessBoard.Tiles[i][x].isOccupied && i!=y)
                break;
            if (!chessBoard.Tiles[i][x].isOccupied) {
                moveList.add(chessBoard.Tiles[i][x]);
            }
        }

        for (int i=y; i>=0; i--) {
            if (chessBoard.Tiles[i][x].isOccupied && (isWhite != chessBoard.Tiles[i][x].currentPiece.isWhite)) {
                moveList.add(chessBoard.Tiles[i][x]);
            }
            if (chessBoard.Tiles[i][x].isOccupied && i!=y)
                break;
            if (!chessBoard.Tiles[i][x].isOccupied) {
                moveList.add(chessBoard.Tiles[i][x]);
            }
        }

        for (int i = x; i < 8; i++) {
            if (chessBoard.Tiles[y][i].isOccupied && (isWhite != chessBoard.Tiles[y][i].currentPiece.isWhite)) {
                moveList.add(chessBoard.Tiles[y][i]);
            }
            if (chessBoard.Tiles[y][i].isOccupied && i!=x)
                break;
            if (!chessBoard.Tiles[y][i].isOccupied) {
                moveList.add(chessBoard.Tiles[y][i]);
            }
        }

        for (int i=x; i>=0; i--) {
            if (chessBoard.Tiles[y][i].isOccupied && (isWhite != chessBoard.Tiles[y][i].currentPiece.isWhite)) {
                moveList.add(chessBoard.Tiles[y][i]);
            }
            if (chessBoard.Tiles[y][i].isOccupied && i!=x)
                break;
            if (!chessBoard.Tiles[y][i].isOccupied) {
                moveList.add(chessBoard.Tiles[y][i]);
            }
        }
        print();
        return moveList;
    }

    public void print() {
        System.out.println();
        System.out.println();
        System.out.println();
        for (int i = 0; i < moveList.size(); i++) {
            int x = moveList.get(i).x;
            int y = moveList.get(i).y;
            System.out.println("row == " + x + " col == "+ y);
        }
    }

    public boolean isValidMove(int currX, int currY, int toX, int toY) {

        if(currX == toX && currY == toY) return false;

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
        return false;
    }

}
