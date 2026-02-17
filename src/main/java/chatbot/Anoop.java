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
 * This class handles the primary application flow, including:
 *     Initializing the user interface, storage, and task list.</li>
 *     Loading saved tasks from file at startup.</li>
 *     Reading and parsing user commands in a loop.</li>
 *     Executing commands and updating task storage.</li>
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
     * Creates an instance of {@code Anoop} and starts the main loop.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Anoop().run();
    }

    /**
     * Starts the main application loop.
     * Displays a welcome message, reads user input, parses commands,
     * executes them, and continues until the exit command is issued.
     */
    public void run() {
        ui.userWelcome();
        runCommandLoop();
    }

    /**
     * Repeatedly processes user commands until an exit command is encountered.
     * Any AnoopException thrown during command handling will be caught
     * and displayed to the user without terminating the program.
     */
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

    /**
     * Reads, parses, and executes a single user command.
     * @return true if the executed command requests program termination, false otherwise
     * @throws AnoopException if parsing or execution fails
     */
    private boolean handleCommand() throws AnoopException {
        Command c = readAndParseCommand();
        c.execute(this.taskList, this.ui, this.storage);
        return c.isExit();
    }

    /**
     * Reads a line of input from the UI and converts it into a Command object
     *
     * @return the parsed Command object
     * @throws AnoopException if the input is invalid or cannot be parsed
     */
    private Command readAndParseCommand() throws AnoopException {
        String input = ui.readCommand().trim();
        return Parser.parse(input);

    }

    /**
     * Loads tasks from persistent storage.
     * If loading fails, an error message is shown and an empty task list is returned.
     *
     * @return the loaded TaskList, or an empty TaskList if loading fails
     */
    private TaskList loadTasks() {
        try {
            return this.storage.load();
        } catch (AnoopException e) {
            ui.showError(e.getMessage());
            return new TaskList();
        }
    }

    /**
     * Processes a single user input string and returns the chatbot's response.
     * This method is primarily used for GUI interaction where input is provided
     * programmatically instead of through standard input.
     *
     * @param input the user input command
     * @return the chatbot's response message
     */
    public String getResponse(String input) {
        assert input != null : "Input to getResponse should not be null";
        try {
            Command c = Parser.parse(input.trim());
            assert c != null : "Parsed command should not be null";
            return c.execute(this.taskList, this.ui, this.storage);
        } catch (AnoopException e) {
            return e.getMessage();
        }
    }
}
