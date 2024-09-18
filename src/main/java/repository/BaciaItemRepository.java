package repository;

import model.Bacia;
import model.BaciaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaciaItemRepository extends JpaRepository<BaciaItem, Long> {

}
