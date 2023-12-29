package com.idstar.employeeApp.repository;

import com.idstar.employeeApp.model.BankAccount;
import com.idstar.employeeApp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
}

