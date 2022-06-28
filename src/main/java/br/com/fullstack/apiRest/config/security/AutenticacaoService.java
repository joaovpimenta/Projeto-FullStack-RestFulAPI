package br.com.fullstack.apiRest.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fullstack.apiRest.model.Usuario;
import br.com.fullstack.apiRest.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> user = usuarioRepository.findByEmail(username);

		return user.orElseThrow(() -> new UsernameNotFoundException(
				UsernameNotFoundException.class.getName() + " " + UsernameNotFoundException.class.descriptorString()));
	}

}
