package model.filterStrategies;

import model.Task;

public class AllTaskStrategy implements TaskFilterStrategy {
    @Override
    public boolean filter(Task task) {
        return true;
    }
}
