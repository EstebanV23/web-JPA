package com.proyectoweb.repository;

import com.proyectoweb.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeInterface extends JpaRepository<Employee, String> {
}
