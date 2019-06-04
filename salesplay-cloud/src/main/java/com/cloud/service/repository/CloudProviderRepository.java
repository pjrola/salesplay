package com.cloud.service.repository;

import com.cloud.service.entity.CloudProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CloudProviderRepository extends JpaRepository<CloudProvider, Long> {
    Optional<CloudProvider> findByUniqueId(String uuid);
}