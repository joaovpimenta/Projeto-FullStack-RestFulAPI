package br.com.fullstack.apiRest.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fullstack.apiRest.dto.TopicoDTO;
import br.com.fullstack.apiRest.dto.form.TopicoForm;
import br.com.fullstack.apiRest.dto.form.UpdateTopicoForm;
import br.com.fullstack.apiRest.model.Topico;
import br.com.fullstack.apiRest.repository.CursoRepository;
import br.com.fullstack.apiRest.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

	@Autowired
	private TopicoRepository topicoRepository;

	@Autowired
	private CursoRepository cursoRepository;

	@GetMapping
	public ResponseEntity<List<TopicoDTO>> listaTopicos(String nomeCurso) {
		List<Topico> topicos = (nomeCurso == null) ? topicoRepository.findAll()
				: topicoRepository.findByCurso_Nome(nomeCurso);

		return ResponseEntity.status(HttpStatus.FOUND).body(TopicoDTO.topicosAsDTOs(Optional.of(topicos)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<TopicoDTO> buscaTopico(@PathVariable Long id) {
		Optional<Topico> retorno = topicoRepository.findById(id);
		TopicoDTO body = retorno.isPresent() ? new TopicoDTO(retorno) : null;
		return ResponseEntity.status((retorno.isPresent()) ? HttpStatus.FOUND : HttpStatus.NOT_FOUND).body(body);
	}

	@PostMapping
	public ResponseEntity<TopicoDTO> novoTopicos(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {

		Topico topico = topicoRepository.save(form.asTopico(cursoRepository));
		URI uri = uriBuilder.path("/topicos/{id}").build(topico.getId());

		return ResponseEntity.status(HttpStatus.CREATED).location(uri).body(new TopicoDTO(topico));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoDTO> atualizaTopico(@PathVariable Long id, @RequestBody UpdateTopicoForm form) {
		Topico topico = form.update(id, topicoRepository);
		return ResponseEntity.status(HttpStatus.OK).body(new TopicoDTO(topico));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoDTO> deletaTopico(@PathVariable Long id) {
		topicoRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}