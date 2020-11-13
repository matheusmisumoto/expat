package br.com.expat.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.expat.model.Cidade;
import br.com.expat.service.CidadeService;


@RestController
@RequestMapping("/expat/cidade")
public class CidadeResource implements ResourceInterface<Cidade> {

	@Autowired
	private CidadeService service = new CidadeService();

	@Override
	@GetMapping	
	public ResponseEntity<List<Cidade>> get() {
		return ResponseEntity.ok(service.findAll());
	}

	@Override
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		Cidade _cidade = service.findById(id);
		if (_cidade != null)
			return ResponseEntity.ok(_cidade);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@Override
	@PostMapping
	public ResponseEntity<Cidade> post(@RequestBody Cidade cidade) {
		service.create(cidade);
		return ResponseEntity.ok(cidade);
	}

	@Override
	@PutMapping
	public ResponseEntity<?> put(@RequestBody Cidade cidade) {
		if (service.update(cidade)) {
			return ResponseEntity.ok(cidade);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
