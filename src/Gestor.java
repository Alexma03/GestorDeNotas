import java.io.IOException;

public class Gestor {
    public static void main(String[] args) {
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
                } catch (NumberFormatException e) {
                    System.err.println("Error: argumentos inválidos");
                } catch (IOException e) {
                    System.err.println("Error al crear el escritor");
                }
            }
            case "r" -> {
                Lector lector = new Lector(args[1]);
                try {
                    System.out.println("La nota del id " + args[2] + " es " + lector.lee(Integer.parseInt(args[2])));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            case "m" -> {
                Lector lector = new Lector(args[1]);
                try {
                    if (lector.lee().get(Integer.parseInt(args[2])) == null) {
                        System.out.println("El id " + args[2] + " no existe");
                        break;
                    } else {
                        Escritor escritor = new Escritor(args[1]);
                        escritor.escribe(Integer.parseInt(args[2]), Double.parseDouble(args[3]));
                    }
                    System.out.println("Modificada la nota del id " + args[2] + " a " + args[3] + " en el fichero " + args[1]);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.err.println("El id " + args[2] + " ya existe");
                }
            }
            default -> System.out.println("Argumentos no válidos");
        }

    }
}
