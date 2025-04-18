import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Graph {
    private Map<String, List<Edge>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    public Graph(String filename) {
        this(); // Call the default constructor to initialize adjacencyList
        readGraphFromFile(filename);
    }

    public void addVertex(String vertex) {
        adjacencyList.put(vertex, new ArrayList<>());
    }

    public void addEdge(String source, String destination, int weight) {
        if (!adjacencyList.containsKey(source)) {
            addVertex(source);
        }
        if (!adjacencyList.containsKey(destination)) {
            addVertex(destination);
        }
        adjacencyList.get(source).add(new Edge(destination, weight));
        adjacencyList.get(destination).add(new Edge(source, weight)); // For undirected graph
    }

    public List<Edge> getNeighbors(String vertex) {
        return adjacencyList.get(vertex);
    }

    private void readGraphFromFile(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            if (scanner.hasNextLine()) {
                String firstLine = scanner.nextLine();
                String[] parts = firstLine.split(" ");
                if (parts.length >= 1) {
                    int numVertices = Integer.parseInt(parts[0]);
                    System.out.println("Number of vertices in file: " + numVertices);
                }
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                if (parts.length == 3) {
                    String source = parts[0];
                    String destination = parts[1];
                    int weight = Integer.parseInt(parts[2]);
                    addEdge(source, destination, weight);
                } else {
                    System.err.println("Warning: Skipping invalid line in file: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + filename);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Error: Invalid file format in " + filename);
        }
    }

    public Map<String, List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }
}

class Edge {
    String destination;
    int weight;

    public Edge(String destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public String getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }
}