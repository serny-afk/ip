package chatbot.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chatbot.exception.AnoopException;

/**
 * Parses date-time strings into {@link LocalDateTime} objects.
 * Input date-time strings are expected to follow a fixed format.
 */
public class DateTimeParser {

    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * Parses a date-time string into a {@code LocalDateTime} object.
     *
     * @param input Date-time string to be parsed.
     * @return Parsed {@code LocalDateTime} object.
     * @throws AnoopException If the input does not match the expected format.
     */
    public static LocalDateTime parse(String input)
            throws AnoopException {
        try {
            return LocalDateTime.parse(input, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new AnoopException("Error. DateTime format must follow dd-MM-yyyy HH:mm");
        }
    }
}
