package br.com.fullstack.apiRest.controller;

import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TopicosControllerTest {

	final String TOPICOS_ENDPOINT = "/topicos";

	@Test
	@DisplayName("Deve criar um tópico com sucesso.")
	void testNovoTopico() {
		assertThatNoException();
	}

	@Test
	void testListaTopicos() {
		assertThatNoException();
	}

	@Test
	void testBuscaTopico() {
		assertThatNoException();
	}

	@Test
	void testAtualizaTopico() {
		assertThatNoException();
	}

	@Test
	void testDeletaTopico() {
		assertThatNoException();
	}

}
