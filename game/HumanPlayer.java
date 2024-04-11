package game;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer(Scanner in) {
        this(System.out, in);
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position");
            out.println(position.toString());
            out.println(cell + "'s move");
            int row;
            int column;
            while (true) {
                out.println("Enter row and column");
                try {
                    row = in.nextInt();
                    column = in.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("This is not a numbers");
                }
            }
            final Move move = new Move(row, column, cell);
            if (position.isValid(move)) {
                return move;
            }
            out.println("Move " + move + " is invalid");
        }
    }
}
