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
                    System.out.println(i+1 + ". " + taskList.get(i).toString());
                }
            }
            else if (userCmd.contains("done")) {
                System.out.println("Nice! I've marked this tasks as done:");
                String[] numberString = userCmd.split(" ");
                int number = parseInt(numberString[1]);
                taskList.get(number-1).toggleIsDone();
                System.out.println("[" + taskList.get(number-1).getStatusIcon() + "] " + taskList.get(number-1).getDescription());
            }

            else if (userCmd.contains("todo")) {
                String[] userInput = userCmd.split(" ");
                String task = "";
                for(int i=1; i<userInput.length; i++) {
                    task += userInput[i] + " ";
                }
                Task t = new ToDo(task);
                taskList.add(t);
                System.out.println("Got it. I've added this task:");
                System.out.println(t.toString());
                System.out.println("Now you have " + taskList.size() + " in the list");
            }

            else if (userCmd.contains("deadline")) {
                String[] userInput = userCmd.split(" ");
                String task = "";
                String date = "";
                for(int i=1; i<userInput.length; i++) {
                    if(userInput[i].equals("/by")) {
                        date = userInput[i+1];
                        break;
                    }
                    task += userInput[i] + " ";
                }
                System.out.println(task);
                Task t = new Deadline(task, date);
                taskList.add(t);
                System.out.println("Got it. I've added this task:");
                System.out.println(t.toString());
                System.out.println("Now you have " + taskList.size() + " in the list");
            }

            else if (userCmd.contains("event")) {
                String[] userInput = userCmd.split(" ");
                String task = "";
                String date = "";
                for(int i=1; i<userInput.length; i++) {
                    if(userInput[i].equals("/at")) {
                        for(int j=i+1; j<userInput.length; j++) {
                            date += userInput[j] + " ";
                        }
                        break;
                    }
                    task += userInput[i] + " ";
                }
                System.out.println(task);
                Task t = new Event(task, date);
                taskList.add(t);
                System.out.println("Got it. I've added this task:");
                System.out.println(t.toString());
                System.out.println("Now you have " + taskList.size() + "tasks in the list");
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
