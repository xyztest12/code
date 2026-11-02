import java.util.*;

public class LocalBeam{

    static int[][] distance;
    static int n;
    static int beamWidth = 3;

    static class State {
        ArrayList<Integer> path;
        int cost;

        State(ArrayList<Integer> path) {
            this.path = new ArrayList<>(path);
            this.cost = calculateCost(path);
        }
    }

    // Calculate total path cost
    static int calculateCost(ArrayList<Integer> path) {
        int cost = 0;

        for (int i = 0; i < path.size() - 1; i++) {
            cost += distance[path.get(i)][path.get(i + 1)];
        }

        if (path.size() == n) {
            cost += distance[path.get(n - 1)][path.get(0)];
        }

        return cost;
    }

    // Generate neighboring states
    static ArrayList<State> getNeighbors(State current) {
        ArrayList<State> neighbors = new ArrayList<>();

        for (int i = 1; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {

                ArrayList<Integer> newPath =
                    new ArrayList<>(current.path);

                Collections.swap(newPath, i, j);

                neighbors.add(new State(newPath));
            }
        }

        return neighbors;
    }

    // Local Beam Search algorithm
    static State beamSearch() {

        ArrayList<Integer> initialPath = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            initialPath.add(i);
        }

        ArrayList<State> beam = new ArrayList<>();
        beam.add(new State(initialPath));

        State best = beam.get(0);

        for (int iteration = 0; iteration < 100; iteration++) {

            ArrayList<State> candidates = new ArrayList<>();

            for (State current : beam) {
                candidates.addAll(getNeighbors(current));
            }

            if (candidates.isEmpty()) {
                break;
            }

            candidates.sort(
                Comparator.comparingInt(s -> s.cost)
            );

            beam = new ArrayList<>();

            HashSet<String> visited = new HashSet<>();

            for (State candidate : candidates) {

                String key = candidate.path.toString();

                if (visited.add(key)) {
                    beam.add(candidate);

                    if (beam.size() == beamWidth) {
                        break;
                    }
                }
            }

            if (beam.get(0).cost < best.cost) {
                best = beam.get(0);
            }
        }

        return best;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Traveling Salesman Problem");
        System.out.print("Enter number of cities: ");

        n = sc.nextInt();

        if (n < 3) {
            System.out.println("Enter at least 3 cities.");
            sc.close();
            return;
        }

        distance = new int[n][n];

        System.out.println("Enter distance matrix:");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = sc.nextInt();
            }
        }

        State result = beamSearch();

        System.out.println("\nOptimal Path Found:");

        for (int city : result.path) {
            System.out.print("City " + (city + 1) + " -> ");
        }

        System.out.println("City " + (result.path.get(0) + 1));

        System.out.println("Minimum Distance: " + result.cost);

        sc.close();
    }
}