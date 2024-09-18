package repository;

import model.MedicaoBaciaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicaoBaciaItemRepository extends JpaRepository<MedicaoBaciaItem, Long> {

}
