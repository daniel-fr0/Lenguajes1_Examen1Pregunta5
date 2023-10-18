package leng1;

import java.util.HashMap;
import java.util.HashSet;

public class Grafo<T> {
	private HashMap<T, HashSet<Arista<T>>> grafo = new HashMap<T, HashSet<Arista<T>>>();

	public HashSet<Arista<T>> getAristas(T vertice) {
		return grafo.get(vertice);
	}

	public void agregarVertice(T vertice) {
		if (grafo.containsKey(vertice)) {
			System.out.println("El vertice " + vertice + " ya existe");
			return;
		}

		grafo.put(vertice, new HashSet<Arista<T>>());
	}

	public boolean contieneVertice(T vertice) {
		return grafo.containsKey(vertice);
	}

	public void agregarArista(T origen, T destino) {
		if (!grafo.containsKey(origen)) {
			System.out.println("El vertice " + origen + " no existe");
			return;
		}

		if (!grafo.containsKey(destino)) {
			System.out.println("El vertice " + destino + " no existe");
			return;
		}

		grafo.get(origen).add(new Arista<T>(destino));
	}

	public void agregarArista(T origen, T destino, String peso) {
		if (!grafo.containsKey(origen)) {
			System.out.println("El vertice " + origen + " no existe");
			return;
		}

		if (!grafo.containsKey(destino)) {
			System.out.println("El vertice " + destino + " no existe");
			return;
		}

		grafo.get(origen).add(new Arista<T>(destino, peso));
	}

	// Busca si hay un camino entre dos vertices con DFS
	public boolean hayCamino(T origen, T destino) {
		if (!grafo.containsKey(origen)) {
			return false;
		}

		if (!grafo.containsKey(destino)) {
			return false;
		}

		HashSet<T> visitados = new HashSet<T>();
		return hayCamino(origen, destino, visitados);
	}

	private boolean hayCamino(T origen, T destino, HashSet<T> visitados) {
		if (origen.equals(destino)) return true;

		visitados.add(origen);

		for (Arista<T> arista: grafo.get(origen)) {
			T vertice = arista.getDestino();
			if (visitados.contains(vertice)) continue;
			if (hayCamino(vertice, destino, visitados)) return true;
		}

		return false;
	}
}