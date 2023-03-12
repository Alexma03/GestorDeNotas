import java.io.*;
import java.util.HashMap;

public class Lector {
    private final File file;
    public Lector(String nombreFichero) {
        file = new File(nombreFichero);
    }

    /**
     * Método que lee una nota de un fichero y la devuelve
     * @param id id de la nota
     * @return nota
     * @throws IOException           si no se puede leer el fichero
     * @throws NumberFormatException    si el id no es un número
     * @throws IllegalArgumentException si el id no existe
     * @throws FileNotFoundException si no se puede leer el fichero
     */
    public double lee(int id) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        if (!file.canRead()){
            throw new FileNotFoundException("No se puede leer el fichero");
        }
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
     * @throws FileNotFoundException si no se puede leer el fichero
     */
    public HashMap<Integer, Double> lee() throws IOException {
        HashMap<Integer, Double> HashMap = new HashMap<>();
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        if (!file.canRead()){
            throw new FileNotFoundException("No se puede leer el fichero");
        }
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] campos = linea.split(" ");
            HashMap.put(Integer.parseInt(campos[0]), Double.parseDouble(campos[1]));
        }
        br.close();
        return HashMap;
    }
}
