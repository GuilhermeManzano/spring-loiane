package com.manzano.crudspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manzano.crudspring.dto.CursoDTO;
import com.manzano.crudspring.service.CourseService;

@RestController
@RequestMapping("/api/courses")
public class CursoController {

  @Autowired
  private CourseService courseService;

  @GetMapping
  public List<CursoDTO> list() {
    return courseService.list();
  }

  @PostMapping
  public CursoDTO create(@RequestBody CursoDTO course) {
    return courseService.create(course);
  }

  @GetMapping("/{id}")
  public CursoDTO findById(@PathVariable Long id) {
    return courseService.findById(id);
  }

  @PutMapping("/{id}")
  public CursoDTO update(@PathVariable Long id, @RequestBody CursoDTO course) {
    return courseService.update(id, course);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    courseService.delete(id);
  }
}