package pb.mulesoft.estados.controller.dto;

import org.springframework.data.domain.Page;

import pb.mulesoft.estados.modelo.Usuario;

public class UsuarioDto {

	private Long id;
	private String nome;
	private String email;
	private String senha;
	
	
	
	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
	}
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getEmail() {
		return email;
	}
	public String getSenha() {
		return senha;
	}

	public static Page<UsuarioDto> converter(Page<Usuario> usuarios) {
		return usuarios.map(UsuarioDto::new);
	}
	
}
