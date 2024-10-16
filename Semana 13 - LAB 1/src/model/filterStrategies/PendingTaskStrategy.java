package model.filterStrategies;

import model.Task;

public class PendingTaskStrategy implements TaskFilterStrategy {
    @Override
    public boolean filter(Task task) {
        return !task.isCompleted();
    }
}
