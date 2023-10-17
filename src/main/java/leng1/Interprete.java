package leng1;

public class Interprete {
	// Atributos
	private String lenguajeBase;
	private String lenguaje;

	// Getters
	public String getLenguajeBase() { return lenguajeBase; }
	public String getLenguaje() { return lenguaje; }

	// Constructor
	public Interprete(String lenguajeBase, String lenguaje) {
		this.lenguajeBase = lenguajeBase;
		this.lenguaje = lenguaje;
	}
}
