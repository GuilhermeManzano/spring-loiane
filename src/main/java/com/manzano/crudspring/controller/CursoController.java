package com.manzano.crudspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manzano.crudspring.model.Course;
import com.manzano.crudspring.service.CourseService;

@RestController
@RequestMapping("/api/courses")
public class CursoController {

  @Autowired
  private CourseService courseService;

  @GetMapping
  public List<Course> list() {
    return courseService.list();
  }

  @PostMapping
  public ResponseEntity<Course> create(@RequestBody Course course) {
    return courseService.create(course);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Course> findById(@PathVariable Long id) {
    return courseService.findById(id);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course) {
    return courseService.update(id, course);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable Long id) {
    return courseService.delete(id);
  }
}