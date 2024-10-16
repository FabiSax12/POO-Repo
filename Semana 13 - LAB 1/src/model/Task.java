package model;

import java.util.Date;

public class Task {
    private String name;
    private String description;
    private boolean isCompleted;
    private int priority;
    private Date dueDate;

    public Task(String name, String description, int priority, Date dueDate) {
        this.name = name;
        this.description = description;
        this.isCompleted = false;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public int getPriority() {
        return priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void completeTask() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        String status = isCompleted ? " [Completada]" : " [Pendiente]";
        String dueDateString = (dueDate != null) ? " | Fecha límite: " + dueDate.toString() : "";
        return name + status + " | Prioridad: " + priority + dueDateString;
    }
}


