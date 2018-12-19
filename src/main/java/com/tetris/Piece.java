package com.tetris;

import java.util.Arrays;

public class Piece {

    Tetrominoes tetrominoes;
    private int x, y;

    public Piece(Tetrominoes tetrominoes) {
        this.tetrominoes = tetrominoes;
        this.x = 1;
        this.y = 5;
    }

    public Tetrominoes getTetrominoes() {
        return tetrominoes;
    }

    public void rotate(String direction) {
        switch (tetrominoes.name()) {
            case "O":
                break;
            case "BaseS":
                setTetrominoes(Tetrominoes.LastS);
                break;
            case "LastS":
                setTetrominoes(Tetrominoes.BaseS);
                break;

            case "BaseZ":
                setTetrominoes(Tetrominoes.LastZ);
                break;
            case "LastZ":
                setTetrominoes(Tetrominoes.BaseZ);
                break;

            case "BaseI":
                setTetrominoes(Tetrominoes.LastI);
                break;
            case "LastI":
                setTetrominoes(Tetrominoes.BaseI);
                break;

            case "BaseJ":
                if (direction.equals("right")) setTetrominoes(Tetrominoes.RightJ);
                else setTetrominoes(Tetrominoes.LeftJ);
                break;
            case "RightJ":
                if (direction.equals("right")) setTetrominoes(Tetrominoes.LastJ);
                else setTetrominoes(Tetrominoes.BaseJ);
                break;
            case "LastJ":
                if (direction.equals("right")) setTetrominoes(Tetrominoes.LeftJ);
                else setTetrominoes(Tetrominoes.RightJ);
                break;
            case "LeftJ":
                if (direction.equals("right")) setTetrominoes(Tetrominoes.BaseJ);
                else setTetrominoes(Tetrominoes.LastJ);
                break;

            case "BaseL":
                if (direction.equals("right")) setTetrominoes(Tetrominoes.RightL);
                else setTetrominoes(Tetrominoes.LeftL);
                break;
            case "RightL":
                if (direction.equals("right")) setTetrominoes(Tetrominoes.LastL);
                else setTetrominoes(Tetrominoes.BaseL);
                break;
            case "LastL":
                if (direction.equals("right")) setTetrominoes(Tetrominoes.LeftL);
                else setTetrominoes(Tetrominoes.RightL);
                break;
            case "LeftL":
                if (direction.equals("right")) setTetrominoes(Tetrominoes.BaseL);
                else setTetrominoes(Tetrominoes.LastL);
                break;

            case "BaseT":
                if (direction.equals("right")) setTetrominoes(Tetrominoes.RightT);
                else setTetrominoes(Tetrominoes.LeftT);
                break;
            case "RightT":
                if (direction.equals("right")) setTetrominoes(Tetrominoes.LastT);
                else setTetrominoes(Tetrominoes.BaseT);
                break;
            case "LastT":
                if (direction.equals("right")) setTetrominoes(Tetrominoes.LeftT);
                else setTetrominoes(Tetrominoes.RightT);
                break;
            case "LeftT":
                if (direction.equals("right")) setTetrominoes(Tetrominoes.BaseT);
                else setTetrominoes(Tetrominoes.LastT);
                break;
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Piece)) return false;
        Piece otherPiece = (Piece) obj;
        return Arrays.deepEquals(otherPiece.getTetrominoes().getShape(), this.tetrominoes.getShape());
    }

    public void setTetrominoes(Tetrominoes tetrominoes) {
        this.tetrominoes = tetrominoes;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
