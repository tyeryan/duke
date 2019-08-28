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

        String userCmd = "";
        ArrayList<String> cmdList = new ArrayList<>();

        while (true) {
            userCmd = input.nextLine();
            if (userCmd.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (userCmd.equals("list")) {
                for(int i=0; i<cmdList.size(); i++) {
                    System.out.println(i+1 + ". " + cmdList.get(i));
                }
            }
            else {
                cmdList.add(userCmd);
                System.out.println("added: " + userCmd);
            }
        }
    }
}
