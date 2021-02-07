package ticTacToe;

import java.util.Arrays;

public class RhombusBoard extends AbstractBoard {

    public RhombusBoard(int n, int k, int numberOfPlayers) {
        super(2 * n - 1, 2 * n - 1, k, numberOfPlayers, new Cell[2 * n - 1][2 * n - 1], 2 * n * (n - 1) + 1);
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.OUT);
        }
        for (int i = 0; i < 2 * n - 1; i++) {
            for (int j = Math.abs(n - 1 - i); j <= 2 * (n - 1) - Math.abs(n - 1 - i); j++) {
                cells[i][j] = Cell.E;
            }
        }
    }
}