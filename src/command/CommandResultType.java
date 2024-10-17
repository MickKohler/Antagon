package command;

/**
 * This enum represents the types that a result of a command can be.
 *
 * @author Mick Kohler
 */
public enum CommandResultType {

    /**
     * The command was executed successfully.
     */
    SUCCESS,

    /**
     * An error occured during processing the command.
     */
    FAILURE,

    /**
     * Program is ordered to terminate after command execution.
     */
    QUIT;
}
