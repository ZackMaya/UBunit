package edu.uacm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 -----------------------@SpringBootApplication-------------------------------
 Es una anotacion que configura la aplicacion web adicionando automaticamente todas las congiguraciones
 de spring y JEE que normalmente se hacen manualmente
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
