package model.filterStrategies;

import model.Task;

public interface TaskFilterStrategy {
    boolean filter(Task task);
}

