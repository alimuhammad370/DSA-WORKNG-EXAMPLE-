import java.util.*;

public class GraphOperations {

    public static void calculateDegrees(int[][] adjacencyMatrix) {
        int nodes = adjacencyMatrix.length;
        for (int i = 0; i < nodes; i++) {
            int degree = 0;
            for (int j = 0; j < nodes; j++) {
                degree += adjacencyMatrix[i][j];
            }
            System.out.println("Degree of node " + i + ": " + degree);
        }
    }

    public static void breadthFirstTraversal(List<List<Integer>> adjacencyList, int startNode) {
        boolean[] visited = new boolean[adjacencyList.size()];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        visited[startNode] = true;

        System.out.print("Breadth First Traversal: ");
        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            System.out.print(currentNode + " ");

            for (int neighbor : adjacencyList.get(currentNode)) {
                if (!visited[neighbor]) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                }
            }
        }
        System.out.println();
    }

    public static void depthFirstTraversal(List<List<Integer>> adjacencyList, int startNode) {
        boolean[] visited = new boolean[adjacencyList.size()];
        System.out.print("Depth First Traversal: ");
        depthFirstTraversalHelper(adjacencyList, startNode, visited);
        System.out.println();
    }

    private static void depthFirstTraversalHelper(List<List<Integer>> adjacencyList, int currentNode, boolean[] visited) {
        visited[currentNode] = true;
        System.out.print(currentNode + " ");

        for (int neighbor : adjacencyList.get(currentNode)) {
            if (!visited[neighbor]) {
                depthFirstTraversalHelper(adjacencyList, neighbor, visited);
            }
        }
    }

    public static void main(String[] args) {
        int[][] adjacencyMatrix = {
            {0, 1, 1, 0},
            {1, 0, 1, 1},
            {1, 1, 0, 1},
            {0, 1, 1, 0}
        };

        System.out.println("Calculating degrees for an undirected graph:");
        calculateDegrees(adjacencyMatrix);

        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        adjacencyList.get(0).add(1);
        adjacencyList.get(0).add(2);
        adjacencyList.get(1).add(2);
        adjacencyList.get(2).add(0);
        adjacencyList.get(2).add(3);
        adjacencyList.get(3).add(3);

        System.out.println("\nPerforming BFS and DFS for a directed graph:");
        breadthFirstTraversal(adjacencyList, 2);
        depthFirstTraversal(adjacencyList, 2);
    }
}
