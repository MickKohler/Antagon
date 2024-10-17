package command;

import model.Board;

/**
 * Command to return the current position of the ant.
 *
 * @author Mick Kohler
 */
public class PositionCommand implements Command{
    private static final String HELP_POSITION_MESSAGE = "position: returns the current position of the ant";
    private static final int NUMBER_OF_ARGUMENTS = 0;

    private static final String POSITION_OUTPUT_FORMAT = "%s, %s";
    private static final String INVALID_POSITION = "Ant is out of bounds!";
    @Override
    public CommandResult execute(Board model, String[] commandArguments) {
        int rowOfAnt = model.getAntPosition().row();
        int columnOfAnt = model.getAntPosition().column();

        if (!model.isWithinBounds(model.getAntPosition())) {
            return new CommandResult(CommandResultType.FAILURE, INVALID_POSITION);
        }

        return new CommandResult(CommandResultType.SUCCESS, POSITION_OUTPUT_FORMAT.formatted(rowOfAnt, columnOfAnt));
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }

    @Override
    public String getCommandDescription() {
        return HELP_POSITION_MESSAGE;
    }
}
