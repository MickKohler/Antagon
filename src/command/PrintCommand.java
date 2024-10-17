package command;

import model.Board;
import model.Position;

/**
 * This command prints the board.
 *
 * @author Mick Kohler
 */
public class PrintCommand implements Command {
    private static final String HELP_PRINT_MESSAGE = "print: prints the board";
    private static final int NUMBER_OF_ARGUMENTS = 0;

    @Override
    public CommandResult execute(Board model, String[] commandArguments) {
        StringBuilder output = new StringBuilder();


        for (int row = NUMBER_OF_ARGUMENTS; row < model.getRowCount(); row++) {
            for (int col = NUMBER_OF_ARGUMENTS; col < model.getColumnCount(); col++){
                output.append(model.getCell(new Position(row, col)).getStateRepresentation());
            }

            if (row != model.getRowCount()-1) { // to prevent an extra line being added after output of board
                output.append(System.lineSeparator());
            }

        }

        return new CommandResult(CommandResultType.SUCCESS, output.toString());
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }

    @Override
    public String getCommandDescription() {
        return HELP_PRINT_MESSAGE;
    }
}
