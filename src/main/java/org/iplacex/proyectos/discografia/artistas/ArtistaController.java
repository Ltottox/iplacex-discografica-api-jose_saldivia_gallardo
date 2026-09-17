package org.iplacex.proyectos.discografia.artistas;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ArtistaController {

    @Autowired
    private IArtistaRepository artistaRepo;

    @PostMapping(
        value = "/artista", 
        consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE)
    //metodo para manejar la solicitud POST para crear un nuevo artista    
    public ResponseEntity<Object> HandleInsertArtistaRequest(@RequestBody Artista artista) {
        Artista nuevo = artistaRepo.insert(artista);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @GetMapping(
        value = "/artistas", 
        produces = MediaType.APPLICATION_JSON_VALUE)
    //metodo para manejar la solicitud GET para obtener todos los artistas    
    public ResponseEntity<List<Artista>> HandleGetArtistasRequest() {
        return new ResponseEntity<>(artistaRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping(
        value = "/artista/{id}", 
        produces = MediaType.APPLICATION_JSON_VALUE)
    //metodo para manejar la solicitud GET para obtener un artista por su id    
    public ResponseEntity<Object> HandleGetArtistaRequest(@PathVariable("id") String id) {
        Optional<Artista> opt = artistaRepo.findById(id);
        if (!opt.isPresent()) {
            return new ResponseEntity<>("Artista no encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(opt.get(), HttpStatus.OK);
    }

    @PutMapping(
        value = "/artista/{id}", 
        consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE)
    //metodo para manejar la solicitud PUT para actualizar un artista existente    
    public ResponseEntity<Object> HandleUpdateArtistaRequest(@PathVariable("id") String id, @RequestBody Artista artista) {
        if (!artistaRepo.existsById(id)) {
            return new ResponseEntity<>("Artista no encontrado", HttpStatus.NOT_FOUND);
        }
        artista._id = id;
        Artista actualizado = artistaRepo.save(artista);
        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    @DeleteMapping(
        value = "/artista/{id}", 
        produces = MediaType.APPLICATION_JSON_VALUE)
    //metodo para manejar la solicitud DELETE para eliminar un artista existente    
    public ResponseEntity<Object> HandleDeleteArtistaRequest(@PathVariable("id") String id) {
        if (!artistaRepo.existsById(id)) { //metodo que verifica si el artista existe antes de eliminarlo, por medio de la interfaz IArtistaRepository
            return new ResponseEntity<>("Artista no encontrado", HttpStatus.NOT_FOUND);
        }
        artistaRepo.deleteById(id);
        return new ResponseEntity<>("Artista eliminado correctamente", HttpStatus.OK);
    }
}