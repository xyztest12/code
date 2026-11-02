import java.util.*;

public class DFSMazeSolver {

    static int[][] maze = {
        {0, 0, 1, 0, 0},
        {0, 0, 1, 0, 1},
        {1, 0, 0, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 0, 0, 1, 0}
    };

    static int rows = 5;
    static int cols = 5;

    static boolean[][] visited = new boolean[5][5];

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static String[] directions = {
        "UP", "DOWN", "LEFT", "RIGHT"
    };

    static ArrayList<String> path = new ArrayList<>();

    static boolean dfs(int x, int y, int goalX, int goalY) {

        if (x == goalX && y == goalY) {
            path.add("(" + x + ", " + y + ")");
            return true;
        }

        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {

            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < rows &&
                ny >= 0 && ny < cols &&
                maze[nx][ny] == 0 &&
                !visited[nx][ny]) {

                if (dfs(nx, ny, goalX, goalY)) {
                    path.add("(" + x + ", " + y + ")");
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("DFS Maze Solving");
        System.out.println("0 = Free Path, 1 = Obstacle");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }

        System.out.print("\nEnter Start Row: ");
        int startX = sc.nextInt();

        System.out.print("Enter Start Column: ");
        int startY = sc.nextInt();

        System.out.print("Enter Goal Row: ");
        int goalX = sc.nextInt();

        System.out.print("Enter Goal Column: ");
        int goalY = sc.nextInt();

        if (startX < 0 || startX >= rows ||
            startY < 0 || startY >= cols ||
            goalX < 0 || goalX >= rows ||
            goalY < 0 || goalY >= cols ||
            maze[startX][startY] == 1 ||
            maze[goalX][goalY] == 1) {

            System.out.println("Invalid Start or Goal Position!");

        } else if (dfs(startX, startY, goalX, goalY)) {

            Collections.reverse(path);

            System.out.println("\nDFS Path:");

            for (String position : path) {
                System.out.println(position);
            }

            System.out.println("\nGoal Reached Successfully!");

        } else {
            System.out.println("\nNo Path Found!");
        }

        sc.close();
    }
}