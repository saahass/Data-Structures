package apps;

import structures.*;
import structures.Vertex.Neighbor;

import java.util.ArrayList;

public class MST {
	
	/**
	 * COMPLETE THIS METHOD
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {
		PartialTreeList L = new PartialTreeList();
		for (int i = 0; i < graph.vertices.length; i++) {
			PartialTree T = new PartialTree(graph.vertices[i]);
			for (Neighbor n = T.getRoot().neighbors; n != null; n = n.next) {
				PartialTree.Arc arc = new PartialTree.Arc(T.getRoot(), n.vertex, n.weight);
				T.getArcs().insert(arc);
			}
			L.append(T);
		}
		return L;
	}

	/**
	 * COMPLETE THIS METHOD
	 * Executes the algorithm on a graph, starting with the initial partial tree list
	 * 
	 * @param ptlist Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
	 */
	public static ArrayList<PartialTree.Arc> execute(PartialTreeList ptlist) {
		ArrayList<PartialTree.Arc> results = new ArrayList<>();
		while (ptlist.size() > 1) {
			PartialTree PTX = ptlist.remove();
			MinHeap<PartialTree.Arc> PQX = PTX.getArcs();
			PartialTree.Arc a = PQX.deleteMin();
			Vertex v = a.v2.parent;
			while (v.name.equals(PTX.getRoot().name) ) {
				a = PQX.deleteMin(); v = a.v2.parent;
			}
			PartialTree PTY = ptlist.removeTreeContaining(a.v1);
            if (PTY == null) {
            		PTY = ptlist.removeTreeContaining(a.v2);
            }
            if (PTY == null) continue;
            results.add(a);
			PTX.merge(PTY);
			ptlist.append(PTX);
		}
		return results;
	}
	
}
