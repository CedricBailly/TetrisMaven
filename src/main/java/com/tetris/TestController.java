package com.tetris;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class TestController implements Initializable {

    @FXML
    private ImageView tetrisTitle;

    @FXML
    private GridPane nextPieceBoard;

    @FXML
    private GridPane visualBoard;

    @FXML
    private Button pauseButton;

    @FXML
    private Label score;

    private Board board;
    private AnimationTimer tetrisGame;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tetrisTitle.setImage(new Image(getClass().getResourceAsStream("tetrisTitle.jpg")));
        board = new Board();
        render();
        tetrisGame.start();
    }

    public void drawCase(Node node, Board board, int[][] grid, String state) {
        Integer colIndex = GridPane.getColumnIndex(node);
        Integer rowIndex = GridPane.getRowIndex(node);
        if (node instanceof Canvas) {
            int value = Board.valueAt(rowIndex, colIndex, grid);
            GraphicsContext gc = ((Canvas) node).getGraphicsContext2D();
            if (value == 0) {
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, 20, 20);
            } else if (value == 1) {
                if (state.equals("current")) {
                    gc.setFill(board.getCurrentPiece().getTetrominoes().getColor());
                } else {
                    gc.setFill(board.getNextPiece().getTetrominoes().getColor());
                }
                gc.fillRoundRect(0, 0, 20, 20, 10, 10);

            } else if (value == -1) {
                gc.setFill(Color.WHITE);
                gc.fillRoundRect(0, 0, 20, 20, 10, 10);
            }
        }
    }

    public void render() {

        board.spawnPiece();

        tetrisGame = new AnimationTimer() {
            private long lastUpdate;

            @Override
            public void handle(long l) {
                if (!board.getGameIsOver()) {
                    for (Node node : visualBoard.getChildren()) {
                        drawCase(node, board, board.getGameBoard(), "current");
                    }

                    for (Node node : nextPieceBoard.getChildren()) {
                        drawCase(node, board, board.getNextPiece().getTetrominoes().getShape(), "next");
                    }

                    score.setText(String.valueOf(board.getScore()));
                    if ((l - lastUpdate) >= 1000000000) {
                        board.move("down");
                        lastUpdate = l;
                    }
                    board.isGameOver();
                } else {
                    this.stop();
                }
            }
        };
    }

    public void reset(MouseEvent mouseEvent) {
        board.reset();
        board.spawnPiece();
        board.setGameIsOver(false);
        if (pauseButton.getText().equals("Continue")) pauseButton.setText("Pause");
        tetrisGame.start();
    }

    public void keyMove(KeyEvent keyEvent) {
        if (!board.getGameIsOver()) {
            switch (keyEvent.getCode()) {
                case D:
                    board.move("right");
                    break;
                case Q:
                    board.move("left");
                    break;
                case S:
                    board.move("down");
                    break;
                case Z:
                    board.rotate();
                    break;
                case C:
                    board.dropPiece();
                    break;
            }
        }
    }

    public void pauseGame(MouseEvent mouseEvent) {
        if (!board.getGameIsOver()) {
            pauseButton.setText("Continue");
            board.setGameIsOver(true);
            tetrisGame.stop();
        } else {
            pauseButton.setText("Pause");
            board.setGameIsOver(false);
            tetrisGame.start();
        }
    }
}
