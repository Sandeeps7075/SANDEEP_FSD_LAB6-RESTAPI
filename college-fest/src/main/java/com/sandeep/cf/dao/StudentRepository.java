package com.sandeep.cf.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandeep.cf.model.Students;

@Repository
public interface StudentRepository extends JpaRepository<Students, Long> {

}
