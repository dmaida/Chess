public class Board {

    private int row = 8;
    private  int col = 8;
    public Tile[][] chessBoard;


    public Board() {
        chessBoard = new Tile[row][col];
        for (int i = 0; i < row; i++) {
            for ( int j = 0; j < col; j++) {
                Tile tile = new Tile();
                chessBoard[i][j] = tile;
            }
        }
    }

    public void printBoard() {

    }

    public class Tile {

        public boolean isOccupied;
        public Piece currentPiece;

        public Tile (){

        }





    }


}
