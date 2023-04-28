package com.manzano.crudspring.dto.mapper;

import org.springframework.stereotype.Component;

import com.manzano.crudspring.dto.CursoDTO;
import com.manzano.crudspring.model.Course;

@Component
public class CourseMapper {

  public CursoDTO toDto(Course course) {
    if(course == null) {
      return null;
    }

    return new CursoDTO(course.getId(), course.getName(), course.getCategory());
  }

  public Course toEntity(CursoDTO courseDto) {
    if(courseDto == null) {
      return null;
    }

    Course course = new Course();
    if (course.getId() != null) {
      course.setId((courseDto.id()));
    }

    course.setName(courseDto.name());
    course.setCategory(courseDto.category());
    course.setStatus("Ativo");

    return course;
  }
}
