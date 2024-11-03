package com.nak.todome_backend.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class FollowUpTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    @JsonBackReference
    private Task originalTask;

    @ManyToOne
    @JoinColumn(name = "follow_up_task_id", nullable = true)
    @JsonBackReference
    private FollowUpTask parentFollowUpTask;

    @OneToMany(mappedBy = "parentFollowUpTask", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<FollowUpTask> childFollowUpTasks;

    private String followUpDetails;
    private LocalDate dueDate;
    private String status;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getOriginalTask() {
        return originalTask;
    }

    public void setOriginalTask(Task originalTask) {
        this.originalTask = originalTask;
    }

    public FollowUpTask getParentFollowUpTask() {
        return parentFollowUpTask;
    }

    public void setParentFollowUpTask(FollowUpTask parentFollowUpTask) {
        this.parentFollowUpTask = parentFollowUpTask;
    }

    public List<FollowUpTask> getChildFollowUpTasks() {
        return childFollowUpTasks;
    }

    public void setChildFollowUpTasks(List<FollowUpTask> childFollowUpTasks) {
        this.childFollowUpTasks = childFollowUpTasks;
    }

    public String getFollowUpDetails() {
        return followUpDetails;
    }

    public void setFollowUpDetails(String followUpDetails) {
        this.followUpDetails = followUpDetails;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
