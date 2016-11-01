import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn (Board board, String name, boolean isWhite, int y, int x) {
        super(board, name, isWhite, y, x);
    }

    public ArrayList<Board.Tile> getMoves(int currX, int currY) {
        moveList = new ArrayList<>();
        int x = currX;
        int y = currY;

        if (isWhite) {
            if (y > 0 && !chessBoard.Tiles[y-1][x].isOccupied) {
                moveList.add(chessBoard.Tiles[y-1][x]);
            }

            if (y > 1 && isFirstMove && !chessBoard.Tiles[y-2][x].isOccupied)
                moveList.add(chessBoard.Tiles[y-2][x]);

            if ((y > 0 && x > 0) && chessBoard.Tiles[y-1][x-1].isOccupied && !chessBoard.Tiles[y-1][x-1].currentPiece.isWhite)
                moveList.add(chessBoard.Tiles[y-1][x-1]);

            if ((y > 0 && x < 7) && chessBoard.Tiles[y-1][x+1].isOccupied && !chessBoard.Tiles[y-1][x+1].currentPiece.isWhite)
                moveList.add(chessBoard.Tiles[y-1][x+1]);
        }

        if (!isWhite) {
            if (y < 7 && !chessBoard.Tiles[y+1][x].isOccupied) {
                moveList.add(chessBoard.Tiles[y+1][x]);
            }

            if (y < 6 && isFirstMove && !chessBoard.Tiles[y+2][x].isOccupied)
                moveList.add(chessBoard.Tiles[y+2][x]);

            if ((y < 7 && x > 0) && chessBoard.Tiles[y+1][x-1].isOccupied && chessBoard.Tiles[y+1][x-1].currentPiece.isWhite)
                moveList.add(chessBoard.Tiles[y+1][x-1]);

            if ((y < 7 && x < 7) && chessBoard.Tiles[y+1][x+1].isOccupied && chessBoard.Tiles[y+1][x+1].currentPiece.isWhite)
                moveList.add(chessBoard.Tiles[y+1][x+1]);
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

        if (isWhite) {
            if ((currY - 1 == toY | (currY - 2 == toY && isFirstMove)) && currX == toX) {
                if (!chessBoard.Tiles[toY][toX].isOccupied) {
                    isFirstMove = false;
                    return true;
                }
            }

            if (currY - 1 == toY) {
                if (chessBoard.Tiles[toY][toX].isOccupied && !chessBoard.Tiles[toY][toX].currentPiece.isWhite) {
                    if (currX + 1 == toX | currX - 1 == toX) {
                        isFirstMove = false;
                        return true;
                    }

                }
            }
        }

        else {
            if ((currY + 1 == toY | (currY + 2 == toY && isFirstMove)) && currX == toX) {
                if (!chessBoard.Tiles[toY][toX].isOccupied) {
                    isFirstMove = false;
                    return true;
                }
            }

            if (currY + 1 == toY) {
                if (chessBoard.Tiles[toY][toX].isOccupied && chessBoard.Tiles[toY][toX].currentPiece.isWhite) {
                    if (currX + 1 == toX | currX - 1 == toX) {
                        isFirstMove = false;
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
