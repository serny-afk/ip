package chatbot.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chatbot.exception.InvalidDateTimeException;
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
     * @throws InvalidDateTimeException If the input does not match the expected format.
     */
    public static LocalDateTime parse(String input)
            throws InvalidDateTimeException {
        try {
            return LocalDateTime.parse(input, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }
}
