import java.io.*;
import java.util.HashMap;
import java.util.Locale;

public class Escritor {
    private final File file;
    public Escritor(String nombreFichero) {
        file = new File(nombreFichero);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método que escribe una nota en un fichero
     * @param id   id de la nota
     * @param nota nota
     * @throws IOException           si no se puede leer el fichero
     * @throws NumberFormatException    si el id no es un número
     * @throws IllegalArgumentException si el id no existe
     * @throws FileNotFoundException si no se puede escribir en el fichero
     */
    public void escribe(int id, double nota) throws IOException {
        HashMap<Integer, Double> notas = new HashMap<>();
        // Leer el contenido del fichero y añadir los valores al HashMap
        BufferedReader br = new BufferedReader(new FileReader(file));
        if (!file.canRead()){
            throw new FileNotFoundException("No se puede leer el fichero");
        }
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(" ");
            notas.put(Integer.parseInt(partes[0]), Double.parseDouble(partes[1]));
        }
        br.close();

        // Comprobar si el id ya existe en el HashMap
        if (notas.containsKey(id)) {
            throw new IllegalArgumentException("El ID ya existe.");
        }

        // Escribir la nueva nota en el fichero
        try (FileWriter fw = new FileWriter(file, true); BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(String.format(Locale.US, "%d %.2f\n", id, nota));
        }
    }

    /**
     * Método que escribe un HashMap en un fichero
     * @param HashMap HashMap de id y nota
     * @throws IOException           si no se puede leer el fichero
     * @throws NumberFormatException    si el id no es un número
     * @throws IllegalArgumentException si el id no existe
     * @throws FileNotFoundException si no se puede escribir en el fichero
     */
    public void escribe(HashMap<Integer, Double> HashMap) throws IOException {
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        if (!file.canWrite()){
            throw new FileNotFoundException("No se puede escribir en el fichero");
        }
        for (Integer id : HashMap.keySet()) {
            bw.write(String.format(Locale.US, "%d %.2f\n", id, HashMap.get(id)));
        }
        bw.close();
    }
}
