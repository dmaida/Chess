import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Stack;

public class Main extends Application {

    protected static int cX, cY, nX, nY;
    protected static boolean whiteTurn = true;
    protected static boolean blackTurn = false;

    protected static Stack<Board> memory = null;

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

    public static boolean checkmate(Board B, boolean isWhite) {
        boolean checkmate = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (B.Tiles[i][j].isOccupied && isWhite ==  B.Tiles[i][j].getPiece().isWhite) {
                    Piece p = B.Tiles[i][j].getPiece();
                    if (p.getMoves(j, i).size() > 0) {
                        checkmate = false;
                    }
                }
            }
        }
        return checkmate;
    }

    public static boolean check(Board B) {
        King blackKing;
        King whiteKing;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (B.Tiles[i][j].isOccupied) {
                    if (B.Tiles[i][j].getPiece().name.compareTo("w_King") == 0) {
                        whiteKing = (King) B.Tiles[i][j].getPiece();

                        if (!whiteKing.isItSafe(j, i) ) {
                            System.out.println("Check: White");
                            if (checkmate(B, true)) {
                                System.out.println("Checkmate: Black won");
                                return true;
                            }
                        }

                    }
                    if (B.Tiles[i][j].getPiece().name.compareTo("b_King") == 0) {
                        blackKing = (King) B.Tiles[i][j].getPiece();

                        if (!blackKing.isItSafe(j, i) ) {
                            System.out.println("Check: Black");
                            if (checkmate(B, false)) {
                                System.out.println("Checkmate: White won");
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
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

    private static Board clone(Board B) {
        Board newBoard = new Board();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                newBoard.Tiles[i][j].currentPiece = B.Tiles[i][j].getPiece();
                newBoard.Tiles[i][j].isOccupied = B.Tiles[i][j].isOccupied;
                newBoard.Tiles[i][j].x = B.Tiles[i][j].x;
                newBoard.Tiles[i][j].y = B.Tiles[i][j].y;
            }
        }
        return newBoard;
    }

    public static boolean makeMove(Board B) {

        if (memory == null) {
            memory = new Stack<>();
        }

        memory.push(clone(B));

        Piece p = B.Tiles[cY][cX].getPiece();

        if(p.isValidMove(cX,cY,nX,nY)){

            if (p.name.contains("King")){
                castling(B, p);
            }
            B.Tiles[cY][cX].isOccupied = false;
            B.Tiles[cY][cX].currentPiece = null;

            B.Tiles[nY][nX].isOccupied = true;
            p.isFirstMove = false;
            p.globalX = nX;
            p.globalY = nY;
            B.Tiles[nY][nX].currentPiece = p;

            return true;
        }
        return false;
    }

    public static Board undo() {
        Board B = null;
        if (memory != null && memory.size() > 0) {
            B = memory.pop();
        }
        return B;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("chess.fxml"));
        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(root, 650, 650));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {launch(args);
    }
}
