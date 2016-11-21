import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    public static int cX, cY, nX, nY;


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
        if (p.name.contains("King") && ((nX == cX + (2 * dir)) | (nX == cX - (2 * dir)))){
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

    public static void makeMove(Board B) {

        Piece p = B.Tiles[cY][cX].getPiece();

        castling(B, p);

        if(p.isValidMove(cX,cY,nX,nY)){
            B.Tiles[cY][cX].isOccupied = false;
            B.Tiles[cY][cX].currentPiece = null;

            B.Tiles[nY][nX].isOccupied = true;
            B.Tiles[nY][nX].currentPiece = p;

        }
    }

    public static void main(String[] args) {launch(args);
    }
}
