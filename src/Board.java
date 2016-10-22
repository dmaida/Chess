public class Board {

    private final int row = 8;
    private final int col = 8;
    public Tile[][] Tiles;

    public Board() {
        Tiles = new Tile[col][row];
        for (int i = 0; i < col; i++) {
            for ( int j = 0; j < row; j++) {

                Tile tile = new Tile();
                Tiles[i][j] = tile;
                tile.x = i;
                tile.y = j;
                tile.isOccupied = false;
            }
        }
    }

    public class Tile {

        public boolean isOccupied;
        public Piece currentPiece;
        public int x;
        public int y;

        public Tile (){

        }
        public Piece getPiece() {
            return currentPiece;
        }
    }


}
