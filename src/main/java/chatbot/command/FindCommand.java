package chatbot.command;

import java.util.ArrayList;

import chatbot.storage.Storage;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.ui.Ui;

/**
 * TODO add javadoc
 */
public class FindCommand extends Command {
    public final String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findTasks(this.query);
        return ui.showMatchingTasks(matchingTasks);
    }
}
