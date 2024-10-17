/*
 * Copyright (c) 2024, KASTEL. All rights reserved.
 */

package command;

import model.Board;

/**
 * This interface represents an executable command.
 *
 * @author Programmieren-Team
 * @author Mick Kohler
 */
public interface Command {

    /**
     * Executes the command.
     *
     * @param model            the model to execute the command on
     * @param commandArguments the arguments of the command
     * @return the result of the command
     */
    CommandResult execute(Board model, String[] commandArguments);


    /**
     * Returns the number of arguments of the command.
     *
     * @return the number of arguments
     */
    int getNumberOfArguments();


    /**
     * Returns the description of the command needed for the help command.
     *
     * @return the description of the command
     */
    String getCommandDescription();

}
