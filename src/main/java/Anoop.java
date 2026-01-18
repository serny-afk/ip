public class Anoop {
    public static void main(String[] args) {
        new Anoop().run();
    }

    private void run() {
        Ui ui = new Ui();
        ui.userWelcome();

        while (true) {
            String command = ui.readCommand();

            if (command.equals("bye")) {
                ui.userGoodbye();
                break;
            } else {
                ui.userEcho(command);
            }

        }
    }
}


