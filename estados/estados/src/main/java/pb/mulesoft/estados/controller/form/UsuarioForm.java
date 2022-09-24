package pb.mulesoft.estados.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import pb.mulesoft.estados.modelo.Usuario;
import pb.mulesoft.estados.repository.UsuarioRepository;

public class UsuarioForm {

	@NotBlank @Length (min = 2)
	private String nome;
	@Email
	private String email;
	@NotBlank @Length (min = 5)
	private String senha;
	
	public UsuarioForm(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public Usuario converter() {
		return new Usuario(nome, email, senha);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario atualizar(Long id, UsuarioRepository usuarioRepository) {
		Usuario usuario = usuarioRepository.getOne(id);
		usuario.setNome(this.nome);
		usuario.setEmail(this.email);
		usuario.setSenha(this.senha);
		
		return usuario;
	}
	
}
