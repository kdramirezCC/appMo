package com.nak.todome_backend.controller;

import com.nak.todome_backend.entity.Task;
import com.nak.todome_backend.entity.FollowUpTask;
import com.nak.todome_backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Task updatedTask = taskService.updateTask(id, taskDetails);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Task> markTaskAsComplete(@PathVariable Long id, @RequestBody FollowUpTask followUpTaskDetails) {
        Task completedTask = taskService.completeTask(id, followUpTaskDetails);
        return ResponseEntity.ok(completedTask);
    }

    @GetMapping("/{id}/follow-up")
    public ResponseEntity<List<FollowUpTask>> getFollowUpDetails(@PathVariable Long id) {
        List<FollowUpTask> followUpTasks = taskService.getFollowUpTasksByTaskId(id);
        return ResponseEntity.ok(followUpTasks);
    }
    
    @PostMapping("/{taskId}/follow-up")
    public ResponseEntity<FollowUpTask> createFollowUpTask(@PathVariable Long taskId, @RequestBody FollowUpTask followUpTask) {
        FollowUpTask createdFollowUpTask = taskService.createFollowUpTask(taskId, followUpTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFollowUpTask);
    }
    
    @PutMapping("/follow-up/{id}")
    public ResponseEntity<FollowUpTask> updateFollowUpTask(@PathVariable Long id, @RequestBody FollowUpTask followUpTaskDetails) {
        FollowUpTask updatedFollowUpTask = taskService.updateFollowUpTask(id, followUpTaskDetails);
        return ResponseEntity.ok(updatedFollowUpTask);
    }
    
    @DeleteMapping("/follow-up/{id}")
    public ResponseEntity<Void> deleteFollowUpTask(@PathVariable Long id) {
        taskService.deleteFollowUpTask(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/follow-up/{followUpTaskId}/complete")
    public ResponseEntity<FollowUpTask> markFollowUpTaskAsComplete(@PathVariable Long followUpTaskId, @RequestBody FollowUpTask newFollowUpTaskDetails) {
        FollowUpTask completedFollowUpTask = taskService.markFollowUpTaskAsComplete(followUpTaskId, newFollowUpTaskDetails);
        return ResponseEntity.ok(completedFollowUpTask);
    }
    
    @PostMapping("/follow-up/{followUpTaskId}/follow-up")
    public ResponseEntity<FollowUpTask> createNestedFollowUpTask(@PathVariable Long followUpTaskId, @RequestBody FollowUpTask followUpTask) {
        FollowUpTask createdFollowUpTask = taskService.createNestedFollowUpTask(followUpTaskId, followUpTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFollowUpTask);
    }

    @GetMapping("/follow-up/{id}/follow-ups")
    public ResponseEntity<List<FollowUpTask>> getNestedFollowUpTasks(@PathVariable Long id) {
        List<FollowUpTask> followUpTasks = taskService.getNestedFollowUpTasks(id);
        return ResponseEntity.ok(followUpTasks);
    }
}
