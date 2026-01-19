public class Anoop {

    private final Ui ui;
    private final TaskList tasklist;

    public Anoop(Ui ui, TaskList tasklist) {
        this.ui = ui;
        this.tasklist = tasklist;
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        new Anoop(ui, taskList).run();
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
                    this.ui.showTasklist(this.tasklist);

                } else if (command.toLowerCase().startsWith("mark ")) {
                    int index = Integer.parseInt(command.substring(5).trim()) - 1;
                    Task task = this.tasklist.getTask(index);
                    task.markAsDone();
                    this.ui.showMarkedtask(task);

                } else if (command.toLowerCase().startsWith("unmark ")) {
                    int index = Integer.parseInt(command.substring(7).trim()) - 1;
                    Task task = this.tasklist.getTask(index);
                    task.markAsUndone();
                    this.ui.showUnmarkedtask(task);

                } else if (command.toLowerCase().startsWith("delete ")) {
                    String arg = command.substring(7).trim();
                    if (arg.isEmpty()) {
                        throw new InvalidTaskNumberException();
                    }
                    int index = Integer.parseInt(arg) - 1;
                    Task removedTask = this.tasklist.getTask(index);
                    this.tasklist.deleteTask(index);
                    this.ui.showDeletedTask(removedTask, this.tasklist);

                } else if (command.toLowerCase().startsWith("todo")) {
                    String description = command.substring(4).trim();
                    if (description.isEmpty()) throw new EmptyDescriptionException("todo");

                    Task todo = new ToDo(description);
                    this.tasklist.addTask(todo);
                    this.ui.showAddedtask(todo);
                    this.ui.showTaskcount(this.tasklist);

                } else if (command.toLowerCase().startsWith("deadline")) {
                    String content = command.substring(8).trim();
                    if (!content.contains(" /by ")) throw new MissingByException();

                    String[] parts = content.split(" /by ", 2);
                    String description = parts[0].trim();
                    String by = parts[1].trim();
                    if (description.isEmpty()) throw new EmptyDescriptionException("deadline");

                    Task deadline = new Deadline(description, by);
                    this.tasklist.addTask(deadline);
                    this.ui.showAddedtask(deadline);
                    this.ui.showTaskcount(this.tasklist);

                } else if (command.toLowerCase().startsWith("event")) {
                    String content = command.substring(5).trim();
                    if (!content.contains(" /from ") || !content.contains(" /to ")) throw new MissingFromToException();

                    String[] parts = content.split(" /from ", 2);
                    String description = parts[0].trim();
                    if (description.isEmpty()) throw new EmptyDescriptionException("event");

                    String[] times = parts[1].split(" /to ", 2);
                    String from = times[0].trim();
                    String to = times[1].trim();

                    Task event = new Event(description, from, to);
                    this.tasklist.addTask(event);
                    this.ui.showAddedtask(event);
                    this.ui.showTaskcount(this.tasklist);

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
}
