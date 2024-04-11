package game;

public class NewPosition implements Position {

    Position position;

    public NewPosition(Position board) {
        this.position = board;
    }

    public boolean isValid(Move move) {
        return position.isValid(move);
    }

    @Override
    public Cell getCell(int r, int c) {
        return position.getCell(r, c);
    }

    public String toString() {
        return position.toString();
    }
}
