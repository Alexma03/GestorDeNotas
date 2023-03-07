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

    public double lee(int id) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] campos = linea.split(",");
            if (Integer.parseInt(campos[0]) == id) {
                br.close();
                return Double.parseDouble(campos[1]);
            }
        }
        br.close();
        return -1;
    }
}
