package com.example.probando_api_pokemon.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

// Se deben ignorar las propiedades desconocidas durante la deserialización
// de un objeto JSON a una clase Java.
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosPokemon(
        @JsonAlias("abilities") Habilidades[] habilidades,
        @JsonAlias("name") String nombre,
        @JsonAlias("height") Integer altura,
        @JsonAlias("id") Integer numeroPokedex,
        @JsonAlias("weight") Integer peso
) {
    @Override
    public String toString() {
        return  " Habilidades = " + Arrays.toString(habilidades).replace("[","").replace("]","") + "\n" +
                " Nombre = " + nombre + "\n" +
                " Altura = " + altura + "\n" +
                " Numero en laPokedex = " + numeroPokedex + "\n" +
                " Peso = " + peso;
    }
}
