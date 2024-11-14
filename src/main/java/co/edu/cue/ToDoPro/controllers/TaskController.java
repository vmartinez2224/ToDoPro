package co.edu.cue.ToDoPro.controllers;

import co.edu.cue.ToDoPro.entities.enums.TaskStatus;
import co.edu.cue.ToDoPro.entities.models.Task;
import co.edu.cue.ToDoPro.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestParam String title) {
        Task newTask = taskService.createTask(title);
        return ResponseEntity.ok(newTask);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam(required = false) TaskStatus status) {
        List<Task> tasks = taskService.getAllTasks(status);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable int id) {
        Task task = taskService.findTaskById(id);
        if (task == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}/title")
    public ResponseEntity<Task> updateTaskTitle(@PathVariable int id, @RequestParam String title) {
        Task updatedTask = taskService.updateTaskTitle(id, title);
        return ResponseEntity.ok(updatedTask);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable int id, @RequestParam TaskStatus status) {
        Task updatedTask = taskService.updateTaskStatus(id, status);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}
