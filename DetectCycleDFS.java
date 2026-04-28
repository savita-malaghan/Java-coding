import java.util.*;

public class DetectCycleDFS {
    private int V; // number of vertices
    private List<List<Integer>> adj; // adjacency list

    public DetectCycleDFS(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // Add edge u -> v
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
    }

    // DFS helper
    private boolean dfs(int node, boolean[] visited, boolean[] recStack) {
        if (recStack[node]) return true; // cycle found
        if (visited[node]) return false; // already processed

        visited[node] = true;
        recStack[node] = true;

        for (int neighbor : adj.get(node)) {
            if (dfs(neighbor, visited, recStack)) return true;
        }

        recStack[node] = false; // backtrack
        return false;
    }

    // Main function
    public boolean hasCycle() {
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (dfs(i, visited, recStack)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        DetectCycleDFS graph = new DetectCycleDFS(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0); // cycle here
        graph.addEdge(2, 3);

        System.out.println("Graph has cycle: " + graph.hasCycle());
    }
}
