package repository;

import model.Obra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ObraRepository extends JpaRepository <Obra, Long>{

    Optional<Obra> findByContrato(String contrato);

    boolean existsByContrato(String contrato);
}
