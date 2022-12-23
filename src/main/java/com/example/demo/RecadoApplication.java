package com.example.demo;

import com.example.demo.modelo.Autor;
import com.example.demo.modelo.Recado;
import com.example.demo.servicios.ServicioAutor;
import com.example.demo.servicios.ServicioRecado;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;

@SpringBootApplication
public class RecadoApplication {
	@Autowired
	ServicioAutor servicioAutor;
	@Autowired
	ServicioRecado servicioRecado;

	public static void main(String[] args) {
		String command = "C:\\xampp\\mysql\\bin\\mysqld.exe";
		try{
			Process process = Runtime.getRuntime().exec(command);
		}catch (IOException e){
			e.printStackTrace();
		}

		SpringApplication.run(RecadoApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(){
		return args -> {
			Autor admin=new Autor("admin", "asalinasci@gmail.com", "1234", "");
			admin.setAvatar("https://i.pravatar.cc/150?u=" + admin.getEmail());
			servicioAutor.save(admin);
			int max = 10;
			Faker faker = new Faker(new Locale("es-ES"));
			HashMap<Integer, Autor> mapAutores = new HashMap<>();
			HashMap<Integer, Recado> mapRecados = new HashMap<>();

			for(int i = 0; i < max; i++)
			{
				//Autor autor = new Autor(faker.name().firstName(), faker.internet().emailAddress(), faker.internet().password(),"http://cdn.onlinewebfonts.com/svg/img_264157.png");
				Autor autor = new Autor(faker.name().firstName(), faker.internet().emailAddress(), faker.internet().password(),"https://i.pravatar.cc/150?u=");
				autor.setAvatar(autor.getAvatar() + autor.getEmail());
				Recado recado=new Recado(faker.chuckNorris().fact(), autor,Date.valueOf(LocalDate.now()));
				mapAutores.put(i, autor);
				mapRecados.put(i, recado);
				servicioAutor.save(autor);
				servicioRecado.save(recado);
			}
		};
	}

}
