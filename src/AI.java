import java.util.ArrayList;
import java.util.Random;

public class AI {

    public static void choosePiece(Board B) {
        ArrayList<Piece> pieceLIst = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (B.Tiles[i][j].isOccupied && !B.Tiles[i][j].getPiece().isWhite) {
                    Piece p = B.Tiles[i][j].getPiece();
                    if (p.getMoves(j, i).size() > 0) {
                        pieceLIst.add(p);
                    }

                }
            }
        }

        Random r = new Random();

        ArrayList<Board.Tile> possibleMoves = null;

        if (pieceLIst.size() > 0) {
            int ranNumb = positiveRan(pieceLIst.size());
            Piece piece = pieceLIst.get(ranNumb);

            Main.cY = piece.globalY;
            Main.cX = piece.globalX;

            possibleMoves = piece.getMoves(Main.cX, Main.cY);
        }

        if(possibleMoves != null && possibleMoves.size() > 0) {
            Board.Tile nextTile = possibleMoves.get(positiveRan(possibleMoves.size()));
            Main.nY = nextTile.y;
            Main.nX = nextTile.x;
            Main.makeMove(B);
        }
    }

    public static int positiveRan(int mod) {
        Random r = new Random();
        int ranNumb = r.nextInt();
        if (ranNumb < 0 ) {
            ranNumb *= -1;
        }
        ranNumb = ranNumb % mod;
        return ranNumb;
    }
}
