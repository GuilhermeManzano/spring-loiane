package com.manzano.crudspring.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.DialectOverride.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?")
@org.hibernate.annotations.Where(clause = "status = 'Ativo'")
public class Course {

  @Id
  @JsonProperty("_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 100, nullable = false)
  private String name;

  @Column(length = 10, nullable = false)
  private String category;

  @Column(length = 10, nullable = false)
  private String status = "Ativo";
}
