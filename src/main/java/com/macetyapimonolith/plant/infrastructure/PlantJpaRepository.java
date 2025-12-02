package com.macetyapimonolith.plant.infrastructure;

import com.macetyapimonolith.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlantJpaRepository extends JpaRepository<PlantEntity, Long> {

    List<PlantEntity> findByUserId(Long userId);

    @Transactional
    void deleteByUser(User user);
}
