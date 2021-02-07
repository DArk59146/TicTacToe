package ticTacToe;

import java.util.List;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Game {
    private final boolean log;
    private final List<Player> players;
    private final int numberOfPlayers;
    private boolean[] losers;

    public Game(final boolean log, final List<Player> players) {
        this.log = log;
        this.players = players;
        numberOfPlayers = players.size();
        losers = new boolean[numberOfPlayers];
    }

    public int play(Board board) {
        while (true) {
            for (int i = 0; i < numberOfPlayers; i++) {
                if (!losers[i]) {
                    final int result = move(board, players.get(i), i + 1);
                    if (result != -1) {
                        System.out.println(board);
                        return result;
                    }
                }
            }
        }

    }

    private int move(final Board board, final Player player, final int no) {
        final Move move = player.move(board.getPosition(), board.getCell());
        final Result result = board.makeMove(move);
        log("Player " + no + " move: " + move);
        log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + no + " won");
            return no;
        } else if (result == Result.LOSE) {
            log("Player " + no + " lose");
            losers[no] = true;
            int numberOfNotLosers = 0;
            int lastNotLoser = 0;
            for (int i = 0; i < numberOfPlayers; i++) {
                if (!losers[i]) {
                    numberOfNotLosers++;
                    lastNotLoser = i;
                }
            }
            if (numberOfNotLosers == 1) {
                return lastNotLoser + 1;
            }
            return -1;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else {
            return -1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
