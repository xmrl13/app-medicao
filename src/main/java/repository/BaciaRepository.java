package repository;

import model.Bacia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaciaRepository extends JpaRepository<Bacia, Long> {

    Optional<Bacia> findByNomeAndContratoObra(String nome, String contratoObra);

    boolean existsByNomeAndContratoObra(String nome, String contratoObra);
}
