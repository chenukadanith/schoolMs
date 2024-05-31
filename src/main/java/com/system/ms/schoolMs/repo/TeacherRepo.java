package com.system.ms.schoolMs.repo;

import com.system.ms.schoolMs.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface TeacherRepo extends JpaRepository<Teacher,Long> {

}
