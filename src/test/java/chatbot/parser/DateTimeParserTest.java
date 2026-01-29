package chatbot.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDateTime;

public class DateTimeParserTest {

    @Test
    public void parse_validDateTime_returnsLocalDateTime() throws InvalidDateTimeException {
        String input = "23-01-2026 18:30";
        LocalDateTime result = DateTimeParser.parse(input);
        assertEquals(2026, result.getYear());
        assertEquals(1, result.getMonthValue());
        assertEquals(23, result.getDayOfMonth());
        assertEquals(18, result.getHour());
        assertEquals(30, result.getMinute());
    }

    @Test
    public void parse_invalidDateTime_throwsException() {
        String input = "23/01/2026 1830";
        assertThrows(InvalidDateTimeException.class, () -> DateTimeParser.parse(input));
    }

    @Test
    public void parse_invalidDayOrMonth_throwsException() {
        String input1 = "45-01-2026 12:00";
        String input2 = "23-14-2026 12:00";
        assertThrows(InvalidDateTimeException.class, () -> DateTimeParser.parse(input1));
        assertThrows(InvalidDateTimeException.class, () -> DateTimeParser.parse(input2));
    }
}
