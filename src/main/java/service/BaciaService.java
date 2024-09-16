package service;

import dto.BaciaDTO;
import model.Bacia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.BaciaRepository;

@Service
public class BaciaService {

    @Autowired
    private BaciaRepository baciaRepository;

    @Transactional
    public BaciaDTO criarBacia(BaciaDTO baciaDTO) {
        // Verifica se a bacia já existe para o contrato fornecido
        boolean baciaExiste = baciaRepository.existsByNomeAndContratoObra(baciaDTO.getNome(), baciaDTO.getContratoObra());

        // Se a bacia já existir, lançamos uma exceção
        if (baciaExiste) {
            throw new IllegalArgumentException("Bacia com o nome " + baciaDTO.getNome() + " já existe para o contrato " + baciaDTO.getContratoObra());
        }

        // Caso contrário, cria uma nova bacia
        Bacia novaBacia = new Bacia();
        novaBacia.setNome(baciaDTO.getNome());
        novaBacia.setContratoObra(baciaDTO.getContratoObra());

        // Salva a nova bacia no banco de dados
        baciaRepository.save(novaBacia);

        // Retorna o DTO da nova bacia criada
        return baciaDTO;
    }
}
