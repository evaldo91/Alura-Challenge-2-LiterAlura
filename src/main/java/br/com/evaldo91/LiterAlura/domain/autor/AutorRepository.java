package br.com.evaldo91.LiterAlura.domain.autor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    public Optional<Autor> findByNomeIgnoreCase(String nomeAutor);
}
