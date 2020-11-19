package br.com.expat.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.expat.model.Usuario;
import br.com.expat.service.UsuarioService;

@RestController
@RequestMapping("/expat/usuario")
public class UsuarioResource implements ResourceInterface<Usuario> {

	@Autowired
	private UsuarioService service = new UsuarioService();

	@Override
	@GetMapping	
	public ResponseEntity<List<Usuario>> get() {
		return ResponseEntity.ok(service.findAll());
	}

	@Override
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		Usuario _usuario = service.findById(id);
		if (_usuario != null)
			return ResponseEntity.ok(_usuario);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@Override
	@PostMapping
	public ResponseEntity<Usuario> post(@RequestBody Usuario usuario) {
		service.create(usuario);
		return ResponseEntity.ok(usuario);
	}

	@Override
	@PutMapping
	public ResponseEntity<?> put(@RequestBody Usuario usuario) {
		if (service.update(usuario)) {
			return ResponseEntity.ok(usuario);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
