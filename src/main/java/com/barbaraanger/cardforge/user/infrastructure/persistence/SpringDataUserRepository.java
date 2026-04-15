package com.barbaraanger.cardforge.user.infrastructure.persistence;

import com.barbaraanger.cardforge.user.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByEmail(String email);
}
