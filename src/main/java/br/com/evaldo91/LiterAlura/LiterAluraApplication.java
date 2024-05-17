package br.com.evaldo91.LiterAlura;

import br.com.evaldo91.LiterAlura.app.App;
import br.com.evaldo91.LiterAlura.domain.autor.AutorRepository;
import br.com.evaldo91.LiterAlura.domain.livro.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {
    @Autowired
    private LivroRepository livrorepository;
    @Autowired
    private AutorRepository autorRepository;

    public static void main(String[] args) {
        SpringApplication.run(LiterAluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        App app = new App(livrorepository , autorRepository);
        app.start();

    }
}