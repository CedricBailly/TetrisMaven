package com.tetris;

import javafx.scene.paint.Color;

import java.util.Random;

public enum Tetrominoes {

    O(new int[][]{
            {0, 0, 0, 0},
            {0, 1, 1, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0}}, Color.YELLOW),

    BaseL(new int[][]{
            {0, 0, 0, 0},
            {0, 1, 1, 1},
            {0, 1, 0, 0},
            {0, 0, 0, 0}}, Color.ORANGE),
    LeftL(new int[][]{
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 1},
            {0, 0, 0, 0}}, Color.ORANGE),
    LastL(new int[][]{
            {0, 0, 1, 0},
            {1, 1, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}}, Color.ORANGE),
    RightL(new int[][]{
            {0, 1, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 0}}, Color.ORANGE),

    BaseI(new int[][]{
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0}}, Color.CYAN),
    LastI(new int[][]{
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0}}, Color.CYAN),

    BaseS(new int[][]{
            {0, 0, 1, 0},
            {0, 0, 1, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 0}}, Color.GREEN),
    LastS(new int[][]{
            {0, 0, 0, 0},
            {0, 0, 1, 1},
            {0, 1, 1, 0},
            {0, 0, 0, 0}}, Color.GREEN),

    BaseZ(new int[][]{
            {0, 0, 0, 1},
            {0, 0, 1, 1},
            {0, 0, 1, 0},
            {0, 0, 0, 0}}, Color.RED),

    LastZ(new int[][]{
            {0, 0, 0, 0},
            {0, 1, 1, 0},
            {0, 0, 1, 1},
            {0, 0, 0, 0}}, Color.RED),

    BaseJ(new int[][]{
            {0, 0, 0, 0},
            {0, 1, 1, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 0}}, Color.BLUE),
    RightJ(new int[][]{
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0}}, Color.BLUE),
    LastJ(new int[][]{
            {0, 1, 1, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 0, 0}}, Color.BLUE),
    LeftJ(new int[][]{
            {0, 1, 0, 0},
            {0, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0}}, Color.BLUE),

    BaseT(new int[][]{
            {0, 0, 0, 0},
            {0, 1, 1, 1},
            {0, 0, 1, 0},
            {0, 0, 0, 0}}, Color.PURPLE),
    RightT(new int[][]{
            {0, 0, 1, 0},
            {0, 1, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 0}}, Color.PURPLE),
    LeftT(new int[][]{
            {0, 0, 1, 0},
            {0, 0, 1, 1},
            {0, 0, 1, 0},
            {0, 0, 0, 0}}, Color.PURPLE),
    LastT(new int[][]{
            {0, 0, 1, 0},
            {0, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0}}, Color.PURPLE);

    private int[][] shape;
    private Color color;

    Tetrominoes(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;
    }

    public int[][] getShape() {
        return shape;
    }

    public void setShape(int[][] shape) {
        this.shape = shape;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static Tetrominoes randomTetrominoes() {
        int nbPieces = Tetrominoes.values().length;
        Random random = new Random();
        return Tetrominoes.values()[random.nextInt(nbPieces)];
    }


}