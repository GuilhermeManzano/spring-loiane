package com.manzano.crudspring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.manzano.crudspring.dto.CursoDTO;
import com.manzano.crudspring.dto.mapper.CourseMapper;
import com.manzano.crudspring.repository.CourseRepository;

import exception.RecordNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class CourseService {

  @Autowired
  private CourseRepository courseRepository;

  private CourseMapper courseMapper;

  public List<CursoDTO> list() {
    return courseRepository.findAll().stream().map(courseMapper::toDto).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
  }

  public CursoDTO create(@RequestBody CursoDTO course) {
    return courseMapper.toDto(courseRepository.save(courseMapper.toEntity(course)));
  }

  public CursoDTO findById(@PathVariable Long id) {
    return courseRepository.findById(id).map(courseMapper::toDto).orElseThrow(() -> new RecordNotFoundException(id));
  }

  public CursoDTO update(@PathVariable Long id, @RequestBody CursoDTO course) {
    return courseRepository.findById(id)
        .map(recordFound -> {
          recordFound.setName(course.name());
          recordFound.setCategory(course.category());
          return courseRepository.save(recordFound);
        })
        .map(courseMapper::toDto)
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