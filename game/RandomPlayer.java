package game;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random;

    private final Type type;

    public RandomPlayer(final Random random, Type type) {
        this.random = random;
        this.type = type;
    }

    public RandomPlayer(Type type) {
        this(new Random(), type);
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            int r = random.nextInt(type.m);
            int c = random.nextInt(type.n);
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
