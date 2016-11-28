import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Stack;

public class Main extends Application {

    protected static int cX, cY, nX, nY;

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
                rook.isFirstMove = false;
                rook.globalX = nX - (dir * 1);
                B.Tiles[nY][nX - (dir * 1)].currentPiece = rook;



            }
            else {
                rook = B.Tiles[nY][nX - (dir * 2)].getPiece();
                B.Tiles[nY][nX - (dir * 2)].isOccupied = false;
                B.Tiles[nY][nX - (dir * 2)].currentPiece = null;

                B.Tiles[nY][nX + (dir * 1)].isOccupied = true;
                rook.isFirstMove = false;
                rook.globalX = nX + (dir * 1);
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

        if (B.whiteTurn && p.isWhite ) {
            if (makeMove(B)) {
                B.whiteTurn = !B.whiteTurn;
                B.blackTurn = !B.blackTurn;
            }
        }
        else if (B.blackTurn && !p.isWhite){
            if (makeMove(B)) {
                B.whiteTurn = !B.whiteTurn;
                B.blackTurn = !B.blackTurn;
            }
        }
        return false;
    }
    public static void print(Board B){
        System.out.print("   ");
        for (int i = 0; i < 8; i++){
            System.out.print(i+" ");
        }

        System.out.println();
        for (int i = 0; i < 8; i++){
            System.out.print(i+" ");
            System.out.print("|");
            for (int j = 0; j < 8; j++) {
                if(B.Tiles[i][j].isOccupied && (B.Tiles[i][j].getPiece().isWhite ==true)){
                    System.out.print("W|");
                }
                else if(B.Tiles[i][j].isOccupied && (B.Tiles[i][j].getPiece().isWhite ==false)){
                    System.out.print("B|");
                }
                else System.out.print("_|");
            }
            System.out.println();
        }
    }

    private static Board clone(Board B) {
        Board newBoard = new Board();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (B.Tiles[i][j].isOccupied) {
                    newBoard.Tiles[i][j].currentPiece = clonePiece(B.Tiles[i][j].getPiece());
                    newBoard.Tiles[i][j].isOccupied = B.Tiles[i][j].isOccupied;
                    newBoard.Tiles[i][j].x = B.Tiles[i][j].x;
                    newBoard.Tiles[i][j].y = B.Tiles[i][j].y;
                }else {
                    newBoard.Tiles[i][j].isOccupied = false;
                }
            }
        }
        return newBoard;
    }

    private static Piece clonePiece(Piece p) {
        Piece newPiece = p;
        if (p != null) {
            newPiece.isWhite = p.isWhite;
            newPiece.globalY = p.globalY;
            newPiece.globalX = p.globalX;
            newPiece.isFirstMove = p.isFirstMove;
            newPiece.chessBoard = p.chessBoard;
            newPiece.moveList = p.moveList;
            newPiece.name = p.name;
            return newPiece;
        } else if (p == null) {
            return p;
        }
        return newPiece;
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
            print(B);
            return true;
        }
        return false;
    }

    public static Board undo() {
        Board B = null;
        if (memory != null && memory.size() > 0) {
            B = memory.pop();
        }
        print(B);
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