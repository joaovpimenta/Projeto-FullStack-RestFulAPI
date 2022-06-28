package br.com.fullstack.apiRest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fullstack.apiRest.dto.form.LoginForm;

@RestController("/auth")
public class AutenticacaoController {

	@Autowired
	AuthenticationManager authenticationManager;

	@PostMapping
	public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		Authentication authentication = authenticationManager.authenticate(dadosLogin);
		return ResponseEntity.ok(authentication);
	}

}