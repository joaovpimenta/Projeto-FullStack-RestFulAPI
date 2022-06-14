package br.com.fullstack.apiRest.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import br.com.fullstack.apiRest.model.Topico;

public class TopicoDTO {

	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private String status;
	private List<RespostaDTO> respostas;

	public TopicoDTO(Topico topico) {

		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
		this.status = topico.getStatus().name();
		this.respostas = topico.getRespostas().stream().map(RespostaDTO::new).toList();
	}

	public TopicoDTO(Optional<Topico> optional) {
		if (optional.isEmpty())
			return;

		this.id = optional.get().getId();
		this.titulo = optional.get().getTitulo();
		this.mensagem = optional.get().getMensagem();
		this.dataCriacao = optional.get().getDataCriacao();
		this.status = optional.get().getStatus().name();
		this.respostas = optional.get().getRespostas().stream().map(RespostaDTO::new).toList();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public String getStatus() {
		return status;
	}

	public List<RespostaDTO> getRespostas() {
		return respostas;
	}

	public static Page<TopicoDTO> topicosAsDTOs(Optional<Page<Topico>> optional) {
		return optional.isEmpty() ? null : optional.get().map(TopicoDTO::new);
	}
}
