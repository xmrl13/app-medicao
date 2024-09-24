package repository;


import model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByNomeAndUnidadeMedida(String nome, String unidadeMedida);

    boolean existsByNomeAndUnidadeMedida(String nome, String unidadeMedida);
}
