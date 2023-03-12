import java.io.*;
import java.util.HashMap;
import java.util.Locale;

public class Escritor {
    private final File file;
    public Escritor(String nombreFichero) {
        file = new File(nombreFichero);
    }

    public void escribe(int id, double nota) throws IOException {
        HashMap<Integer, Double> notas = new HashMap<>();
        // Leer el contenido del fichero y añadir los valores al HashMap
        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(" ");
            notas.put(Integer.parseInt(partes[0]), Double.parseDouble(partes[1]));
        }
        br.close();

        // Comprobar si el id ya existe en el HashMap
        if (notas.containsKey(id)) {
            throw new IllegalArgumentException("El ID ya existe en el mapa.");
        }

        // Escribir la nueva nota en el fichero
        try (FileWriter fw = new FileWriter(file, true); BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(String.format(Locale.US, "%d %.2f\n", id, nota));
        }
    }


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
