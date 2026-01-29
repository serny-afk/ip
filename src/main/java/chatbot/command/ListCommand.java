package chatbot.command;

import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.ui.Ui;

/**
 * TODO add javadoc
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
