package br.com.evaldo91.LiterAlura;

import br.com.evaldo91.LiterAlura.domain.autor.AutorRepository;
import br.com.evaldo91.LiterAlura.domain.livro.LivrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private LivrosRepository livrosRepository;
	@Autowired
	private AutorRepository autorRepository;



	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);

	}


	@Override
	public void run(String... args) throws Exception {

		App app = new App(autorRepository, livrosRepository);
		//            var logo = "\n" +
//                    ".____    .__  __                  _____  .__                       \n" +
//                    "|    |   |__|/  |_  ___________  /  _  \\ |  |  __ ______________   \n" +
//                    "|    |   |  \\   __\\/ __ \\_  __ \\/  /_\\  \\|  | |  |  \\_  __ \\__  \\  \n" +
//                    "|    |___|  ||  | \\  ___/|  | \\/    |    \\  |_|  |  /|  | \\// __ \\_\n" +
//                    "|_______ \\__||__|  \\___  >__|  \\____|__  /____/____/ |__|  (____  /\n" +
//                    "        \\/             \\/              \\/                       \\/ \n";
		var logo = "\n" +
				" __     __   ____  ____  ____   __   __    _  _  ____   __  \n" +
				"(  )   (  ) (_  _)(  __)(  _ \\ / _\\ (  )  / )( \\(  _ \\ / _\\ \n" +
				"/ (_/\\  )(    )(   ) _)  )   //    \\/ (_/\\) \\/ ( )   //    \\\n" +
				"\\____/ (__)  (__) (____)(__\\_)\\_/\\_/\\____/\\____/(__\\_)\\_/\\_/\n";

		System.out.println(logo);
		app.iniciar();

	}
}

