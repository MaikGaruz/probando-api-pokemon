package com.example.probando_api_pokemon;

import com.example.probando_api_pokemon.controller.PokemonController;
import com.example.probando_api_pokemon.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProbandoApiPokemonApplication implements CommandLineRunner{

	@Autowired
	private PokemonRepository pokemonRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProbandoApiPokemonApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		PokemonController pokemonController = new PokemonController(pokemonRepository);
		pokemonController.menu();
	}
}
