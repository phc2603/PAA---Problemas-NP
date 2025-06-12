import java.util.*;

/*
    * Lógica do algoritmo guloso: Ordena em uma lista, os vértices com maior grau.
    * Para cada vértice V, verificar as cores atribuídas aos vinhos, conectados pela aresta VW
    * Para todos os vértices W adjacentes ao vértice V, colorir de outra cor, diferente de Y
    * Atribua a menor cor (inteiro positivo, começando de 0);
*/

class GreedyGraphColoring{
    private List<Integer> colors;
    private Map<String, Integer> vertexColor;

    public GreedyGraphColoring(){
        vertexColor = new HashMap<>();
        colors = new ArrayList<>();
    }

    public void paintAllVertexs(List<Map.Entry<String, List<String>>> graph){
        for (Map.Entry<String, List<String>> vertex : graph){
            int color = 0;
            for (String adjacentVertex : vertex.getValue()){
                if (vertexColor.containsKey(adjacentVertex) && vertexColor.get(adjacentVertex) == color){
                    color++;
                }
            }
            vertexColor.put(vertex.getKey(), color);
            if (!colors.contains(color)){
                colors.add(color);
            }
        }

        for (String vertex : vertexColor.keySet()){
            System.out.println("Vértice: "+vertex + "----> Cor: " + String.valueOf(vertexColor.get(vertex)));
        }

        System.out.println("Quantidade mínima de cores: " + String.valueOf(this.getMinimumColors()));
    }

    private int getMinimumColors(){
        return colors.size();
    }
    
}

class BuildGraph{
    private Map<String, List<String>> graph;
    List<Map.Entry<String, List<String>>> orderedGraphByDegreeList;

    public BuildGraph() {
        graph = new HashMap<>();
    }

    public void addVertex(String vertex){
        graph.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(String originVertex, String destinyVertex){ //{"A": ["B", "C"]}
        graph.get(originVertex).add(destinyVertex);
        graph.get(destinyVertex).add(originVertex);
    }

    public void showGraph(){
        for (String vertex : graph.keySet()){
            System.out.println(vertex + " ---> " + graph.get(vertex));
        }
    }

    public void orderGraphByDegree(){
        this.orderedGraphByDegreeList = new ArrayList<>(this.graph.entrySet());

        // Ordenar por tamanho da lista (grau), em ordem decrescente
        orderedGraphByDegreeList.sort((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()));


    }

    public Map<String, List<String>>getGraph(){
        return graph;
    }

    public List<Map.Entry<String, List<String>>> getOrderedGraph(){
        return this.orderedGraphByDegreeList;
    }

}



public class GraphColoring{
        public static void main(String[] args){
        BuildGraph graph = new BuildGraph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "C");
        graph.addEdge("D", "A");
        graph.addEdge("E", "B");

        GreedyGraphColoring greedyGraph = new GreedyGraphColoring();
        graph.orderGraphByDegree();
        greedyGraph.paintAllVertexs(graph.getOrderedGraph());
    }
}