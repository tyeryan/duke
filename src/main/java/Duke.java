import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
        userCmd();
    }

    public static void greeting() {
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void userCmd() {
        Scanner input = new Scanner(System.in);

        String userCmd = input.nextLine();
        ArrayList<String> cmdList = new ArrayList<>();

        while (true) {
            if (userCmd.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(userCmd);
            userCmd = input.nextLine();
            cmdList.add(userCmd);
        }
    }
}
