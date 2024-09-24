package service;

import dto.MedicaoDTO;
import jakarta.transaction.Transactional;
import model.Medicao;
import org.springframework.stereotype.Service;
import repository.MedicaoRepository;

@Service
public class MedicaoService {

    private final MedicaoRepository medicaoRepository;


    public MedicaoService(MedicaoRepository medicaoRepository) {
        this.medicaoRepository = medicaoRepository;
    }

    @Transactional
    public MedicaoDTO createMedicao(MedicaoDTO medicaoDTO) {

        if (medicaoDTO.getDataInicio().isAfter(medicaoDTO.getDataFim())) {
            throw new IllegalArgumentException("A data de início não deve ser posterior a data de fim");
        }

        if (medicaoRepository.existsByContratoObraAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(medicaoDTO.getContratoObra(), medicaoDTO.getDataFim(), medicaoDTO.getDataInicio())) {
            throw new IllegalArgumentException("Já existe uma medição no mesmo período para esta obra.");
        }

        if (medicaoRepository.existsByCompetencia(medicaoDTO.getCompetencia())){
            throw new IllegalArgumentException("Já existe uma medição para essa competência");
        }

        Medicao medicaoSalva = new Medicao(medicaoDTO.getContratoObra(), medicaoDTO.getDataInicio(), medicaoDTO.getDataFim(), medicaoDTO.getCompetencia());
        medicaoRepository.save(medicaoSalva);
        return new MedicaoDTO(medicaoSalva.getContratoObra(), medicaoSalva.getDataInicio(), medicaoSalva.getDataFim(), medicaoSalva.getCompetencia());
    }
}