import java.util.HashSet;
import java.util.Map;
import java.util.List;

class DFTraversal {

    public static void DFTraversal(Graph graph, String startVertex) {
        HashSet<String> visited = new HashSet<>();
        DFTraversalRecursive(graph, startVertex, visited);
    }

    private static void DFTraversalRecursive(Graph graph, String vertex, HashSet<String> visited) {
        visited.add(vertex);
        System.out.print(vertex + " "); // Process the vertex (e.g., print it)

        List<Edge> neighbors = graph.getNeighbors(vertex);
        if (neighbors != null) {
            for (Edge edge : neighbors) {
                if (!visited.contains(edge.getDestination())) {
                    DFTraversalRecursive(graph, edge.getDestination(), visited);
                }
            }
        }
    }
}