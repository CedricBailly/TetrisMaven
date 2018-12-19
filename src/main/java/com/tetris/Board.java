package com.tetris;

import java.util.ArrayList;

public class Board {

    private final static int BOARD_LINES = 20;
    private final static int BOARD_COLUMNS = 10;
    private long score;
    private int[][] gameBoard;
    private Piece currentPiece;
    private Piece nextPiece;
    private boolean gameIsOver;

    public void setGameIsOver(boolean gameIsOver) {
        this.gameIsOver = gameIsOver;
    }

    public boolean getGameIsOver() {
        return gameIsOver;
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public Piece getNextPiece() {
        return nextPiece;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public Board() {
        this.gameBoard = new int[BOARD_LINES][BOARD_COLUMNS];
        this.gameIsOver = false;
    }

    public void spawnPiece() {
        if (this.currentPiece == null) {
            this.currentPiece = new Piece(Tetrominoes.randomTetrominoes());
        } else {
            this.currentPiece = this.nextPiece;
        }
        do {
            this.nextPiece = new Piece(Tetrominoes.randomTetrominoes());
        } while (this.currentPiece.equals(this.nextPiece));
        drawPiece();

    }

    public void isGameOver() {
        for (int j = 0; j < BOARD_COLUMNS; j++) {
            if (gameBoard[0][j] == -1) {
                this.gameIsOver = true;
                break;
            }
        }
    }

    public void clearPiece() {
        int x = this.currentPiece.getX();
        int y = this.currentPiece.getY();

        int minX = Math.max(x - 1, 0);
        int maxX = Math.min(20, x + 2 + 1);
        int minY = Math.max(0, y - 2);
        int maxY = Math.min(10, y + 1 + 1);

        for (int i = minX; i < maxX; i++) {
            for (int j = minY; j < maxY; j++) {
                if (gameBoard[i][j] != -1) {
                    gameBoard[i][j] = 0;
                }
            }
        }
    }

    public boolean isColliding(int minX, int maxX, int minY, int maxY) {
        for (int i = minX; i < maxX; i++) {
            for (int j = minY; j < maxY; j++) {
                if (maxY < 4) {
                    if (gameBoard[i][j] == -1 && currentPiece
                            .getTetrominoes()
                            .getShape()[i - minX][j - minY + (4 - (maxY - minY))] == 1) {
                        return true;
                    }
                } else {
                    if (gameBoard[i][j] == -1 && currentPiece
                            .getTetrominoes()
                            .getShape()[i - minX][j - minY] == 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isMovable(String direction) {
        int x = currentPiece.getX();
        int y = currentPiece.getY();

        int minX = Math.max(x - 1, 0);
        int maxX = Math.min(20, x + 2 + 1);
        int minY = Math.max(0, y - 2);
        int maxY = Math.min(10, y + 1 + 1);

        switch (direction) {
            case "right":
                int rightMin = maxY - 1;
                for (int i = minX; i < maxX; i++) {
                    if (gameBoard[i][rightMin] == 1 && maxY == 10) {
                        return false;
                    }
                }

                y = y + 1;
                minY = Math.max(0, y - 2);
                maxY = Math.min(10, y + 1 + 1);

                return !isColliding(minX, maxX, minY, maxY);
            case "left":
                int leftMin = Math.max(0, minY - 1);
                for (int i = minX; i < maxX; i++) {
                    if (gameBoard[i][leftMin] == 1 && minY == 0) {
                        return false;
                    }
                }

                y = y - 1;
                minY = Math.max(0, y - 2);
                maxY = Math.min(10, y + 1 + 1);

                return !isColliding(minX, maxX, minY, maxY);
            case "down":
                int botMax = Math.min(19, x + 2);
                for (int j = minY; j < maxY; j++) {
                    if (gameBoard[botMax][j] == 1 && maxX == 20) {
                        return false;
                    }
                }

                x = x + 1;
                minX = Math.max(x - 1, 0);
                maxX = Math.min(20, x + 2 + 1);

                return !isColliding(minX, maxX, minY, maxY);
        }
        return false;
    }

    public boolean isRotable() {
        int x = currentPiece.getX();
        int y = currentPiece.getY();

        int minX = Math.max(x - 1, 0);
        int maxX = Math.min(20, x + 2 + 1);
        int minY = Math.max(0, y - 2);
        int maxY = Math.min(10, y + 1 + 1);

        if (y - 1 < 0) {
            for (int i = 0; i < 4; i++) {
                if (currentPiece.getTetrominoes().getShape()[i][1] == 1) {
                    return false;
                }
            }
        } else if (y - 2 < 0) {
            for (int i = 0; i < 4; i++) {
                if (currentPiece.getTetrominoes().getShape()[i][0] == 1) {
                    return false;
                }
            }
        } else if (y + 1 >= 10) {
            for (int i = 0; i < 4; i++) {
                if (currentPiece.getTetrominoes().getShape()[i][3] == 1) {
                    return false;
                }
            }

        } else if (x + 1 >= 20) {
            for (int j = 0; j < 4; j++) {
                if (currentPiece.getTetrominoes().getShape()[2][j] == 1) {
                    return false;
                }
            }
        } else if (x + 2 >= 20) {
            for (int j = 0; j < 4; j++) {
                if (currentPiece.getTetrominoes().getShape()[3][j] == 1) {
                    return false;
                }
            }

        }

        return !isColliding(minX, maxX, minY, maxY);
    }

    public void move(String direction) {

        if (direction.equals("right") && isMovable("right")) {
            clearPiece();
            currentPiece.setY(currentPiece.getY() + 1);
            drawPiece();
        } else if (direction.equals("left") && isMovable("left")) {
            clearPiece();
            currentPiece.setY(currentPiece.getY() - 1);
            drawPiece();
        } else if (direction.equals("down") && isMovable("down")) {
            clearPiece();
            currentPiece.setX(currentPiece.getX() + 1);
            drawPiece();
        } else if (direction.equals("down") && !isMovable("down")) {
            blockPiece();
        }
    }

    public void drawPiece() {
        int x = this.getCurrentPiece().getX();
        int y = this.getCurrentPiece().getY();

        int minX = Math.max(x - 1, 0);
        int maxX = Math.min(20, x + 2 + 1);
        int minY = Math.max(0, y - 2);
        int maxY = Math.min(10, y + 1 + 1);
        for (int i = minX; i < maxX; i++) {
            for (int j = minY; j < maxY; j++) {
                if (maxY < 4) {
                    if (gameBoard[i][j] != -1)
                        gameBoard[i][j] = currentPiece.getTetrominoes().getShape()[i - minX][j - minY + (4 - (maxY - minY))];
                } else {
                    if (gameBoard[i][j] != -1)
                        gameBoard[i][j] = currentPiece.getTetrominoes().getShape()[i - minX][j - minY];
                }
            }
        }
    }

    public void rotate() {
        clearPiece();
        this.getCurrentPiece().rotate("right");
        if (isRotable()) {
            drawPiece();
        } else {
            this.getCurrentPiece().rotate("left");
            drawPiece();
        }

    }

    private void blockPiece() {

        int x = this.getCurrentPiece().getX();
        int y = this.getCurrentPiece().getY();

        int minX = Math.max(x - 1, 0);
        int maxX = Math.min(20, x + 2 + 1);
        int minY = Math.max(0, y - 2);
        int maxY = Math.min(10, y + 1 + 1);

        for (int i = minX; i < maxX; i++) {
            for (int j = minY; j < maxY; j++) {
                if (gameBoard[i][j] == 1) {
                    gameBoard[i][j] = -1;
                }
            }
        }

        int nbLinesCompleted = clearLines();
        updateScore(nbLinesCompleted);
        spawnPiece();
    }

    public long getScore() {
        return score;
    }

    public void updateScore(int nbLinesCompleted){

        switch (nbLinesCompleted){
            case 1:
                this.score += 40;
                break;
            case 2:
                this.score += 100;
                break;
            case 3:
                this.score += 300;
                break;
            case 4:
                this.score += 1200;
                break;
        }

    }

    public String boardState() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] i : this.gameBoard) {
            for (int j : i) {
                stringBuilder.append(j).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static int valueAt(int i, int j, int[][] matrix) {
        return matrix[i][j];
    }

    public void reset() {
        for (int i = 0; i < BOARD_LINES; i++) {
            for (int j = 0; j < BOARD_COLUMNS; j++) {
                gameBoard[i][j] = 0;
            }
        }
    }

    public void dropPiece() {
        while (isMovable("down")) {
            move("down");
        }
    }

    public int clearLines() {
        int completedLines = 0;
        int cpt = 0;
        ArrayList<Integer> lineCleared = new ArrayList<>();

        for (int i = 0; i < BOARD_LINES; i++) {
            for (int j = 0; j < BOARD_COLUMNS; j++) {
                if (gameBoard[i][j] != -1) break;
                cpt++;
            }
            if (cpt == BOARD_COLUMNS) {
                completedLines++;
                lineCleared.add(i);
            }
            cpt = 0;
        }

        for (int k : lineCleared) {
            for (int i = k; i > 0; i--) {
                for (int j = 0; j < BOARD_COLUMNS; j++) {
                    gameBoard[i][j] = gameBoard[i - 1][j];
                }
            }
        }

        if (completedLines > 0) {
            for (int j = 0; j < BOARD_COLUMNS; j++) {
                gameBoard[0][j] = 0;
            }
        }

        return completedLines;
    }

}
