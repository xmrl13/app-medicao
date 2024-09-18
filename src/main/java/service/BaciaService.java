package service;

import dto.BaciaDTO;
import model.Bacia;
import org.springframework.dao.DataIntegrityViolationException;
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
    public void criarBacia(BaciaDTO baciaDTO) {

        try {
            Bacia novaBacia = new Bacia();
            novaBacia.setNome(baciaDTO.getNome());
            novaBacia.setContratoObra(baciaDTO.getContratoObra());
            baciaRepository.save(novaBacia);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Bacia com o nome " + baciaDTO.getNome() +
                    " já existe para o contrato " + baciaDTO.getContratoObra());
        }
    }
}