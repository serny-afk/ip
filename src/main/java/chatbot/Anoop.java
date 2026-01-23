package chatbot;
import chatbot.ui.Ui;
import chatbot.parser.Parser;
import chatbot.parser.DateTimeParser;
import chatbot.storage.Storage;
import chatbot.task.*;
import chatbot.exception.*;

import java.time.LocalDateTime;

public class Anoop {

    private final Ui ui;
    private final TaskList tasklist;
    private final Storage storage;

    public Anoop(Ui ui, TaskList tasklist, Storage storage) {
        this.ui = ui;
        this.tasklist = tasklist;
        this.storage = storage;
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage =  new Storage("./data/chatbot.Anoop.txt");

        TaskList loadedTasks = new TaskList();

        try {
            loadedTasks = storage.load();
        } catch (Exception e) {
            ui.showError("Failed to load tasks: " + e.getMessage());
        }

        new Anoop(ui, loadedTasks, storage).run();
    }

    // Use of ChatGPT for formatting of try-catch blocks
    // Use of ChatGPT for overall organization of classes / methods (design choice)
    // (i.e. where printing should be handled, where inputs should be parsed)

    private void run() {
        this.ui.userWelcome();

        while (true) {
            String input = this.ui.readCommand().trim(); // trim once at the start

            try {
                String commandType = Parser.getCommandType(input);
                String args = Parser.getArguments(input);

                if (commandType.equals("bye")) {
                    this.ui.userGoodbye();
                    break;

                } else if (commandType.equals("list")) {
                    this.ui.showTaskList(this.tasklist);

                } else if (commandType.startsWith("mark")) {
                    int index = Parser.parseIndex(args);
                    Task task = this.tasklist.getTask(index);
                    task.markAsDone();
                    this.saveTasks();
                    this.ui.showMarkedTask(task);

                } else if (commandType.startsWith("unmark")) {
                    int index = Parser.parseIndex(args);
                    Task task = this.tasklist.getTask(index);
                    task.markAsUndone();
                    this.saveTasks();
                    this.ui.showUnmarkedTask(task);

                } else if (commandType.startsWith("delete")) {
                    int index = Parser.parseIndex(args);
                    Task removedTask = this.tasklist.getTask(index);
                    this.tasklist.deleteTask(index);
                    this.saveTasks();
                    this.ui.showDeletedTask(removedTask, this.tasklist);

                } else if (commandType.startsWith("todo")) {
                    if (args.isEmpty()) throw new EmptyDescriptionException("todo");
                    Task todo = new ToDo(args);
                    this.tasklist.addTask(todo);
                    this.saveTasks();
                    this.ui.showAddedTask(todo);
                    this.ui.showTaskCount(this.tasklist);

                } else if (commandType.startsWith("deadline")) {
                    String[] parts = Parser.parseDeadline(args);
                    String description = parts[0];
                    String by = parts[1];
                    if (description.isEmpty()) throw new EmptyDescriptionException("deadline");

                    LocalDateTime deadlineTime = DateTimeParser.parse(by);
                    Task deadline = new Deadline(description, deadlineTime);

                    this.tasklist.addTask(deadline);
                    this.saveTasks();
                    this.ui.showAddedTask(deadline);
                    this.ui.showTaskCount(this.tasklist);

                } else if (commandType.startsWith("event")) {
                    String[] parts = Parser.parseEvent(args);
                    String description = parts[0];
                    String from = parts[1];
                    String to = parts[2];
                    if (description.isEmpty()) throw new EmptyDescriptionException("event");

                    LocalDateTime fromTime = DateTimeParser.parse(from);
                    LocalDateTime toTime = DateTimeParser.parse(to);

                    Task event = new Event(description, fromTime, toTime);
                    this.tasklist.addTask(event);
                    this.saveTasks();
                    this.ui.showAddedTask(event);
                    this.ui.showTaskCount(this.tasklist);

                } else {
                    throw new UnknownCommandException();
                }

            } catch (AnoopException e) {
                this.ui.showError(e.getMessage());
            }
        }
    }

    private void saveTasks() {
        try {
            this.storage.save(this.tasklist.getTasks());
        } catch (Exception e) {
            this.ui.showError(e.getMessage());
        }
    }
}
