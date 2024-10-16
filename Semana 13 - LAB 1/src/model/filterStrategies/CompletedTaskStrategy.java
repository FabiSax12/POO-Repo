package model.filterStrategies;

import model.Task;

public class CompletedTaskStrategy implements TaskFilterStrategy {
    @Override
    public boolean filter(Task task) {
        return task.isCompleted();
    }
}
