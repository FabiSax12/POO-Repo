package view;

import model.Task;
import model.UrgentTask;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskView extends JFrame {
    private DefaultListModel<Task> taskListModel;
    private JList<Task> taskList;
    private JButton addButton;
    private JButton completeButton;
    private JComboBox<String> filterComboBox;

    public TaskView() {
        setTitle("Gestor de Tareas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);

        addButton = new JButton("Añadir Tarea");
        completeButton = new JButton("Completar Tarea");

        String[] filterOptions = {"Todas", "Completadas", "Pendientes"};
        filterComboBox = new JComboBox<>(filterOptions);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(completeButton);
        buttonPanel.add(filterComboBox);

        setLayout(new BorderLayout());
        add(new JScrollPane(taskList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        taskList.setCellRenderer(new TaskListRenderer());

        setVisible(true);
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getCompleteButton() {
        return completeButton;
    }

    public JList<Task> getTaskList() {
        return taskList;
    }

    public DefaultListModel<Task> getTaskListModel() {
        return taskListModel;
    }

    public JComboBox<String> getFilterComboBox() {
        return filterComboBox;
    }

    public Task getTaskFromUserInput() {
        String name = JOptionPane.showInputDialog("Ingrese el nombre de la tarea:");
        if (name == null || name.trim().isEmpty()) {
            return null;
        }

        String description = JOptionPane.showInputDialog("Ingrese una descripción para la tarea:");
        if (description == null || description.trim().isEmpty()) {
            description = "Sin descripción";
        }

        String priorityStr = JOptionPane.showInputDialog("Ingrese la prioridad (1 - alta, 5 - baja):");
        int priority;
        try {
            priority = Integer.parseInt(priorityStr);
            if (priority < 1 || priority > 5) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            priority = 3;
        }

        String dueDateStr = JOptionPane.showInputDialog("Ingrese la fecha límite (dd/MM/yyyy) o deje en blanco:");
        Date dueDate = null;
        if (dueDateStr != null && !dueDateStr.trim().isEmpty()) {
            try {
                dueDate = new SimpleDateFormat("dd/MM/yyyy").parse(dueDateStr);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Formato de fecha inválido. No se establecerá la fecha límite.");
            }
        }

        return new Task(name, description, priority, dueDate);
    }
}



class TaskListRenderer extends JLabel implements ListCellRenderer<Task> {
    @Override
    public Component getListCellRendererComponent(JList<? extends Task> list, Task task, int index, boolean isSelected, boolean cellHasFocus) {
        setText(task.toString());

        if (task instanceof UrgentTask) {
            setForeground(Color.RED);
            setFont(getFont().deriveFont(Font.BOLD));
        } else {
            setForeground(Color.BLACK);
            setFont(getFont().deriveFont(Font.PLAIN));
        }

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
        }

        setOpaque(true);
        return this;
    }
}
