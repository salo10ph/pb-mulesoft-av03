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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pb.mulesoft.estados.controller.dto.UsuarioDto;
import pb.mulesoft.estados.controller.form.UsuarioForm;
import pb.mulesoft.estados.modelo.Usuario;
import pb.mulesoft.estados.repository.UsuarioRepository;

@RequestMapping("/api/v1/usuarios")
@RestController
public class UsuariosController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public Page<UsuarioDto> listar(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 5) Pageable paginacao){
		
		Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
		return UsuarioDto.converter(usuarios);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> especificarUsuario(@PathVariable Long id, @RequestBody @Valid UsuarioForm form) {
		Optional<Usuario> idDoUsuarioExiste = usuarioRepository.findById(id);
		
		if(idDoUsuarioExiste.isPresent()) {
			Usuario usuario = form.atualizar(id, usuarioRepository);
			return ResponseEntity.ok(new UsuarioDto(usuario));
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioDto> inserir(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder){
		Usuario usuario = form.converter();
		usuarioRepository.save(usuario);
		
		URI uri = uriBuilder.path("/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
	}

}
