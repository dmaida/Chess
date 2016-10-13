import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static int cX, cY, nX, nY;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
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

    public static void makeMove(Board B) {

        Piece p = B.Tiles[cY][cX].getPiece();

        if(p.isValidMove(cX,cY,nX,nY)){
            B.Tiles[cY][cX].isOccupied = false;
            B.Tiles[cY][cX].currentPiece = null;

            B.Tiles[nY][nX].isOccupied = true;
            B.Tiles[nY][nX].currentPiece = p;
        }
    }


    public static void main(String[] args) {

        //launch(args);

        Board B = new Board();
        Pawn whitePawn = new Pawn();
        whitePawn.isWhite = true;

        Pawn blackPawn = new Pawn();
        blackPawn.isWhite = false;

/*        cX = 5;
        cY = 5;
        nX = 5;
        nY = 6;*/
        //System.out.println("("+cX+" , "+cY+")");

        whitePawn.chessBoard = B;
        B.Tiles[5][5].isOccupied = true;
        B.Tiles[5][5].currentPiece = whitePawn;

        blackPawn.chessBoard = B;
        B.Tiles[4][4].isOccupied = true;
        B.Tiles[4][4].currentPiece = blackPawn;

        print(B);

        cX = 5;
        cY = 5;
        nX = 4;
        nY = 4;

        makeMove(B);
        print(B);







    }
}
