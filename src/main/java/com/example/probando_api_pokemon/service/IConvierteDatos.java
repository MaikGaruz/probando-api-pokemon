package com.example.probando_api_pokemon.service;

public interface IConvierteDatos {
    //<T> inidica que es un metodo generico
    <T> T obtenerDatos(String json, Class<T> clase);
}
