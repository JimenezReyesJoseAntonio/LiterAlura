package com.example.LiterAlura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Clave primaria

    @Column(nullable = false, length = 100)
    private String nombre; // Nombre del autor, obligatorio y con un máximo de 100 caracteres

    @Column(name = "anio_nacimiento")
    private Integer anioNacimiento; // Año de nacimiento, opcional

    @Column(name = "anio_muerte")
    private Integer anioMuerte; // Año de muerte, opcional

    @ManyToOne
    @JoinColumn(name = "libro_id", nullable = false)
    private Libro libro; // Relación con la tabla `libros`

    // Constructor vacío requerido por JPA
    public Autor() {}

    // Constructor con parámetros
    public Autor(String nombre, Integer anioNacimiento, Integer anioMuerte, Libro libro) {
        this.nombre = nombre;
        this.anioNacimiento = anioNacimiento;
        this.anioMuerte = anioMuerte;
        this.libro = libro;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public Integer getAnioMuerte() {
        return anioMuerte;
    }

    public void setAnioMuerte(Integer anioMuerte) {
        this.anioMuerte = anioMuerte;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", anioNacimiento=" + anioNacimiento +
                ", anioMuerte=" + anioMuerte +
                ", libro=" + (libro != null ? libro.getTitulo() : "null") +
                '}';
    }
}