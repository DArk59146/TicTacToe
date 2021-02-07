package ticTacToe;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MoveReader implements Closeable {
    private FastScanner scanner;

    public MoveReader(InputStream in) {
        try {
            scanner = new FastScanner(in, StandardCharsets.UTF_8, new RequiredSymbols() {
                @Override
                public boolean isRequiredSymbol(int c) {
                    return true;
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public int nextMove() {
        try {
            String move = scanner.nextToSpace();
            for (int i = 0; i < move.length(); i++) {
                if (!Character.isDigit(move.charAt(i))) {
                    return -1;
                }
            }
            return Integer.parseInt(move);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @Override
    public void close() throws IOException {
        scanner.close();
    }
}