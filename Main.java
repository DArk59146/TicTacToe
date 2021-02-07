package ticTacToe;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static Scanner sc;

    public static void main(String[] args) {
        final List<Player> players = new LinkedList<>();
        int result;
        sc = new Scanner(System.in);
        do {
            System.out.print("Enter n, k, number of players and number of human players: ");
            int n,k, numberOfPlayers, numberOfHumanPlayers;
            n = readData();
            k = readData();
            numberOfPlayers = readData();
            numberOfHumanPlayers = readData();
            while (n <= 0 || k <= 0 || k > 2 * n - 1 || numberOfPlayers < 1 || numberOfPlayers > 4 ||
                    numberOfHumanPlayers < 0 || numberOfHumanPlayers > 4 || numberOfHumanPlayers > numberOfPlayers) {
                System.out.println("Wrong input, try again");
                n = readData();
                k = readData();
                numberOfPlayers = readData();
                numberOfHumanPlayers = readData();
            }
            for (int i = 0; i < numberOfHumanPlayers; i++) {
                players.add(new HumanPlayer());
            }
            for (int i = 0; i < numberOfPlayers - numberOfHumanPlayers; i++) {
                players.add(new RandomPlayer());
            }
            final Game game = new Game(false, players);
            result = game.play(new RhombusBoard(n, k, numberOfPlayers));
            System.out.println("Game result: " + result);
            players.removeAll(players);
        } while (result != 0);
        sc.close();
        for (Player player : players) {
            if (player instanceof HumanPlayer) {
                try {
                    ((HumanPlayer) player).endOfGame();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private static int readData() {
        if (sc.hasNextInt()) {
            return sc.nextInt();
        } else {
            if (sc.hasNext()) {
                sc.next();
            }
            return -1;
        }
    }
}
