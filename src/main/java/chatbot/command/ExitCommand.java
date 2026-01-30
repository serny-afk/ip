package chatbot.command;

import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.ui.Ui;

/**
 * TODO javadoc
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.userGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }


}
