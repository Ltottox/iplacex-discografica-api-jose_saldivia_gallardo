package org.iplacex.proyectos.discografia.artistas;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
// Clase que representa un artista en la discografía, con sus atributos y anotaciones para mapearlo a un documento de MongoDB
@Document("artistas")// Anotación que indica que esta clase se mapeará a la colección "artistas" en MongoDB   
public class Artista {
// Atributos de la clase Artista, que representan las propiedades de un artistass
    @Id
    public String _id;

    public String nombre;
    public List<String> estilos;
    public int anioFundacion;
    public boolean estaActivo;
}

