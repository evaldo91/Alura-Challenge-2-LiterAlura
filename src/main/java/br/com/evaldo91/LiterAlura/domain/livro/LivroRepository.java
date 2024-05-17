package br.com.evaldo91.LiterAlura.domain.livro;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findByTitulo(String titulo);

    Optional<Livro> findByTituloContainingIgnoreCase(String titulo);
}
