import javafx.collections.MapChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.EventListener;

public class Controller {

    @FXML
    private GridPane gp;
    @FXML
    private Button[][] buttonMatrix;

    @FXML
    private BorderPane bp;

    private final int row = 8;
    private final int col = 8;
    private Board B;
    private boolean firstClick = true;
    private boolean secondClick = false;

    @FXML
    private void initialize() {
        initializeGame();
        makeButtons();
        updateView();
    }

    private void initializeGame() {

        B = new Board();

        Bishop w_bishop_0 = new Bishop(B, "w_Bishop", true, 7, 2);
        Bishop w_bishop_1 = new Bishop(B, "w_Bishop", true, 7, 5);

        King w_king = new King(B, "w_King", true, 7, 4);
        Knight w_knight_0 = new Knight(B, "w_Knight", true, 7, 1);
        Knight w_knight_1 = new Knight(B, "w_Knight", true, 7, 6);

        Pawn w_pawn_0 = new Pawn(B, "w_Pawn", true, 6, 0);
        Pawn w_pawn_1 = new Pawn(B, "w_Pawn", true, 6, 1);
        Pawn w_pawn_2 = new Pawn(B, "w_Pawn", true, 6, 2);
        Pawn w_pawn_3 = new Pawn(B, "w_Pawn", true, 6, 3);
        Pawn w_pawn_4 = new Pawn(B, "w_Pawn", true, 6, 4);
        Pawn w_pawn_5 = new Pawn(B, "w_Pawn", true, 6, 5);
        Pawn w_pawn_6 = new Pawn(B, "w_Pawn", true, 6, 6);
        Pawn w_pawn_7 = new Pawn(B, "w_Pawn", true, 6, 7);

        Queen w_queen = new Queen(B, "w_Queen", true, 7, 3);

        Rook w_rook_0 = new Rook(B, "w_Rook", true, 7, 0);
        Rook w_rook_1 = new Rook(B, "w_Rook", true, 7, 7);

        B.Tiles[7][2].currentPiece = w_bishop_0;
        B.Tiles[7][5].currentPiece = w_bishop_1;

        B.Tiles[7][4].currentPiece = w_king;

        B.Tiles[7][1].currentPiece = w_knight_0;
        B.Tiles[7][6].currentPiece = w_knight_1;

        B.Tiles[6][0].currentPiece = w_pawn_0;
        B.Tiles[6][1].currentPiece = w_pawn_1;
        B.Tiles[6][2].currentPiece = w_pawn_2;
        B.Tiles[6][3].currentPiece = w_pawn_3;
        B.Tiles[6][4].currentPiece = w_pawn_4;
        B.Tiles[6][5].currentPiece = w_pawn_5;
        B.Tiles[6][6].currentPiece = w_pawn_6;
        B.Tiles[6][7].currentPiece = w_pawn_7;

        B.Tiles[7][3].currentPiece = w_queen;

        B.Tiles[7][0].currentPiece = w_rook_0;
        B.Tiles[7][7].currentPiece = w_rook_1;

        Bishop b_bishop_0 = new Bishop(B, "b_Bishop", false, 0, 2);
        Bishop b_bishop_1 = new Bishop(B, "b_Bishop", false, 0, 5);

        King b_king = new King(B, "b_King", false, 0, 3);
        Knight b_knight_0 = new Knight(B, "b_Knight", false, 0, 1);
        Knight b_knight_1 = new Knight(B, "b_Knight", false, 0, 6);

        Pawn b_pawn_0 = new Pawn(B, "b_Pawn", false, 1, 0);
        Pawn b_pawn_1 = new Pawn(B, "b_Pawn", false, 1, 1);
        Pawn b_pawn_2 = new Pawn(B, "b_Pawn", false, 1, 2);
        Pawn b_pawn_3 = new Pawn(B, "b_Pawn", false, 1, 3);
        Pawn b_pawn_4 = new Pawn(B, "b_Pawn", false, 1, 4);
        Pawn b_pawn_5 = new Pawn(B, "b_Pawn", false, 1, 5);
        Pawn b_pawn_6 = new Pawn(B, "b_Pawn", false, 1, 6);
        Pawn b_pawn_7 = new Pawn(B, "b_Pawn", false, 1, 7);

        Queen b_queen = new Queen(B, "b_Queen", false, 0, 4);

        Rook b_rook_0 = new Rook(B, "b_Rook", false, 0, 0);
        Rook b_rook_1 = new Rook(B, "b_Rook", false, 0, 7);

        B.Tiles[0][2].currentPiece = b_bishop_0;
        B.Tiles[0][5].currentPiece = b_bishop_1;

        B.Tiles[0][3].currentPiece = b_king;

        B.Tiles[0][1].currentPiece = b_knight_0;
        B.Tiles[0][6].currentPiece = b_knight_1;

        B.Tiles[1][0].currentPiece = b_pawn_0;
        B.Tiles[1][1].currentPiece = b_pawn_1;
        B.Tiles[1][2].currentPiece = b_pawn_2;
        B.Tiles[1][3].currentPiece = b_pawn_3;
        B.Tiles[1][4].currentPiece = b_pawn_4;
        B.Tiles[1][5].currentPiece = b_pawn_5;
        B.Tiles[1][6].currentPiece = b_pawn_6;
        B.Tiles[1][7].currentPiece = b_pawn_7;

        B.Tiles[0][4].currentPiece = b_queen;

        B.Tiles[0][0].currentPiece = b_rook_0;
        B.Tiles[0][7].currentPiece = b_rook_1;
    }

