package game;

import java.util.Scanner;

public class Standard extends TypeOfGame {

    Scanner in;
    int result;
    public Standard(Scanner in) {
        this.in = in;
    }

    public void play() {
        Board board;
        String shape = TypeOfGame.settingShape(in);
        Type type = TypeOfGame.settingType(in, shape);
        if (shape.equals("1")) {
            board = new CircleBoard(type);
        } else {
            board = new MNKBoard(type);
        }
        final Game game = new Game(false, new HumanPlayer(in), new RandomPlayer(type));
        result = game.play(board);
    }

    public void getResult() {
        System.out.println("Game result: " + result);
    }
}
