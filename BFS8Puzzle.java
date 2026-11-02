import java.util.*;

public class BFS8Puzzle {

    static int[][] goal = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 0}
    };

    static class Node {
        int[][] state;
        Node parent;

        Node(int[][] state, Node parent) {
            this.state = state;
            this.parent = parent;
        }
    }

    static String getKey(int[][] state) {
        StringBuilder key = new StringBuilder();

        for (int[] row : state) {
            for (int num : row) {
                key.append(num);
            }
        }

        return key.toString();
    }

    static int[][] copy(int[][] state) {
        int[][] newState = new int[3][3];

        for (int i = 0; i < 3; i++) {
            newState[i] = state[i].clone();
        }

        return newState;
    }

    static void printState(int[][] state) {
        for (int[] row : state) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void solve(int[][] initial) {

        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        Node start = new Node(initial, null);

        queue.add(start);
        visited.add(getKey(initial));

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            Node current = queue.poll();

            if (getKey(current.state).equals(getKey(goal))) {

                Stack<Node> path = new Stack<>();

                while (current != null) {
                    path.push(current);
                    current = current.parent;
                }

                System.out.println("\nSolution Found!");

                int step = 0;

                while (!path.isEmpty()) {
                    System.out.println("Step " + step++);
                    printState(path.pop().state);
                }

                return;
            }

            int x = 0, y = 0;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (current.state[i][j] == 0) {
                        x = i;
                        y = j;
                    }
                }
            }

            for (int i = 0; i < 4; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < 3 &&
                    ny >= 0 && ny < 3) {

                    int[][] newState = copy(current.state);

                    newState[x][y] = newState[nx][ny];
                    newState[nx][ny] = 0;

                    String key = getKey(newState);

                    if (!visited.contains(key)) {

                        visited.add(key);

                        queue.add(new Node(
                            newState, current
                        ));
                    }
                }
            }
        }

        System.out.println("No solution exists!");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[][] initial = new int[3][3];

        System.out.println("BFS - 8 Puzzle Problem");
        System.out.println("Enter 0 for empty space:");

        System.out.println("Enter initial state:");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                initial[i][j] = sc.nextInt();
            }
        }

        System.out.println("\nInitial State:");
        printState(initial);

        solve(initial);

        sc.close();
    }
}