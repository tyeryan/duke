import java.io.*;
import java.util.ArrayList;

/**
 * Storage class which acts like a database for Duke.
 * All tasks will be written to an output file and the data can be read
 * by the user inputting a read command
 */

public class Storage {
    protected String type;
    protected String description;
    protected String date;
    public String file = "C:\\Users\\javie\\OneDrive\\Desktop\\CS2113T\\duke\\data\\data.txt";


    public Storage() {

    }

    public Storage(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public Storage(String type, String description, String date) {
        this.type = type;
        this.description = description;
        this.date = date;
    }

    /**
     * Adds the tasks into a text file with a certain format
     *
     * @throws IOException
     */
    public void addToFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        String fileContent = type + "|0|" + description + "|" + date + System.lineSeparator();
        bw.append(fileContent);
        bw.close();
    }

    /**
     * Read the tasks from the data file and inserts it back into Duke.
     *
     * @return The old taskList that has been stored in the text file
     * @throws IOException
     */
    public TaskList read() throws IOException {
        TaskList taskList = new TaskList();
        BufferedReader br = new BufferedReader(new FileReader(file));

        String inputLine;
        //System.out.println(inputLine);
        while((inputLine = br.readLine()) != null) {
            inputLine = inputLine.trim();

            if (inputLine.substring(0,1).equals("T")) {
                String[] arrayLine = inputLine.split("[|]");
                if(arrayLine[1].equals("0")) {
                    Task t = new ToDo(arrayLine[2], false);
                    taskList.addTask(t);
                }
                else {
                    Task t = new ToDo(arrayLine[2], true);
                    taskList.addTask(t);
                }
            }
            else if (inputLine.substring(0,1).equals("D")) {
                String[] arrayLine = inputLine.split("[|]");
                if(arrayLine[1].equals("0")) {
                    Task t = new Deadline(arrayLine[2], false, arrayLine[3]);
                    taskList.addTask(t);
                }
                else {
                    Task t = new Deadline(arrayLine[2], true, arrayLine[3]);
                    taskList.addTask(t);
                }
            }
            else if (inputLine.substring(0,1).equals("E")) {
                String[] arrayLine = inputLine.split("[|]");
                if(arrayLine[1].equals("0")) {
                    Task t = new Event(arrayLine[2], false, arrayLine[3]);
                    taskList.addTask(t);
                }
                else {
                    Task t = new Event(arrayLine[2], true, arrayLine[3]);
                    taskList.addTask(t);
                }
            }
        }
        return taskList;
    }

    /**
     * Tasks in the task number and mark it as completed in the database by changing
     * the value of the output from '0' to '1'. Following a boolean function.
     *
     * @param taskNum the task number in the taskList
     * @throws IOException
     */
    public void markDone (int taskNum) throws IOException {
        String des = "";
        File fileModified = new File(file);
        BufferedReader br = new BufferedReader(new FileReader(fileModified));

        int count = 1;
        String input = br.readLine();
        while (input != null) {
            if (count == taskNum) {
                input = input.replace("0", "1");
            }
            des = des + input + System.lineSeparator();
            input = br.readLine();
            count += 1;
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(fileModified));
        bw.write(des);
        br.close();
        bw.close();
    }

    /**
     * Remove a certain task in the database based on the task number inputted.
     *
     * @param taskNum the task number in the taskList
     * @throws IOException
     */
    public void remove(int taskNum) throws IOException {
        String content = "";
        File fileModified = new File(file);
        BufferedReader br = new BufferedReader(new FileReader(fileModified));

        String taskLine = br.readLine();
        int count = 1;
        while (taskLine != null) {
            if (count == taskNum) {
                taskLine = br.readLine();
                count += 1;
                continue;
            }
            content = content + taskLine + System.lineSeparator();
            taskLine = br.readLine();
            count += 1;
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(fileModified));
        bw.write(content);

        br.close();
        bw.close();
    }


    /**
     * Clearing the whole list. Making the list empty.
     *
     * @throws IOException
     */
    public void clear() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(" ");
        bw.close();
    }


    /**
     * Finding certain tasks which has the same keywords as the input string.
     *
     * @param input A string which contains the keywords the user wants to find
     * @return An ArrayList of integers containing the task numbers of the tasks found.
     * @throws IOException
     */
    public ArrayList<Integer> find(String input) throws IOException {
        ArrayList<Integer> findList = new ArrayList<>();
        File fileModified = new File(file);
        BufferedReader br = new BufferedReader(new FileReader(fileModified));

        String inputLine = br.readLine();
        int count = 0;
        while (inputLine != null) {
            if (inputLine.contains(input)) {
                findList.add(count);
            }
            inputLine = br.readLine();
            count += 1;
        }
        return findList;
    }

}
