package com.cloud.service.repository;

import com.cloud.service.entity.Instance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface InstanceRepository extends JpaRepository<Instance, Long> {
    Optional<Instance> findByAssignee(String assignee);
}