package chatbot.parser;

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
 */
public class Parser {

    /**
     * Parses full user input into a Command object.
     * @param input full user input
     * @return Command object
     * @throws AnoopException if input is empty or invalid
     */
    public static Command parse(String input) throws AnoopException {
        if (input == null || input.trim().isEmpty()) {
            throw new AnoopException("Please enter a command.");
        }

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
        default -> throw new AnoopException("Command not found");
        }
        return c;
    }

    /**
     * Extracts the argument portion of input (after the command keyword).
     * @param input full user input
     * @return trimmed argument string
     */
    public static String getArguments(String input) {
        int spaceIndex = input.indexOf(" ");
        if (spaceIndex == -1) {
            return "";
        }
        return input.substring(spaceIndex + 1).trim();
    }

    /**
     * TODO add javadoc
     * @param args
     * @return
     * @throws AnoopException
     */

    public static Task parseToDo(String args) throws AnoopException {
        if (args.isEmpty()) {
            throw new AnoopException("Description cannot be empty.");
        }
        return new ToDo(args);
    }

    /**
     * TODO add javadoc
     * @param args
     * @return
     * @throws AnoopException
     */

    public static Task parseDeadline(String args) throws AnoopException {
        if (args.isEmpty()) {
            throw new AnoopException("Description cannot be empty.");
        }

        String[] parts = args.split("\\s+/by\\s+", 2);
        if (parts.length < 2) {
            throw new AnoopException("Usage: deadline <description> /by <time>");
        }

        String description = parts[0].trim();
        String time = parts[1].trim();
        if (description.isEmpty()) {
            throw new AnoopException("Description cannot be empty.");
        }
        if (time.isEmpty()) {
            throw new AnoopException("/by time cannot be empty.");
        }

        try {
            return new Deadline(description, DateTimeParser.parse(time));
        } catch (Exception e) {
            throw new AnoopException("Invalid date/time format. Use dd-MM-yyyy HH:mm");
        }
    }

    /**
     * TODO add javadoc
     * @param args
     * @return
     * @throws AnoopException
     */

    public static Task parseEvent(String args) throws AnoopException {
        if (args.isEmpty()) {
            throw new AnoopException("Usage: event <description> /from <from> /to <to>");
        }

        String[] firstSplit = args.split("\\s+/from\\s+", 2);
        if (firstSplit.length < 2) {
            throw new AnoopException("Usage: event <description> /from <from> /to <to>");
        }

        String description = firstSplit[0].trim();
        String rest = firstSplit[1].trim();

        String[] secondSplit = rest.split("\\s+/to\\s+", 2);
        if (secondSplit.length < 2) {
            throw new AnoopException("Usage: event <description> /from <from> /to <to>");
        }

        String from = secondSplit[0].trim();
        String to = secondSplit[1].trim();
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new AnoopException("Event must have description, /from, and /to.");
        }

        try {
            return new Event(description, DateTimeParser.parse(from), DateTimeParser.parse(to));
        } catch (Exception e) {
            throw new AnoopException("Invalid date/time format. Use dd-MM-yyyy HH:mm");
        }
    }

    /**
     * TODO add javadoc
     * @param args
     * @return
     * @throws AnoopException
     */

    public static int parseIndex(String args) throws AnoopException {
        if (args == null || args.trim().isEmpty()) {
            throw new AnoopException("Task number cannot be empty");
        }

        String[] parts = args.trim().split("\\s+");
        if (parts.length != 1) {
            throw new AnoopException("Usage: <command> <taskNumber>");
        }

        try {
            int index = Integer.parseInt(parts[0]) - 1;
            if (index < 0) {
                throw new AnoopException("Error. Task number not valid.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new AnoopException("Error. Task number must be a number.");
        }
    }
}
