package com.manzano.crudspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.manzano.crudspring.repository.CourseRepository;

@RestController
@RequestMapping("/api/courses")
public class CursoController {

  @Autowired
  private CourseRepository courseRepository;

  @GetMapping
  public List<Course> list() {
    return courseRepository.findAll();
  }

  @PostMapping
  public ResponseEntity<Course> create(@RequestBody Course course) {
    var save = courseRepository.save(course);
    return ResponseEntity.status(HttpStatus.CREATED).body(save);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Course> findById(@PathVariable Long id) {
    return courseRepository.findById(id)
        .map(record -> ResponseEntity.ok().body(record))
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course) {
    return courseRepository.findById(id)
        .map(record -> {
          record.setName(course.getName());
          record.setCategory(course.getCategory());
          Course updated = courseRepository.save(record);
          return ResponseEntity.status(HttpStatus.CREATED).body(updated);
        })
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable Long id) {
    return courseRepository.findById(id)
    .map(record -> {
      courseRepository.deleteById(id);
      return ResponseEntity.noContent().<Void>build();
    })
    .orElse(ResponseEntity.notFound().build());
  }
}