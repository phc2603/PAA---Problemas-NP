import java.util.*;

public class BacktrackGraphColoringMap {
    private Map<String, List<String>> graph;
    private Map<String, Integer> colorMap;
    private List<String> vertices;

    public BacktrackGraphColoringMap(Map<String, List<String>> graph) {
        this.graph = graph;
        this.colorMap = new HashMap<>();
        this.vertices = new ArrayList<>(graph.keySet());
    }

    public boolean isSafe(String vertex, int color) {
        for (String neighbor : graph.get(vertex)) {
            if (colorMap.containsKey(neighbor) && colorMap.get(neighbor) == color) {
                return false;
            }
        }
        return true;
    }

    public boolean colorGraph(int index, int maxColors) {
        if (index == vertices.size()) {
            return true;
        }

        String vertex = vertices.get(index);
        for (int c = 1; c <= maxColors; c++) {
            if (isSafe(vertex, c)) {
                colorMap.put(vertex, c);
                if (colorGraph(index + 1, maxColors)) {
                    return true;
                }
                colorMap.remove(vertex); // backtrack
            }
        }

        return false;
    }

    public void solve() {
        for (int k = 1; k <= graph.size(); k++) {
            colorMap.clear();
            if (colorGraph(0, k)) {
                System.out.println("Solução ótima encontrada com " + k + " cores:");
                for (String vertex : vertices) {
                    System.out.println("Vértice " + vertex + " -> Cor " + colorMap.get(vertex));
                }
                return;
            }
        }
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B", "C", "D"));
        graph.put("B", Arrays.asList("A", "C", "E"));
        graph.put("C", Arrays.asList("A", "B"));
        graph.put("D", Arrays.asList("A", "E"));
        graph.put("E", Arrays.asList("B", "D"));

        BacktrackGraphColoringMap solver = new BacktrackGraphColoringMap(graph);
        solver.solve();
    }
}
