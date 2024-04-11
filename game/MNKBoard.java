package game;

import java.util.Arrays;
import java.util.Map;

public class MNKBoard extends Boards {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );
    public MNKBoard(Type type) {
        super(type);
        this.type = type;
        this.cells = new Cell[this.type.m][this.type.n];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
    }
    @Override
    public Result makeMove(final Move move, Player player, Board board, int number, boolean valid) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        cells[move.getRow()][move.getColumn()] = move.getValue();
        int max = moveOnLine(move, 0, 0);
        max = moveOnLine(move, 1, max);

        int minRC = Math.min(move.getRow(), move.getColumn());
        int maxRC = Math.min(type.m - move.getRow() - 1, type.n - move.getColumn() - 1);
        max = moveOnDiagonal(move, minRC, maxRC, max, 1);

        minRC = Math.min(type.m - move.getRow() - 1, move.getColumn());
        maxRC = Math.min(move.getRow(), type.n - move.getColumn() - 1);
        max = moveOnDiagonal(move, minRC, maxRC, max, -1);

        if (max >= type.k) {
            return Result.WIN;
        }
        if (extraMove(move)) {
            return Result.EXTRAMOVE;
        }
        if (number + 1 == type.n * type.m) {
            return Result.DRAW;
        }
        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move) {
        return !(0 > move.getRow() || move.getRow() >= type.m
                || 0 > move.getColumn() || move.getColumn() >= type.n
                || cells[move.getRow()][move.getColumn()] != Cell.E);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ".repeat(String.valueOf(type.d).length()));
        for (int i = 0; i < type.n; i++) {
            sb.append(" ");
            sb.append(i);
        }
        for (int r = 0; r < type.m; r++) {
            sb.append(System.lineSeparator());
            sb.append(r);
            sb.append(" ".repeat(String.valueOf(type.d).length() + 1 - String.valueOf(r).length()));
            for (int c = 0; c < type.n; c++) {
                sb.append(SYMBOLS.get(cells[r][c])).append(" ".repeat(String.valueOf(c).length()));
            }
        }
        return sb.toString();
    }
}
