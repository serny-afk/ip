package chatbot.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import chatbot.exception.AnoopException;

public class DateTimeParserTest {

    @Test
    public void parse_validInput_returnsLocalDateTime() throws AnoopException {
        String input = "29-01-2026 14:30";
        LocalDateTime expected = LocalDateTime.of(2026, 1, 29, 14, 30);

        LocalDateTime result = DateTimeParser.parse(input);

        assertEquals(expected, result, "Parsed LocalDateTime should match expected value");
    }

    @Test
    public void parse_invalidFormat_throwsAnoopException() {
        String invalidInput1 = "2026-01-29 14:30";
        String invalidInput2 = "29/01/2026 14:30";
        String invalidInput3 = "29-01-2026";

        assertThrows(AnoopException.class, () -> DateTimeParser.parse(invalidInput1),
                "Should throw AnoopException for wrong format");
        assertThrows(AnoopException.class, () -> DateTimeParser.parse(invalidInput2),
                "Should throw AnoopException for wrong separator");
        assertThrows(AnoopException.class, () -> DateTimeParser.parse(invalidInput3),
                "Should throw AnoopException for missing time");
    }

    @Test
    public void parse_edgeTimes_returnsLocalDateTime() throws AnoopException {
        // Midnight
        LocalDateTime midnight = LocalDateTime.of(2026, 1, 29, 0, 0);
        assertEquals(midnight, DateTimeParser.parse("29-01-2026 00:00"));

        // End of day
        LocalDateTime endOfDay = LocalDateTime.of(2026, 1, 29, 23, 59);
        assertEquals(endOfDay, DateTimeParser.parse("29-01-2026 23:59"));
    }
}
