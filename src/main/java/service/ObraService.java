package service;

import dto.ObraDTO;
import model.Obra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ObraRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ObraService {

    @Autowired
    private ObraRepository obraRepository;

    public Obra criarObra(ObraDTO obraDTO) {
        // Verifica se já existe uma obra com o contrato fornecido
        obraRepository.findByContrato(obraDTO.getContrato())
                .ifPresent(obra -> {
                    throw new IllegalArgumentException("Contrato já cadastrado");
                });

        // Cria e salva a nova obra
        Obra novaObra = new Obra(obraDTO.getNome(), obraDTO.getContrato(), obraDTO.getOrcamento());
        return obraRepository.save(novaObra);
    }

    public List<ObraDTO> getAllObras() {

        List<Obra> obras = obraRepository.findAll();

        return obras.parallelStream()
                .map(obra -> new ObraDTO(obra.getNome(), obra.getContrato(), obra.getOrcamento()))
                .collect(Collectors.toList());
    }

    public ObraDTO findByContrato(String contrato) {
        Obra obraAchada = obraRepository.findByContrato(contrato)
                .orElseThrow(() -> new NoSuchElementException(String.format("Contrato: %s não encontrado", contrato)));
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
