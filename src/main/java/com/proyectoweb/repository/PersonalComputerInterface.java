package com.proyectoweb.repository;

import com.proyectoweb.entity.PersonalComputer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalComputerInterface extends JpaRepository<PersonalComputer, String> {
}
