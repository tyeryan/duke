public class Event extends Task {
    protected String at;

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }


    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + description + " (at: " + at + ")";
    }
}

