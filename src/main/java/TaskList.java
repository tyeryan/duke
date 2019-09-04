import java.util.ArrayList;

public class TaskList {

    public static ArrayList<Task> taskArrayList;

    public TaskList() {
        taskArrayList = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        this.taskArrayList.add(task);
    }

    public Task getTask(int num) {
        return this.taskArrayList.get(num);
    }


    public void clear() {
        this.taskArrayList.clear();
    }

    public void remove(int num) {
        this.taskArrayList.remove(num);
    }

    public ArrayList<Task> getTasks() {
        return this.taskArrayList;
    }

    public int size() {
        return this.taskArrayList.size();
    }
}
