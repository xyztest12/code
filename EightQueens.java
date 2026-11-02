
public class EightQueens {

    static final int N = 8;
    static int[][] board = new int[N][N];

    // Check whether queen can be placed safely
    static boolean isSafe(int row, int col) {

        // Check same column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }

        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1;
             i >= 0 && j >= 0; i--, j--) {

            if (board[i][j] == 1) {
                return false;
            }
        }

        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1;
             i >= 0 && j < N; i--, j++) {

            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    // Solve 8 Queens using Backtracking
    static boolean solveNQueens(int row) {

        if (row == N) {
            return true;
        }

        for (int col = 0; col < N; col++) {

            if (isSafe(row, col)) {

                board[row][col] = 1;

                if (solveNQueens(row + 1)) {
                    return true;
                }

                // Backtracking
                board[row][col] = 0;
            }
        }

        return false;
    }

    // Display chessboard
    static void printBoard() {

        System.out.println("\nSolution of 8 Queens Problem:");

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {

                if (board[i][j] == 1) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        System.out.println("8 Queens Problem Using Backtracking");

        if (solveNQueens(0)) {
            printBoard();
            System.out.println("\nSolution Found Successfully!");
        } else {
            System.out.println("No Solution Exists!");
        }
    }
}