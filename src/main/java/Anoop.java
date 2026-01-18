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
            String command = this.ui.readCommand();

            if (command.equals("bye")) {
                this.ui.userGoodbye();
                break;
            } else if (command.equals("list")) {
                this.ui.showTasklist(this.tasklist);
            } else {
                try {
                    this.tasklist.addTask(command);
                    this.ui.showAddedtask(command);
                } catch (TaskListFullException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }

}


