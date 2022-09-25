package pb.mulesoft.estados.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import pb.mulesoft.estados.modelo.Estado;
import pb.mulesoft.estados.modelo.Regiao;

public interface EstadoRepository extends JpaRepository<Estado, Long>{

	Page<Estado> findByRegiao(Regiao regiao, Pageable paginacao);

}
