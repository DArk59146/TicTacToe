package ticTacToe;

import java.util.Arrays;


public class MnkBoard extends AbstractBoard {

    public MnkBoard(int m, int n, int k, int numberOfPlayers) {
        super(m, n, k, numberOfPlayers, new Cell[n][m], m * n);
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
    }
}