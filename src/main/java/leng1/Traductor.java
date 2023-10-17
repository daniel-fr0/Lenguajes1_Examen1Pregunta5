package leng1;

public class Traductor {
	// Atributos
	private String lenguajeBase;
	private String lenguajeOrigen;
	private String lenguajeDestino;

	// Getters
	public String getLenguajeBase() { return lenguajeBase; }
	public String getLenguajeOrigen() { return lenguajeOrigen; }
	public String getLenguajeDestino() { return lenguajeDestino; }

	// Constructor
	public Traductor(String lenguajeBase, String lenguajeOrigen, String lenguajeDestino) {
		this.lenguajeBase = lenguajeBase;
		this.lenguajeOrigen = lenguajeOrigen;
		this.lenguajeDestino = lenguajeDestino;
	}
}
