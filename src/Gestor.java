import java.io.IOException;
import java.util.HashMap;

public class Gestor {
    public static void main(String[] args) {
        HashMap<Integer, Double> lectura;
        switch (args[0]) {
            case "w" -> {
                if (args.length < 4) {
                    System.err.println("Error: argumentos insuficientes");
                    return;
                }
                try {
                    Escritor escritor = new Escritor(args[1]);
                    escritor.escribe(Integer.parseInt(args[2]), Double.parseDouble(args[3]));
                    System.out.println("Guardado el id " + args[2] + " con nota " + args[3] + " en el fichero " + args[1]);
                } catch (IOException e) {
                    System.err.println("Error al crear el escritor");
                }
                catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            }
            case "r" -> {
                Lector lector = new Lector(args[1]);
                try {
                    lectura = lector.lee();
                    if (lectura.containsKey(Integer.parseInt(args[2]))) {
                        System.out.println("La nota del id " + args[2] + " es " + lector.lee(Integer.parseInt(args[2])));
                    } else {
                        System.err.println("El id " + args[2] + " no existe");
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            case "m" -> {
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
                        System.err.println("El id " + args[2] + " no existe");
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.err.println("El id " + args[2] + " ya existe");
                }
            }
            case "d" -> {
                Lector lector = new Lector(args[1]);
                try {
                    lectura = lector.lee();
                    if (lectura.containsKey(Integer.parseInt(args[2]))) {
                        lectura.remove(Integer.parseInt(args[2]));
                        Escritor escritor = new Escritor(args[1]);
                        escritor.escribe(lectura);
                        System.out.println("Eliminado el id " + args[2]);
                    } else {
                        System.err.println("El id " + args[2] + " no existe");
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            default -> System.out.println("Argumentos no válidos");
        }

    }
}
