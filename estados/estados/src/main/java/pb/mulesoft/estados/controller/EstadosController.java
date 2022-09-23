package pb.mulesoft.estados.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pb.mulesoft.estados.controller.dto.EstadoDto;
import pb.mulesoft.estados.controller.form.EstadoForm;
import pb.mulesoft.estados.modelo.Estado;
import pb.mulesoft.estados.repository.EstadoRepository;

@RestController
@RequestMapping("/api/v1/estados")
public class EstadosController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@GetMapping
	public Page<EstadoDto> lista(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 5) Pageable paginacao){
		Page<Estado> estados = estadoRepository.findAll(paginacao);
		return EstadoDto.converter(estados);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<EstadoDto> inserir(@RequestBody EstadoForm form, UriComponentsBuilder uriBuilder){
		Estado estado = form.converter();
		estadoRepository.save(estado);
		
		URI uri = uriBuilder.path("/{id}").buildAndExpand(estado.getId()).toUri();
		return ResponseEntity.created(uri).body(new EstadoDto(estado));
	}

}
