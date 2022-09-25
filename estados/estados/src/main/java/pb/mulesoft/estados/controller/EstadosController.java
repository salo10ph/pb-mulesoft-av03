package pb.mulesoft.estados.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pb.mulesoft.estados.controller.dto.EstadoDto;
import pb.mulesoft.estados.controller.form.EstadoForm;
import pb.mulesoft.estados.modelo.Estado;
import pb.mulesoft.estados.modelo.Regiao;
import pb.mulesoft.estados.repository.EstadoRepository;

@RestController
@RequestMapping("/api/v1/estados")
public class EstadosController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@GetMapping
	public Page<EstadoDto> listar(@RequestParam(required = false) Regiao regiao, @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 5) Pageable paginacao){
		if(regiao == null) {
			Page<Estado> estados = estadoRepository.findAll(paginacao);
			return EstadoDto.converter(estados);
		} else {
			Page<Estado> estados = estadoRepository.findByRegiao(regiao, paginacao);
			return EstadoDto.converter(estados);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> especificarEstado(@PathVariable("id") Long id) {
		Optional<Estado> estado = estadoRepository.findById(id);
		
		if(estado.isPresent()) {
			return ResponseEntity.ok(new EstadoDto(estado.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<EstadoDto> inserir(@RequestBody @Valid EstadoForm form, UriComponentsBuilder uriBuilder){
		Estado estado = form.converter();
		estadoRepository.save(estado);
		
		URI uri = uriBuilder.path("/{id}").buildAndExpand(estado.getId()).toUri();
		return ResponseEntity.created(uri).body(new EstadoDto(estado));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody @Valid EstadoForm form){
		Optional<Estado> idDoEstadoExiste = estadoRepository.findById(id);
		
		if(idDoEstadoExiste.isPresent()) {
			
			Estado estado = form.atualizar(id, estadoRepository);
			return ResponseEntity.ok(new EstadoDto(estado));
		
		}
		
		return ResponseEntity.notFound().build();
	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id){
		Optional<Estado> estadoExiste = estadoRepository.findById(id);
		
		if(estadoExiste.isPresent()) {
			estadoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}

}
