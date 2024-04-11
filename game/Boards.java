package game;

import java.util.Arrays;

import static java.lang.Math.abs;

public abstract class Boards implements Board, Position{
    static final int CONST_TO_EXTRA_MOVE = 4;
    protected Cell[][] cells;
    protected Type type;

    protected Cell turn;
    public Boards(Type type) {
        this.type = type;
        this.cells = new Cell[this.type.m][this.type.n];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }
    @Override
    public Position getPosition() {
        return new NewPosition(this);
    }


    int moveOnDiagonal(Move move, int minRC, int maxRC, int max, int sign) {
        int diagonal = 0;
        for (int i = - Math.min(minRC, type.k); i <= Math.min(type.k, maxRC); i++) {
            if (cells[move.getRow() + i * sign]
                    [move.getColumn() + i] == move.getValue()) {
                diagonal++;
                max = Math.max(max, diagonal);
            } else {
                diagonal = 0;
            }
        }
        return max;
    }

    int moveOnLine(Move move, int typeOfLine, int max) {
        int inLine = 0;
        int getCol = - (move.getColumn() * (typeOfLine - 1));
        int getRow = (move.getRow() * typeOfLine);
        int get = getCol + getRow;
        for (int i = Math.max(0, get - type.k);
             i < Math.min(type.m * typeOfLine - type.n * (typeOfLine - 1), get + type.k); i++) {
            if (cells[i * typeOfLine - move.getRow() * (typeOfLine - 1)]
                    [- i * (typeOfLine - 1) + move.getColumn() * typeOfLine] == move.getValue()) {
                inLine++;
                max = Math.max(max, inLine);
            } else {
                inLine = 0;
            }
        }
        return max;
    }

    private boolean extraMoveOnDiagonal(Move move, int sign) {
        int r1 = move.getRow();
        int r2 = move.getRow();
        int c1 = move.getColumn();
        int c2 = move.getColumn();
        while (c1 > -1 && c1 < cells[0].length && r1 > -1 && r1 < cells.length && cells[r1][c1] == move.getValue()) {
            r1 += sign;
            c1--;
        }
        while (c2 > -1 && c2 < cells[0].length && r2 > -1 && r2 < cells.length && cells[r2][c2] == move.getValue()) {
            r2 += sign;
            c2++;
        }
        return move.getColumn() - c1 < CONST_TO_EXTRA_MOVE + 1
                && c2 - move.getColumn() < CONST_TO_EXTRA_MOVE + 1
                && c2 - c1 > CONST_TO_EXTRA_MOVE;
    }

    private boolean extraMoveOnLine(Move move, int digit, int sign) {
        int d1 = digit;
        int d2 = digit;
        while (d1 > -1 && cells[d1 * sign + move.getRow() * abs(sign - 1)]
                [d1 * abs(sign - 1) + move.getColumn() * sign] == move.getValue()) {
            d1--;
        }
        while (d2 < cells.length * sign + cells[0].length * abs(sign - 1)
                && cells[d2 * sign + move.getRow() * abs(sign - 1)]
                [d2 * abs(sign - 1) + move.getColumn() * sign] == move.getValue()) {
            d2++;
        }
        return (digit - d1 < CONST_TO_EXTRA_MOVE + 1
                && d2 - digit < CONST_TO_EXTRA_MOVE + 1
                && d2 - d1 > CONST_TO_EXTRA_MOVE);
    }
    boolean extraMove(Move move) {
        return extraMoveOnLine(move, move.getRow(), 1)
                || extraMoveOnLine(move, move.getColumn(), 0)
                || extraMoveOnDiagonal(move, 1)
                || extraMoveOnDiagonal(move, -1);
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public void clear() {
        for (Cell[] cell : cells) {
            Arrays.fill(cell, Cell.E);
        }
    }
}
