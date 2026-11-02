import java.util.*;

public class MinimaxTicTacToe {

    static char[] board = {
        ' ', ' ', ' ',
        ' ', ' ', ' ',
        ' ', ' ', ' '
    };

    static char human = 'X';
    static char ai = 'O';

    static void printBoard() {

        System.out.println();

        for (int i = 0; i < 9; i++) {

            System.out.print(" " + board[i] + " ");

            if (i % 3 != 2) {
                System.out.print("|");
            }

            if (i % 3 == 2 && i != 8) {
                System.out.println("\n-----------");
            }
        }

        System.out.println("\n");
    }

    static boolean checkWin(char player) {

        int[][] win = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
        };

        for (int[] w : win) {

            if (board[w[0]] == player &&
                board[w[1]] == player &&
                board[w[2]] == player) {

                return true;
            }
        }

        return false;
    }

    static boolean isDraw() {

        for (char cell : board) {
            if (cell == ' ') {
                return false;
            }
        }

        return true;
    }

    // Minimax algorithm
    static int minimax(boolean isMaximizing) {

        if (checkWin(ai)) {
            return 10;
        }

        if (checkWin(human)) {
            return -10;
        }

        if (isDraw()) {
            return 0;
        }

        if (isMaximizing) {

            int bestScore = Integer.MIN_VALUE;

            for (int i = 0; i < 9; i++) {

                if (board[i] == ' ') {

                    board[i] = ai;

                    int score = minimax(false);

                    board[i] = ' ';

                    bestScore = Math.max(bestScore, score);
                }
            }

            return bestScore;

        } else {

            int bestScore = Integer.MAX_VALUE;

            for (int i = 0; i < 9; i++) {

                if (board[i] == ' ') {

                    board[i] = human;

                    int score = minimax(true);

                    board[i] = ' ';

                    bestScore = Math.min(bestScore, score);
                }
            }

            return bestScore;
        }
    }

    // AI chooses the best move
    static void bestMove() {

        int bestScore = Integer.MIN_VALUE;
        int move = -1;

        for (int i = 0; i < 9; i++) {

            if (board[i] == ' ') {

                board[i] = ai;

                int score = minimax(false);

                board[i] = ' ';

                if (score > bestScore) {
                    bestScore = score;
                    move = i;
                }
            }
        }

        if (move != -1) {
            board[move] = ai;
        }

        System.out.println("AI chooses position: " + (move + 1));
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("   TIC TAC TOE USING MINIMAX");
        System.out.println("================================");

        System.out.println("You are X");
        System.out.println("AI is O");

        System.out.println("Positions:");

        System.out.println(" 1 | 2 | 3");
        System.out.println("-----------");
        System.out.println(" 4 | 5 | 6");
        System.out.println("-----------");
        System.out.println(" 7 | 8 | 9");

        printBoard();

        while (true) {

            // Human turn
            System.out.print("Enter your position (1-9): ");

            int position = sc.nextInt();

            if (position < 1 || position > 9 ||
                board[position - 1] != ' ') {

                System.out.println("Invalid move! Try again.");
                continue;
            }

            board[position - 1] = human;

            printBoard();

            if (checkWin(human)) {
                System.out.println("Congratulations! You Win!");
                break;
            }

            if (isDraw()) {
                System.out.println("Game Draw!");
                break;
            }

            // AI turn
            System.out.println("AI is thinking...");

            bestMove();

            printBoard();

            if (checkWin(ai)) {
                System.out.println("AI Wins!");
                break;
            }

            if (isDraw()) {
                System.out.println("Game Draw!");
                break;
            }
        }

        System.out.println("Game Over!");

        sc.close();
    }
}