package com.proyectoweb.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name = "company")
public class Company {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotEmpty
  private String name;
  private String address;
  @OneToMany()
  @JoinColumn(name = "id_company")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private List<Employee> employees;
  @ManyToOne
  @JoinColumn(name = "id_owner")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Owner owner;
  @ManyToMany
  @JoinColumn(name = "id_supplier")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private List<Supplier> suppliers;
  public Owner getOwner() {
    return owner;
  }

  public List<Supplier> getSuppliers() {
    return suppliers;
  }

  public Company(Long id, String name, String address, List<Employee> employees, Owner owner, List<Supplier> suppliers) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.employees = employees;
    this.owner = owner;
    this.suppliers = suppliers;
  }

  public void setSuppliers(List<Supplier> suppliers) {
    this.suppliers = suppliers;
  }

  public void setOwner(Owner owner) {
    this.owner = owner;
  }

  public Company(Long id, String name, String address, List<Employee> employees, Owner owner) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.employees = employees;
    this.owner = owner;
  }

  public Company() {
  }

  public Company(Long id, String name, String address, List<Employee> employees) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.employees = employees;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }

  public Company(Long id, String name, String address) {
    this.id = id;
    this.name = name;
    this.address = address;
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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
