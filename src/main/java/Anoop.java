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

            } else {
                try {
                    this.tasklist.addTask(command);
                    ui.showAddedtask(command);
                } catch (TaskListFullException e) {
                    System.out.println(e.getMessage());
                }
            }

        }
    }
}


