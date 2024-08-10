package com.example.probando_api_pokemon.controller;

import com.example.probando_api_pokemon.model.DatosPokemon;
import com.example.probando_api_pokemon.model.Pokemon;
import com.example.probando_api_pokemon.repository.PokemonRepository;
import com.example.probando_api_pokemon.service.ConsumoAPI;
import com.example.probando_api_pokemon.service.ConvierteDatos;

import java.util.*;

public class PokemonController {
    Scanner teclado = new Scanner(System.in);
    ConsumoAPI consumoAPI = new ConsumoAPI();
    ConvierteDatos convierteDatos = new ConvierteDatos();

    PokemonRepository pokemonRepository;

    private final String URL_BASE = "https://pokeapi.co/api/v2/pokemon/";

    private List<Pokemon> pokemones;

    public PokemonController(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public void menu() {
        int salir = 0;
        int opcion = -1;
        do {
            System.out.println("""
                    \n
                    1. Buscar pokemon
                    2. Ver pokemones guardados
                    3. Buscar por nombre
                    4. Buscar por un peso en adelante
                    5. Buscar por RANGO en la pokedex
                    6. Buscar por INICIAL
                    7. Borrar un pokemon por nombre
                    8. Buscar por ID de la BD
                                  
                    0. Salir
                    """);

            try {
                System.out.println("OPCION: ");
                opcion = teclado.nextInt();
                teclado.nextLine();
            } catch (InputMismatchException ignored){
            }

            switch (opcion) {
                case 1:
                    buscarPokemon();
                    break;
                case 2:
                    mostrarPokemonesBuscados();
                    break;
                case 3:
                    buscarPorNombre();
                    break;
                case 4:
                    buscarPesoEnAdelante();
                    break;
                case 5:
                    buscarPorRangoPokedex();
                    break;
                case 6:
                    buscarPorInicial();
                    break;
                case 7:
                    borrarPokemon();
                    break;
                case 8:
                    buscarPorID();
                    break;
                default:
                    System.out.println("SALIENDO");
                    salir = 1;
                    break;
            }

        } while (salir == 0);
    }


    public DatosPokemon getPokemon(){

        System.out.println("¿Que pokemon deseas buscar? ");
        String pokemon = teclado.nextLine().toLowerCase();

        var jsonPokemon = consumoAPI.obtenerDatos(URL_BASE + pokemon);
        //System.out.println(jsonPokemon);


        DatosPokemon datosPokemon = convierteDatos.obtenerDatos(jsonPokemon, DatosPokemon.class);
        return datosPokemon;

    }

    public void buscarPokemon(){
        DatosPokemon datosPokemon = getPokemon();

        Pokemon pokemonEncontrado = new Pokemon(datosPokemon);
        pokemonRepository.save(pokemonEncontrado);

        System.out.println(datosPokemon);
    }

    public void mostrarPokemonesBuscados(){
        pokemones = pokemonRepository.findAll();
        System.out.println(pokemones);
    }

    public void buscarPorNombre(){
        System.out.println("Que pokemon deseas buscar en la BD? ");
        var nombreABuscar = teclado.nextLine();

        pokemones = pokemonRepository.findByNombre(nombreABuscar);
        System.out.println(pokemones);
    }

    public void buscarPesoEnAdelante(){
        System.out.println("Indica el peso? ");
        int pesoCapturado = Integer.parseInt(teclado.nextLine());

        pokemones = pokemonRepository.pokemonesConPesoMayorA(pesoCapturado);
        System.out.println(pokemones);
    }

    public void buscarPorRangoPokedex(){
        System.out.println("Indica el limite inferior: ");
        int n1 = Integer.parseInt(teclado.nextLine());

        System.out.println("Indica el limite superior: ");
        int n2 = Integer.parseInt(teclado.nextLine());


        pokemones = pokemonRepository.pokemonPorRangoPokedex(n1,n2);
        System.out.println(pokemones);
    }

    public void buscarPorInicial(){
        System.out.println("Indica la inicial: ");
        String inicial = teclado.nextLine();

        pokemones = pokemonRepository.buscarPorInicial(inicial);
        System.out.println(pokemones);

    }



    public void borrarPokemon() {
        System.out.println("Que pokemon deseas borrar? ");
        String nombreABuscar = teclado.nextLine();

        boolean eliminado = borrarPokemonPorNombre(nombreABuscar);

        if (eliminado) {
            System.out.println("Pokémon borrado exitosamente.");
        } else {
            System.out.println("Pokémon no encontrado.");
        }

    }

    public boolean borrarPokemonPorNombre(String nombreABuscar) {
        // Se utiliza para contar el número de registros en la base de datos
        // que cumplen con un criterio específico.
        long count = pokemonRepository.countByNombre(nombreABuscar);
        if (count > 0) {
            pokemonRepository.borrarPorNombre(nombreABuscar);
            return true;
        }
        return false;
    }

    public void buscarPorID(){
        System.out.println("Cual es el ID?");
        Long id = Long.valueOf(teclado.nextLine());

        Optional<Pokemon> pokemonEncontrado;

        pokemonEncontrado = pokemonRepository.findById(id);

        if(pokemonEncontrado.isPresent()) {
            System.out.println(pokemonEncontrado);
        } else {
            System.out.println("El ID no existe en la BD");
        }
    }

}
