package game;

public interface Board {
    Position getPosition();
    Cell getCell();

    Result makeMove(Move move, Player player, Board board, int number, boolean valid);
    void clear();
    boolean isValid(Move move);

}
