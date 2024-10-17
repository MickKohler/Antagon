import command.CommandHandler;
import model.Board;

import java.util.ArrayList;
import java.util.List;

/*META_DISCLAIMER
 * This little project is a modified version/implementation of the the famous game 'Langton's Ant'
 * created by Chris Langton in 1986. The game is a two-dimensional universal Turing machine with
 * very simple rules but complex emergent behavior. Note that this version may differ from
 * the original game (rules) otherwise it would be boring to implement.
 * For more information, see the README.md file on the project's GitHub page.
 *
 */
/**
 * Main class to start the the application.
 *
 * @author Mick Kohler
 */
public final class Application {

    private Application() {
    }


    /**
     * Entry point of the program.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        List<String> lines = FileHelper.readLines(args[0]);
        Board board = Board.fromRepresentation(getBoardRepresentation(lines));
        CommandHandler commandHandler = new CommandHandler(board);
        commandHandler.handleUserInput();
    }

    private static List<List<Character>> getBoardRepresentation(List<String> lines) {
        List<List<Character>> rows = new ArrayList<>(lines.size());

        for (String line : lines) {
            List<Character> cellContent = new ArrayList<>(lines.size());
            for (char representation : line.toCharArray()) {
                cellContent.add(representation);
            }
            rows.add(cellContent);
        }

        return rows;
    }

}
