package com.system.ms.schoolMs.repo;

import com.system.ms.schoolMs.entity.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialsRepo extends JpaRepository<Credentials, Long> {
    Optional<Credentials> findByUsernameAndPassword(String username, String password);
}
