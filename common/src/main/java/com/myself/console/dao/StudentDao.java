package com.myself.console.dao;

import com.myself.console.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentDao extends JpaRepository<StudentEntity,Long>, JpaSpecificationExecutor<StudentEntity> {
}
