package game;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            String system;
            while (true) {
                System.out.println("Select the game system: Olympic - \"1\" or Standard - \"2\"");
                try {
                    system = in.next();
                    if (system.equals("1")) {
                        final Tournament game = new Tournament(in);
                        game.play();
                        game.getResult();
                        break;
                    } else if (system.equals("2")) {
                        final Standard game = new Standard(in);
                        game.play();
                        game.getResult();
                        break;
                    }
                } catch (InputMismatchException | NullPointerException e) {
                    System.out.println("incorrect answer");
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("no input, no game");
        }
    }
}