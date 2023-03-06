import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

public class Escritor {
    private final File file;
    public Escritor(String nombreFichero) {
        file = new File(nombreFichero);
    }

    public void escribe(int id, double nota) throws Exception {
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(String.format("%d,%.2f\n", id, nota));
        bw.close();
    }

    public void escribe(HashMap<Integer, Double> HashMap) {

    }
}
