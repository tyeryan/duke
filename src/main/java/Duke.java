import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

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
        ArrayList<Task> taskList = new ArrayList<>();

        while (true) {
            userCmd = input.nextLine();
            if (userCmd.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (userCmd.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for(int i=0; i<taskList.size(); i++) {
                    System.out.println(i+1 + ". " + "[" + taskList.get(i).getStatusIcon() + "] " + taskList.get(i).getDescription());
                }
            }
            else if (userCmd.contains("done")) {
                System.out.println("Nice! I've marked this tasks as done:");
                String[] numberString = userCmd.split(" ");
                int number = parseInt(numberString[1]);
                taskList.get(number-1).toggleIsDone();
                System.out.println("[" + taskList.get(number-1).getStatusIcon() + "] " + taskList.get(number-1).getDescription());
            }
            else {
                Task t = new Task(userCmd);
                taskList.add(t);
                cmdList.add(userCmd);
                System.out.println("added: " + userCmd);
            }
        }
    }
}
