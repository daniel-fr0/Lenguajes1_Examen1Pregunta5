package leng1;

import java.util.HashMap;
import java.util.HashSet;


public class Computadora {
    // Atributos
    private HashMap<String, Programa> programas = new HashMap<String, Programa>();
    private HashMap<String, Interprete> interpretes = new HashMap<String, Interprete>();
    private HashMap<String, Traductor> traductores = new HashMap<String, Traductor>();
    private HashSet<String> lenguajesEjecutables = new HashSet<String>();

    // Constructor
    public Computadora() {
        lenguajesEjecutables.add("LOCAL");
    }

    public static void main(String[] args)
    {
        Computadora computadora = new Computadora();
        computadora.iniciar();
    }

    public void iniciar() {
        while (true) {
            System.out.println("\n");
            String[] accion = pedirAccion();
            System.out.println("\n");
            if (accion == null) {
                System.out.println("No se especificó ninguna accion");
                continue;
            }

            // Procesar accion
            String comando = accion[0].toUpperCase();

            switch(comando) {
                case "DEFINIR":
                    System.out.println(definir(accion));
                    break;
                case "EJECUTABLE":
                    System.out.println(ejecutable(accion));
                    break;
                case "SALIR":
                    return;
                default:
                    System.out.println("Comando no reconocido");
                    break;
            }
        }
    }

    public String definir(String[] accion) {
        if (accion.length < 2) return "No se especificó el tipo a definir";

        String tipo = accion[1].toUpperCase();
        String nombre, lenguaje, base, origen, destino;

        switch(tipo) {
            case "PROGRAMA":
                if (accion.length < 4) {
                    return "No se especificó el nombre del programa o el lenguaje\nUSO: DEFINIR PROGRAMA <nombre> <lenguaje>";
                }

                nombre = accion[2];
                lenguaje = accion[3];

                if (programas.containsKey(nombre)) {
                    return "Ya existe un programa con ese nombre";
                }

                programas.put(nombre, new Programa(nombre, lenguaje));

                return String.format("Se definió el programa '%s', ejecutable en '%s'", nombre, lenguaje);

            case "INTERPRETE":
                if (accion.length < 4) {
                    return "No se especificó el lenguaje base o el lenguaje\nUSO: DEFINIR INTERPRETE <lenguaje base> <lenguaje>";
                }

                base = accion[2];
                lenguaje = accion[3];

                if (interpretes.containsKey(String.format("%s en %s", lenguaje, base))) {
                    return "Ya existe un interprete para ese lenguaje escrito en ese lenguaje base";
                }

                interpretes.put(String.format("%s en %s", lenguaje, base), new Interprete(base, lenguaje));

                // Cada vez que se agrega un interprete se actualiza la lista de lenguajes ejecutables
                if (lenguajesEjecutables.contains(base.toUpperCase())) {
                    lenguajesEjecutables.add(lenguaje.toUpperCase());
                }
                actualizarEjecutables();
                
                return String.format("Se definió un interprete para '%s', escrito en '%s'", lenguaje, base);

            case "TRADUCTOR":
                if (accion.length < 5) {
                    return "No se especificó el lenguaje base, el lenguaje origen o el lenguaje destino\nUSO: DEFINIR TRADUCTOR <lenguaje base> <lenguaje origen> <lenguaje destino>";
                }

                base = accion[2];
                origen = accion[3];
                destino = accion[4];

                if (traductores.containsKey(String.format("%s a %s en %s", origen, destino, base))) {
                    return "Ya existe un traductor para ese lenguaje origen y destino, escrito en ese lenguaje base";
                }

                traductores.put(String.format("%s a %s en %s", origen, destino, base), new Traductor(base, origen, destino));

                // Cada vez que se agrega un traductor se actualiza la lista de lenguajes ejecutables
                if (lenguajesEjecutables.contains(base.toUpperCase()) && lenguajesEjecutables.contains(destino.toUpperCase())) {
                    lenguajesEjecutables.add(origen.toUpperCase());
                }
                actualizarEjecutables();

                return String.format("Se definió un traductor de '%s' hacia '%s', escrito en '%s'", origen, destino, base);
                
            default:
                return "Tipo no reconocido";
        }
    }

    public String ejecutable(String[] accion) {
        if (accion.length < 2) {
            return "No se especificó el nombre del programa";
        }

        String nombre = accion[1];

        if (!programas.containsKey(nombre)) {
            return "No existe un programa con ese nombre";
        }

        String lenguaje = programas.get(nombre).getLenguaje().toUpperCase();

        if (lenguajesEjecutables.contains(lenguaje)) {
            return String.format("Si, es posible ejecutar el programa '%s'", nombre);
        }
        return String.format("No es posible ejecutar el programa '%s'", nombre);
    }

    public void actualizarEjecutables() {
        for (Interprete interprete : interpretes.values()) {
            // Se agregan todos los lenguajes que se puedan ejecutar con interpretes
            if (lenguajesEjecutables.contains(interprete.getLenguajeBase().toUpperCase())) {
                lenguajesEjecutables.add(interprete.getLenguaje().toUpperCase());
            }
        }

        // Se agregan todos los lenguajes que se puedan traducir a lenguajes ejecutables
        for (Traductor traductor : traductores.values()) {
            // Si no se puede ejecutar el traductor o el lenguaje destino, se ignora
            if (!lenguajesEjecutables.contains(traductor.getLenguajeBase().toUpperCase()) 
            || !lenguajesEjecutables.contains(traductor.getLenguajeDestino().toUpperCase())) continue;

            // Se agregan todos los lenguajes que se puedan traducir a lenguajes ejecutables
            if (lenguajesEjecutables.contains(traductor.getLenguajeDestino().toUpperCase())) {
                lenguajesEjecutables.add(traductor.getLenguajeOrigen().toUpperCase());
            }
        }
    }

    public String[] pedirAccion() {
        System.out.println("Acciones disponibles:");
        System.out.println("DEFINIR <tipo> [<argumentos>]");
        System.out.println("EJECUTABLE <nombre>");
        System.out.println("SALIR");
        System.out.println("Que desea hacer?\n");

        String accion = System.console().readLine();

        if (accion.length() == 0) {
            return null;
        }

        return accion.split(" ");
    }
}
