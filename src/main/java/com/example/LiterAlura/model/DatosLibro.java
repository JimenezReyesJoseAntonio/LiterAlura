package com.example.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("subjects") List<String> categorias,
        @JsonAlias("authors") List<DatosAutor> autores,
        @JsonAlias("bookshelves")List<String> colecciones,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("media_type")String formato,
        @JsonAlias("download_count")Integer descargas

) {
}
