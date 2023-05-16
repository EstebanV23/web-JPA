package com.proyectoweb.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "employee")
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotEmpty
  private String name;
  @NotEmpty
  private String email;
  @OneToOne
  @JoinColumn(name = "id_personal_computer")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private PersonalComputer personalComputer;

  public PersonalComputer getPersonalComputer() {
    return personalComputer;
  }

  public void setPersonalComputer(PersonalComputer personalComputer) {
    this.personalComputer = personalComputer;
  }

  public Employee() {
  }

  public Employee(Long id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  public Employee(Long id, String name, String email, PersonalComputer personalComputer) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.personalComputer = personalComputer;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
