import java.io.File;
import java.io.IOException;

interface IPProcess {
    void process(File in, File out) throws IOException;
}
