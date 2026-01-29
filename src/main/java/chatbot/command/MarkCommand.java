package chatbot.command;

import chatbot.exception.AnoopException;
import chatbot.storage.Storage;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.ui.Ui;

/**
 * TODO add javadoc
 */
public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AnoopException {
        Task t = tasks.markTask(this.index);
        storage.save(tasks.getTasks());
        ui.showMarkedTask(t);
    }
}
