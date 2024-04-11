package game;

import java.util.*;

public class Tournament extends TypeOfGame {

    Type type;
    Scanner in;
    Map<Player, Integer> playerMap = new HashMap<>();
    List<Player> players = new ArrayList<>();
    List<Player> winner = new ArrayList<>();
    List<Player> loser = new ArrayList<>();
    public Tournament(Scanner in) {
        this.in = in;
    }

    private int settingNumberOfPlayers() {
        int numberOfPlayers = 0;
        while (numberOfPlayers < 2) {
            System.out.println("Select number of players");
            try {
                numberOfPlayers = Integer.parseInt(in.next());
            } catch (NumberFormatException e) {
                System.out.println("This is not a number");
            }
        }
        return numberOfPlayers;
    }
    private int settingHuman() {
        int human = -1;
        while (human < 0) {
            System.out.println("Select number of Human players");
            try {
                human = Integer.parseInt(in.next());
            } catch (NumberFormatException e) {
                System.out.println("This is not a number");
            }
        }
        return human;
    }

    private void settings() {
        int numberOfPlayers = this.settingNumberOfPlayers();
        int human = this.settingHuman();

        for (int i = 0; i < human; i++) {
            players.add(new HumanPlayer(in));
            playerMap.put(players.get(players.size() - 1), players.size());
        }
        for (int i = human; i < numberOfPlayers; i++) {
            players.add(new RandomPlayer(type));
            playerMap.put(players.get(players.size() - 1), players.size());
        }
        Collections.shuffle(players);
    }
    public void play() {
        Board board;
        String shape = TypeOfGame.settingShape(in);
        this.type = TypeOfGame.settingType(in, shape);
        if (shape.equals("1")) {
            board = new CircleBoard(type);
        } else {
            board = new MNKBoard(type);
        }
        this.settings();
        while (players.size() > 1) {
            while (Integer.bitCount(players.size()) > 1) {
                winner.add(players.get(0));
                players.remove(0);
            }
            board.clear();
            Game game = new Game(false, players.get(0), players.get(1));
            int result = game.play(board);
            if (result > 0) {
                winner.add(players.get(result - 1));
                loser.add(players.get(result % 2));
                players.remove(0);
                players.remove(0);
                if (players.isEmpty()) {
                    players = new ArrayList<>(winner);
                    winner = new ArrayList<>();
                }
            }
        }
    }

    public void getResult() {
        System.out.println("Game result: ");
        System.out.println("Winner: Player №" + playerMap.get(players.get(0)));
        System.out.println("2-nd place: Player №" + playerMap.get(loser.get(loser.size() - 1)));
        int place = 4;
        for (int i = 3; i <= loser.size() + 1; i++) {
            if (i > place) {
                place *= 2;
            }
            System.out.println((place / 2 + 1)
                    + "-"
                    + (place)
                    + " place: Player №"
                    + playerMap.get(loser.get(loser.size() - (i - 1))));
        }
    }

}
