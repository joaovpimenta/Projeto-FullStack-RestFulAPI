package br.com.fullstack.apiRest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ProjetoFullStackRestFulApiApplicationTest {

	@Test
	void testMain() {
		ProjetoFullStackRestFulApiApplication.main(new String[] {});
		Assertions.assertThatNoException();
	}

}
