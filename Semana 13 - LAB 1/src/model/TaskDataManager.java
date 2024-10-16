package model;

import org.json.JSONObject;
import org.json.JSONArray;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskDataManager {
    private static TaskDataManager instance;
    private static String path;
    private TaskModel tasks;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private TaskDataManager() {
        path = System.getProperty("user.dir") + "\\src\\data\\tasks.json";
        tasks = new TaskModel();
    }

    public static TaskDataManager getInstance() {
        if (instance == null) {
            instance = new TaskDataManager();
        }
        return instance;
    }

    public void setTaskModel(TaskModel taskModel) {
        this.tasks = taskModel;
    }

    public void saveTasks() throws IOException {
        JSONArray data = new JSONArray();

        for (Task task : tasks.getTasks()) {
            JSONObject taskJson = new JSONObject();
            taskJson.put("name", task.getName());
            taskJson.put("description", task.getDescription());
            taskJson.put("isCompleted", task.isCompleted());
            taskJson.put("priority", task.getPriority());
            taskJson.put("dueDate", task.getDueDate() != null ? dateFormat.format(task.getDueDate()) : null);

            data.put(taskJson);
        }

        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(data.toString(4));
        fileWriter.close();
    }

    public void loadTasks() throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder jsonContent = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            jsonContent.append(line);
        }
        reader.close();

        JSONArray data = new JSONArray(jsonContent.toString());

        tasks.clearTasks();

        for (int i = 0; i < data.length(); i++) {
            JSONObject taskJson = data.getJSONObject(i);

            String name = taskJson.getString("name");
            String description = taskJson.getString("description");
            boolean isCompleted = taskJson.getBoolean("isCompleted");
            int priority = taskJson.getInt("priority");

            String dueDateStr = taskJson.optString("dueDate", null);
            Date dueDate = null;
            if (dueDateStr != null && !dueDateStr.isEmpty()) {
                dueDate = dateFormat.parse(dueDateStr);
            }

            Task task = TaskFactory.createTask(name, description, priority, dueDate);
            if (isCompleted) {
                task.completeTask();
            }

            tasks.addTask(task);
        }
    }
}

