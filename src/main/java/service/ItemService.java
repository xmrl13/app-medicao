package service;

import dto.ItemDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import model.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import repository.ItemRepository;

import java.util.List;
import java.util.stream.Stream;


@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Transactional
    public ResponseEntity<?> createItem(@Valid ItemDTO itemDTO) {

        itemRepository.findByNomeAndUnidadeMedida(itemDTO.getNome(), itemDTO.getUnidadeMedida()).ifPresent(item -> {
            throw new IllegalArgumentException("Já existe um item com o nome: " + itemDTO.getNome() + "e unidade de medida: " + itemDTO.getUnidadeMedida());
        });
        return ResponseEntity.ok().build();
    }


    @Transactional
    public ResponseEntity<?> createItens(@Valid List<ItemDTO> itensDTO) {

        itensDTO.forEach(dto -> {
            itemRepository.findByNomeAndUnidadeMedida(dto.getNome(), dto.getUnidadeMedida()).ifPresent(item -> {
                throw new IllegalArgumentException("Já existe um item com o nome: " + dto.getNome() +
                        " e unidade de medida: " + dto.getUnidadeMedida());
            });
        });

        List<Item> itens = itensDTO.parallelStream()
                .map(dto -> new Item(dto.getNome(), dto.getUnidadeMedida()))
                .toList();

        saveInBatches(itens);

        return ResponseEntity.ok().build();
    }


    private void saveInBatches(List<Item> itens) {
        Stream.iterate(0, i -> i + 5)
                .limit((itens.size() + 5 - 1) / 5)
                .forEach(i -> {
                    List<Item> bloco = itens.subList(i, Math.min(i + 5, itens.size()));
                    itemRepository.saveAll(bloco);
                });
    }

    public void verifyDuplicate(ItemDTO itemDTO) {
        if (itemRepository.existsByNomeAndUnidadeMedida(itemDTO.getNome(), itemDTO.getUnidadeMedida())) {
            throw new IllegalArgumentException("Item já existe no banco de dados: " + itemDTO.getNome());
        }
    }

    @Transactional
    public ItemDTO updateItem(ItemDTO itemDTO) {

        Item existingItem = itemRepository.findByNomeAndUnidadeMedida(itemDTO.getNome(), itemDTO.getUnidadeMedida())
                .orElseThrow(() -> new IllegalArgumentException("Item não encontrado"));

        existingItem.setUnidadeMedida(itemDTO.getUnidadeMedida());
        existingItem.setNome(itemDTO.getNome());

        itemRepository.save(existingItem);

        return new ItemDTO(existingItem.getNome(), existingItem.getUnidadeMedida());
    }
}
