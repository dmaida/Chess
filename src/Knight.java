import java.util.ArrayList;

public class Knight extends Piece{

    public Knight (Board board, String name, boolean isWhite, int y, int x) {
        super(board, name, isWhite, y, x);
    }

    public ArrayList<Board.Tile> getMoves(int currX, int currY) {
        moveList = new ArrayList<>();
        int x = currX;
        int y = currY;

        if (isWhite) {
            if ((y>1 && x>0) && (!chessBoard.Tiles[y-2][x-1].isOccupied | (chessBoard.Tiles[y-2][x-1].isOccupied && !chessBoard.Tiles[y-2][x-1].currentPiece.isWhite))){
                moveList.add(chessBoard.Tiles[y-2][x-1]);
            }
            if ((y>1 && x<7) && (!chessBoard.Tiles[y-2][x+1].isOccupied | (chessBoard.Tiles[y-2][x+1].isOccupied && !chessBoard.Tiles[y-2][x+1].currentPiece.isWhite))){
                moveList.add(chessBoard.Tiles[y-2][x+1]);
            }
            if ((y>0 && x>1) && (!chessBoard.Tiles[y-1][x-2].isOccupied | (chessBoard.Tiles[y-1][x-2].isOccupied && !chessBoard.Tiles[y-1][x-2].currentPiece.isWhite))){
                moveList.add(chessBoard.Tiles[y-1][x-2]);
            }
            if ((y>0 && x<6) && (!chessBoard.Tiles[y-1][x+2].isOccupied | (chessBoard.Tiles[y-1][x+2].isOccupied && !chessBoard.Tiles[y-1][x+2].currentPiece.isWhite))){
                moveList.add(chessBoard.Tiles[y-1][x+2]);
            }
            if ((y<7 && x<6) && (!chessBoard.Tiles[y+1][x+2].isOccupied | (chessBoard.Tiles[y+1][x+2].isOccupied && !chessBoard.Tiles[y+1][x+2].currentPiece.isWhite))){
                moveList.add(chessBoard.Tiles[y+1][x+2]);
            }
            if ((y<6 && x<7) && (!chessBoard.Tiles[y+2][x+1].isOccupied | (chessBoard.Tiles[y+2][x+1].isOccupied && !chessBoard.Tiles[y+2][x+1].currentPiece.isWhite))){
                moveList.add(chessBoard.Tiles[y+2][x+1]);
            }
            if ((y<6 && x>0) && (!chessBoard.Tiles[y+2][x-1].isOccupied | (chessBoard.Tiles[y+2][x-1].isOccupied && !chessBoard.Tiles[y+2][x-1].currentPiece.isWhite))){
                moveList.add(chessBoard.Tiles[y+2][x-1]);
            }
            if ((y<7 && x>1) && (!chessBoard.Tiles[y+1][x-2].isOccupied | (chessBoard.Tiles[y+1][x-2].isOccupied && !chessBoard.Tiles[y+1][x-2].currentPiece.isWhite))){
                moveList.add(chessBoard.Tiles[y+1][x-2]);
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

    public boolean isValidMove(int currX, int currY, int toX, int toY){

        if(currX == toX && currY == toY) return false;

        int xDir = 1;
        int yDir = 1;

        if(currX > toX)
            xDir = -1;

        if (currY > toY)
            yDir = -1;

        if( ((toX == currX + (2*xDir)) && (toY == currY + yDir)) | ((toY == currY + (2*yDir)) && (toX == currX + xDir)) ){

            if ((!chessBoard.Tiles[toY][toX].isOccupied) |
                    (isWhite && chessBoard.Tiles[toY][toX].isOccupied && !chessBoard.Tiles[toY][toX].currentPiece.isWhite) |
                    (!isWhite && chessBoard.Tiles[toY][toX].isOccupied && chessBoard.Tiles[toY][toX].currentPiece.isWhite)) {
                isFirstMove = false;
                return true;
            }
        }

        return false;
    }


}
