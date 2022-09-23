package pb.mulesoft.estados.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pb.mulesoft.estados.modelo.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long>{

}
