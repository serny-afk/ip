package chatbot.command;

import chatbot.exception.AnoopException;
import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.ui.Ui;

/**
 * TODO javadoc comment
 */
public abstract class Command {

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws AnoopException;

    public boolean isExit() {
        return false;
    }
}
