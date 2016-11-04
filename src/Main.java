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

    public static void makeMove(Board B) {

        Piece p = B.Tiles[cY][cX].getPiece();

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
