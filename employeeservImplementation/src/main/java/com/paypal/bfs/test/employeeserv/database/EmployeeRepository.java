package com.paypal.bfs.test.employeeserv.database;

import com.paypal.bfs.test.employeeserv.database.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
}