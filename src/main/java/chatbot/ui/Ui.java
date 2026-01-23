package chatbot.ui;

import java.util.Scanner;
import chatbot.task.*;
import chatbot.exception.InvalidTaskNumberException;

public class Ui {
    private static final String STRAIGHT_LINE = "____________________________________________________________";
    private final Scanner scanner = new Scanner(System.in); // user CLI input

    // class handles printing of text, user input and system output

    public void userWelcome() {
        System.out.println(STRAIGHT_LINE);
        System.out.println("\nHello, I'm chatbot.Anoop. I'm taking 48 MCs this sem.");
        System.out.println("\nWhat can I do for you?\n");
        System.out.println(STRAIGHT_LINE);
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void userGoodbye() {
        System.out.println(STRAIGHT_LINE);
        System.out.println("\nBye! See you in France!\n");
        System.out.println(STRAIGHT_LINE);
    }

    public void userEcho(String command) {
        System.out.println(STRAIGHT_LINE);
        System.out.println("\n" + command + "\n");
        System.out.println(STRAIGHT_LINE);
    }

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

    public void showAddedTask(Task task) {
        System.out.println("\n" + STRAIGHT_LINE);
        System.out.println("Got it. I've added this task:\n" + task.toString());
    }

    public void showMarkedTask(Task task) {
        System.out.println("\n" + STRAIGHT_LINE);
        System.out.println("\n" + "Nice! I've marked this task as done:");
        System.out.println(task.toString());
        System.out.println("\n" + STRAIGHT_LINE);
    }

    public void showUnmarkedTask(Task task) {
        System.out.println("\n" + STRAIGHT_LINE);
        System.out.println("\n" + "OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
        System.out.println("\n" + STRAIGHT_LINE);
    }

    public void showError(String errorMessage) {
        System.out.println("\n" + STRAIGHT_LINE);
        System.out.println("\n" + "Error. " + errorMessage);
        System.out.println("\n" + STRAIGHT_LINE);
    }

    public void showTaskCount(TaskList tasklist) {
        System.out.println(tasklist.toString());
        System.out.println("\n" + STRAIGHT_LINE);
    }

    public void showDeletedTask(Task task, TaskList tasklist) {
        System.out.println("\n" + STRAIGHT_LINE);
        System.out.println("Noted. I've removed this task:\n  " + task.toString());
        System.out.println(tasklist.toString());
        System.out.println("\n" + STRAIGHT_LINE);
    }

}
