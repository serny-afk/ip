package chatbot.command;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AnoopException {
        tasks.addTask(this.taskToAdd);
        storage.save(tasks.getTasks());
        return ui.showAddedTask(this.taskToAdd);
    }

}
