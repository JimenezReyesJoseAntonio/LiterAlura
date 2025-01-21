package com.example.LiterAlura.principal;

import com.example.LiterAlura.model.Autor;
import com.example.LiterAlura.model.DatosLibro;
import com.example.LiterAlura.model.Libro;
import com.example.LiterAlura.model.RespuestaGutendex;
import com.example.LiterAlura.repository.AutorRepository;
import com.example.LiterAlura.repository.LibroRepository;
import com.example.LiterAlura.service.ConsumoAPI;
import com.example.LiterAlura.service.ConvierteDatos;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository repositorio;
    private AutorRepository repositoryAutor;
    private List<Libro> libros;

    public Principal(LibroRepository repository, AutorRepository repositoryAutor) {
        this.repositoryAutor = repositoryAutor;
        this.repositorio = repository;
    }


    public void muestraElMenu() throws IOException {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro
                    2 - Mostrar libros buscados
                    4 - Autores  vivos en un año dado
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    mostrarLibrosBuscados();
                    break;
                case 3:
                    mostrarAutoresVivosAnio();
                    break;
                case 4:
                    break;
                case 5:

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private List<DatosLibro> getDatosLibro() throws IOException {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "%20") );
        System.out.println("url" +URL_BASE + "?search=" + nombreLibro.replace(" ", "%20"));
        RespuestaGutendex respuesta = conversor.obtenerDatos(json, RespuestaGutendex.class);

        return respuesta.getResults();
    }

    private void buscarLibroWeb() throws IOException {
        List<DatosLibro> libros = getDatosLibro();
        if (libros != null && !libros.isEmpty()) {
            System.out.println("Libros encontrados:");
            DatosLibro primerLibro = libros.get(0);
            Libro libro = new Libro(primerLibro);
            repositorio.save(libro);
            System.out.println("Guardar"+primerLibro);

        } else {
            System.out.println("No se encontraron libros con ese nombre.");
        }

    }


    private void mostrarLibrosBuscados() {
        libros = repositorio.findAll();
        libros.stream()
                .forEach(System.out::println);
    }

    public void mostrarAutoresVivosAnio(){
        System.out.println("Ingresa el año para buscar los autores vivos");
        var anio = teclado.nextInt();

        List<Autor> autoresVivos = repositoryAutor.findByAnioNacimientoLessThanEqualAndAnioMuerteGreaterThanEqualOrAnioMuerteIsNull(
                anio, anio
        );

        autoresVivos.stream()
                .forEach(System.out::println);
    }
}
