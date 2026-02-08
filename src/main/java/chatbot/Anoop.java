package chatbot;

import java.nio.file.Path;
import java.nio.file.Paths;

import chatbot.command.Command;
import chatbot.exception.AnoopException;
import chatbot.parser.Parser;
import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.ui.Ui;

// --- AI & Code Reuse Declaration ---
// ChatGPT use: Formatting of JavaDoc comments, JUnit tests, string manipulation methods, code quality
// ChatGPT use: Debugging and formatting of program
// Code reuse: Organization of classes and general design for reformatting code inspired by:
// @omgeta and @lemonishi on CS2103T GitHub, JavaFX tutorial on CS2103T website

/**
 * Main controller class for the Anoop chatbot application.
 * <p>
 * This class handles the primary application flow, including:
 * <ul>
 *     <li>Initializing the user interface, storage, and task list.</li>
 *     <li>Loading saved tasks from file at startup.</li>
 *     <li>Reading and parsing user commands in a loop.</li>
 *     <li>Executing commands and updating task storage.</li>
 * </ul>
 */
public class Anoop {

    /** File path where task data is stored. */
    private static final Path FILE_PATH = Paths.get("data", "chatbot.Anoop.txt");

    /** List of tasks managed by the chatbot. */
    private final TaskList taskList;

    /** User interface for input/output. */
    private final Ui ui;

    /** Handles persistent storage of tasks. */
    private final Storage storage;

    /**
     * Constructs a new Anoop chatbot instance.
     * <p>
     * Initializes the UI, storage, and task list. Loads existing tasks
     * from the specified file path. If loading fails, starts with an empty task list.
     */
    public Anoop() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        this.taskList = loadTasks();
    }

    /**
     * Entry point of the application.
     * <p>
     * Creates an instance of {@code Anoop} and starts the main loop.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Anoop().run();
    }

    /**
     * Starts the main application loop.
     * <p>
     * Displays a welcome message, reads user input, parses commands,
     * executes them, and continues until the exit command is issued.
     */
    public void run() {
        ui.userWelcome();
        runCommandLoop();
    }

    private void runCommandLoop() {
        boolean isExit = false;

        while (!isExit) {
            try {
                isExit = handleCommand();
            } catch (AnoopException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    private boolean handleCommand() throws AnoopException {
        Command c = readAndParseCommand();
        c.execute(this.taskList, this.ui, this.storage);
        return c.isExit();
    }

    private Command readAndParseCommand() throws AnoopException {
        String input = ui.readCommand().trim();
        return Parser.parse(input);

    }

    private TaskList loadTasks() {
        try {
            return this.storage.load();
        } catch (AnoopException e) {
            ui.showError(e.getMessage());
            return new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input.trim());
            return c.execute(this.taskList, this.ui, this.storage);
        } catch (AnoopException e) {
            return e.getMessage();
        }
    }
}
