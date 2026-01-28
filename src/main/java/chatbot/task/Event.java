package chatbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that occurs over a specific time interval.
 */
public class Event extends Task {
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private final LocalDateTime from;
    private final LocalDateTime to;
    /**
     * Creates a new Event type object with the following
     * @param description Description of the task
     * @param from Event start time
     * @param to Event end time
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.from.format(OUTPUT_FORMAT)
                + " to: " + this.to.format(OUTPUT_FORMAT) + ")";
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }
}
