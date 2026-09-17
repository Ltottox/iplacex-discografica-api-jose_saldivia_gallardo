package org.iplacex.proyectos.discografia.discos;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
// Interfaz que extiende de MongoRepository para proporcionar operaciones CRUD para la entidad Disco
public interface IDiscoRepository extends MongoRepository<Disco, String> {

    @Query("{ 'idArtista': ?0 }")
    List<Disco> findDiscosByIdArtista(String idArtista);
}