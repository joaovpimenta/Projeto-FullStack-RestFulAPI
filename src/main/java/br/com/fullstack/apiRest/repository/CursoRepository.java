package br.com.fullstack.apiRest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fullstack.apiRest.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	List<Curso> findAllByNome(String nome);

	Curso findByNome(String nome);

}
