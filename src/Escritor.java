import java.io.*;
import java.util.HashMap;
import java.util.Locale;

public class Escritor {
    private final File file;
    public Escritor(String nombreFichero) {
        file = new File(nombreFichero);
    }

    /*public void escribe(int id, double nota) throws IOException {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
            bw.write(String.format(Locale.US,"%d %.2f\n", id, nota));
        } finally {
            if (bw != null) {
                bw.close();
            }
            if (fw != null) {
                fw.close();
            }
        }
    }*/

    public void escribe(int id, double nota) throws IOException {
        HashMap<Integer, Double> notas = new HashMap<Integer, Double>();

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
            System.out.println("El id ya existe en el fichero.");
            return;
        }

        // Escribir la nueva nota en el fichero
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
            bw.write(String.format(Locale.US,"%d %.2f\n", id, nota));
        } finally {
            if (bw != null) {
                bw.close();
            }
            if (fw != null) {
                fw.close();
            }
        }
    }


    public void escribe(HashMap<Integer, Double> HashMap) throws IOException {
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        for (Integer id : HashMap.keySet()) {
            bw.write(String.format(Locale.US, "%d %.2f\n", id, HashMap.get(id)));
        }
        bw.close();
    }
}
