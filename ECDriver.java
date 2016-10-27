/*
 * Erik Zorn - CSCE 146 - EC Driver
 */
public class ECDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UndirectedGraph g = new UndirectedGraph();
		
		
		System.out.println("Adding verticies");
		g.addVertex("A");
		g.addVertex("B");
		g.addVertex("C");
		g.addVertex("D");
		g.addVertex("E");
		g.addVertex("F");
		g.addVertex("G");

		System.out.println("Adding edges");
		
		g.addEdge("A", "D", 3);
		g.addEdge("A", "C", 3);
		g.addEdge("A", "B", 2);
		
		g.addEdge("B", "C", 4);
		g.addEdge("B", "E", 3);
		
		g.addEdge("C", "D", 5);
		g.addEdge("C", "E", 1);
		
		g.addEdge("D", "F", 7);
		
		g.addEdge("E", "F", 8);
		
		g.addEdge("F", "G", 9);

		
		System.out.println("Printing graph's vertices and edges...");
		g.printGraph();
		
		g.Kruskals();
		System.out.println("\nFinding Minimum Spanning Tree Using Kruskals's Algorithm...");
		g.printTreeEdges();
		
		g.clearTreeEdges();
		
		g.Prims();
		System.out.println("\nFinding Minimum Spanning Tree Using Prim's Algorithm...");
		g.printTreeEdges();
		
	}

}
