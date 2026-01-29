package chatbot.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import chatbot.exception.AnoopException;
import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Task;

public class ParserTest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @Test
    void parseDeadline_validInput_returnsDeadline() throws AnoopException {
        String input = "Submit report /by 28-02-2026 23:59";
        Task task = Parser.parseDeadline(input);

        assertTrue(task instanceof Deadline, "Expected a Deadline instance");

        Deadline deadline = (Deadline) task;
        assertEquals("Submit report", deadline.getDescription());
        assertEquals(LocalDateTime.parse("28-02-2026 23:59", FORMATTER), deadline.getBy());
    }

    @Test
    void parseDeadline_missingBy_throwsException() {
        String input = "Submit report 28-02-2026 23:59";
        AnoopException e = assertThrows(AnoopException.class, () -> Parser.parseDeadline(input));
        assertTrue(e.getMessage().contains("Usage: deadline"), "Expected usage error message");
    }

    @Test
    void parseDeadline_invalidDate_throwsException() {
        String input = "Submit report /by tomorrow";
        AnoopException e = assertThrows(AnoopException.class, () -> Parser.parseDeadline(input));
        assertTrue(e.getMessage().contains("Invalid date/time format"), "Expected date/time format error");
    }

    @Test
    void parseEvent_validInput_returnsEvent() throws AnoopException {
        String input = "Team meeting /from 01-03-2026 10:00 /to 01-03-2026 11:00";
        Task task = Parser.parseEvent(input);

        assertTrue(task instanceof Event, "Expected an Event instance");

        Event event = (Event) task;
        assertEquals("Team meeting", event.getDescription());
        assertEquals(LocalDateTime.parse("01-03-2026 10:00", FORMATTER), event.getFrom());
        assertEquals(LocalDateTime.parse("01-03-2026 11:00", FORMATTER), event.getTo());
    }

    @Test
    void parseEvent_missingFrom_throwsException() {
        String input = "Team meeting /to 01-03-2026 11:00";
        AnoopException e = assertThrows(AnoopException.class, () -> Parser.parseEvent(input));
        assertTrue(e.getMessage().contains("Usage: event"), "Expected usage error message");
    }

    @Test
    void parseEvent_invalidDate_throwsException() {
        String input = "Team meeting /from now /to later";
        AnoopException e = assertThrows(AnoopException.class, () -> Parser.parseEvent(input));
        assertTrue(e.getMessage().contains("Invalid date/time format"), "Expected date/time format error");
    }
}
