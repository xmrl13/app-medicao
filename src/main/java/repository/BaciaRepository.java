package repository;

import model.Bacia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaciaRepository extends JpaRepository<Bacia, Long> {


    boolean existsByNomeAndContratoObra(String nome, String contratoObra);
}
