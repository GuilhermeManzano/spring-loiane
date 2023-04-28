package com.manzano.crudspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.manzano.crudspring.model.Course;
import com.manzano.crudspring.repository.CourseRepository;

import exception.RecordNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class CourseService {

  @Autowired
  private CourseRepository courseRepository;

  public List<Course> list() {
    return courseRepository.findAll();
  }

  public Course create(@RequestBody Course course) {
    return courseRepository.save(course);
  }

  public Course findById(@PathVariable Long id) {
    return courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
  }

  public Course update(@PathVariable Long id, @RequestBody Course course) {
    return courseRepository.findById(id)
        .map(record -> {
          record.setName(course.getName());
          record.setCategory(course.getCategory());
          return courseRepository.save(record);
        })
        .orElseThrow(() -> new RecordNotFoundException(id));
  }

  public void delete(@PathVariable Long id) {
    var getId = courseRepository.findById(id);

    if (getId.isPresent()) {
      courseRepository.delete(getId.get());
    } 
    throw new RecordNotFoundException(id);
  }
}