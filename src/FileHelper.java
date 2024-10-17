import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Helper class for reading the input file consisting of the board.
 *
 * @author Mick Kohler
 */
public final class FileHelper {

    private FileHelper() {
    }

    /**
     * Reads the file and returns a list of strings, where each string represents a line in the file.
     *
     * @param path the path to the file
     * @return a list of strings, where each string represents a line in the file
     */
    public static List<String> readLines(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid path has been passed", e);
        }

    }

}
