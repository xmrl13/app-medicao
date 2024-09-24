package service;

import dto.BaciaDTO;
import model.Bacia;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.BaciaRepository;

@Service
public class BaciaService {

    private final BaciaRepository baciaRepository;

    public BaciaService(BaciaRepository baciaRepository) {
        this.baciaRepository = baciaRepository;
    }

    @Transactional
    public BaciaDTO criarBacia(BaciaDTO baciaDTO) {

        baciaRepository.findByNomeAndContratoObra(baciaDTO.getNome(), baciaDTO.getContratoObra()).ifPresent(bacia -> {
            throw new IllegalArgumentException(String.format("A bacia: %s já existe para o contrato: %s", baciaDTO.getNome(), baciaDTO.getContratoObra()));
        });

        Bacia baciaSalva = new Bacia(baciaDTO.getNome(), baciaDTO.getContratoObra());
        baciaRepository.save(baciaSalva);
        return new BaciaDTO(baciaSalva.getNome(), baciaSalva.getContratoObra());
    }


}