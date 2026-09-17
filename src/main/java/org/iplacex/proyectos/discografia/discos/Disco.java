package org.iplacex.proyectos.discografia.discos;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
// Clase que representa un disco en la discografía, con sus atributos y anotaciones para mapearlo a un documento de MongoDB
@Document("discos")// Anotación que indica que esta clase se mapeará a la colección "discos" en MongoDB   
public class Disco {

    @Id
    public String _id;

    public String idArtista;
    public String nombre;
    public int anioLanzamiento;
    public List<String> canciones;
}