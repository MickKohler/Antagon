package command;

import model.Board;
import model.Position;

/**
 * Command to return the state of a cell at a given position.
 *
 * @author Mick Kohler
 */
public class FieldCommand implements Command{
    private static final String HELP_FIELD_MESSAGE = "field <x>,<y>: returns the state of the cell at position x, y";

    private static final String WRONG_COORDINATES = "These coordinates are invalid.";
    private static final char COORDINATE_SEPARATOR = ',';
    private static final String OUTSIDE_COORDINATES = "Cooordinates are not inside the board.";
    private static final int NUMBER_OF_ARGUMENTS = 1;

    @Override
    public CommandResult execute(Board model, String[] commandArguments) {
        int indexOfSeparator = commandArguments[0].indexOf(COORDINATE_SEPARATOR);
        int row;
        int column;

        try {
            row = Integer.parseInt(commandArguments[0].substring(0, indexOfSeparator));
            column = Integer.parseInt(commandArguments[0].substring(indexOfSeparator + NUMBER_OF_ARGUMENTS));
        } catch (NumberFormatException e) {
            return new CommandResult(CommandResultType.FAILURE, WRONG_COORDINATES);
        }

        Position positionOfField = new Position(row, column);

        if (!model.isWithinBounds(positionOfField)) {
            return new CommandResult(CommandResultType.FAILURE, OUTSIDE_COORDINATES);
        }
        String output = String.valueOf(model.getCell(positionOfField).getStateRepresentation());


        return new CommandResult(CommandResultType.SUCCESS, output);
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }

    @Override
    public String getCommandDescription() {
        return HELP_FIELD_MESSAGE;
    }
}
