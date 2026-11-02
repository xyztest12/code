import java.util.*;

public class AdversarialSearch {

    static int alphaBeta(int depth, int nodeIndex,
                         boolean isMax, int[] values,
                         int alpha, int beta, int maxDepth) {

        if (depth == maxDepth) {
            return values[nodeIndex];
        }

        if (isMax) {
            int best = Integer.MIN_VALUE;

            for (int i = 0; i < 2; i++) {
                int value = alphaBeta(
                    depth + 1, nodeIndex * 2 + i,
                    false, values, alpha, beta, maxDepth
                );

                best = Math.max(best, value);
                alpha = Math.max(alpha, best);

                if (beta <= alpha) {
                    System.out.println("Alpha-Beta Pruning occurred");
                    break;
                }
            }
            return best;

        } else {
            int best = Integer.MAX_VALUE;

            for (int i = 0; i < 2; i++) {
                int value = alphaBeta(
                    depth + 1, nodeIndex * 2 + i,
                    true, values, alpha, beta, maxDepth
                );

                best = Math.min(best, value);
                beta = Math.min(beta, best);

                if (beta <= alpha) {
                    System.out.println("Alpha-Beta Pruning occurred");
                    break;
                }
            }
            return best;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Adversarial Search using Alpha-Beta Pruning");

        System.out.print("Enter tree depth: ");
        int depth = sc.nextInt();

        if (depth < 0 || depth > 20) {
            System.out.println("Invalid depth!");
            sc.close();
            return;
        }

        int size = 1 << depth;
        int[] values = new int[size];

        System.out.println("Enter " + size + " leaf node values:");

        for (int i = 0; i < size; i++) {
            values[i] = sc.nextInt();
        }

        int result = alphaBeta(
            0, 0, true, values,
            Integer.MIN_VALUE,
            Integer.MAX_VALUE,
            depth
        );

        System.out.println("Optimal Value: " + result);

        sc.close();
    }
}