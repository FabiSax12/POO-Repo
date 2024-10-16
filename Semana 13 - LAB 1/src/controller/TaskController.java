package controller;

import model.Task;
import model.TaskDataManager;
import model.TaskFactory;
import model.TaskModel;
import model.filterStrategies.AllTaskStrategy;
import model.filterStrategies.CompletedTaskStrategy;
import model.filterStrategies.PendingTaskStrategy;
import model.filterStrategies.TaskFilterStrategy;
import view.TaskView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

public class TaskController {
    private TaskView view;
    private TaskModel taskModel;
    private TaskFilterStrategy filterStrategy;
    private TaskDataManager dataManager;

    public TaskController(TaskView view, TaskModel taskModel) {
        this.view = view;
        this.taskModel = taskModel;
        this.dataManager = TaskDataManager.getInstance();
        dataManager.setTaskModel(taskModel);

        try {
            dataManager.loadTasks();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        this.view.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task newTask = view.getTaskFromUserInput();
                if (newTask != null) {
                    Task task = TaskFactory.createTask(newTask.getName(), newTask.getDescription(), newTask.getPriority(), newTask.getDueDate());
                    taskModel.addTask(task);
                    refreshTaskList();
                }
            }
        });

        this.view.getCompleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task selectedTask = view.getTaskList().getSelectedValue();
                if (selectedTask != null) {
                    selectedTask.completeTask();
                    refreshTaskList();
                } else {
                    JOptionPane.showMessageDialog(view, "Seleccione una tarea para completar.");
                }
            }
        });

        this.view.getFilterComboBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFilter = (String) view.getFilterComboBox().getSelectedItem();
                switch (selectedFilter) {
                    case "Completadas":
                        filterStrategy = new CompletedTaskStrategy();
                        break;
                    case "Pendientes":
                        filterStrategy = new PendingTaskStrategy();
                        break;
                    default:
                        filterStrategy = new AllTaskStrategy();
                }
                refreshTaskList();
            }
        });

        filterStrategy = new AllTaskStrategy();
        refreshTaskList();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                dataManager.saveTasks();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }

    private void refreshTaskList() {
        DefaultListModel<Task> listModel = view.getTaskListModel();
        listModel.clear();

        for (Task task : taskModel.getTasks()) {
            if (filterStrategy.filter(task)) {
                listModel.addElement(task);
            }
        }
    }
}
