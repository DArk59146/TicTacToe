package ticTacToe;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Position {
    boolean isValid(Move move);
    int getRow();
    int getColumn();
    Cell getCell(int r, int c);
}