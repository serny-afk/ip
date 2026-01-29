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
 * Main controller of the chatbot application
 * Handles user input, manages tasks, coordinates between
 * the UI, task storage and task list
 */
public class Anoop {

    private static final Path FILE_PATH = Paths.get("data", "chatbot.Anoop.txt");

    private final TaskList taskList;
    private final Ui ui;
    private final Storage storage;

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

    public static void main(String[] args) {
        new Anoop().run();
    }

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

    private void saveTasks() {
        try {
            this.storage.save(this.taskList.getTasks());
        } catch (Exception e) {
            this.ui.showError(e.getMessage());
        }
    }

}
