import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import static java.lang.Integer.parseInt;

/**
 * The Duke program implements an personal assistant application
 * which helps you keep track of your tasks.
 *
 * @author  Ryan Tay
 * @version 1.0
 */
public class Duke {
    public static TaskList taskList = new TaskList();
    public static Storage storage = new Storage();


    public static void main(String[] args) throws DukeException, IOException, ParseException {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        greeting();
        userCmd();
    }

    /**
     * This function prints the welcome message for duke
     * prompting the user for its input
     */
    public static void greeting() {
        String line = "_____________________________________________\n";
        System.out.println("Hello I'm Duke\n" + "What can I do for you?\n" + line);
    }

    /**
     * This function takes in the user inputs and will execute a command based on the input
     * if the command is valid if not it will prompt the user for a valid input.
     * List of valid inputs:
     * 1. bye: Exits the duke program
     * 2. list: Lists out all the tasks
     * 3. done: Marks a tasks as done by inputting the task number. eg. done 2
     * 4. todo: Creates a new todo task
     * 5. deadline: Creates a new deadline task
     * 6. event: Creates a new event task
     * 7. delete: Deletes a task from the list. eg. delete 3
     * 8. clear: Clears all the tasks in the list
     * 9. find: Find all the tasks that contains the keyword in the list. eg. Find work
     * 10. read: Reads the saved tasks from the previous time the program was ran.
     *
     * @throws IOException
     * @throws ParseException
     */
    public static void userCmd() throws IOException, ParseException {
        Scanner input = new Scanner(System.in);
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy HHmm");
        formatDate.setLenient(false);

        while (true) {
            String userCmd = input.nextLine();
            int indexSpace = userCmd.indexOf(" ");
            String command = (indexSpace != -1) ? userCmd.substring(0, indexSpace) : userCmd;

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            else if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(i + 1 + ". " + taskList.getTask(i).toString());
                }
            }

            else if (command.equals("done")) {
                System.out.println("Nice! I've marked this tasks as done:");
                String[] numberString = userCmd.split(" ");
                int number = parseInt(numberString[1]);
                taskList.getTask(number - 1).toggleIsDone();
                System.out.println("[" + taskList.getTask(number - 1).getStatusIcon() + "] " + taskList.getTask(number - 1).getDescription());
                //Storage s = new Storage();
                storage.markDone(number);
            }


            else if (command.equals("todo")) {
                String[] userInput = userCmd.split(" ");
                String task = "";

                //Throwing an exception if argument is empty
                if (userCmd.substring(5).isEmpty()) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    continue;
                }

                for (int i = 1; i < userInput.length; i++) {
                    task += userInput[i] + " ";
                }
                Task t = new ToDo(task, false);
                taskList.addTask(t);
                System.out.println("Got it. I've added this task:");
                System.out.println(t.toString());
                System.out.println("Now you have " + taskList.size() + " in the list");

                try {
                    storage = new Storage("T", t.getDescription());
                    storage.addToFile();
                }
                catch (IOException e) {
                    System.out.println("There is something wrong with the file");
                }
            }

            //if deadline is the command
            else if (command.equals("deadline")) {
                String[] userInput = userCmd.split(" ");
                if (userCmd.substring(5).isEmpty()) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    continue;
                }
                int indexOfTime = userCmd.indexOf("/by");
                String task = "";
                String time = "";
                Date date = formatDate.parse(userCmd.substring(indexOfTime + 4));
                for (int i = 1; i < userInput.length; i++) {
                    if (userInput[i].equals("/by")) {
                        for (int j = i + 1; j < userInput.length; j++) {
                            time += userInput[j] + " ";
                        }
                        break;
                    }
                    task += userInput[i] + " ";
                }

                Task t = new Deadline(task, false, time);
                taskList.addTask(t);
                System.out.println("Got it. I've added this task:");
                System.out.println(t.toString());
                System.out.println("Now you have " + taskList.size() + " in the list");

                //Writing to file
                storage = new Storage("D", t.getDescription(), time);
                storage.addToFile();
            }

            //if event is the command
            else if (command.equals("event")) {
                String[] userInput = userCmd.split(" ");
                if (userCmd.substring(5).isEmpty()) {
                    System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                    continue;
                }
                int indexOfTime = userCmd.indexOf("/at");
                Date date = formatDate.parse(userCmd.substring(indexOfTime + 4));

                String task = "";
                String time = "";
                for (int i = 1; i < userInput.length; i++) {
                    if (userInput[i].equals("/at")) {
                        for (int j = i + 1; j < userInput.length; j++) {
                            time += userInput[j] + " ";
                        }
                        break;
                    }
                    task += userInput[i] + " ";
                }
                System.out.println(task);
                Task t = new Event(task, false, time);
                taskList.addTask(t);
                System.out.println("Got it. I've added this task:");
                System.out.println(t.toString());
                System.out.println("Now you have " + taskList.size() + " tasks in the list");

                storage = new Storage("E", t.getDescription(), time);
                storage.addToFile();
            }

            else if (command.equals("delete")) {
                try {
                    int index = parseInt(userCmd.substring(7));
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(taskList.getTask(index-1));
                    taskList.remove(index-1);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");

                    storage = new Storage();
                    storage.remove(index);
                }
                catch (IOException e){
                    System.out.println("There is something wrong with the file");
                }
            }

            else if (command.equals("clear")) {
                System.out.println("List is cleared");
                storage = new Storage();
                storage.clear();
                taskList.clear();
            }


            else if (command.equals("find")) {
                storage = new Storage();
                ArrayList<Integer> findList = storage.find(userCmd.substring(5));
                System.out.println("Here are the matching tasks in your list:");
                for(int i=1; i<=findList.size(); i++) {
                    System.out.println(i + ". " + taskList.getTask(findList.get(i-1)));
                }
            }


            else if (command.equals("read")) {
                storage = new Storage();
                taskList = storage.read();
            }

            //if no command words are being used
            else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}


