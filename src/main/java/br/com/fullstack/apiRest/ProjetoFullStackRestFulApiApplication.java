package br.com.fullstack.apiRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class ProjetoFullStackRestFulApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoFullStackRestFulApiApplication.class, args);
	}

}
