package model;

import java.util.Date;

public class TaskFactory {
    public static Task createTask(String name, String description, int priority, Date dueDate) {
        if (priority == 1) {
            return new UrgentTask(name, description, dueDate);
        } else {
            return new Task(name, description, priority, dueDate);
        }
    }
}

