package com.proyectoweb.repository;

import com.proyectoweb.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyInterface extends JpaRepository<Company, String> {
}
