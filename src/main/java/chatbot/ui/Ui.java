package chatbot.ui;

import java.util.Scanner;
import chatbot.task.*;
import chatbot.exception.InvalidTaskNumberException;

/**
 * Handles interaction with the user through the command line.
 * Responsible for printing messages, reading user input, and displaying tasks and errors.
 */
public class Ui {
    private static final String STRAIGHT_LINE = "____________________________________________________________";
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Prints a welcome message to the user.
     */
    public void userWelcome() {
        System.out.println(STRAIGHT_LINE);
        System.out.println("\nHello, I'm Anoop. I'm taking 48 MCs this sem.");
        System.out.println("\nWhat can I do for you?\n");
        System.out.println(STRAIGHT_LINE);
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The user's input as a string.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Prints a goodbye message to the user.
     */
    public void userGoodbye() {
        System.out.println(STRAIGHT_LINE);
        System.out.println("\nBye! See you in France!\n");
        System.out.println(STRAIGHT_LINE);
    }

    /**
     * Prints a command or message echoed back to the user.
     *
     * @param command The message to echo.
     */
    public void userEcho(String command) {
        System.out.println(STRAIGHT_LINE);
        System.out.println("\n" + command + "\n");
        System.out.println(STRAIGHT_LINE);
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasklist The TaskList to display.
     */
    public void showTaskList(TaskList tasklist) {
        System.out.println("\n" + STRAIGHT_LINE);
        System.out.println("Here are the tasks in your list:");
        try {
            for (int i = 0; i < tasklist.getSize(); i++) {
                System.out.println((i + 1) + "." + tasklist.getTask(i).toString());
            }
        } catch (InvalidTaskNumberException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n" + STRAIGHT_LINE);
    }

    /**
     * Displays the task that has been added.
     *
     * @param task The task that was added.
     */
    public void showAddedTask(Task task) {
        System.out.println("\n" + STRAIGHT_LINE);
        System.out.println("Got it. I've added this task:\n" + task.toString());
    }

    /**
     * Displays the task that has been marked as done.
     *
     * @param task The task that was marked done.
     */
    public void showMarkedTask(Task task) {
        System.out.println("\n" + STRAIGHT_LINE);
        System.out.println("\nNice! I've marked this task as done:");
        System.out.println(task.toString());
        System.out.println("\n" + STRAIGHT_LINE);
    }

    /**
     * Displays the task that has been unmarked (not done).
     *
     * @param task The task that was unmarked.
     */
    public void showUnmarkedTask(Task task) {
        System.out.println("\n" + STRAIGHT_LINE);
        System.out.println("\nOK, I've marked this task as not done yet:");
        System.out.println(task.toString());
        System.out.println("\n" + STRAIGHT_LINE);
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to display.
     */
    public void showError(String errorMessage) {
        System.out.println("\n" + STRAIGHT_LINE);
        System.out.println("\nError. " + errorMessage);
        System.out.println("\n" + STRAIGHT_LINE);
    }

    /**
     * Displays the current count of tasks.
     *
     * @param tasklist The TaskList whose count is displayed.
     */
    public void showTaskCount(TaskList tasklist) {
        System.out.println(tasklist.toString());
        System.out.println("\n" + STRAIGHT_LINE);
    }

    /**
     * Displays the task that was deleted and the updated task list.
     *
     * @param task     The task that was deleted.
     * @param tasklist The updated TaskList after deletion.
     */
    public void showDeletedTask(Task task, TaskList tasklist) {
        System.out.println("\n" + STRAIGHT_LINE);
        System.out.println("Noted. I've removed this task:\n  " + task.toString());
        System.out.println(tasklist.toString());
        System.out.println("\n" + STRAIGHT_LINE);
    }
}
