package command;

import model.Board;

/**
 * Command to quit the game.
 *
 * @author Mick Kohler
 */
public class QuitCommand implements Command{
    private static final String HELP_QUIT_MESSAGE = "quit: exits the program";
    private static final int NUMBER_OF_ARGUMENTS = 0;

    private final CommandHandler commandHandler;

    /**
     * Constructor for QuitCommand.
     *
     * @param commandHandler the command handler
     */
    public QuitCommand(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Override
    public CommandResult execute(Board model, String[] commandArguments) {
        commandHandler.quit();
        return new CommandResult(CommandResultType.QUIT, null);
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }

    @Override
    public String getCommandDescription() {
        return HELP_QUIT_MESSAGE;
    }
}