    private void updateView() {

        int height = 50;
        int width = 50;

        Image w_bishop = new Image(getClass().getResourceAsStream("gui/w_bishop.png"), width, height, true, true);
        Image w_king = new Image(getClass().getResourceAsStream("gui/w_king.png"), width, height, true, true);
        Image w_knight = new Image(getClass().getResourceAsStream("gui/w_knight.png"), width, height, true, true);
        Image w_pawn = new Image(getClass().getResourceAsStream("gui/w_pawn.png"), width, height, true, true);
        Image w_queen = new Image(getClass().getResourceAsStream("gui/w_queen.png"), width, height, true, true);
        Image w_rook = new Image(getClass().getResourceAsStream("gui/w_rook.png"), width, height, true, true);

        Image b_bishop = new Image(getClass().getResourceAsStream("gui/b_bishop.png"), width, height, true, true);
        Image b_king = new Image(getClass().getResourceAsStream("gui/b_king.png"), width, height, true, true);
        Image b_knight = new Image(getClass().getResourceAsStream("gui/b_knight.png"), width, height, true, true);
        Image b_pawn = new Image(getClass().getResourceAsStream("gui/b_pawn.png"), width, height, true, true);
        Image b_queen = new Image(getClass().getResourceAsStream("gui/b_queen.png"), width, height, true, true);
        Image b_rook = new Image(getClass().getResourceAsStream("gui/b_rook.png"), width, height, true, true);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (B.Tiles[i][j].currentPiece != null) {
                    Piece p = B.Tiles[i][j].getPiece();
                    String name = p.name;
                   if ((name.compareTo("w_Bishop") == 0)) {
                       buttonMatrix[i][j].setGraphic(new ImageView(w_bishop));
                    }
                    else if (name.compareTo("w_King") == 0) {
                       buttonMatrix[i][j].setGraphic(new ImageView(w_king));
                   }
                   else if (name.compareTo("w_Knight") == 0) {
                       buttonMatrix[i][j].setGraphic(new ImageView(w_knight));
                   }
                   else if (name.compareTo("w_Pawn") == 0) {
                       buttonMatrix[i][j].setGraphic(new ImageView(w_pawn));
                   }
                   else if (name.compareTo("w_Queen") == 0) {
                       buttonMatrix[i][j].setGraphic(new ImageView(w_queen));
                   }
                   else if (name.compareTo("w_Rook") == 0) {
                       buttonMatrix[i][j].setGraphic(new ImageView(w_rook));
                   }
                   else if ((name.compareTo("b_Bishop") == 0)) {
                       buttonMatrix[i][j].setGraphic(new ImageView(b_bishop));
                   }
                   else if (name.compareTo("b_King") == 0) {
                       buttonMatrix[i][j].setGraphic(new ImageView(b_king));
                   }
                   else if (name.compareTo("b_Knight") == 0) {
                       buttonMatrix[i][j].setGraphic(new ImageView(b_knight));
                   }
                   else if (name.compareTo("b_Pawn") == 0) {
                       buttonMatrix[i][j].setGraphic(new ImageView(b_pawn));
                   }
                   else if (name.compareTo("b_Queen") == 0) {
                       buttonMatrix[i][j].setGraphic(new ImageView(b_queen));
                   }
                   else if (name.compareTo("b_Rook") == 0) {
                       buttonMatrix[i][j].setGraphic(new ImageView(b_rook));
                   }
                }
                else {
                    buttonMatrix[i][j].setGraphic(null);
                }
            }
        }
    }

    private void drawMoves(ArrayList<Board.Tile> moveList) {
        for (int i = 0; i < moveList.size(); i++) {
            int x = moveList.get(i).x;
            int y = moveList.get(i).y;
            buttonMatrix[y][x].setStyle("-fx-background-color: #FF0000");
        }
    }

    private void eraseMoves() {

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if ((r) % 2 == 0 && (c + 1) % 2 == 0) {
                    buttonMatrix[r][c].setStyle("-fx-background-color: #808080");
                } else if ((r + 1) % 2 == 0 && (c) % 2 == 0) {
                    buttonMatrix[r][c].setStyle("-fx-background-color: #808080");
                } else {
                    buttonMatrix[r][c].setStyle("-fx-background-color: #ffffff");
                }
            }
        }
    }

    public void makeButtons ( ) {

        int width = 1500;
        int height = 1000;

        Image desktopBG = new Image(getClass().getResourceAsStream("gui/desktop.jpg"), width, height, true, true);

        BackgroundImage backgroundOne= new BackgroundImage(desktopBG,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        bp.setBackground(new Background(backgroundOne));


        gp.getColumnConstraints().removeAll(gp.getColumnConstraints());
        gp.getRowConstraints().removeAll(gp.getRowConstraints());
        gp.getChildren().removeAll(gp.getChildren());
        buttonMatrix = new Button[row][col];


        for (int i = 0; i < row; i++) {
            RowConstraints row = new RowConstraints();
            row.setMaxHeight(70);
            row.setMinHeight(70);
            gp.getRowConstraints().add(row);

        }

        for (int i = 0; i < col; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setMaxWidth(70);
            column.setMinWidth(70);
            gp.getColumnConstraints().add(column);
        }

        gp.setVgap(0);
        gp.setHgap(0);

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                buttonMatrix[r][c] = new Button();
                buttonMatrix[r][c].setPrefSize(70, 70);
                gp.add(buttonMatrix[r][c], c, r);
                Button currentButton = buttonMatrix[r][c];

                if ((r) % 2 == 0 && (c+1) % 2 == 0) {
                    buttonMatrix[r][c].setStyle("-fx-background-color: grey");
                }else if ((r+1) % 2 == 0 && (c) % 2 == 0) {
                    buttonMatrix[r][c].setStyle("-fx-background-color: grey");
                }
                else {
                    buttonMatrix[r][c].setStyle("-fx-background-color: white");
                }

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
                            drawMoves(Main.getListOfMoves(B));
                        } else if (secondClick){
                            firstClick = true;
                            secondClick = false;
                            int row = gp.getRowIndex(currentButton);
                            int col = gp.getColumnIndex(currentButton);
                            Main.nX = col;
                            Main.nY = row;
                            eraseMoves();
                            Main.makeMove(B);
                        }
                        updateView();

                    }
                });
            }
        }
    }
}
