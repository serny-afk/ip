import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
        Storage storage =  new Storage("./data/Anoop.txt");

        TaskList loadedTasks = new TaskList();

        try {
            loadedTasks = storage.load();
        } catch (Exception e) {
            ui.showError("Failed to load tasks: " + e.getMessage());
        }

        new Anoop(ui, loadedTasks, storage).run();
    }

    // Use of ChatGPT for overall formatting of try-catch blocks, substring manipulation
    // i.e. trim, substring methods, as well as overall class organization / design choice
    // (e.g. where printing should be handled, etc.)

    private void run() {
        this.ui.userWelcome();

        while (true) {
            String command = this.ui.readCommand().trim(); // trim once at the start

            try {
                if (command.equalsIgnoreCase("bye")) {
                    this.ui.userGoodbye();
                    break;

                } else if (command.equalsIgnoreCase("list")) {
                    this.ui.showTaskList(this.tasklist);

                } else if (command.toLowerCase().startsWith("mark ")) {
                    int index = Integer.parseInt(command.substring(5).trim()) - 1;
                    Task task = this.tasklist.getTask(index);
                    task.markAsDone();
                    this.saveTasks();
                    this.ui.showMarkedTask(task);

                } else if (command.toLowerCase().startsWith("unmark ")) {
                    int index = Integer.parseInt(command.substring(7).trim()) - 1;
                    Task task = this.tasklist.getTask(index);
                    task.markAsUndone();
                    this.saveTasks();
                    this.ui.showUnmarkedTask(task);

                } else if (command.toLowerCase().startsWith("delete ")) {
                    String arg = command.substring(7).trim();
                    if (arg.isEmpty()) {
                        throw new InvalidTaskNumberException();
                    }
                    int index = Integer.parseInt(arg) - 1;
                    Task removedTask = this.tasklist.getTask(index);
                    this.tasklist.deleteTask(index);
                    this.saveTasks();
                    this.ui.showDeletedTask(removedTask, this.tasklist);

                } else if (command.toLowerCase().startsWith("todo")) {
                    String description = command.substring(4).trim();
                    if (description.isEmpty()) throw new EmptyDescriptionException("todo");

                    Task todo = new ToDo(description);
                    this.tasklist.addTask(todo);
                    this.saveTasks();
                    this.ui.showAddedTask(todo);
                    this.ui.showTaskCount(this.tasklist);

                } else if (command.toLowerCase().startsWith("deadline")) {
                    String content = command.substring(8).trim();
                    if (!content.contains(" /by ")) throw new MissingByException();

                    String[] parts = content.split(" /by ", 2);
                    String description = parts[0].trim();
                    String by = parts[1].trim();
                    if (description.isEmpty()) throw new EmptyDescriptionException("deadline");

                    LocalDateTime deadlineTime = DateTimeParser.parse(by);

                    Task deadline = new Deadline(description, deadlineTime);
                    this.tasklist.addTask(deadline);
                    this.saveTasks();
                    this.ui.showAddedTask(deadline);
                    this.ui.showTaskCount(this.tasklist);

                } else if (command.toLowerCase().startsWith("event")) {
                    String content = command.substring(5).trim();
                    if (!content.contains(" /from ") || !content.contains(" /to ")) throw new MissingFromToException();

                    String[] parts = content.split(" /from ", 2);
                    String description = parts[0].trim();
                    if (description.isEmpty()) throw new EmptyDescriptionException("event");

                    String[] times = parts[1].split(" /to ", 2);
                    String from = times[0].trim();
                    LocalDateTime fromTime = DateTimeParser.parse(from);
                    String to = times[1].trim();
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
            } catch (NumberFormatException e) {
                this.ui.showError("Task number must be an integer.");
            } catch (Exception e) {
                this.ui.showError("Something went wrong: " + e.getMessage());
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
