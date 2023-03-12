import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Lector {
    private final File file;
    public Lector(String nombreFichero) {
        file = new File(nombreFichero);
    }

    /**
     * Método que lee una nota de un fichero y la devuelve
     * @param id
     * @return nota
     * @throws IOException           si no se puede leer el fichero
     * @throws NumberFormatException    si el id no es un número
     * @throws IllegalArgumentException si el id no existe
     */
    public double lee(int id) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] campos = linea.split(" ");
            if (Integer.parseInt(campos[0]) == id) {
                br.close();
                return Double.parseDouble(campos[1]);
            }
        }
        br.close();
        return -1;
    }

    /**
     * Método que lee un fichero y lo devuelve en un HashMap
     * @return HashMap
     * @throws IOException           si no se puede leer el fichero
     * @throws NumberFormatException    si el id no es un número
     * @throws IllegalArgumentException si el id no existe
     */
    public HashMap<Integer, Double> lee() throws IOException {
        HashMap<Integer, Double> HashMap = new HashMap<>();
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] campos = linea.split(" ");
            HashMap.put(Integer.parseInt(campos[0]), Double.parseDouble(campos[1]));
        }
        br.close();
        return HashMap;
    }
}
