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
            String command = this.ui.readCommand().trim(); // ignore whitespace

            if (command.equalsIgnoreCase("bye")) {
                this.ui.userGoodbye();
                break;

            } else if (command.equalsIgnoreCase("list")) {
                this.ui.showTasklist(this.tasklist);

            } else if (command.toLowerCase().startsWith("mark ")) {
                try {
                    int index = Integer.parseInt(command.substring(5).trim()) - 1; // ?
                    Task task = this.tasklist.getTask(index);
                    task.markAsDone();
                    this.ui.showMarkedtask(task);

                } catch (Exception e) {
                    System.out.println("Invalid task number");
                }

            } else if (command.toLowerCase().startsWith("unmark ")) {
                try {
                    int index = Integer.parseInt(command.substring(7).trim()) - 1;
                    Task task = this.tasklist.getTask(index);
                    task.markAsUndone();
                    ui.showUnmarkedtask(task);
                } catch (Exception e) {
                    System.out.println("Invalid task number");
                }

            } else if (command.toLowerCase().startsWith("todo ")) {
                String description = command.substring(5).trim();
                Task todo = new ToDo(description);
                this.tasklist.addTask(todo);
                this.ui.showAddedtask(todo);

            } else if (command.toLowerCase().startsWith("deadline ")) {
                String content = command.substring(9).trim();
                String[] parts = content.split(" /by ");

                String description = parts[0];
                String by = parts[1];

                Task deadline = new Deadline(description, by);
                this.tasklist.addTask(deadline);
                this.ui.showAddedtask(deadline);

            } else if (command.toLowerCase().startsWith("event ")) {
                String content = command.substring(6).trim();
                String[] parts = content.split(" /from ");

                String description = parts[0];

                String[] timeParts = parts[1].split(" /to ");
                String from = timeParts[0];
                String to = timeParts[1];

                Task event = new Event(description, from, to);
                this.tasklist.addTask(event);
                this.ui.showAddedtask(event);
            }


            else {
                this.ui.showCommanderror();
            }

        }
    }
}


