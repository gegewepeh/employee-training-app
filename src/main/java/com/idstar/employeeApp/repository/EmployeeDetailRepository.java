package com.idstar.employeeApp.repository;

import com.idstar.employeeApp.model.Employee;
import com.idstar.employeeApp.model.EmployeeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDetailRepository extends JpaRepository<EmployeeDetail, Long> {
}
