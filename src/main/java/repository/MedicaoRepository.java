package repository;

import jakarta.validation.constraints.NotNull;
import model.Medicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.YearMonth;

@Repository
public interface MedicaoRepository extends JpaRepository<Medicao, Long> {

    boolean existsByContratoObraAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(String contratoObra, LocalDate dataFim, LocalDate dataInicio);

    boolean existsByCompetencia(@NotNull YearMonth competencia);
}

