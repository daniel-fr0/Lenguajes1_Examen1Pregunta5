package leng1;

public class Arista<T> {
	// Atributos
	private T destino;
	private String peso;

	// Getters
	public T getDestino() { return destino; }
	public String getPeso() { return peso; }

	// Constructor sin peso
	public Arista(T destino) {
		this.destino = destino;
		this.peso = null;
	}

	// Constructor con peso
	public Arista(T destino, String peso) {
		this.destino = destino;
		this.peso = peso;
	}
}
