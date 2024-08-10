package com.example.probando_api_pokemon.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Habilidad(
        @JsonAlias("name") String nombreHabilidad
) {
    @Override
    public String toString() {
        return nombreHabilidad;
    }
}
