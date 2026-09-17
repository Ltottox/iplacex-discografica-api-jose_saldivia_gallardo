package org.iplacex.proyectos.discografia.discos;

import java.util.List;
import java.util.Optional;

import org.iplacex.proyectos.discografia.artistas.IArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")//ruta base para todas las solicitudes relacionadas con discos
public class DiscoController {
//inyección de dependencias para los repositorios de discos y artistas
    @Autowired
    private IDiscoRepository discoRepo;

    @Autowired
    private IArtistaRepository artistaRepo;

    @PostMapping(
        value = "/disco", 
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    //metodo para manejar la solicitud POST para crear un nuevo disco    
    public ResponseEntity<Object> HandlePostDiscoRequest(@RequestBody Disco disco) {
        if (!artistaRepo.existsById(disco.idArtista)) {
            return new ResponseEntity<>("El artista no existe", HttpStatus.NOT_FOUND);
        }
        Disco nuevo = discoRepo.insert(disco);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @GetMapping(
        value = "/discos", 
        produces = MediaType.APPLICATION_JSON_VALUE)
    //metodo para manejar la solicitud GET para obtener todos los discos    
    public ResponseEntity<List<Disco>> HandleGetDiscosRequest() {
        return new ResponseEntity<>(discoRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping(
        value = "/disco/{id}", 
        produces = MediaType.APPLICATION_JSON_VALUE)
    //metodo para manejar la solicitud GET para obtener un disco por su id    
    public ResponseEntity<Object> HandleGetDiscoRequest(@PathVariable("id") String id) {
        Optional<Disco> opt = discoRepo.findById(id);
        if (!opt.isPresent()) {
            return new ResponseEntity<>("Disco no encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(opt.get(), HttpStatus.OK);
    }

    @GetMapping(
        value = "/artista/{id}/discos", 
        produces = MediaType.APPLICATION_JSON_VALUE)
    //metodo para manejar la solicitud GET para obtener todos los discos de un artista por su id
    public ResponseEntity<List<Disco>> HandleGetDiscosByArtistaRequest(@PathVariable("id") String id) {
        List<Disco> discos = discoRepo.findDiscosByIdArtista(id);
        return new ResponseEntity<>(discos, HttpStatus.OK);
    }
}