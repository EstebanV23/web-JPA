package com.proyectoweb.repository;

import com.proyectoweb.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerInterface extends JpaRepository<Owner, String> {
}
