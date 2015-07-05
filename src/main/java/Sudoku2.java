import java.util.Scanner;
import java.util.Stack;

public class Sudoku2 {
    private static class P {
        int x;
        int y;

        public P(final int i, final int j) {
            x = i;
            y = j;
        }
    }

    private static class I {
        P p;
        int value;

        public I(final P p, final int v) {
            this.p = p;
            value = v;
        }
    }

    static void sudoku_solve(final int[][] g) {
        int n = g.length;
        Stack<I> ei = new Stack<I>();
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (g[i][j] == 0) {
                    ei.push(new I(new P(i, j), 0));
                }
            }
        }

        Stack<I> uis = new Stack<I>();
        m:
        while (!ei.empty()) {
            I i = ei.pop();
            for (int cv = i.value + 1; cv <= n; cv++) {
                g[i.p.x][i.p.y] = cv;
                if (iV(g, i.p)) {
                    uis.push(new I(i.p, cv));
                    continue m;
                }
            }

            g[i.p.x][i.p.y] = 0;
            ei.push(new I(i.p, 0));
            if (!uis.empty()) {
                I ui = uis.pop();
                g[ui.p.x][ui.p.y] = 0;
                ei.push(ui);
            }
        }

        pr(g, n);
    }

    static void pr(final int[][] g, final int n) {
        for (int i = 0; i < n; i++) {
            StringBuffer r = new StringBuffer();
            for (int j = 0; j < n; j++) {
                if (r.length() > 0) {
                    r.append(" ");
                }

                r.append(g[i][j]);
            }

            System.out.println(r.toString());
        }
    }

    static boolean iV(final int[][] g, final P p) {
        int n = g.length;
        for (int row = 0; row < n; row++) {
            if (row == p.x) {
                continue;
            }

            if (g[row][p.y] == g[p.x][p.y]) {
                return false;
            }
        }

        for (int col = 0; col < n; col++) {
            if (col == p.y) {
                continue;
            }

            if (g[p.x][col] == g[p.x][p.y]) {
                return false;
            }
        }

        int a = 3 * (p.x / 3);
        int b = a + 3;
        int c = 3 * (p.y / 3);
        int d = c + 3;

        // System.out.println(startX + " " + endX + ":" + startY + " " + endY);
        for (int i = a; i < b; i++) {
            for (int j = c; j < d; j++) {
                if (i == p.x && j == p.y) {
                    continue;
                }

                if (g[i][j] == g[p.x][p.y]) {
                    return false;
                }
            }
        }

        return true;
    }

    /* Tail starts here */
    public static void main(final String[] args) {
        Scanner in = new Scanner(System.in);
        int n;
        int N = 9;
        int[][] board = new int[N][N];

        n = in.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    board[j][k] = in.nextInt();
                }
            }

            sudoku_solve(board);
        }
    }
}
