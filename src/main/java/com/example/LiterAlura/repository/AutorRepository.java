package com.example.LiterAlura.repository;

import com.example.LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    // Consulta derivada para obtener autores vivos en un año dado
    List<Autor> findByAnioNacimientoLessThanEqualAndAnioMuerteGreaterThanEqualOrAnioMuerteIsNull(
            Integer anioInicio, Integer anioFin
    );
}
