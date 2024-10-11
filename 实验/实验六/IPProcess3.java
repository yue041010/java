import java.io.*;

public class IPProcess3 implements IPProcess {
    private static final int SIZE = 256 * 256 * 256 * 256; // 2^32

    @Override
    public void process(File in, File out) throws IOException {
        File tempFile = new File("temp.dat");
        try (RandomAccessFile tempRAF = new RandomAccessFile(tempFile, "rw");
             BufferedReader reader = new BufferedReader(new FileReader(in));
             BufferedWriter writer = new BufferedWriter(new FileWriter(out))) {

            // Set all bits to 0
            tempRAF.setLength(SIZE / 8);
            byte[] buffer = new byte[8192];
            String line;
            while ((line = reader.readLine()) != null) {
                int ipIndex = ipToInteger(line);
                int byteIndex = ipIndex / 8;
                int bitIndex = ipIndex % 8;

                tempRAF.seek(byteIndex);
                byte b = tempRAF.readByte();
                b |= (1 << bitIndex);
                tempRAF.seek(byteIndex);
                tempRAF.writeByte(b);
            }

            // Read and write unique IPs
            for (int i = 0; i < SIZE / 8; i++) {
                tempRAF.seek(i);
                byte b = tempRAF.readByte();
                for (int bit = 0; bit < 8; bit++) {
                    if ((b & (1 << bit)) != 0) {
                        writer.write(integerToIp(i * 8 + bit));
                        writer.newLine();
                    }
                }
            }
        } finally {
            tempFile.delete(); // Clean up temp file
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
