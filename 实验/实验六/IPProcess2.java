import java.io.*;
import java.util.BitSet;

public class IPProcess2 implements IPProcess {
    private static final int SIZE = 256 * 256 * 256 * 256; // 2^32

    @Override
    public void process(File in, File out) throws IOException {
        BitSet bitSet = new BitSet(SIZE);

        try (BufferedReader reader = new BufferedReader(new FileReader(in));
             BufferedWriter writer = new BufferedWriter(new FileWriter(out))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int ip = ipToInteger(line);
                bitSet.set(ip);
            }

            for (int i = bitSet.nextSetBit(0); i >= 0; i = bitSet.nextSetBit(i + 1)) {
                writer.write(integerToIp(i));
                writer.newLine();
            }
        }
    }

    private int ipToInteger(String ip) {
        String[] parts = ip.split("\\.");
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = (result << 8) | Integer.parseInt(parts[i]);
        }
        return result;
    }

    private String integerToIp(int ip) {
        return String.format("%d.%d.%d.%d",
                (ip >> 24) & 0xFF,
                (ip >> 16) & 0xFF,
                (ip >> 8) & 0xFF,
                ip & 0xFF);
    }
}
