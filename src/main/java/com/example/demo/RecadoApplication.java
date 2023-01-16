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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;

//TODO: Retweet, "me gusta" AJAX sin recargar,Login y registro
@SpringBootApplication
public class RecadoApplication {
	@Autowired
	ServicioAutor servicioAutor;
	@Autowired
	ServicioRecado servicioRecado;
	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		//Esto inicia MySQL al arrancar la app
		//Como es lógico, solo funciona si tienes instalado MySQL en la misma ruta
		//Si no la conoces o prefieres iniciar MySQL desde el terminal o con Xampp(o similares) puedes borrar estas líneas
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
			//Generamos datos de relleno en la base de datos con Faker y Pravatar (nos da un avatar diferente para cada email)
			Autor admin=new Autor("admin", "asalinasci@gmail.com", passwordEncoder.encode("1234"), "");
			admin.setAvatar("https://i.pravatar.cc/150?u=" + admin.getEmail());
			servicioAutor.save(admin);
			int max = 10;
			Faker faker = new Faker(new Locale("es-ES"));

			for(int i = 0; i < max; i++){
				Autor autor = new Autor(faker.name().firstName(), faker.internet().emailAddress(), faker.internet().password(),"https://i.pravatar.cc/150?u=");
				autor.setAvatar(autor.getAvatar() + autor.getEmail());
				autor.setPassword(passwordEncoder.encode("1234"));
				Recado recado=new Recado(faker.chuckNorris().fact(), autor,Date.valueOf(LocalDate.now()));
				servicioAutor.save(autor);
				servicioRecado.save(recado);
			}
		};
	}

}
