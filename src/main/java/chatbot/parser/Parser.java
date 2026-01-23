package chatbot.parser;

import chatbot.exception.InvalidTaskNumberException;
import chatbot.exception.MissingByException;
import chatbot.exception.MissingFromToException;

public class Parser {

    // Use of ChatGPT for substring manipulation
    // (i.e. trim, substring methods, indexing)

    public static String getCommandType(String input) {
        int spaceIndex = input.indexOf(" ");
        if (spaceIndex == -1) {
            return input.toLowerCase();
        }
        return input.substring(0, spaceIndex).toLowerCase();
    }

    // returns everything after the command type
    public static String getArguments(String input) {
        int spaceIndex = input.indexOf(" ");
        return input.substring(spaceIndex + 1).trim();
    }

    public static String[] parseDeadline(String args) throws MissingByException {
        if (!args.contains(" /by ")) {
            throw new MissingByException();
        }
        String[] parts = args.split(" /by ", 2);
        String description = parts[0].trim();
        String time = parts[1].trim();

        return new String[]{description, time};
    }

    public static String[] parseEvent(String args) throws MissingFromToException {
        if (!args.contains(" /from ") || !args.contains(" /to ")) {
            throw new MissingFromToException();
        }
        String[] firstSplit = args.split(" /from ", 2);
        String description = firstSplit[0].trim();

        String[] secondSplit = firstSplit[1].split(" /to ", 2);
        String from = secondSplit[0].trim();
        String to = secondSplit[1].trim();

        return new String[]{description, from, to};
    }

    public static int parseIndex(String arg) throws InvalidTaskNumberException {
        try {
            return Integer.parseInt(arg.trim()) - 1; // 1 - indexed by chatgpt
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
    }
}
