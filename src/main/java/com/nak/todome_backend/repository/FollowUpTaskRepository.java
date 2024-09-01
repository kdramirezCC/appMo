package com.nak.todome_backend.repository;

import com.nak.todome_backend.entity.FollowUpTask;
import com.nak.todome_backend.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowUpTaskRepository extends JpaRepository<FollowUpTask, Long> {
    List<FollowUpTask> findByOriginalTask(Task task);
}