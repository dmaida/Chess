import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class Controller {


    @FXML
    private GridPane gp;
    @FXML
    public Button[][] buttonMatrix;

    private int row = 8;
    private int col = 8;
    Board B;
    boolean firstClick = true;
    boolean secondClick = false;

    @FXML
    private void initialize() {
        B = new Board();
        /*Bishop piece1 = new Bishop();
        piece1.isWhite = false;

        Bishop piece2 = new Bishop();
        piece2.isWhite = true;

        Pawn pawn = new Pawn();
        pawn.isWhite = true;

        Rook rook = new Rook();
        rook.isWhite = false;*/

        Queen queen = new Queen();
        queen.isWhite = false;

        queen.chessBoard = B;
        B.Tiles[5][5].isOccupied = true;
        B.Tiles[5][5].currentPiece = queen;

        /*rook.chessBoard = B;
        B.Tiles[1][1].isOccupied = true;
        B.Tiles[1][1].currentPiece = rook;

        pawn.chessBoard = B;
        B.Tiles[2][2].isOccupied = true;
        B.Tiles[2][2].currentPiece = pawn;

        piece1.chessBoard = B;
        B.Tiles[5][3].isOccupied = true;
        B.Tiles[5][3].currentPiece = piece1;

        piece2.chessBoard = B;
        B.Tiles[1][4].isOccupied = true;
        B.Tiles[1][4].currentPiece = piece2;*/



        makeButtons();
        updateView();
    }

    private void updateView() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                buttonMatrix[i][j].setText("");
                if (B.Tiles[i][j].getPiece() != null &&  B.Tiles[i][j].getPiece().isWhite) {
                    buttonMatrix[i][j].setText("w");
                } else if (B.Tiles[i][j].getPiece() != null &&  (B.Tiles[i][j].getPiece().isWhite==false)){
                    buttonMatrix[i][j].setText("b");
                }
            }
        }
    }


    public void makeButtons ( ) {

        gp.getColumnConstraints().removeAll(gp.getColumnConstraints());
        gp.getRowConstraints().removeAll(gp.getRowConstraints());
        gp.getChildren().removeAll(gp.getChildren());
        buttonMatrix = new Button[row][col];


        for (int i = 0; i < row; i++) {
            RowConstraints row = new RowConstraints();
            row.setMaxHeight(30);
            row.setMinHeight(30);
            gp.getRowConstraints().add(row);
        }

        for (int i = 0; i < col; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setMaxWidth(30);
            column.setMinWidth(30);
            gp.getColumnConstraints().add(column);
        }

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                buttonMatrix[r][c] = new Button();
                buttonMatrix[r][c].setPrefSize(30, 30);
                gp.add(buttonMatrix[r][c], c, r);
                Button currentButton = buttonMatrix[r][c];
                buttonMatrix[r][c].setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {

                        int r = gp.getRowIndex(currentButton);
                        int c = gp.getColumnIndex(currentButton);

                        if (firstClick && B.Tiles[r][c].isOccupied) {
                            //System.out.println("["+r+"]["+c+"] Click 1 made");
                            firstClick = false;
                            secondClick = true;
                            Main.cX = c;
                            Main.cY = r;
                        } else if (secondClick){
                            //System.out.println("["+r+"]["+c+"] Click 2 made");
                            //if(B.Tiles[r][c].isOccupied) System.out.println("That tile is occupied");
                            firstClick = true;
                            secondClick = false;

                            int row = gp.getRowIndex(currentButton);
                            int col = gp.getColumnIndex(currentButton);
                            Main.nX = col;
                            Main.nY = row;
                            Main.makeMove(B);
                        }

                        Main.print(B);
                        updateView();

                    }
                });
            }
        }
    }
}
