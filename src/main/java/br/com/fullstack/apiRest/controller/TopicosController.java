package br.com.fullstack.apiRest.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
	@Cacheable(cacheNames = "listaTopicos")
	@Transactional(readOnly = true)
	public ResponseEntity<Page<TopicoDTO>> listaTopicos(String nomeCurso,
			@PageableDefault(direction = Direction.DESC, size = 10, page = 0, sort = "dataCriacao") Pageable paginacao) {

		Page<Topico> topicos = (nomeCurso == null) ? topicoRepository.findAll(paginacao)
				: topicoRepository.findByCurso_Nome(nomeCurso, paginacao);

		return ResponseEntity.status(HttpStatus.FOUND).body(TopicoDTO.topicosAsDTOs(Optional.of(topicos)));
	}

	@GetMapping("/{id}")
	@Transactional(readOnly = true)
	public ResponseEntity<TopicoDTO> buscaTopico(@PathVariable Long id) {
		Optional<Topico> retorno = topicoRepository.findById(id);
		TopicoDTO body = retorno.isPresent() ? new TopicoDTO(retorno) : null;
		return ResponseEntity.status((retorno.isPresent()) ? HttpStatus.FOUND : HttpStatus.NOT_FOUND).body(body);
	}

	@PostMapping
	@Transactional
	@CacheEvict(cacheNames = "listaTopicos", allEntries = true)
	public ResponseEntity<TopicoDTO> novoTopico(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {

		Topico topico = topicoRepository.save(form.asTopico(cursoRepository));
		URI uri = uriBuilder.path("/topicos/{id}").build(topico.getId());

		return ResponseEntity.status(HttpStatus.CREATED).location(uri).body(new TopicoDTO(topico));
	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(cacheNames = "listaTopicos", allEntries = true)
	public ResponseEntity<TopicoDTO> atualizaTopico(@PathVariable Long id, @RequestBody UpdateTopicoForm form) {

		Optional<Topico> topico = form.update(id, topicoRepository);

		return topico.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(new TopicoDTO(topico.get()))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(cacheNames = "listaTopicos", allEntries = true)
	public ResponseEntity<TopicoDTO> deletaTopico(@PathVariable Long id) {
		try {
			topicoRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}