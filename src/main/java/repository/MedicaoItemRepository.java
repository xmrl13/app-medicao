package repository;

import model.MedicaoItem;
import model.MedicaoItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicaoItemRepository extends JpaRepository<MedicaoItem, MedicaoItemId> {

}
