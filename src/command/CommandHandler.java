/*
 * Copyright (c) 2024, KASTEL. All rights reserved.
 */

package command;


import model.Board;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * This class handles the user input and executes the commands.
 *
 * @author Programmieren-Team
 * @author Mick Kohler
 */
public final class CommandHandler {
    private static final String COMMAND_SEPARATOR_REGEX = " +";
    private static final String ERROR_PREFIX = "Error, ";
    private static final String COMMAND_NOT_FOUND_FORMAT = "command '%s' not found!";
    private static final String WRONG_ARGUMENTS_COUNT_FORMAT = "wrong number of arguments for command '%s'!";
    private static final String QUIT_COMMAND_NAME = "quit";
    private static final String INVALID_RESULT_TYPE_FORMAT = "Unexpected value: %s";
    private static final String FIELD_COMMAND_NAME = "field";
    private static final String MOVE_VALUE_COMMAND_NAME = "move";
    private static final String POSITION_COMMAND_NAME = "position";
    private static final String PRINT_COMMAND_NAME = "print";
    private static final String HELP_COMMAND_NAME = "help";
    private static final String INTRODUCTION = "Welcome to the Ant Game! Type 'help' to see all available commands.";
    private final Map<String, Command> commands;
    private final Board board;
    private boolean running = false;

    /**
     * Constructs new CommandHandler.
     *
     * @param board the board to execute the commands on
     */
    public CommandHandler(Board board) {
        this.board = Objects.requireNonNull(board);
        this.commands = new HashMap<>();
        this.initCommands();
    }

    /**
     * Starts the interaction with the user.
     */
    public void handleUserInput() {
        this.running = true;
        System.out.println(INTRODUCTION);

        try (Scanner scanner = new Scanner(System.in)) {
            while (running && scanner.hasNextLine()) {
                executeCommand(scanner.nextLine());
            }
        }
    }

    /**
     * Quits the interaction with the user.
     */
    public void quit() {
        this.running = false;
    }

    private void executeCommand(String commandWithArguments) {
        String[] splittedCommand = commandWithArguments.trim().split(COMMAND_SEPARATOR_REGEX);

        // Default to the first word as the command name
        String commandName = splittedCommand[0];
        String[] commandArguments;

        // Check if the first two words form a valid command
        if (splittedCommand.length > 1) {
            String combinedCommandName = commandName + " " + splittedCommand[1];
            if (commands.containsKey(combinedCommandName)) {
                commandName = combinedCommandName;
                commandArguments = Arrays.copyOfRange(splittedCommand, 2, splittedCommand.length);
            } else {
                // Fall back to the first word only
                commandArguments = Arrays.copyOfRange(splittedCommand, 1, splittedCommand.length);
            }
        } else {
            commandArguments = Arrays.copyOfRange(splittedCommand, 1, splittedCommand.length);
        }

        // Execute the command
        executeCommand(commandName, commandArguments);
    }


    private void executeCommand(String commandName, String[] commandArguments) {
        if (!commands.containsKey(commandName)) {
            System.err.println(ERROR_PREFIX + COMMAND_NOT_FOUND_FORMAT.formatted(commandName));
        } else if (commandArguments.length != commands.get(commandName).getNumberOfArguments()) {
            System.err.println(ERROR_PREFIX + WRONG_ARGUMENTS_COUNT_FORMAT.formatted(commandName));
        }else {
            CommandResult result = commands.get(commandName).execute(board, commandArguments);
            String output = switch (result.getType()) {
                case SUCCESS -> result.getMessage();
                case FAILURE -> ERROR_PREFIX + result.getMessage();
                case QUIT -> null; 
            };
            if (output != null) {
                switch (result.getType()) {
                    case SUCCESS -> System.out.println(output);
                    case FAILURE -> System.err.println(output);
                    case QUIT -> quit();
                    default -> throw new IllegalStateException(INVALID_RESULT_TYPE_FORMAT.formatted(result.getType()));
                }
            }
        }
    }

    private void initCommands() {
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.setCommands(commands);
        this.addCommand(QUIT_COMMAND_NAME, new QuitCommand(this));
        this.addCommand(FIELD_COMMAND_NAME, new FieldCommand());
        this.addCommand(MOVE_VALUE_COMMAND_NAME, new MoveValueCommand(this));
        this.addCommand(POSITION_COMMAND_NAME, new PositionCommand());
        this.addCommand(PRINT_COMMAND_NAME, new PrintCommand());
        this.addCommand(HELP_COMMAND_NAME, helpCommand);
    }

    private void addCommand(String commandName, Command command) {
        this.commands.put(commandName, command);
    }

    /**
     * Gets all existing commands used for help-command.
     * @return returns Map of all commands.
     */
    public Map<String, Command> getAllCommands() {
        return Collections.unmodifiableMap(commands);
    }

}
