package com.example.LiterAlura.principal;

import com.example.LiterAlura.model.DatosLibro;
import com.example.LiterAlura.model.RespuestaGutendex;
import com.example.LiterAlura.service.ConsumoAPI;
import com.example.LiterAlura.service.ConvierteDatos;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books";
    private ConvierteDatos conversor = new ConvierteDatos();



    public void muestraElMenu() throws IOException {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro
                    3 - Mostrar libros buscados
                    4 - Buscar series por titulo
                    5 - Top 5 mejores series
                    6 - Buscar Series por categoría
                    7 - filtrar series por temporadas y evaluación
                    8 - Buscar episodios por titulo
                    9 - Top 5 episodios por Serie
                                  
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
                    break;
                case 3:
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
        // Mostrar los libros encontrados
        if (libros != null && !libros.isEmpty()) {
            System.out.println("Libros encontrados:");
            for (DatosLibro libro : libros) {
                System.out.println("- " + libro);
            }
        } else {
            System.out.println("No se encontraron libros con ese nombre.");
        }    }
}
