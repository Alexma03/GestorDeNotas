import java.io.IOException;
import java.util.HashMap;

public class Gestor {
    public static void main(String[] args) {
        HashMap<Integer, Double> lectura;
        switch (args[0]) {
            case "w" -> {
                write(args);
            }
            case "r" -> {
                read(args);
            }
            case "m" -> {
                modificar(args);
            }
            case "d" -> {
                delete(args);
            }
            default -> System.out.println("Argumentos no válidos");
        }

    }

    //JavaDoc delete
    /**
     * Método que elimina una nota de un fichero
     * @param args
     * @throws IOException           si no se puede leer el fichero
     * @throws NumberFormatException    si el id no es un número
     * @throws IllegalArgumentException si el id no existe
     */
    private static void delete(String[] args) {
        HashMap<Integer, Double> lectura;
        Lector lector = new Lector(args[1]);
        try {
            lectura = lector.lee();
            if (lectura.containsKey(Integer.parseInt(args[2]))) {
                lectura.remove(Integer.parseInt(args[2]));
                Escritor escritor = new Escritor(args[1]);
                escritor.escribe(lectura);
                System.out.println("Eliminado el id " + args[2]);
            } else {
                System.out.println("El id " + args[2] + " no existe");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    //JavaDoc modificar
    /**
     * Método que modifica una nota de un fichero
     * @param args
     * @throws IOException           si no se puede leer el fichero
     * @throws NumberFormatException    si el id no es un número
     * @throws IllegalArgumentException si el id no existe
     */
    private static void modificar(String[] args) {
        HashMap<Integer, Double> lectura;
        Lector lector = new Lector(args[1]);
        try {
            lectura = lector.lee();
            if (lectura.containsKey(Integer.parseInt(args[2]))) {
                double notaModificada = lector.lee(Integer.parseInt(args[2]));
                lectura.put(Integer.parseInt(args[2]), Double.parseDouble(args[3]));
                Escritor escritor = new Escritor(args[1]);
                escritor.escribe(lectura);
                System.out.println("Modificada la nota del id " + args[2] + " de " + notaModificada + " a " + args[3]);
            } else {
                System.out.println("El id " + args[2] + " no existe");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("El id " + args[2] + " ya existe");
        }
    }

    //JavaDoc read
    /**
     * Método que lee una nota de un fichero
     * @param args
     * @throws IOException           si no se puede leer el fichero
     * @throws NumberFormatException    si el id no es un número
     * @throws IllegalArgumentException si el id no existe
     */
    private static void read(String[] args) {
        HashMap<Integer, Double> lectura;
        Lector lector = new Lector(args[1]);
        try {
            lectura = lector.lee();
            if (lectura.containsKey(Integer.parseInt(args[2]))) {
                System.out.println("La nota del id " + args[2] + " es " + lector.lee(Integer.parseInt(args[2])));
            } else if (Integer.parseInt(args[2]) == -1) {
                for (Integer key : lectura.keySet()) {
                    System.out.println("La nota del id " + key + " es " + lector.lee(key));
                }
            } else {
                System.out.println("El id " + args[2] + " no existe");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //JavaDoc write
    /**
     * Método que escribe una nota en un fichero
     * @param args
     * @throws IOException           si no se puede leer el fichero
     * @throws NumberFormatException    si el id no es un número
     * @throws IllegalArgumentException si el id ya existe
     */
    private static void write(String[] args) {
        if (args.length < 4) {
            System.out.println("Error: argumentos insuficientes");
            return;
        }
        try {
            Escritor escritor = new Escritor(args[1]);
            escritor.escribe(Integer.parseInt(args[2]), Double.parseDouble(args[3]));
            System.out.println("Guardado el id " + args[2] + " con nota " + args[3] + " en el fichero " + args[1]);
        } catch (IOException e) {
            System.out.println("Error al crear el escritor");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
