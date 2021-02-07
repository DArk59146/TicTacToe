package ticTacToe;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HumanPlayer implements Player {
    private final PrintStream out;
    private final MoveReader in;

    public HumanPlayer(final PrintStream out, final MoveReader in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new MoveReader(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            int row = in.nextMove();
            int column = in.nextMove();
            if (row != -1 && column != -1) {
                final Move move = new Move(row, column, cell);
                if (position.isValid(move)) {
                    return move;
                }
                out.println("Move " + move + " is invalid");
            } else {
                out.println("Expected Int, try again please.");
            }
        }
    }

    public void endOfGame() throws IOException {
        in.close();
    }
}
