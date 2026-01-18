import java.util.Scanner;

public class Ui {
    private static final String STRAIGHT_LINE = "____________________________________________________________";
    private final Scanner scanner = new Scanner(System.in); // mutable instance

    public void userWelcome() {
        System.out.println(STRAIGHT_LINE);
        System.out.println("\nHello, I'm Anoop. I'm taking 48 MCs this sem.");
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
}
