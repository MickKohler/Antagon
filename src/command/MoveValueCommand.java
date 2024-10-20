package command;

import model.Board;
import model.Position;


/**
 * Command to move the ant a certain number of steps.
 * A move consists of:
 *      1. Move the ant to the next cell in the ant's direction
 *      2. Turn the ant depending on the color the ant is now on
 *      3. Change the color of the cell the ant is on
 *
 * @author Mick Kohler
 */
public class MoveValueCommand implements Command{
    private static final String HELP_MOVE_MESSAGE = "move <steps>: moves the ant <steps> times";

    private static final String NEGATIVE_STEPS = "Steps can not be negative";
    private static final String OFF_BOARD_CELL_FORMAT = "%d, %d";
    private static final int NUMBER_OF_ARGUMENTS = 1;
    private final CommandHandler commandHandler;

    public MoveValueCommand(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Override
    public CommandResult execute(Board model, String[] commandArguments) {
        int steps = -NUMBER_OF_ARGUMENTS;
        try {
            steps = Integer.parseInt(commandArguments[0]);
        } catch (NumberFormatException e) {
            return new CommandResult(CommandResultType.FAILURE, String.valueOf(steps));
        }

        if (!(steps >= 0)) {
            return new CommandResult(CommandResultType.FAILURE, NEGATIVE_STEPS);
        }


        Position lastPosition = model.moveAnt(steps);git
        String output = null;
        if (model.getAntPosition() == null) {
            output = OFF_BOARD_CELL_FORMAT.formatted(lastPosition.row(), lastPosition.column());
            commandHandler.quit();
        }

        return new CommandResult(CommandResultType.SUCCESS, output);
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }

    @Override
    public String getCommandDescription() {
        return HELP_MOVE_MESSAGE;
    }
}
