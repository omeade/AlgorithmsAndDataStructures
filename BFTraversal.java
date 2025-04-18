import java.util.*;

class BFTraversal {

    public static void BFTraversal(Graph graph, String startVertex) {
        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();

        queue.add(startVertex);
        visited.add(startVertex);

        while (!queue.isEmpty()) {
            String currentVertex = queue.poll();
            System.out.print(currentVertex + " "); // Process the vertex

            List<Edge> neighbors = graph.getNeighbors(currentVertex);
            if (neighbors != null) {
                for (Edge edge : neighbors) {
                    if (!visited.contains(edge.getDestination())) {
                        queue.add(edge.getDestination());
                        visited.add(edge.getDestination());
                    }
                }
            }
        }
    }
}