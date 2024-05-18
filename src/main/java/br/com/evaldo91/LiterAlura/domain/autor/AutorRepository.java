package br.com.evaldo91.LiterAlura.domain.autor;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {


    Optional<Autor> findByNome(String nome);


    List<Autor> findByFalecimentoLessThan(int ano);

    List<Autor> findByFalecimentoGreaterThan(int ano);

    Autor findByNomeContainingIgnoreCase(String nome);
}