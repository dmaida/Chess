import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    public static int cX, cY, nX, nY;
    public static boolean whiteTurn = true;
    public static boolean blackTurn = false;


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("chess.fxml"));
        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(root, 650, 650));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static ArrayList<Board.Tile> getListOfMoves(Board B) {
        Piece p = B.Tiles[cY][cX].getPiece();
        return p.getMoves(cX, cY);
    }
    private static void castling(Board B, Piece p){
        int dir = 1;
        Piece rook;

        if (p.name.contains("b")){
            dir = -1;
        }
        if (p.name.contains("King") && ((nX == cX + (2 * dir)) || (nX == cX - (2 * dir)))){
            if (nX == cX + (2 * dir)) {
                rook = B.Tiles[nY][nX + (dir * 1)].getPiece();
                B.Tiles[nY][nX + (dir * 1)].isOccupied = false;
                B.Tiles[nY][nX + (dir * 1)].currentPiece = null;

                B.Tiles[nY][nX - (dir * 1)].isOccupied = true;
                B.Tiles[nY][nX - (dir * 1)].currentPiece = rook;
            }
            else {
                rook = B.Tiles[nY][nX - (dir * 2)].getPiece();
                B.Tiles[nY][nX - (dir * 2)].isOccupied = false;
                B.Tiles[nY][nX - (dir * 2)].currentPiece = null;

                B.Tiles[nY][nX + (dir * 1)].isOccupied = true;
                B.Tiles[nY][nX + (dir * 1)].currentPiece = rook;
            }
        }
    }

    public static void check(Board B) {
        King blackKing;
        King whiteKing;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (B.Tiles[i][j].isOccupied) {
                    if (B.Tiles[i][j].getPiece().name.compareTo("w_King") == 0) {
                        whiteKing = (King) B.Tiles[i][j].getPiece();

                        if (whiteKing.getMoves(j, i).size() == 0 && !whiteKing.isItSafe(j, i) ) {
                            System.out.println("Black won");
                        }

                    }
                    else if (B.Tiles[i][j].getPiece().name.compareTo("b_King") == 0) {
                        blackKing = (King) B.Tiles[i][j].getPiece();

                        if (blackKing.getMoves(j, i).size() == 0 && !blackKing.isItSafe(j, i) ) {
                            System.out.println("White won");
                        }
                    }
                }
            }
        }
    }

    public static boolean takeTurn(Board B) {

        Piece p = B.Tiles[cY][cX].getPiece();

        if (whiteTurn && p.isWhite ) {
            if (makeMove(B)) {
                whiteTurn = !whiteTurn;
                blackTurn = !blackTurn;
            }
        }
        else if (blackTurn && !p.isWhite){
            if (makeMove(B)) {
                whiteTurn = !whiteTurn;
                blackTurn = !blackTurn;
            }
        }
        return false;
    }

    public static boolean makeMove(Board B) {

        Piece p = B.Tiles[cY][cX].getPiece();

        if(p.isValidMove(cX,cY,nX,nY)){

            if (p.name.contains("King")){
                castling(B, p);
            }
            B.Tiles[cY][cX].isOccupied = false;
            B.Tiles[cY][cX].currentPiece = null;

            B.Tiles[nY][nX].isOccupied = true;
            //p.isFirstMove = false;
            B.Tiles[nY][nX].currentPiece = p;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {launch(args);
    }
}
