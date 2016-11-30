import javafx.collections.MapChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.*;

public class Controller {

    @FXML
    private GridPane gp;
    @FXML
    private Button[][] buttonMatrix;
    @FXML
    private BorderPane bp;
    @FXML
    private MenuItem undo;
    @FXML
    private MenuItem reset;
    @FXML
    private MenuItem theme;

    private final int row = 8;
    private final int col = 8;
    protected Board B;
    private boolean firstClick = true;
    private boolean secondClick = false;
    private int themeNumb = 0;

    private double windowSize = 1.45;

    double height = 50 *windowSize;
    double width = 50 * windowSize;

    private Image w_bishop = new Image(getClass().getResourceAsStream("gui/w_bishop.png"), width, height, true, true);
    private Image w_king = new Image(getClass().getResourceAsStream("gui/w_king.png"), width, height, true, true);
    private Image w_knight = new Image(getClass().getResourceAsStream("gui/w_knight.png"), width, height, true, true);
    private Image w_pawn = new Image(getClass().getResourceAsStream("gui/w_pawn.png"), width, height, true, true);
    private Image w_queen = new Image(getClass().getResourceAsStream("gui/w_queen.png"), width, height, true, true);
    private Image w_rook = new Image(getClass().getResourceAsStream("gui/w_rook.png"), width, height, true, true);

    private Image b_bishop = new Image(getClass().getResourceAsStream("gui/b_bishop.png"), width, height, true, true);
    private Image b_king = new Image(getClass().getResourceAsStream("gui/b_king.png"), width, height, true, true);
    private Image b_knight = new Image(getClass().getResourceAsStream("gui/b_knight.png"), width, height, true, true);
    private Image b_pawn = new Image(getClass().getResourceAsStream("gui/b_pawn.png"), width, height, true, true);
    private Image b_queen = new Image(getClass().getResourceAsStream("gui/b_queen.png"), width, height, true, true);
    private Image b_rook = new Image(getClass().getResourceAsStream("gui/b_rook.png"), width, height, true, true);

    @FXML
    private void initialize() {
        initializeGame();
        makeButtons();
        updateView();
    }
    @FXML
    public void initializeGame() {

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
    @FXML
    private void undoMove() {
        B = Main.undo(B);
        updateView();
    }

    @FXML
    private void resetGame() {
        firstClick = true;
        secondClick = false;
        Main.memory = new Stack<>();
        setTheme();
        initializeGame();
        updateView();
    }

    @FXML
    private void exitChess() {
        System.exit(0);
    }

    private void drawMoves(ArrayList<Board.Tile> moveList, int cX, int cY) {
        selected(cX, cY);

        for (int i = 0; i < moveList.size(); i++) {
            int x = moveList.get(i).x;
            int y = moveList.get(i).y;
            buttonMatrix[y][x].setStyle("-fx-background-color: #cc0000");
        }
    }

    private void selected(int cX, int cY) {

        if (B.Tiles[cY][cX].isOccupied) {
            buttonMatrix[cY][cX].setStyle("-fx-background-color: #990000");
        }

    }

    private boolean pawnReachedEnd() {
        for (int i = 0; i < col; i++) {
            if (B.Tiles[0][i].isOccupied && B.Tiles[0][i].currentPiece.name.compareTo("w_Pawn") == 0) {
                return true;
            }
        }

        for (int i = 0; i < col; i++) {
            if (B.Tiles[7][i].isOccupied && B.Tiles[7][i].currentPiece.name.compareTo("b_Pawn") == 0) {
                return true;
            }
        }
        return false;
    }

    private void choosePiece(int x, int y) {
        List<String> choices = new ArrayList<>();
        choices.add("Bishop");
        choices.add("Knight");
        choices.add("Queen");
        choices.add("Rook");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Pieces", choices);

        dialog.setTitle("Chess");
        dialog.setHeaderText("Pawn reached opposite side.");
        dialog.setContentText("Choose your piece:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){

            if (result.get().contains("Bishop")) {
                if (B.Tiles[y][x].getPiece().isWhite) {
                    B.Tiles[y][x].currentPiece = new Bishop(B, "w_Bishop", true, y, x);
                } else {
                    B.Tiles[y][x].currentPiece = new Bishop(B, "b_Bishop", false, y, x);
                }
            }
            if (result.get().contains("Knight")) {
                if (B.Tiles[y][x].getPiece().isWhite) {
                    B.Tiles[y][x].currentPiece = new Knight(B, "w_Knight", true, y, x);
                } else {
                    B.Tiles[y][x].currentPiece = new Knight(B, "b_Knight", false, y, x);
                }
            }
            if (result.get().contains("Queen")) {
                if (B.Tiles[y][x].getPiece().isWhite) {
                    B.Tiles[y][x].currentPiece = new Queen(B, "w_Queen", true, y, x);
                } else {
                    B.Tiles[y][x].currentPiece = new Queen(B, "b_Queen", false, y, x);
                }
            }

            if (result.get().contains("Rook")) {
                if (B.Tiles[y][x].getPiece().isWhite) {
                    B.Tiles[y][x].currentPiece = new Rook(B, "w_Rook", true, y, x);
                } else {
                    B.Tiles[y][x].currentPiece = new Rook(B, "b_Rook", false, y, x);
                }
            }
        }
    }

    private void setTheme() {

        if (themeNumb == 0) {
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if ((r) % 2 == 0 && (c+1) % 2 == 0) {
                        buttonMatrix[r][c].setStyle("-fx-background-color: grey");
                    }else if ((r+1) % 2 == 0 && (c) % 2 == 0) {
                        buttonMatrix[r][c].setStyle("-fx-background-color: grey");
                    }
                    else {
                        buttonMatrix[r][c].setStyle("-fx-background-color: white");
                    }
                }
            }
        }

