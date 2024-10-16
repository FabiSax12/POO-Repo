package model;

import java.util.Date;

public class UrgentTask extends Task {
    public UrgentTask(String name, String description, Date dueDate) {
        super(name, description, 1, dueDate);
    }

    @Override
    public String toString() {
        return super.toString() + " [URGENTE]";
    }
}



