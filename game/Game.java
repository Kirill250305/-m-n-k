package game;

public class Game {
    private final boolean log;
    private final Player player1, player2;

    public Game(final boolean log, final Player player1, final Player player2) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
    }

    public int play(Board board) {
        int number = 0;
        int result2 = 4;
        int result1 = 4;
        while (true) {
            if (result2 != 3) {
                result1 = move(board, player1, 1, number);
                number++;
                if (result1 != -1 && result1 != 3) {
                    return result1;
                }
            }
            if (result1 != 3) {
                result2 = move(board, player2, 2, number);
                number++;
                if (result2 != -1 && result2 != 3) {
                    return result2;
                }
            }
        }
    }

    private int move(final Board board, final Player player, final int no, int number) {
        try {
            final Move move = player.move(board.getPosition(), board.getCell());
            final Result result = board.makeMove(move, player, board, number, true);
            log("Player " + no + " move: " + move);
            log("Position:" + System.lineSeparator() + board);
            if (result == Result.EXTRAMOVE) {
                log("Player " + no + " gets an extra move");
                return 3;
            } else if (result == Result.WIN) {
                log("Player " + no + " won");
                return no;
            } else if (result == Result.LOSE) {
                log("Player " + no + " lose");
                return 3 - no;
            } else if (result == Result.DRAW) {
                log("Draw");
                return 0;
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 3 - no;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
