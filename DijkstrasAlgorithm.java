import java.util.*;

class DijkstrasAlgorithm {

    public static void DijkstrasAlgorithm(Graph graph, String startVertex) {
        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> dist = new HashMap<>();
        HashSet<String> sptSet = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

        for (String vertex : graph.getAdjacencyList().keySet()) {
            dist.put(vertex, Integer.MAX_VALUE);
        }
        dist.put(startVertex, 0);
        pq.add(new Edge(startVertex, 0));
        parent.put(startVertex, null);

        System.out.println("Dijkstra's Algorithm Steps:");
        while (!pq.isEmpty()) {
            Edge minEdge = pq.poll();
            String u = minEdge.getDestination();

            System.out.println("  - Processing vertex: " + u);
            sptSet.add(u);

            List<Edge> neighbors = graph.getNeighbors(u);
            if (neighbors != null) {
                for (Edge edge : neighbors) {
                    String v = edge.getDestination();
                    int weight = edge.getWeight();
                    if (!sptSet.contains(v) && dist.get(u) != Integer.MAX_VALUE && dist.get(u) + weight < dist.get(v)) {
                        dist.put(v, dist.get(u) + weight);
                        parent.put(v, u);
                        pq.add(new Edge(v, dist.get(v)));
                        System.out.println("    - Updating dist[" + v + "] to " + dist.get(v) + ", parent[" + v + "] to " + u);
                    }
                }
            }
            printHeap(pq);
            printParent(parent);
            printDist(dist);
        }

        System.out.println("\nShortest Path Tree:");
        printSPT(parent, graph, startVertex);
    }

    private static void printSPT(Map<String, String> parent, Graph graph, String startVertex) {
        for (String vertex : graph.getAdjacencyList().keySet()) {
            if (!vertex.equals(startVertex)) {
                System.out.println("Path to " + vertex + ": " + getPath(parent, vertex) + ", Distance: " + getDistance(parent, vertex, graph));
            }
        }
    }

    private static String getPath(Map<String, String> parent, String vertex) {
        if (parent.get(vertex) == null) {
            return vertex;
        }
        return getPath(parent, parent.get(vertex)) + " -> " + vertex;
    }

    private static int getDistance(Map<String, String> parent, String vertex, Graph graph) {
        if (parent.get(vertex) == null) {
            return 0;
        }
        String current = vertex;
        int distance = 0;
        while (parent.get(current) != null) {
            String prev = parent.get(current);
            for (Edge edge : graph.getNeighbors(current)) {
                if (edge.getDestination().equals(prev)) {
                    distance += edge.getWeight();
                    break;
                }
            }
            current = prev;
        }
        return distance;
    }

    private static void printHeap(PriorityQueue<Edge> pq) {
        System.out.print("    - Heap: ");
        PriorityQueue<Edge> temp = new PriorityQueue<>(pq);
        while (!temp.isEmpty()) {
            System.out.print("(" + temp.poll().getDestination() + ") ");
        }
        System.out.println();
    }

    private static void printParent(Map<String, String> parent) {
        System.out.print("    - Parent: ");
        for (Map.Entry<String, String> entry : parent.entrySet()) {
            System.out.print(entry.getKey() + ":" + entry.getValue() + " ");
        }
        System.out.println();
    }

    private static void printDist(Map<String, Integer> dist) {
        System.out.print("    - Distance: ");
        for (Map.Entry<String, Integer> entry : dist.entrySet()) {
            System.out.print(entry.getKey() + ":" + entry.getValue() + " ");
        }
        System.out.println();
    }
}