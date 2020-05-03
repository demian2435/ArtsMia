package it.polito.tdp.artsmia.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {

	private Graph<ArtObject, DefaultWeightedEdge> grafo;
	private Map<Integer, ArtObject> idMap = new HashMap<Integer, ArtObject>();
	private ArtsmiaDAO dao = new ArtsmiaDAO();

	public Model() {
	}

	public void creaGrafo() {
		grafo = new SimpleWeightedGraph<ArtObject, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		dao.listObjects(idMap);
		Graphs.addAllVertices(grafo, idMap.values());
		List<Arco> archi = dao.listArchi(idMap);
		for (Arco A : archi) {
			Graphs.addEdge(grafo, A.getO1(), A.getO2(), A.getPeso());
		}
		
		//System.out.println(String.format("Grafo creato, %d vertici %d archi", grafo.vertexSet().size(), grafo.edgeSet().size()));
	}
	
	public int nVertici() {
		return grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return grafo.edgeSet().size();
	}
}
