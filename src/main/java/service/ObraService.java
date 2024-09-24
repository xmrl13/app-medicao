package service;

import dto.ObraDTO;
import jakarta.transaction.Transactional;
import model.Obra;
import org.springframework.stereotype.Service;
import repository.ObraRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Service
public class ObraService {

    private final ObraRepository obraRepository;

    public ObraService(ObraRepository obraRepository) {
        this.obraRepository = obraRepository;
    }

    @Transactional
    public ObraDTO criarObra(ObraDTO obraDTO) {

        if (obraDTO.getNome().replaceAll("\\s", "").length() <= 4) {
            throw new IllegalArgumentException("Nome da obra deve começar com 'SES', não pode conter caracteres especiais e deve ter ao menos 4 caracteres");
        }

        obraRepository.findByContrato(obraDTO.getContrato()).ifPresent(obra -> {
            throw new IllegalArgumentException("Já existe uma obra cadastrada com esse contrato");
        });

        Obra novaObra = new Obra(obraDTO.getNome(), obraDTO.getContrato(), obraDTO.getOrcamento());
        obraRepository.save(novaObra);

        return new ObraDTO(novaObra.getNome(), novaObra.getContrato(), novaObra.getOrcamento());
    }

    public List<ObraDTO> getAllObras() {
        List<Obra> obras = obraRepository.findAll();

        return obras.parallelStream().map(obra -> new ObraDTO(obra.getNome(), obra.getContrato(), obra.getOrcamento())).collect(Collectors.toList());
    }

    public ObraDTO findByContrato(String contrato) {
        Obra obraAchada = obraRepository.findByContrato(contrato).orElseThrow(() -> new NoSuchElementException(String.format("Contrato: %s não encontrado", contrato)));
        ObraDTO obraDTO = new ObraDTO();
        obraDTO.setContrato(obraAchada.getContrato());
        obraDTO.setNome(obraAchada.getNome());
        obraDTO.setOrcamento(obraAchada.getOrcamento());
        return obraDTO;
    }

    public boolean obraExistePorContrato(String contrato) {
        return obraRepository.existsByContrato(contrato);
    }
}
