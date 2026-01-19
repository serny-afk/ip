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

    private void run() {
        this.ui.userWelcome();

        while (true) {
            String command = this.ui.readCommand().trim();

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

                } else if (command.toLowerCase().startsWith("todo")) {
                    String description = command.length() > 4 ? command.substring(5).trim() : "";
                    if (description.isEmpty()) throw new EmptyDescriptionException("todo");

                    Task todo = new ToDo(description);
                    this.tasklist.addTask(todo);
                    this.ui.showAddedtask(todo);
                    this.ui.showTaskcount(this.tasklist);

                } else if (command.toLowerCase().startsWith("deadline")) {
                    String content = command.length() > 8 ? command.substring(9).trim() : "";
                    if (!content.contains(" /by ")) throw new MissingByException();

                    String[] parts = content.split(" /by ", 2);
                    if (parts[0].trim().isEmpty()) throw new EmptyDescriptionException("deadline");

                    Task deadline = new Deadline(parts[0].trim(), parts[1].trim());
                    this.tasklist.addTask(deadline);
                    this.ui.showAddedtask(deadline);
                    this.ui.showTaskcount(this.tasklist);

                } else if (command.toLowerCase().startsWith("event")) {
                    String content = command.length() > 5 ? command.substring(6).trim() : "";
                    if (!content.contains(" /from ") || !content.contains(" /to ")) throw new MissingFromToException();

                    String[] parts = content.split(" /from ", 2);
                    if (parts[0].trim().isEmpty()) throw new EmptyDescriptionException("event");

                    String[] times = parts[1].split(" /to ", 2);
                    Task event = new Event(parts[0].trim(), times[0].trim(), times[1].trim());

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
