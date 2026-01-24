package chatbot.parser;

import chatbot.exception.InvalidDateTimeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {

    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public static LocalDateTime parse(String input) throws InvalidDateTimeException {
        try {
            return LocalDateTime.parse(input, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }
}
