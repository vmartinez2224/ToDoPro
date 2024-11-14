package co.edu.cue.ToDoPro.services;

import co.edu.cue.ToDoPro.entities.enums.TaskStatus;
import co.edu.cue.ToDoPro.entities.models.Task;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {

    private final List<Task> taskList = new ArrayList<>();
    private int idCounter = 1;

    public Task createTask(String title) {
        Task newTask = new Task(idCounter++, title, TaskStatus.PENDING);
        taskList.add(newTask);
        return newTask;
    }

    public List<Task> getAllTasks(TaskStatus status) {
        if (status == null) {
            return new ArrayList<>(taskList);
        }
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getStatus() == status) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    public Task findTaskById(int id) {
        return taskList.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Task updateTaskTitle(int id, String title) {
        Task task = findTaskById(id);
        task.setTitle(title);
        return task;
    }

    public Task updateTaskStatus(int id, TaskStatus status) {
        Task task = findTaskById(id);
        task.setStatus(status);
        return task;
    }

    public void deleteTask(int id) {
        Task task = findTaskById(id);
        taskList.remove(task);
    }

    public void clearTasks() {
        taskList.clear();
        idCounter = 1;
    }

}
