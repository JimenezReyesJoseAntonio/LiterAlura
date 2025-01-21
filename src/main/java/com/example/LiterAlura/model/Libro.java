package com.example.LiterAlura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Clave primaria

    @Column(unique = true, nullable = false)
    private String titulo; // Título único y obligatorio

    @ElementCollection
    @CollectionTable(name = "libro_categorias", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "categoria")
    private List<String> categorias; // Lista de categorías del libro

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Autor> autores; // Relación uno-a-muchos con autores

    @ElementCollection
    @CollectionTable(name = "libro_colecciones", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "coleccion")
    private List<String> colecciones; // Lista de colecciones del libro

    @ElementCollection
    @CollectionTable(name = "libro_idiomas", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "idioma")
    private List<String> idiomas; // Lista de idiomas disponibles

    private String formato; // Formato del libro (ej. PDF, EPUB)

    private Integer descargas; // Número de descargas

    // Constructor vacío requerido por JPA
    public Libro() {}

    // Constructor que recibe un objeto `DatosLibro`
    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.categorias = datosLibro.categorias();
        this.colecciones = datosLibro.colecciones();
        this.idiomas = datosLibro.idiomas();
        this.formato = datosLibro.formato();
        this.descargas = datosLibro.descargas();

        // Construir lista de autores y establecer relación
        this.autores = datosLibro.autores().stream()
                .map(a -> new Autor(a.nombre(), a.anioNacimiento(), a.anioMuerte(), this))
                .toList();
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", formato='" + formato + '\'' +
                ", descargas=" + descargas +
                '}';
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<String> getColecciones() {
        return colecciones;
    }

    public void setColecciones(List<String> colecciones) {
        this.colecciones = colecciones;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }
}