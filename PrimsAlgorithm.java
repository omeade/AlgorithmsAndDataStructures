import java.util.*;

class PrimsAlgorithm {

    public static void PrimsAlgorithm(Graph graph, String startVertex) {
        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> key = new HashMap<>();
        HashSet<String> mstSet = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

        for (String vertex : graph.getAdjacencyList().keySet()) {
            key.put(vertex, Integer.MAX_VALUE);
        }
        key.put(startVertex, 0);
        pq.add(new Edge(startVertex, 0));

        parent.put(startVertex, null);

        System.out.println("Prim's Algorithm Steps:");
        while (!pq.isEmpty()) {
            Edge minEdge = pq.poll();
            String u = minEdge.getDestination();

            System.out.println("  - Adding vertex: " + u);
            mstSet.add(u);

            List<Edge> neighbors = graph.getNeighbors(u);
            if (neighbors != null) {
                for (Edge edge : neighbors) {
                    String v = edge.getDestination();
                    int weight = edge.getWeight();
                    if (!mstSet.contains(v) && weight < key.get(v)) {
                        key.put(v, weight);
                        parent.put(v, u);
                        pq.add(new Edge(v, weight));
                        System.out.println("    - Updating key[" + v + "] to " + weight + ", parent[" + v + "] to " + u);
                    }
                }
            }
            printHeap(pq);
            printParent(parent);
            printKey(key);
        }

        System.out.println("\nMinimum Spanning Tree:");
        printMST(parent, graph);
    }

    private static void printMST(Map<String, String> parent, Graph graph) {
        for (String vertex : graph.getAdjacencyList().keySet()) {
            if (parent.get(vertex) != null) {
                System.out.println(parent.get(vertex) + " - " + vertex);
            }
        }
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

    private static void printKey(Map<String, Integer> key) {
        System.out.print("    - Key: ");
        for (Map.Entry<String, Integer> entry : key.entrySet()) {
            System.out.print(entry.getKey() + ":" + entry.getValue() + " ");
        }
        System.out.println();
    }
}