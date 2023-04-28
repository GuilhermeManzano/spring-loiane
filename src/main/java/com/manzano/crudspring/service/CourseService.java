package com.manzano.crudspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.manzano.crudspring.model.Course;
import com.manzano.crudspring.repository.CourseRepository;

import org.springframework.stereotype.Service;

@Service
public class CourseService {

  @Autowired
  private CourseRepository courseRepository;

  public List<Course> list() {
    return courseRepository.findAll();
  }

  public ResponseEntity<Course> create(@RequestBody Course course) {
    var save = courseRepository.save(course);
    return ResponseEntity.status(HttpStatus.CREATED).body(save);
  }

  public ResponseEntity<Course> findById(@PathVariable Long id) {
    return courseRepository.findById(id)
        .map(record -> ResponseEntity.ok().body(record))
        .orElse(ResponseEntity.notFound().build());
  }

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

  public ResponseEntity delete(@PathVariable Long id) {
    return courseRepository.findById(id)
        .map(record -> {
          courseRepository.deleteById(id);
          return ResponseEntity.noContent().<Void>build();
        })
        .orElse(ResponseEntity.notFound().build());
  }
}