package chatbot.command;

import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.ui.Ui;

/**
 * TODO javadoc
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.userGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }


}