        else if (themeNumb == 1) {
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if ((r) % 2 == 0 && (c+1) % 2 == 0) {
                        buttonMatrix[r][c].setStyle("-fx-background-color: d08c47");
                    }else if ((r+1) % 2 == 0 && (c) % 2 == 0) {
                        buttonMatrix[r][c].setStyle("-fx-background-color: d08c47");
                    }
                    else {
                        buttonMatrix[r][c].setStyle("-fx-background-color: fece9e");
                    }
                }
            }
        }
    }

    private void makeButtons ( ) {

        double width = 1500 * windowSize;
        double height = 1000 * windowSize;

        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION,"", ButtonType.OK, ButtonType.CANCEL);
                alert.setTitle("Chess");
                alert.setContentText("Press OK to reset.");
                alert.setHeaderText("Are you sure you want to reset game?");
                alert.showAndWait();
                alert.getResult();

                if (alert.getResult().equals(ButtonType.OK)) {
                    resetGame();
                }
            }
        });

        theme.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (themeNumb == 0) {

                    Image desktopBG = new Image(getClass().getResourceAsStream("gui/desktop.jpg"), width, height, true, true);

                    BackgroundImage backgroundOne = new BackgroundImage(desktopBG,
                            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                            BackgroundSize.DEFAULT);
                    bp.setBackground(new Background(backgroundOne));

                    themeNumb++;
                }
                else if (themeNumb > 0) {
                    Image desktopBG = new Image(getClass().getResourceAsStream("gui/desktop2.jpg"), width, height, true, true);

                    BackgroundImage backgroundOne = new BackgroundImage(desktopBG,
                            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                            BackgroundSize.DEFAULT);
                    bp.setBackground(new Background(backgroundOne));
                    themeNumb = 0;
                }
                setTheme();
            }
        });

        Image desktopBG = new Image(getClass().getResourceAsStream("gui/desktop.jpg"), width, height, true, true);

        BackgroundImage backgroundOne = new BackgroundImage(desktopBG,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        bp.setBackground(new Background(backgroundOne));
        themeNumb++;

        gp.getColumnConstraints().removeAll(gp.getColumnConstraints());
        gp.getRowConstraints().removeAll(gp.getRowConstraints());
        gp.getChildren().removeAll(gp.getChildren());
        buttonMatrix = new Button[row][col];

        for (int i = 0; i < row; i++) {
            RowConstraints row = new RowConstraints();
            row.setMaxHeight(70 * windowSize);
            row.setMinHeight(70 * windowSize);
            gp.getRowConstraints().add(row);

        }
        for (int i = 0; i < col; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setMaxWidth(70 * windowSize);
            column.setMinWidth(70 * windowSize);
            gp.getColumnConstraints().add(column);
        }

        gp.setVgap(0);
        gp.setHgap(0);

        Rectangle rec = new Rectangle();
        rec.setHeight(70 * windowSize);
        rec.setWidth(70 * windowSize);
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                buttonMatrix[r][c] = new Button();
                buttonMatrix[r][c].setShape(rec);
                buttonMatrix[r][c].setPrefSize(70*windowSize, 70 * windowSize);
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
                            selected(c, r);
                            if (B.whiteTurn && B.Tiles[r][c].getPiece().isWhite ) {
                                drawMoves(Main.getListOfMoves(B), c, r);
                            } else if (B.blackTurn && !B.Tiles[r][c].getPiece().isWhite){
                                drawMoves(Main.getListOfMoves(B), c, r);
                            }
                        } else if (secondClick){
                            int row = gp.getRowIndex(currentButton);
                            int col = gp.getColumnIndex(currentButton);
                            Main.nX = col;
                            Main.nY = row;
                            firstClick = true;
                            secondClick = false;
                            setTheme();
                            Main.takeTurn(B);
                        }
                        if (pawnReachedEnd()) {
                            choosePiece(c, r);
                        }
                        Main.check(B);
                        updateView();
                    }
                });
            }
        }
        setTheme();
    }
}
