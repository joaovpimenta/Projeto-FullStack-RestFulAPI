package br.com.fullstack.apiRest.dto.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.fullstack.apiRest.model.Topico;
import br.com.fullstack.apiRest.repository.CursoRepository;

public class TopicoForm {

	@NotNull
	@NotEmpty
	private String titulo;
	@NotNull
	@NotEmpty
	private String mensagem;
	@NotNull
	@NotEmpty
	private String nomeCurso;

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public Topico asTopico(CursoRepository repository) {
		return new Topico(titulo, mensagem, repository.findByNome(nomeCurso));
	}

}