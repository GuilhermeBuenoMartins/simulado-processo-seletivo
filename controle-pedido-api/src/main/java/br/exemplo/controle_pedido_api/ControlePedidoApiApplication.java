package br.exemplo.controle_pedido_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ControlePedidoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlePedidoApiApplication.class, args);
	}

}
