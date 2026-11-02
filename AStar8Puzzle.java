import java.util.*;

public class AStar8Puzzle {

    static class Node {
        int[][] state;
        int g, h;
        Node parent;

        Node(int[][] state, int g, Node parent) {
            this.state = state;
            this.g = g;
            this.h = heuristic(state);
            this.parent = parent;
        }

        int f() {
            return g + h;
        }
    }

    static int[][] goal = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 0}
    };

    static int heuristic(int[][] state) {
        int distance = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                int value = state[i][j];

                if (value != 0) {
                    int goalX = (value - 1) / 3;
                    int goalY = (value - 1) % 3;

                    distance += Math.abs(i - goalX)
                              + Math.abs(j - goalY);
                }
            }
        }

        return distance;
    }

    static String getKey(int[][] state) {
        StringBuilder key = new StringBuilder();

        for (int[] row : state) {
            for (int value : row) {
                key.append(value);
            }
        }

        return key.toString();
    }

    static int[][] copyState(int[][] state) {
        int[][] copy = new int[3][3];

        for (int i = 0; i < 3; i++) {
            copy[i] = state[i].clone();
        }

        return copy;
    }

    static void printState(int[][] state) {
        for (int[] row : state) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void solve(int[][] initial) {

        PriorityQueue<Node> open = new PriorityQueue<>(
            Comparator.comparingInt(Node::f)
        );

        HashMap<String, Integer> visited = new HashMap<>();

        open.add(new Node(initial, 0, null));

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        String[] moves = {"UP", "DOWN", "LEFT", "RIGHT"};

        while (!open.isEmpty()) {

            Node current = open.poll();

            String key = getKey(current.state);

            if (visited.containsKey(key) &&
                visited.get(key) <= current.g) {
                continue;
            }

            visited.put(key, current.g);

            if (current.h == 0) {

                Stack<Node> path = new Stack<>();

                Node temp = current;

                while (temp != null) {
                    path.push(temp);
                    temp = temp.parent;
                }

                System.out.println("\nSolution Found!");
                System.out.println("Total Moves: " + current.g);

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

                    int[][] newState = copyState(current.state);

                    newState[x][y] = newState[nx][ny];
                    newState[nx][ny] = 0;

                    String newKey = getKey(newState);

                    if (!visited.containsKey(newKey) ||
                        current.g + 1 < visited.get(newKey)) {

                        open.add(new Node(
                            newState,
                            current.g + 1,
                            current
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

        System.out.println("A* Search - 8 Puzzle Problem");
        System.out.println("Enter 0 for blank space:");

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