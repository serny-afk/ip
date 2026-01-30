package chatbot.command;

import chatbot.exception.AnoopException;
import chatbot.storage.Storage;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.ui.Ui;

/**
 * TODO add javadoc
 */
public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AnoopException {
        Task t = tasks.unmarkTask(index);
        storage.save(tasks.getTasks());
        return ui.showUnmarkedTask(t);
    }
}
