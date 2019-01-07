import java.util.Scanner;

public class BomberManGame {

    static String[] bomberMan(int n, String[] grid) {
        if (n == 1) {
            return grid;
        }
        if (n - 3 >= 0) {
            n -= ((n - 3) / 4) * 4;
        }
        n--;
        State state = new State(grid, '0');
        for (int i = 0; i < n; i++) {
            //System.out.println(i + ":\n" + state);
            if (i % 2 == 0) {
                state = state.plant();
            } else {
                state = state.detonate();
            }
        }
        //System.out.println(n + ":\n" + state);
        return state.convert();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int r = in.nextInt();
        int c = in.nextInt();
        int n = in.nextInt();
        String[] grid = new String[r];
        for (int grid_i = 0; grid_i < r; grid_i++) {
            grid[grid_i] = in.next();
        }
        String[] result = bomberMan(n, grid);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i != result.length - 1 ? "\n" : ""));
        }
        System.out.println("");


        in.close();
    }

    static class State {
        char[][] grid = null;
        char bomb;

        State(String[] grid, char bomb) {
            this.bomb = bomb;
            this.grid = new char[grid.length][grid[0].length()];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length(); j++) {
                    this.grid[i][j] = grid[i].charAt(j);
                }
            }
        }

        State(char[][] grid, char bomb) {
            this.bomb = bomb;
            this.grid = grid;
        }

        public State plant() {

            char[][] newGrid = new char[grid.length][grid[0].length];
            char newBomb = bomb == '0' ? '1' : '0';

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '.') {
                        newGrid[i][j] = newBomb;
                    } else {
                        newGrid[i][j] = grid[i][j];
                    }
                }
            }
            return new State(newGrid, newBomb);
        }

        public State detonate() {

            char[][] newGrid = new char[grid.length][grid[0].length];

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    newGrid[i][j] = grid[i][j];
                }
            }
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] != '.' && grid[i][j] != bomb) {
                        newGrid[i][j] = '.';
                        if (i - 1 >= 0) newGrid[i - 1][j] = '.';
                        if (i + 1 < grid.length) newGrid[i + 1][j] = '.';
                        if (j - 1 >= 0) newGrid[i][j - 1] = '.';
                        if (j + 1 < grid[0].length) newGrid[i][j + 1] = '.';
                    }
                }
            }
            return new State(newGrid, bomb);
        }

        public String[] convert() {
            String[] result = new String[grid.length];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] != '.') grid[i][j] = 'O';
                }
                result[i] = new String(grid[i]);
            }
            return result;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < grid.length; i++) {
                sb.append(new String(grid[i]) + "\n");
            }
            return sb.toString();
        }

    }
}
