package command;

import model.Board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * Command to display all available commands.
 *
 * @author Mick Kohler
 */
public class HelpCommand implements Command{
    private static final String HELP_COMMAND_NAME = "help";
    private static final int NUMBER_OF_ARGUMENTS = 0;
    private Map<String, Command> commands;

    /**
     * Creates a new HelpCommand.
     */
    public HelpCommand() {
    }

    /**
     * Sets the commands to display.
     *
     * @param commands the commands to display
     */
    public void setCommands(Map<String, Command> commands) {
        this.commands = commands;
    }

    /**
     * Executes the command.
     * Retrieves and displays all available commands from the game model.
     * The commands are sorted alphabetically and displayed in that order.
     *
     * @param model the game model
     * @param commandArguments the command arguments
     * @return the result of the command execution
     */
    @Override
    public CommandResult execute(Board model, String[] commandArguments) {
        StringBuilder helpMessage = new StringBuilder();
        List<String> availableCommands = new ArrayList<>(commands.keySet());

        Collections.sort(availableCommands);

        availableCommands.remove(HELP_COMMAND_NAME);

        for (String command : availableCommands) {
            helpMessage.append(this.commands.get(command).getCommandDescription()).append(System.lineSeparator());
        }

        return new CommandResult(CommandResultType.SUCCESS, helpMessage.toString());

    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }

    @Override
    public String getCommandDescription() {
        return null;
    }
}
