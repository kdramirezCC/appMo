package com.nak.todome_backend.service;
import com.nak.todome_backend.entity.Task;
import com.nak.todome_backend.exception.ResourceNotFoundException;
import com.nak.todome_backend.entity.FollowUpTask;
import com.nak.todome_backend.repository.TaskRepository;
import com.nak.todome_backend.repository.FollowUpTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private FollowUpTaskRepository followUpTaskRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = getTaskById(id);

        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setDueDate(taskDetails.getDueDate());
        task.setStatus(taskDetails.getStatus());

        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }

    public Task completeTask(Long id, FollowUpTask followUpTaskDetails) {
        Task task = getTaskById(id);
        task.setStatus("COMPLETED");

        FollowUpTask followUpTask = new FollowUpTask();
        followUpTask.setOriginalTask(task);
        followUpTask.setFollowUpDetails(followUpTaskDetails.getFollowUpDetails());
        followUpTask.setDueDate(followUpTaskDetails.getDueDate());

        followUpTaskRepository.save(followUpTask);

        return taskRepository.save(task);
    }

    public List<FollowUpTask> getFollowUpTasksByTaskId(Long id) {
        Task task = getTaskById(id);
        return followUpTaskRepository.findByOriginalTask(task);
    }
    
    
    public FollowUpTask createFollowUpTask(Long taskId, FollowUpTask followUpTask) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        followUpTask.setOriginalTask(task);
        return followUpTaskRepository.save(followUpTask);
    }

    public FollowUpTask createNestedFollowUpTask(Long followUpTaskId, FollowUpTask followUpTask) {
        FollowUpTask parentFollowUpTask = followUpTaskRepository.findById(followUpTaskId).orElseThrow(() -> new ResourceNotFoundException("FollowUpTask not found"));
        followUpTask.setParentFollowUpTask(parentFollowUpTask);
        followUpTask.setOriginalTask(parentFollowUpTask.getOriginalTask());
        return followUpTaskRepository.save(followUpTask);
    }

    public List<FollowUpTask> getNestedFollowUpTasks(Long followUpTaskId) {
        FollowUpTask followUpTask = followUpTaskRepository.findById(followUpTaskId).orElseThrow(() -> new ResourceNotFoundException("FollowUpTask not found"));
        return followUpTask.getChildFollowUpTasks();
    }
    
    public FollowUpTask updateFollowUpTask(Long followUpTaskId, FollowUpTask followUpTaskDetails) {
        FollowUpTask existingFollowUpTask = followUpTaskRepository.findById(followUpTaskId)
            .orElseThrow(() -> new ResourceNotFoundException("FollowUpTask not found"));

        existingFollowUpTask.setFollowUpDetails(followUpTaskDetails.getFollowUpDetails());
        existingFollowUpTask.setDueDate(followUpTaskDetails.getDueDate());
        existingFollowUpTask.setStatus(followUpTaskDetails.getStatus());

        return followUpTaskRepository.save(existingFollowUpTask);
    }
    
    public void deleteFollowUpTask(Long followUpTaskId) {
        FollowUpTask followUpTask = followUpTaskRepository.findById(followUpTaskId)
            .orElseThrow(() -> new ResourceNotFoundException("FollowUpTask not found"));
        
        followUpTaskRepository.delete(followUpTask);
    }
    
    public FollowUpTask markFollowUpTaskAsComplete(Long followUpTaskId, FollowUpTask newFollowUpTaskDetails) {
        FollowUpTask followUpTask = followUpTaskRepository.findById(followUpTaskId)
            .orElseThrow(() -> new ResourceNotFoundException("FollowUpTask not found"));

        followUpTask.setStatus("Completed");
        followUpTaskRepository.save(followUpTask);

        if (newFollowUpTaskDetails != null) {
            newFollowUpTaskDetails.setOriginalTask(followUpTask.getOriginalTask());
            newFollowUpTaskDetails.setParentFollowUpTask(followUpTask);
            return followUpTaskRepository.save(newFollowUpTaskDetails);
        }

        return followUpTask;
    }

}
