package leng1;

import java.util.HashMap;

public class Computadora {
    private HashMap<String, Programa> programas = new HashMap<String, Programa>();
    private HashMap<String, Interprete> interpretes = new HashMap<String, Interprete>();
    private HashMap<String, Traductor> traductores = new HashMap<String, Traductor>();

    public static void main(String[] args)
    {
        Computadora computadora = new Computadora();
        computadora.iniciar();
    }

    public void iniciar() {
        while (true) {
            System.out.println("\n");
            String[] accion = pedirAccion();
            if (accion == null) {
                continue;
            }

            // Procesar accion
            String comando = accion[0].toUpperCase();

            switch(comando) {
                case "DEFINIR":
                    if (accion.length < 2) {
                        System.out.println("No se especifico el tipo a definir");
                        continue;
                    }
                    
                    definir(accion);
                    break;
                case "EJECUTABLE":
                    if (accion.length < 2) {
                        System.out.println("No se especifico el nombre del programa");
                        continue;
                    }

                    ejecutable(accion);
                    break;

                case "SALIR":
                    return;
                
                default:
                    System.out.println("Comando no reconocido");
                    break;
            }
        }
    }

    public void definir(String[] accion) {
        String tipo = accion[1].toUpperCase();
        String nombre, lenguaje, base, origen, destino;
        switch(tipo) {
            case "PROGRAMA":
                if (accion.length < 4) {
                    System.out.println("No se especifico el nombre del programa o el lenguaje");
                    System.out.println("USO: DEFINIR PROGRAMA <nombre> <lenguaje>");
                    return;
                }

                nombre = accion[2];
                lenguaje = accion[3];

                if (programas.containsKey(nombre)) {
                    System.out.println("Ya existe un programa con ese nombre");
                    return;
                }

                programas.put(nombre, new Programa(nombre, lenguaje));

                System.out.println(String.format("Se definio el programa '%s', ejecutable en '%s'", nombre, lenguaje));
                break;

            case "INTERPRETE":
                if (accion.length < 4) {
                    System.out.println("No se especifico el lenguaje base o el lenguaje");
                    System.out.println("USO: DEFINIR INTERPRETE <lenguaje base> <lenguaje>");
                    return;
                }

                base = accion[2];
                lenguaje = accion[3];

                if (interpretes.containsKey(String.format("%s en %s", lenguaje, base))) {
                    System.out.println("Ya existe un interprete para ese lenguaje escrito en ese lenguaje base");
                    return;
                }

                interpretes.put(String.format("%s en %s", lenguaje, base), new Interprete(base, lenguaje));
                
                System.out.println(String.format("Se definio un interprete '%s', escrito en '%s'", lenguaje, base));
                break;

            case "TRADUCTOR":
                if (accion.length < 5) {
                    System.out.println("No se especifico el lenguaje base, el lenguaje origen o el lenguaje destino");
                    System.out.println("USO: DEFINIR TRADUCTOR <lenguaje base> <lenguaje origen> <lenguaje destino>");
                    return;
                }

                base = accion[2];
                origen = accion[3];
                destino = accion[4];

                if (traductores.containsKey(String.format("%s a %s en %s", origen, destino, base))) {
                    System.out.println("Ya existe un traductor para ese lenguaje origen y destino, escrito en ese lenguaje base");
                    return;
                }

                traductores.put(String.format("%s a %s en %s", origen, destino, base), new Traductor(base, origen, destino));

                System.out.println(String.format("Se definio un traductor '%s' a '%s', escrito en '%s'", origen, destino, base));
                break;
                
            default:
                System.out.println("Tipo no reconocido");
                break;
        }
    }

    public void ejecutable(String[] accion) {
        if (accion.length < 2) {
            System.out.println("No se especifico el nombre del programa");
            return;
        }

        String nombre = accion[1];

        if (!programas.containsKey(nombre)) {
            System.out.println("No existe un programa con ese nombre");
            return;
        }

        System.out.println("IMPLEMENTAR");
        // QUIZAS esto sea util
        // Programa programa = programas.get(nombre);
    }

    public String[] pedirAccion() {
        System.out.println("Acciones disponibles:");
        System.out.println("DEFINIR <tipo> [<argumentos>]");
        System.out.println("EJECUTABLE <nombre>");
        System.out.println("SALIR");
        System.out.println("Que desea hacer?\n");

        String accion = System.console().readLine();

        if (accion.length() == 0) {
            System.out.println("No se especifico ninguna accion");
            return null;
        }

        System.out.println("\n");
        return accion.split(" ");
    }
}
