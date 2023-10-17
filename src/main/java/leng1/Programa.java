package leng1;

public class Programa {
	// Atributos
	private String nombre;
	private String lenguaje;

	// Getters
	public String getNombre() { return nombre; }
	public String getLenguaje() { return lenguaje; }

	// Constructor
	public Programa(String nombre, String lenguaje) {
		this.nombre = nombre;
		this.lenguaje = lenguaje;
	}
}
