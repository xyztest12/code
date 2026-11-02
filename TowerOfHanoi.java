import java.util.*;

public class TowerOfHanoi {

    static int moveCount = 0;

    static void solveHanoi(int n, char source, char auxiliary, char destination) {

        if (n == 1) {
            moveCount++;
            System.out.println("Move " + moveCount +
                    ": Disk 1 from " + source + " to " + destination);
            return;
        }

        solveHanoi(n - 1, source, destination, auxiliary);

        moveCount++;
        System.out.println("Move " + moveCount +
                ": Disk " + n + " from " + source + " to " + destination);

        solveHanoi(n - 1, auxiliary, source, destination);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Tower of Hanoi using State Space Search");

        System.out.print("Enter number of disks: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Please enter a positive number of disks.");
        } else {
            System.out.println("\nSteps to solve Tower of Hanoi:");

            solveHanoi(n, 'A', 'B', 'C');

            System.out.println("\nTotal Moves: " + moveCount);
            System.out.println("Goal State Reached Successfully!");
        }

        sc.close();
    }
}