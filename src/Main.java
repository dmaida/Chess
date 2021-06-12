import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Main extends Application {

    protected static int cX, cY, nX, nY;

    protected static double windowSize = 1.45;

    protected static boolean isAIOn = false;

    protected static Stack<Board> memory = new Stack<>();

    public static ArrayList<Board.Tile> getListOfMoves(Board B) {
        Piece p = B.Tiles[cY][cX].getPiece();
        return p.getMoves(cX, cY);
    }

    private static void castling(Board B, Piece p) {
        int dir = 1;
        Piece rook;

        if (p.name.contains("b")) {
            dir = -1;
        }
        if (p.name.contains("King") && ((nX == cX + (2 * dir)) || (nX == cX - (2 * dir)))) {
            if (nX == cX + (2 * dir)) {
                rook = B.Tiles[nY][nX + (dir * 1)].getPiece();
                B.Tiles[nY][nX + (dir * 1)].isOccupied = false;
                B.Tiles[nY][nX + (dir * 1)].currentPiece = null;

                B.Tiles[nY][nX - (dir * 1)].isOccupied = true;
                rook.isFirstMove = false;
                rook.globalX = nX - (dir * 1);
                B.Tiles[nY][nX - (dir * 1)].currentPiece = rook;
            } else {
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
                if (B.Tiles[i][j].isOccupied && isWhite == B.Tiles[i][j].getPiece().isWhite) {
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

                        if (!whiteKing.isItSafe(j, i)) {
                            if (checkmate(B, true)) {
                                return true;
                            }
                        }
                    }
                    if (B.Tiles[i][j].getPiece().name.compareTo("b_King") == 0) {
                        blackKing = (King) B.Tiles[i][j].getPiece();
                        if (!blackKing.isItSafe(j, i)) {
                            if (checkmate(B, false)) {
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

        if (B.whiteTurn && p.isWhite) {
            if (makeMove(B)) {
                B.whiteTurn = !B.whiteTurn;
                B.blackTurn = !B.blackTurn;

                if (isAIOn) {
                    AI.choosePiece(B);
                    B.whiteTurn = !B.whiteTurn;
                    B.blackTurn = !B.blackTurn;
                    return false;
                }
            }

        } else if (B.blackTurn && !p.isWhite) {
            if (makeMove(B)) {
                B.whiteTurn = !B.whiteTurn;
                B.blackTurn = !B.blackTurn;
            }
        }
        return false;
    }

    public static void print(Board B) {
        System.out.print("   ");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + " ");
        }

        System.out.println();
        for (int i = 0; i < 8; i++) {
            System.out.print(i + " ");
            System.out.print("|");
            for (int j = 0; j < 8; j++) {
                if (B.Tiles[i][j].isOccupied && (B.Tiles[i][j].getPiece().isWhite == true)) {
                    System.out.print("W|");
                } else if (B.Tiles[i][j].isOccupied && (B.Tiles[i][j].getPiece().isWhite == false)) {
                    System.out.print("B|");
                } else
                    System.out.print("_|");
            }
            System.out.println();
        }
    }

    private static Board clone(Board B) {
        Board newBoard = new Board();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (B.Tiles[i][j].isOccupied) {
                    newBoard.Tiles[i][j].isOccupied = true;
                    newBoard.Tiles[i][j].x = j;
                    newBoard.Tiles[i][j].y = i;
                    Piece p = clonePiece(B.Tiles[i][j].getPiece(), newBoard);
                    p.globalY = i;
                    p.globalX = j;
                    newBoard.Tiles[i][j].currentPiece = p;
                } else {
                    newBoard.Tiles[i][j].isOccupied = false;
                }
            }
        }
        return newBoard;
    }

    private static Piece clonePiece(Piece oldPiece, Board B) {
        int y = oldPiece.globalY;
        int x = oldPiece.globalX;
        boolean isWhite = oldPiece.isWhite;
        String name = oldPiece.name;
        boolean isFirstMove = oldPiece.isFirstMove;

        if (oldPiece.name.contains("King")) {
            King w_king = new King(B, name, isWhite, y, x);
            w_king.isFirstMove = isFirstMove;
            return w_king;
        }
        if (oldPiece.name.contains("Bishop")) {
            Bishop w_bishop = new Bishop(B, name, isWhite, y, x);
            w_bishop.isFirstMove = isFirstMove;
            return w_bishop;
        }
        if (oldPiece.name.contains("Knight")) {
            Knight w_knight = new Knight(B, name, isWhite, y, x);
            w_knight.isFirstMove = isFirstMove;
            return w_knight;
        }
        if (oldPiece.name.contains("Rook")) {
            Rook w_rook = new Rook(B, name, isWhite, y, x);
            w_rook.isFirstMove = isFirstMove;
            return w_rook;
        }
        if (oldPiece.name.contains("Pawn")) {
            Pawn w_pawn = new Pawn(B, name, isWhite, y, x);
            w_pawn.isFirstMove = isFirstMove;
            return w_pawn;
        }
        if (oldPiece.name.contains("Queen")) {
            Queen w_queen = new Queen(B, name, isWhite, y, x);
            w_queen.isFirstMove = isFirstMove;
            return w_queen;
        }
        return null;
    }

    public static boolean makeMove(Board B) {
        Piece p = B.Tiles[cY][cX].getPiece();

        p.getMoves(cX, cY);
        if (p.isValidMove(cX, cY, nX, nY)) {
            memory.push(clone(B));
            if (p.name.contains("King")) {
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

    public static Board undo(Board oldB) {
        if (memory.size() == 0) {
            return oldB;
        }
        Board B = memory.pop();
        if (oldB.blackTurn) {
            B.blackTurn = false;
            B.whiteTurn = true;
        } else {
            B.blackTurn = true;
            B.whiteTurn = false;
        }
        return B;
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("chess.fxml"));
        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(root, 650 * windowSize, 650 * windowSize));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
