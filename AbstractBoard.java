package ticTacToe;

import java.util.Map;

public abstract class AbstractBoard implements Board{
    protected final int m;
    protected final int n;
    protected final int k;
    protected int remainPositions;
    protected final Cell[][] cells;
    protected final int numberOfPlayers;
    private final AbstractPosition position;
    protected final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.DASH, '|',
            Cell.MINUS, '-',
            Cell.E, '.',
            Cell.OUT, ' '
    );
    protected Cell turn;

    public AbstractBoard(int m, int n, int k, int numberOfPlayers, Cell[][] cells, int startRemainPositions) {
        remainPositions = startRemainPositions;
        this.cells = cells;
        position = new AbstractPosition();
        this.m = m;
        this.numberOfPlayers = numberOfPlayers;
        this.n = n;
        this.k = k;
        turn = Cell.X;
    }


    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public void nextTurn() {
        if (turn == Cell.X) {
            if (numberOfPlayers > 2) {
                turn = Cell.O;
            }
        } else if (turn == Cell.O) {
            if (numberOfPlayers > 2) {
                turn = Cell.MINUS;
            } else {
                turn = Cell.X;
            }
        } else  if (turn == Cell.MINUS) {
            if (numberOfPlayers > 3) {
                turn = Cell.DASH;
            } else {
                turn = Cell.X;
            }
        } else {
            turn = Cell.X;
        }
    }

    protected boolean outOfBounds(int row, int column) {
        if (row < 0 || column < 0 || row >= n || column >= m) {
            return true;
        } else {
            return cells[row][column] == Cell.OUT;
        }
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!position.isValid(move) || move.getValue() != turn) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();

        int inDiag1 = 0;
        int inDiag2 = 0;
        int inRow = 0;
        int inColumn = 0;
        for (int i = 1 - k; i < k; i++) {

            if (requiredValue(move.getRow() + i, move.getColumn())) {
                inColumn++;
            } else if (inColumn >= k) {
                return Result.WIN;
            } else {
                inColumn = 0;
            }

            if (requiredValue(move.getRow(), move.getColumn() + i)) {
                inRow++;
            } else if (inRow >= k) {
                return Result.WIN;
            } else {
                inRow = 0;
            }

            if (requiredValue(move.getRow() + i, move.getColumn() + i)) {
                inDiag1++;
            } else if (inDiag1 >= k) {
                return Result.WIN;
            } else {
                inDiag1 = 0;
            }

            if (requiredValue(move.getRow() + i, move.getColumn() - i)) {
                inDiag2++;
            } else if (inDiag2 >= k) {
                return Result.WIN;
            } else {
                inDiag2 = 0;
            }
        }
        if (inDiag1 >= k || inDiag2 >= k || inColumn >= k || inRow >= k) {
            return Result.WIN;
        }
        remainPositions--;
        if (remainPositions == 0) {
            return Result.DRAW;
        }
        nextTurn();
        return Result.UNKNOWN;
    }

    @Override
    public String toString() {
        return position.toString();
    }

    private boolean requiredValue(int i, int j) {
        return !outOfBounds(i, j) && cells[i][j] == turn;
    }

    private class AbstractPosition implements Position {


        @Override
        public boolean isValid(final Move move) {
            return !outOfBounds(move.getRow(), move.getColumn())
                    && getCell(move.getRow(), move.getColumn()) == Cell.E
                    && move.getValue() == turn;
        }

        @Override
        public int getRow() {
            return n;
        }


        @Override
        public int getColumn() {
            return m;
        }

        @Override
        public Cell getCell(int r, int c) {
            return cells[r][c];
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(" ");
            final int size = Integer.toString(2*n - 2).length();
            for (int i = size; i > 0; i--) {
                sb.append(" ");
            }
            String space = sb.toString();;
            for (int i = 0; i < n; i++) {
                sb.append(i).append(space, 0, size - Integer.toString(i).length() + 1);
            }
            for (int r = 0; r < m; r++) {
                sb.append("\n");
                sb.append(r).append(space, 0, size - Integer.toString(r).length() + 1);
                for (int c = 0; c < n; c++) {
                    sb.append(SYMBOLS.get(cells[r][c])).append(space, 0, size);
                }
            }
            return sb.toString();
        }
    }
}
