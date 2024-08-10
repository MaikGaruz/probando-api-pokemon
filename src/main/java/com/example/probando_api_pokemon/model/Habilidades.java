package com.example.probando_api_pokemon.model;

import com.example.probando_api_pokemon.model.Habilidad;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Habilidades(
        @JsonAlias("ability") Habilidad habilidad
) {
    @Override
    public String toString() {
        return habilidad.toString();
    }
}
