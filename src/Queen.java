import java.util.ArrayList;

public class Queen extends Piece{

    public Queen (Board board, String name, boolean isWhite, int y, int x) {
        super(board, name, isWhite, y, x);
    }

    public ArrayList<Board.Tile> getMoves(int currX, int currY) {
        moveList = new ArrayList<>();
        int x = currX;
        int y = currY;

        diagonalPath(currX, currY, moveList);
        straightPath(currX, currY, moveList);

        return moveList;
    }

    private ArrayList<Board.Tile> diagonalPath(int currX, int currY, ArrayList<Board.Tile> moveList) {
        int j = currX;
        int i = currY;

        while (i < 7 && j > 0){
            i++;
            j--;
            if (chessBoard.Tiles[i][j].isOccupied && (isWhite != chessBoard.Tiles[i][j].currentPiece.isWhite)) {
                moveList.add(chessBoard.Tiles[i][j]);
            }
            if (chessBoard.Tiles[i][j].isOccupied)
                break;
            if (!chessBoard.Tiles[i][j].isOccupied) {
                moveList.add(chessBoard.Tiles[i][j]);
            }
        }
        j = currX;
        i = currY;
        while (i > 0 && j < 7){
            i--;
            j++;
            if (chessBoard.Tiles[i][j].isOccupied && (isWhite != chessBoard.Tiles[i][j].currentPiece.isWhite)) {
                moveList.add(chessBoard.Tiles[i][j]);
            }
            if (chessBoard.Tiles[i][j].isOccupied)
                break;
            if (!chessBoard.Tiles[i][j].isOccupied) {
                moveList.add(chessBoard.Tiles[i][j]);
            }
        }

        j = currX;
        i = currY;

        while (i < 7 && j < 7){
            i++;
            j++;
            if (chessBoard.Tiles[i][j].isOccupied && (isWhite != chessBoard.Tiles[i][j].currentPiece.isWhite)) {
                moveList.add(chessBoard.Tiles[i][j]);
            }
            if (chessBoard.Tiles[i][j].isOccupied)
                break;
            if (!chessBoard.Tiles[i][j].isOccupied) {
                moveList.add(chessBoard.Tiles[i][j]);
            }
        }

        j = currX;
        i = currY;

        while (i > 0 && j > 0){
            i--;
            j--;
            if (chessBoard.Tiles[i][j].isOccupied && (isWhite != chessBoard.Tiles[i][j].currentPiece.isWhite)) {
                moveList.add(chessBoard.Tiles[i][j]);
            }
            if (chessBoard.Tiles[i][j].isOccupied)
                break;
            if (!chessBoard.Tiles[i][j].isOccupied) {
                moveList.add(chessBoard.Tiles[i][j]);
            }
        }
        return moveList;
    }

    private ArrayList<Board.Tile> straightPath(int currX, int currY, ArrayList<Board.Tile> moveList) {
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
        return moveList;
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
            if((tempX == toX && tempY == toY)) {
                if (isWhite) {
                    if ((!chessBoard.Tiles[toY][toX].isOccupied | (isWhite != chessBoard.Tiles[toY][toX].currentPiece.isWhite))) {
                        isFirstMove = false;
                        return true;
                    }
                }
                else if ((!chessBoard.Tiles[toY][toX].isOccupied | (isWhite == chessBoard.Tiles[toY][toX].currentPiece.isWhite))) {
                    isFirstMove = false;
                    return true;
                }
            }
        }
        return false;
    }




}
