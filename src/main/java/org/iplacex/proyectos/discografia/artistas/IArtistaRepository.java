package org.iplacex.proyectos.discografia.artistas;

import org.springframework.data.mongodb.repository.MongoRepository;
// Interfaz que extiende de MongoRepository para proporcionar operaciones CRUD para la entidad Artista
public interface IArtistaRepository extends MongoRepository<Artista, String> {
    
}
