package br.com.fullstack.apiRest.dto.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.fullstack.apiRest.model.Topico;
import br.com.fullstack.apiRest.repository.TopicoRepository;

public class UpdateTopicoForm {

	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String titulo;

	@NotNull
	@NotEmpty
	@Length(min = 10)
	private String mensagem;

	public Topico update(Long id, TopicoRepository topicoRepository) {
		Topico topico = topicoRepository.findById(id).get();

		topico.setTitulo(this.titulo);
		topico.setMensagem(this.mensagem);

		return topico;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

}
