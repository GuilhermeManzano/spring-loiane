package com.manzano.crudspring.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manzano.crudspring.model.Course;
import com.manzano.crudspring.repository.CourseRepository;

@RestController
@RequestMapping("/api/courses")
public class CursoController {

  @Autowired
  private CourseRepository courseRepository;

  @GetMapping
  public List<Course> list() {
    return  courseRepository.findAll();
  }
}