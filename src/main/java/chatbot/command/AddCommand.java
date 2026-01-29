package chatbot.command;

import chatbot.Anoop;
import chatbot.exception.AnoopException;
import chatbot.storage.Storage;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.ui.Ui;

/**
 * TODO javadoc
 */
public class AddCommand extends Command {
    private Task taskToAdd;

    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AnoopException {
        tasks.addTask(this.taskToAdd);
        storage.save(tasks.getTasks());
        ui.showAddedTask(this.taskToAdd);
    }

}
