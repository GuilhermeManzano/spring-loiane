package com.manzano.crudspring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CursoDTO(
    @JsonProperty("_id") Long id,
    String name,
    String category) { 
}
