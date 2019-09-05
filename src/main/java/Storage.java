import java.io.*;
import java.util.ArrayList;


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

    public void addToFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        String fileContent = type + "|0|" + description + "|" + date + System.lineSeparator();
        bw.append(fileContent);
        bw.close();
    }

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


    public void clear() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(" ");
        bw.close();
    }



}



