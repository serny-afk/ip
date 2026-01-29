package chatbot.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    // first 2 are trivial cases
    @Test
    public void getCommandType_todoInput_returnsTodo() {
        String input = "todo study for midterms";
        String command = Parser.getCommandType(input);
        assertEquals("todo", command);
    }

    @Test
    public void getArguments_deadlineInput_returnsCorrectArguments() {
        String input = "deadline finish assignment /by 12-12-2026 14:30";
        String args = Parser.getArguments(input);
        assertEquals("finish assignment /by 12-12-2026 14:30", args);
    }

    // non-trivial test cases
    @Test
    public void parseDeadline_deadlineInput_returnsArray() throws Exception {
        String input = "finish cooking dinner /by 23-01-2026 18:00";
        String[] result = Parser.parseDeadline(input);
        assertEquals("finish cooking dinner", result[0]);
        assertEquals("23-01-2026 18:00", result[1]);
    }

    @Test
    public void parseDeadline_missingBy_throwsByException() {
        String input = "finish cooking dinner by 12-12-2026 18:00";
        assertThrows(MissingByException.class, () -> Parser.parseDeadline(input));
    }

    @Test
    public void parseDeadline_extraSpaces_trimmedCorrectly() throws MissingByException {
        String input = "   finish cooking dinner  /by 12-12-2026 18:00  ";
        String[] result = Parser.parseDeadline(input);
        assertEquals("finish cooking dinner", result[0]);
        assertEquals("12-12-2026 18:00", result[1]);
    }

}
