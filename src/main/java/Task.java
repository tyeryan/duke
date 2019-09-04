import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }


    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getDescription() {
        return this.description;
    }

    public void toggleIsDone() {
        this.isDone = !isDone;
    }


    @Override
    public String toString() {
        return '[' + getStatusIcon() + "] " + description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

}
