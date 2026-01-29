package chatbot.parser;

// Use of ChatGPT for phrasing of JavaDoc documentation

import chatbot.command.AddCommand;
import chatbot.command.Command;
import chatbot.command.DeleteCommand;
import chatbot.command.ExitCommand;
import chatbot.command.FindCommand;
import chatbot.command.ListCommand;
import chatbot.command.MarkCommand;
import chatbot.command.UnmarkCommand;
import chatbot.exception.AnoopException;
import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.ToDo;

/**
 * Parses user input strings into command components and arguments
 * required by the chatbot logic.
 * This class provides helper methods to extract command types,
 * arguments, and structured task information from raw user input.
 */
public class Parser {

    // ChatGPT recommendation to use '->' instead of ': and break'
    // ChatGPT provided methods for string manipulation

    /**
     * Extracts the command keyword from the user input.
     *
     * @param input Full user input string.
     * @return The command keyword in lowercase.
     */
    public static Command parse(String input) throws AnoopException {
        // Method provided by ChatGPT
        String[] parts = input.trim().split("\\s+", 2);
        String commandType = parts[0].toLowerCase();
        String args = (parts.length == 2) ? parts[1] : "";

        Command c;
        switch(commandType) {
        case "bye" -> c = new ExitCommand();
        case "list" -> c = new ListCommand();
        case "mark" -> c = new MarkCommand(parseIndex(args));
        case "unmark" -> c = new UnmarkCommand(parseIndex(args));
        case "delete" -> c = new DeleteCommand(parseIndex(args));
        case "todo" -> c = new AddCommand(parseToDo(args));
        case "deadline" -> c = new AddCommand(parseDeadline(args));
        case "event" -> c = new AddCommand(parseEvent(args));
        case "find" -> c = new FindCommand(args);
        default -> throw new AnoopException("Error. Command not found");
        }

        return c;
    }

    /**
     * Extracts the arguments portion of the user input
     * (everything after the command keyword).
     *
     * @param input Full user input string.
     * @return Arguments string with leading and trailing whitespace removed.
     */
    public static String getArguments(String input) {
        int spaceIndex = input.indexOf(" ");
        return input.substring(spaceIndex + 1).trim();
    }

    /**
     * TODO add javadoc
     * @param args 1
     * @return task
     * @throws AnoopException exception
     */
    public static Task parseToDo(String args) throws AnoopException {
        if (args.isEmpty()) {
            throw new AnoopException("Error. Description cannot be empty.");
        }
        return new ToDo(args);
    }

    /**
     * TODO add javadoc
     * @param args 1
     * @return task
     * @throws AnoopException exception
     */
    public static Task parseDeadline(String args) throws AnoopException {
        if (args.isEmpty()) {
            throw new AnoopException("Description cannot be empty.");
        }
        String[] parts = args.split("\\s+/by\\s+", 2);
        String description = parts[0].trim();
        String time = parts[1].trim();
        return new Deadline(description, DateTimeParser.parse(time));
    }

    /**
     * TODO add javadoc
     * @param args 1
     * @return task
     * @throws AnoopException exception
     */
    public static Task parseEvent(String args) throws AnoopException {
        if (args.isEmpty()) {
            throw new AnoopException("Usage: event <description> /from <from> /to <to>");
        }
        String[] firstSplit = args.split("\\s+/by\\s+", 2);
        if (firstSplit.length < 2) {
            throw new AnoopException("Error. Usage: event <description> /from <from> /to <to>");
        }

        String description = firstSplit[0].trim();
        String rest = firstSplit[1].trim();

        if (description.isEmpty()) {
            throw new AnoopException("Error. Description cannot be empty.");
        }

        String[] secondSplit = args.split("\\s+/by\\s+", 2);
        if (secondSplit.length < 2) {
            throw new AnoopException("Error. Usage: event <description> /from <from> /to <to>.");
        }

        String from = secondSplit[0].trim();
        String to = secondSplit[1].trim();

        if (from.isEmpty() || to.isEmpty()) {
            throw new AnoopException("Error. Event must have /from and /to.");
        }

        return new Event(description, DateTimeParser.parse(from), DateTimeParser.parse(to));
    }

    /**
     * TODO add javadoc
     * @param args string
     * @return int
     * @throws AnoopException exception
     */
    public static int parseIndex(String args) throws AnoopException {
        if (args == null || args.trim().isEmpty()) {
            throw new AnoopException("Task number cannot be empty");
        }

        // Split into tokens by whitespace
        String[] parts = args.trim().split("\\s+");

        // Must be exactly one token
        if (parts.length != 1) {
            throw new AnoopException("Usage: <command> <taskNumber>");
        }

        try {
            int index = Integer.parseInt(parts[0]) - 1; // convert to 0-based
            if (index < 0) {
                throw new AnoopException("Error. Task number not valid.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new AnoopException("Error. Task number must be a number.");
        }
    }

}

