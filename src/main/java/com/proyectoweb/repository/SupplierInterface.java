package com.proyectoweb.repository;

import com.proyectoweb.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierInterface extends JpaRepository<Supplier, String> {
}
