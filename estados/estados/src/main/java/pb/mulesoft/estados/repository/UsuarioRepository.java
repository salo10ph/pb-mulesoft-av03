package pb.mulesoft.estados.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pb.mulesoft.estados.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByEmail(String Email);
	
}
