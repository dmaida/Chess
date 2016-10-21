import javafx.collections.MapChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.EventListener;

public class Controller {


    @FXML
    private GridPane gp;
    @FXML
    public Button[][] buttonMatrix;

    private final int row = 8;
    private final int col = 8;
    Board B;
    boolean firstClick = true;
    boolean secondClick = false;

    @FXML
    private void initialize() {

        B = new Board();

        Queen queen = new Queen(B, "Queen", true);
        King king = new King(B, "King", true);

        B.Tiles[7][4].isOccupied = true;
        B.Tiles[7][4].currentPiece = queen;

        B.Tiles[6][4].isOccupied = true;
        B.Tiles[6][4].currentPiece = king;

        makeButtons();
        updateView();
    }

    private void updateView() {

        Image img_bishop = new Image(getClass().getResourceAsStream("gui/w_bishop.png"), 30, 30, true, true);
        Image img_king = new Image(getClass().getResourceAsStream("gui/w_king.png"), 30, 30, true, true);
        Image img_knight = new Image(getClass().getResourceAsStream("gui/w_knight.png"), 30, 30, true, true);
        Image img_pawn = new Image(getClass().getResourceAsStream("gui/w_pawn.png"), 30, 30, true, true);
        Image img_queen = new Image(getClass().getResourceAsStream("gui/w_queen.png"), 30, 30, true, true);
        Image img_rook = new Image(getClass().getResourceAsStream("gui/w_rook.png"), 30, 30, true, true);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (B.Tiles[i][j].currentPiece != null) {
                    Piece p = B.Tiles[i][j].getPiece();
                    String name = p.name;
                   if ((name.compareTo("Queen") == 0)) {
                       buttonMatrix[i][j].setGraphic(new ImageView(img_queen));
                    }
                    else if (name.compareTo("King") == 0) {
                       buttonMatrix[i][j].setGraphic(new ImageView(img_king));
                   }
                   else if (name.compareTo("Rook") == 0) {
                       buttonMatrix[i][j].setGraphic(new ImageView(img_king));
                   }
                }
                else {
                    buttonMatrix[i][j].setGraphic(null);
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
            row.setMaxHeight(50);
            row.setMinHeight(50);
            gp.getRowConstraints().add(row);

        }

        for (int i = 0; i < col; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setMaxWidth(50);
            column.setMinWidth(50);
            gp.getColumnConstraints().add(column);
        }

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                buttonMatrix[r][c] = new Button();
                buttonMatrix[r][c].setPrefSize(50, 50);
                gp.add(buttonMatrix[r][c], c, r);
                Button currentButton = buttonMatrix[r][c];
                buttonMatrix[r][c].setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {

                        int r = gp.getRowIndex(currentButton);
                        int c = gp.getColumnIndex(currentButton);

                        if (firstClick && B.Tiles[r][c].isOccupied) {
                            firstClick = false;
                            secondClick = true;
                            Main.cX = c;
                            Main.cY = r;
                        } else if (secondClick){
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
