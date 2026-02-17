package chatbot.command;

import chatbot.exception.AnoopException;
import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.ui.Ui;
/**
 * TODO add javadoc
 */
public class HelpCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AnoopException {
        return ui.showHelpPage();
    }
}
