package com.development.Clientes_Back;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.development.Clientes_Back.Model.Entities.Cliente;
import com.development.Clientes_Back.Repositories.InMemoryClienteRepository;
import java.util.Date;

@SpringBootApplication
public class ClientesBackApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		SpringApplication.run(ClientesBackApplication.class, args);
		
		//Pruebas
		 InMemoryClienteRepository clienteRepository = new InMemoryClienteRepository();
		 Cliente nuevoCliente = new Cliente(9L, "jperez", "Jaime Pérez", "jperez@gmail.com", "12356", new Date(), null, null);
		 clienteRepository.save(nuevoCliente);
		 if (!clienteRepository.getClientes().isEmpty()) {
			 Cliente cliente = clienteRepository.getClientes().get(0);
			 System.out.println("Cliente encontrado: " + cliente);
		 } else {
			 System.out.println("No hay clientes en el repositorio.");
		 }
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ClientesBackApplication.class);
	}
	
	@Bean
	protected ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
			.setFieldMatchingEnabled(true)
			.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
		return mapper;
	}

}
