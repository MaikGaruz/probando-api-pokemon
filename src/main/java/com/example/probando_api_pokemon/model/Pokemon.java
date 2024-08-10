package com.example.probando_api_pokemon.model;


import com.example.probando_api_pokemon.model.DatosPokemon;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Arrays;

@Getter
@Entity
@Table(name = "pokemones")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String habilidades;

    @Column(unique = true)
    private String nombre;

    private Integer altura;
    private Integer numeroPokedex;
    private Integer peso;

    public Pokemon(){
    }

    public Pokemon(DatosPokemon datosPokemon) {
        //Aqui se puede modificar para quitarle los caracteres no validos
        this.habilidades = Arrays.toString(datosPokemon.habilidades()).replace("[","").replace("]","");
        this.nombre = datosPokemon.nombre();
        this.altura = datosPokemon.altura();
        this.numeroPokedex = datosPokemon.numeroPokedex();
        this.peso = datosPokemon.peso();
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "Nombre = '" + nombre + '\'' +
                ", Habilidades = '" + habilidades + '\'' +
                ", Altura = '" + altura + '\'' +
                ", NumeroPokedex = '" + numeroPokedex + '\'' +
                ", Peso = '" + peso + '\'' +
                '}'+"\n";
    }
}
