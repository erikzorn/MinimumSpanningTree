
/*
 * Erik Zorn - CSCE146 - ExtraCredit - Spring2016
 * 
 * Kruskal's Algorithm
 * Prim's Algorithm
 * 
 */

import java.util.*;

//import Graph.Edge;

public class UndirectedGraph {
	private class Vertex // creates vertex properties
	{
		String name;
		ArrayList<Edge> neighbors;

		public Vertex(String aName) {
			this.name = aName; // assigns name
			this.neighbors = new ArrayList<Edge>(); // arrayList of neighbors
		}
	}

	private class Edge {
		Vertex Vert1; // linked vertex
		Vertex Vert2;
		double weight; // distance to nextVert // distance of edge

		public Edge(Vertex aV1, Vertex aV2, double aWeight) {
			Vert1 = aV1;
			Vert2 = aV2;
			weight = aWeight;
		}
	}
	private Vertex origin;
	private ArrayList<Vertex> vertices; // all vertices
	private ArrayList<Edge> treeEdges;
	private ArrayList<Edge> edges;
	private ArrayList<Edge> markedEdges;
	private ArrayList<Vertex> treeVertices;
	private int loop = 1;

	public UndirectedGraph() { // initialize the graph
		origin = null;
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		treeEdges = new ArrayList<Edge>();
		treeVertices = new ArrayList<Vertex>();
		markedEdges = new ArrayList<Edge>();
	}

	public void addVertex(String aName) {
		if (vertexIsContained(aName)) // dont add duplicate
			return;
		Vertex v = new Vertex(aName);
		vertices.add(v);
		if (origin == null) { // if empty, make vertex origin
			origin = v;
		}
	}

	public boolean vertexIsContained(String aName) { // method checks to see if
														// the vertex is already
														// contained in the
														// graph
		for (Vertex vert : vertices) {
			if (vert.name.equals(aName))
				return true;
		}
		return false;
	}

	public void addEdge(String fromVert, String toVert, double weight) {
		Vertex v1 = getVertex(fromVert); // vert is linked by fromVert
		Vertex v2 = getVertex(toVert); // next Vert
		if (v1 == null || v2 == null)
			return;
		Edge edge = new Edge(v1, v2, weight);
		edges.add(edge);
		v1.neighbors.add(edge);
		v2.neighbors.add(edge);
	}

	public Vertex getVertex(String aName) { // return vertex by name identifier
		for (Vertex vert : vertices) {
			if (vert.name.equals(aName))
				return vert;
		}
		return null;
	}

	public void printVerticesAndNeighbors() {
		// for(Vertex vert : verticies ) {
		for (Edge edge : edges) {

			System.out.println(edge.Vert1.name + " --- " + edge.weight + " --- " + edge.Vert2.name);
		}
		System.out.println();
		// }
	}

	public void sortEdges() {
		boolean flag = true;
		while (flag) {
			flag = false;
			for (int j = 0; j < edges.size() - 1; j++) {
				if (edges.get(j).weight > edges.get(j + 1).weight) {
					Edge temp = edges.get(j);
					edges.set(j, edges.get(j + 1));
					edges.set(j + 1, temp);
					flag = true;
				}
			}
		}
	}

	public void Kruskals() {
		treeEdges.clear();
		sortEdges();
		for (Edge edge : edges) {
			markedEdges.clear();
			loop = 1;
			KruskalsAlgorithm(edge);
		}
	}

	public void KruskalsAlgorithm(Edge aEdge) {
		// if tree not empty and both vertices have neighbors
		markedEdges.clear();	loop = 1;
		checkForLoop(aEdge.Vert1, aEdge.Vert2);
		if (loop == 1) {
			treeEdges.add(aEdge);
		}
	}
	public void checkForLoop(Vertex vert, Vertex endVert) {
		if (vert.equals(endVert)) {
			loop = 0;
			return;
		}
		for (Edge edge : vert.neighbors) {
			// if edge has not yet been traversed
			if (!markedEdges.contains(edge) && treeEdges.contains(edge)) { 
				markedEdges.add(edge);
				if (edge.Vert1.equals(vert)) {

					checkForLoop(edge.Vert2, endVert); //
				} else {
					checkForLoop(edge.Vert1, endVert);
				}
			}
		}
	}

	public void Prims() {
		Vertex vert = vertices.get(1);
		markedEdges.clear();
		treeVertices.add(vert);
		PrimsAlgorithm(vert);

	}

	public void PrimsAlgorithm(Vertex aVert) {
		while (treeVertices.size() != vertices.size()) {
			Edge smallestEdge = null;
			for (Vertex vert : treeVertices) {
				for (Edge edge : vert.neighbors) {
					if (!markedEdges.contains(edge) && smallestEdge == null) {
						smallestEdge = edge;
					} else if (!markedEdges.contains(edge) && edge.weight < smallestEdge.weight)
						smallestEdge = edge;
				}
			}			
			if (treeVertices.contains(smallestEdge.Vert1) && treeVertices.contains(smallestEdge.Vert2))//(loop == 1) {
				markedEdges.add(smallestEdge);
			else {
				treeEdges.add(smallestEdge);
				if (!treeVertices.contains(smallestEdge.Vert1))
					treeVertices.add(smallestEdge.Vert1);
				else
					treeVertices.add(smallestEdge.Vert2);
			}	
		}
	}
	public void printGraph() {
		for (Edge edge : edges) {
			System.out.println(edge.Vert1.name + " --- " + edge.weight + " --- " + edge.Vert2.name);
		}
	}

	public void printTreeEdges() {
		for (Edge edge : treeEdges) {
			System.out.println(edge.Vert1.name + " --- " + edge.weight + " --- " + edge.Vert2.name);
		}
	}

	public void printNeighbors() {
		for (Vertex vert : vertices) {
			System.out.println("Home: " + vert.name);
			for (Edge edge : vert.neighbors) {
				System.out.println(edge.Vert1.name + " --- " + edge.weight + " --- " + edge.Vert2.name);
			}
		}
	}

	public void clearTreeEdges() {
		treeEdges.clear();
	}
}