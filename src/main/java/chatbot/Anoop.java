package chatbot;

import java.nio.file.Path;
import java.nio.file.Paths;

import chatbot.command.Command;
import chatbot.exception.AnoopException;
import chatbot.parser.Parser;
import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.ui.Ui;

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

        TaskList loadedTasks = new TaskList();

        try {
            loadedTasks = storage.load();
        } catch (Exception e) {
            ui.showError("Failed to load tasks: " + e.getMessage());
        }

        this.taskList = loadedTasks;
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
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readCommand().trim();
                Command cmd = Parser.parse(input);
                cmd.execute(this.taskList, this.ui, this.storage);
                isExit = cmd.isExit();
            } catch (AnoopException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Saves the current task list to persistent storage.
     * <p>
     * If saving fails, an error message is displayed to the user.
     */
    private void saveTasks() {
        try {
            this.storage.save(this.taskList.getTasks());
        } catch (Exception e) {
            this.ui.showError(e.getMessage());
        }
    }

}
