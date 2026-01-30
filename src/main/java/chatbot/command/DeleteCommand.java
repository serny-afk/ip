package chatbot.command;

import chatbot.exception.AnoopException;
import chatbot.storage.Storage;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.ui.Ui;

/**
 * TODO add javadoc
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AnoopException {
        Task t = tasks.deleteTask(index);
        storage.save(tasks.getTasks());
        return ui.showDeletedTask(t, tasks);
    }
}
