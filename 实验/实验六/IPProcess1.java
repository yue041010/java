import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class IPProcess1 implements IPProcess {
    @Override
    public void process(File in, File out) throws IOException {
        Set<String> ipSet = new HashSet<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(in));
             BufferedWriter writer = new BufferedWriter(new FileWriter(out))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ipSet.add(line);
            }
            
            for (String ip : ipSet) {
                writer.write(ip);
                writer.newLine();
            }
        }
    }
}
