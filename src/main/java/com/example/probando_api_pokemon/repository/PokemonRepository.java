package com.example.probando_api_pokemon.repository;

import com.example.probando_api_pokemon.model.Pokemon;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    List<Pokemon> findByNombre(String nombreABuscar);

    @Query("SELECT s FROM Pokemon s WHERE s.peso >= :pesoCapturado")
    List<Pokemon> pokemonesConPesoMayorA(Integer pesoCapturado);

    @Query("SELECT p FROM Pokemon p WHERE p.numeroPokedex >= :n1 AND p.numeroPokedex <= :n2")
    List<Pokemon> pokemonPorRangoPokedex(int n1, int n2);

    @Query("SELECT p FROM Pokemon p WHERE p.nombre LIKE :inicial%")
    List<Pokemon> buscarPorInicial(String inicial);

    //El modifying se usa en consultas que modifican la BD
    @Modifying
    @Transactional
    //Este metodo no tiene que retornar nada, por eso el void
    @Query("DELETE FROM Pokemon p WHERE p.nombre = :nombre")
    void borrarPorNombre(@Param("nombre") String nombreABuscar);

    long countByNombre(String nombre);
}
