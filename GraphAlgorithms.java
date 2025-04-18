public class GraphAlgorithms {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java GraphAlgorithms <filename> <startVertex>");
            return;
        }

        String filename = "Users/odhranmeade/Desktop/AlgProject/wGraph1";
        String startVertex = args[0];

        Graph graph = new Graph(filename);

        System.out.println("Graph Loaded from " + filename + ":");
        System.out.println("Adjacency List: " + graph.getAdjacencyList());

        System.out.println("\nDepth First Traversal (starting from " + startVertex + "):");
        DFTraversal.DFTraversal(graph, startVertex);
        System.out.println();

        System.out.println("\nBreadth First Traversal (starting from " + startVertex + "):");
        BFTraversal.BFTraversal(graph, startVertex);
        System.out.println();

        System.out.println("\nPrim's Algorithm (starting from " + startVertex + "):");
        PrimsAlgorithm.PrimsAlgorithm(graph, startVertex);
        System.out.println();

        System.out.println("\nDijkstra's Algorithm (starting from " + startVertex + "):");
        DijkstrasAlgorithm.DijkstrasAlgorithm(graph, startVertex);
        System.out.println();
    }
}